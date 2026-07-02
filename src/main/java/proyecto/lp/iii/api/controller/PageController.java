package proyecto.lp.iii.api.controller;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpSession;
import proyecto.lp.iii.api.entity.Usuarios;
import proyecto.lp.iii.api.entity.Cliente;
import proyecto.lp.iii.api.entity.Producto;
import proyecto.lp.iii.api.entity.CategoriaProducto;
import proyecto.lp.iii.api.entity.Marca;
import proyecto.lp.iii.api.entity.Venta;
import proyecto.lp.iii.api.entity.DetalleVenta;
import proyecto.lp.iii.api.entity.Sede;
import proyecto.lp.iii.api.entity.SesionCaja;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.service.IUsuariosService;
import proyecto.lp.iii.api.service.IClienteService;
import proyecto.lp.iii.api.service.IProductoService;
import proyecto.lp.iii.api.service.ICategoriaProductoService;
import proyecto.lp.iii.api.service.IMarcaService;
import proyecto.lp.iii.api.service.IVentaService;
import proyecto.lp.iii.api.service.IDetalleVentaService;
import proyecto.lp.iii.api.service.ISedeService;
import proyecto.lp.iii.api.service.ISesionCajaService;
import proyecto.lp.iii.api.service.ITenantsService;

@Controller
public class PageController {

    @Autowired
    private IUsuariosService serviceUsuarios;

    @Autowired
    private IClienteService serviceCliente;

    @Autowired
    private IProductoService serviceProducto;

    @Autowired
    private ICategoriaProductoService serviceCategoria;

    @Autowired
    private IMarcaService serviceMarca;

    @Autowired
    private IVentaService serviceVenta;

    @Autowired
    private IDetalleVentaService serviceDetalleVenta;

    @Autowired
    private ISedeService serviceSede;

    @Autowired
    private ISesionCajaService serviceSesionCaja;

    @Autowired
    private ITenantsService serviceTenants;

    // Helper classes for Checkout request parsing
    public static class CheckoutRequest {
        private String nombre;
        private String apellidos;
        private String correo;
        private String telefono;
        private String direccion;
        private String distrito;
        private String tipoDocumento;
        private String numeroDocumento;
        private String metodoPago;
        private List<CartItem> items;

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getApellidos() { return apellidos; }
        public void setApellidos(String apellidos) { this.apellidos = apellidos; }
        public String getCorreo() { return correo; }
        public void setCorreo(String correo) { this.correo = correo; }
        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
        public String getDireccion() { return direccion; }
        public void setDireccion(String direccion) { this.direccion = direccion; }
        public String getDistrito() { return distrito; }
        public void setDistrito(String distrito) { this.distrito = distrito; }
        public String getTipoDocumento() { return tipoDocumento; }
        public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }
        public String getNumeroDocumento() { return numeroDocumento; }
        public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }
        public String getMetodoPago() { return metodoPago; }
        public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
        public List<CartItem> getItems() { return items; }
        public void setItems(List<CartItem> items) { this.items = items; }
    }

    public static class CartItem {
        private Integer id_productos;
        private Integer cantidad;
        private Double precio_venta;

        public Integer getId_productos() { return id_productos; }
        public void setId_productos(Integer id_productos) { this.id_productos = id_productos; }
        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
        public Double getPrecio_venta() { return precio_venta; }
        public void setPrecio_venta(Double precio_venta) { this.precio_venta = precio_venta; }
    }

    // ========== PORTAL PÚBLICO: TIENDA VIRTUAL ==========

    @GetMapping("/")
    public String tienda(Model model, HttpSession session) {
        model.addAttribute("cliente", session.getAttribute("cliente"));
        return "tienda";
    }

    @GetMapping("/tienda/login")
    public String tiendaLogin(HttpSession session) {
        if (session.getAttribute("cliente") != null) {
            return "redirect:/";
        }
        return "tienda_login";
    }

    @PostMapping("/tienda/login")
    public String tiendaLoginPost(@RequestParam String correo,
                                  @RequestParam String documento,
                                  HttpSession session, Model model) {
        Optional<Cliente> client = serviceCliente.buscarTodos().stream()
            .filter(c -> c.getCorreo() != null && c.getCorreo().equalsIgnoreCase(correo)
                && c.getNumero_documento() != null && c.getNumero_documento().equals(documento))
            .findFirst();

        if (client.isPresent()) {
            session.setAttribute("cliente", client.get());
            return "redirect:/";
        }

        model.addAttribute("error", "Credenciales incorrectas (Verifique Correo y DNI/RUC)");
        return "tienda_login";
    }

    @GetMapping("/tienda/registro")
    public String tiendaRegistro(HttpSession session) {
        if (session.getAttribute("cliente") != null) {
            return "redirect:/";
        }
        return "tienda_registro";
    }

    @PostMapping("/tienda/registro")
    public String tiendaRegistroPost(@RequestParam String nombre,
                                     @RequestParam String apellidos,
                                     @RequestParam String correo,
                                     @RequestParam String telefono,
                                     @RequestParam String direccion,
                                     @RequestParam String distrito,
                                     @RequestParam String tipoDocumento,
                                     @RequestParam String numeroDocumento,
                                     HttpSession session, Model model) {
        // Validar si ya existe
        boolean existe = serviceCliente.buscarTodos().stream()
            .anyMatch(c -> c.getCorreo() != null && c.getCorreo().equalsIgnoreCase(correo));

        if (existe) {
            model.addAttribute("error", "El correo ya se encuentra registrado");
            return "tienda_registro";
        }

        Cliente cliente = new Cliente();
        cliente.setNombre_cliente(nombre);
        cliente.setApellidos_clientes(apellidos);
        cliente.setCorreo(correo);
        cliente.setTelefono(telefono);
        cliente.setDireccion(direccion);
        cliente.setDistrito(distrito);
        cliente.setTipo_documento(tipoDocumento);
        cliente.setNumero_documento(numeroDocumento);
        cliente.setTipo_cliente("regular");
        cliente.setEstado(1);

        Tenants tenant = serviceTenants.buscarId(1).orElse(null);
        cliente.setId_tenants(tenant);

        serviceCliente.guardar(cliente);
        session.setAttribute("cliente", cliente);

        return "redirect:/";
    }

    @GetMapping("/tienda/logout")
    public String tiendaLogout(HttpSession session) {
        session.removeAttribute("cliente");
        return "redirect:/";
    }

    @GetMapping("/tienda/checkout")
    public String tiendaCheckout(Model model, HttpSession session) {
        model.addAttribute("cliente", session.getAttribute("cliente"));
        return "tienda_checkout";
    }

    @GetMapping("/tienda/success")
    public String tiendaSuccess(Model model, HttpSession session) {
        return "tienda_success";
    }

    // ========== API PÚBLICA TIENDA (JSON) ==========

    @GetMapping("/tienda/api/productos")
    @ResponseBody
    public List<Producto> getStorefrontProductos() {
        return serviceProducto.buscarTodos().stream()
            .filter(p -> p.getVisible_storefront() != null && p.getVisible_storefront() == 1)
            .collect(Collectors.toList());
    }

    @GetMapping("/tienda/api/categorias")
    @ResponseBody
    public List<CategoriaProducto> getStorefrontCategorias() {
        return serviceCategoria.buscarTodos();
    }

    @GetMapping("/tienda/api/marcas")
    @ResponseBody
    public List<Marca> getStorefrontMarcas() {
        return serviceMarca.buscarTodos();
    }

    @PostMapping("/tienda/api/checkout")
    @ResponseBody
    public String procesarCheckout(@RequestBody CheckoutRequest request, HttpSession session) {
        try {
            // 1. Obtener o registrar al cliente
            Cliente cliente = null;
            Optional<Cliente> optCliente = serviceCliente.buscarTodos().stream()
                .filter(c -> c.getCorreo() != null && c.getCorreo().equalsIgnoreCase(request.getCorreo()))
                .findFirst();

            if (optCliente.isPresent()) {
                cliente = optCliente.get();
            } else {
                cliente = new Cliente();
                cliente.setNombre_cliente(request.getNombre());
                cliente.setApellidos_clientes(request.getApellidos());
                cliente.setCorreo(request.getCorreo());
                cliente.setTelefono(request.getTelefono());
                cliente.setDireccion(request.getDireccion());
                cliente.setDistrito(request.getDistrito());
                cliente.setTipo_documento(request.getTipoDocumento());
                cliente.setNumero_documento(request.getNumeroDocumento());
                cliente.setTipo_cliente("regular");
                cliente.setEstado(1);
                cliente.setId_tenants(serviceTenants.buscarId(1).orElse(null));
                serviceCliente.guardar(cliente);
            }

            // 2. Resolver dependencias de Sede, Tenant y SesionCaja
            Tenants tenant = serviceTenants.buscarId(1).orElse(null);
            Sede sede = serviceSede.buscarTodos().stream().findFirst().orElse(null);
            SesionCaja sesion = serviceSesionCaja.buscarTodos().stream()
                .filter(s -> s.getEstado() != null && s.getEstado() == 1)
                .findFirst()
                .orElseGet(() -> serviceSesionCaja.buscarTodos().stream().findFirst().orElse(null));

            // 3. Crear Venta
            Venta venta = new Venta();
            venta.setId_tenants(tenant);
            venta.setId_sedes(sede);
            venta.setId_sesiones_caja(sesion);
            venta.setId_clientes(cliente);
            venta.setNumero_ticket("TK-" + System.currentTimeMillis());
            venta.setComprobante_numero("C-" + System.currentTimeMillis());
            venta.setTipo_comprobante("boleta");
            venta.setEstado(1);
            venta.setEstado_sunat("Aceptado");

            // Calcular montos
            double total = 0.0;
            for (CartItem item : request.getItems()) {
                total += item.getCantidad() * item.getPrecio_venta();
            }
            venta.setTotal(BigDecimal.valueOf(total));
            venta.setSubtotal(BigDecimal.valueOf(total / 1.18));
            venta.setImpuesto(BigDecimal.valueOf(total - (total / 1.18)));

            serviceVenta.guardar(venta);

            // 4. Crear DetalleVenta para cada producto
            for (CartItem item : request.getItems()) {
                Producto prod = serviceProducto.buscarId(item.getId_productos()).orElse(null);
                if (prod != null) {
                    DetalleVenta det = new DetalleVenta();
                    det.setId_ventas(venta);
                    det.setId_productos(prod);
                    det.setCantidad(item.getCantidad());
                    det.setPrecio_unitario(BigDecimal.valueOf(item.getPrecio_venta()));
                    det.setSubtotal(BigDecimal.valueOf(item.getCantidad() * item.getPrecio_venta()));
                    serviceDetalleVenta.guardar(det);
                }
            }

            // Limpiar carrito o guardar datos de cliente en sesion
            session.setAttribute("cliente", cliente);

            return "{\"success\": true, \"ventaId\": " + venta.getId_ventas() + "}";
        } catch (Exception e) {
            return "{\"success\": false, \"error\": \"" + e.getMessage() + "\"}";
        }
    }

    // ========== CONTROL DE ACCESO DE ADMINISTRACIÓN ==========

    @GetMapping("/admin/login")
    public String adminLogin(HttpSession session) {
        if (session.getAttribute("usuario") != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @PostMapping("/admin/login")
    public String adminLoginPost(@RequestParam String correo,
                                 @RequestParam String contrasenia,
                                 HttpSession session, Model model) {
        Optional<Usuarios> user = serviceUsuarios.buscarTodos().stream()
            .filter(u -> u.getCorreo() != null && u.getCorreo().equals(correo)
                && u.getContrasenia() != null && u.getContrasenia().equals(contrasenia))
            .findFirst();

        if (user.isPresent()) {
            session.setAttribute("usuario", user.get());
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Credenciales incorrectas");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Dashboard");
        model.addAttribute("contentTemplate", "dashboard");
        return "base";
    }

    // ========== MÓDULO 1: Usuarios y Seguridad ==========

    @GetMapping("/modulos/usuarios")
    public String usuarios(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Usuarios");
        model.addAttribute("contentTemplate", "modulos/usuarios");
        return "base";
    }

    @GetMapping("/modulos/tenants")
    public String tenants(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Tenants");
        model.addAttribute("contentTemplate", "modulos/tenants");
        return "base";
    }

    @GetMapping("/modulos/roles")
    public String roles(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Roles Personalizados");
        model.addAttribute("contentTemplate", "modulos/roles");
        return "base";
    }

    @GetMapping("/modulos/permisos")
    public String permisos(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Permisos de Rol");
        model.addAttribute("contentTemplate", "modulos/permisos");
        return "base";
    }

    @GetMapping("/modulos/usuario-sedes")
    public String usuarioSedes(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Asignacion Usuario-Sede");
        model.addAttribute("contentTemplate", "modulos/usuario_sedes");
        return "base";
    }

    @GetMapping("/modulos/preferencias")
    public String preferencias(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Preferencias de Usuario");
        model.addAttribute("contentTemplate", "modulos/preferencias");
        return "base";
    }

    @GetMapping("/modulos/auditoria")
    public String auditoria(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Auditoria");
        model.addAttribute("contentTemplate", "modulos/auditoria");
        return "base";
    }

    // ========== MÓDULO 2: Productos e Inventario ==========

    @GetMapping("/modulos/productos")
    public String productos(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Productos");
        model.addAttribute("contentTemplate", "modulos/productos");
        return "base";
    }

    @GetMapping("/modulos/categorias")
    public String categorias(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Categorias de Productos");
        model.addAttribute("contentTemplate", "modulos/categorias");
        return "base";
    }

    @GetMapping("/modulos/marcas")
    public String marcas(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Marcas");
        model.addAttribute("contentTemplate", "modulos/marcas");
        return "base";
    }

    @GetMapping("/modulos/almacenes")
    public String almacenes(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Almacenes");
        model.addAttribute("contentTemplate", "modulos/almacenes");
        return "base";
    }

    @GetMapping("/modulos/lotes")
    public String lotes(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Lotes de Inventario");
        model.addAttribute("contentTemplate", "modulos/lotes");
        return "base";
    }

    @GetMapping("/modulos/movimientos")
    public String movimientos(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Movimientos de Inventario");
        model.addAttribute("contentTemplate", "modulos/movimientos");
        return "base";
    }

    @GetMapping("/modulos/combos")
    public String combos(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Combos Promocionales");
        model.addAttribute("contentTemplate", "modulos/combos");
        return "base";
    }

    @GetMapping("/modulos/promociones")
    public String promociones(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Promociones");
        model.addAttribute("contentTemplate", "modulos/promociones");
        return "base";
    }

    // ========== MÓDULO 3: Clientes y Contacto ==========

    @GetMapping("/modulos/clientes")
    public String clientes(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Clientes");
        model.addAttribute("contentTemplate", "modulos/clientes");
        return "base";
    }

    @GetMapping("/modulos/repartidores")
    public String repartidores(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Repartidores");
        model.addAttribute("contentTemplate", "modulos/repartidores");
        return "base";
    }

    @GetMapping("/modulos/zonas-delivery")
    public String zonasDelivery(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Zonas de Delivery");
        model.addAttribute("contentTemplate", "modulos/zonas_delivery");
        return "base";
    }

    @GetMapping("/modulos/citas")
    public String citas(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Citas");
        model.addAttribute("contentTemplate", "modulos/citas");
        return "base";
    }

    @GetMapping("/modulos/notificaciones")
    public String notificaciones(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Notificaciones");
        model.addAttribute("contentTemplate", "modulos/notificaciones");
        return "base";
    }

    @GetMapping("/modulos/sedes")
    public String sedes(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Sedes");
        model.addAttribute("contentTemplate", "modulos/sedes");
        return "base";
    }

    @GetMapping("/modulos/horarios-operacion")
    public String horariosOperacion(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Horarios de Operacion");
        model.addAttribute("contentTemplate", "modulos/horarios_operacion");
        return "base";
    }

    // ========== MÓDULO 4: Ventas y Pedidos ==========

    @GetMapping("/modulos/ventas")
    public String ventas(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Ventas");
        model.addAttribute("contentTemplate", "modulos/ventas");
        return "base";
    }

    @GetMapping("/modulos/pedidos")
    public String pedidos(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Pedidos");
        model.addAttribute("contentTemplate", "modulos/pedidos");
        return "base";
    }

    @GetMapping("/modulos/devoluciones-venta")
    public String devolucionesVenta(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Devoluciones de Venta");
        model.addAttribute("contentTemplate", "modulos/devoluciones_venta");
        return "base";
    }

    @GetMapping("/modulos/formas-pago-venta")
    public String formasPagoVenta(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Formas de Pago Venta");
        model.addAttribute("contentTemplate", "modulos/formas_pago_venta");
        return "base";
    }

    @GetMapping("/modulos/comprobantes-electronicos")
    public String comprobantesElectronicos(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Comprobantes Electronicos");
        model.addAttribute("contentTemplate", "modulos/comprobantes_electronicos");
        return "base";
    }

    @GetMapping("/modulos/series-comprobantes")
    public String seriesComprobantes(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Series de Comprobantes");
        model.addAttribute("contentTemplate", "modulos/series_comprobantes");
        return "base";
    }

    // ========== MÓDULO 5: Compras y Finanzas ==========

    @GetMapping("/modulos/ordenes-compra")
    public String ordenesCompra(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Ordenes de Compra");
        model.addAttribute("contentTemplate", "modulos/ordenes_compra");
        return "base";
    }

    @GetMapping("/modulos/proveedores")
    public String proveedores(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Proveedores");
        model.addAttribute("contentTemplate", "modulos/proveedores");
        return "base";
    }

    @GetMapping("/modulos/proveedores-categorias")
    public String proveedoresCategorias(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Categorias de Proveedor");
        model.addAttribute("contentTemplate", "modulos/proveedores_categorias");
        return "base";
    }

    @GetMapping("/modulos/devoluciones-proveedor")
    public String devolucionesProveedor(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Devoluciones a Proveedor");
        model.addAttribute("contentTemplate", "modulos/devoluciones_proveedor");
        return "base";
    }

    @GetMapping("/modulos/pagos-proveedor")
    public String pagosProveedor(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Pagos a Proveedor");
        model.addAttribute("contentTemplate", "modulos/pagos_proveedor");
        return "base";
    }

    @GetMapping("/modulos/cuentas-por-pagar")
    public String cuentasPorPagar(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Cuentas por Pagar");
        model.addAttribute("contentTemplate", "modulos/cuentas_por_pagar");
        return "base";
    }

    @GetMapping("/modulos/gastos-operativos")
    public String gastosOperativos(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Gastos Operativos");
        model.addAttribute("contentTemplate", "modulos/gastos_operativos");
        return "base";
    }

    @GetMapping("/modulos/gastos-recurrentes")
    public String gastosRecurrentes(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Gastos Recurrentes");
        model.addAttribute("contentTemplate", "modulos/gastos_recurrentes");
        return "base";
    }

    @GetMapping("/modulos/caja-chica")
    public String cajaChica(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Caja Chica");
        model.addAttribute("contentTemplate", "modulos/caja_chica");
        return "base";
    }

    @GetMapping("/modulos/sesiones-caja")
    public String sesionesCaja(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Sesiones de Caja");
        model.addAttribute("contentTemplate", "modulos/sesiones_caja");
        return "base";
    }

    @GetMapping("/modulos/metodos-pago")
    public String metodosPago(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("title", "Metodos de Pago");
        model.addAttribute("contentTemplate", "modulos/metodos_pago");
        return "base";
    }
}
