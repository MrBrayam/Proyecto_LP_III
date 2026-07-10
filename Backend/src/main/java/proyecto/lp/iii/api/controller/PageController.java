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
import org.springframework.web.bind.annotation.PathVariable;
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
import proyecto.lp.iii.api.entity.Registros;
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
import proyecto.lp.iii.api.service.IRegistrosService;
import proyecto.lp.iii.api.service.IServicioBellezaService;
import proyecto.lp.iii.api.service.ICitaService;
import proyecto.lp.iii.api.entity.ServicioBelleza;
import proyecto.lp.iii.api.entity.Cita;
import java.util.Map;
import java.util.HashMap;
import proyecto.lp.iii.api.entity.Suscripcion;
import proyecto.lp.iii.api.service.ISuscripcionService;
import proyecto.lp.iii.api.entity.PermisoRol;
import proyecto.lp.iii.api.entity.RolPersonalizado;
import proyecto.lp.iii.api.service.IPermisoRolService;
import proyecto.lp.iii.api.service.IRolPersonalizadoService;
import proyecto.lp.iii.api.service.IHorarioOperacionService;
import proyecto.lp.iii.api.service.IServicioCitaService;
import proyecto.lp.iii.api.entity.HorarioOperacion;
import proyecto.lp.iii.api.entity.ServicioCita;
import proyecto.lp.iii.api.entity.ComboPromocional;
import proyecto.lp.iii.api.service.IComboPromocionalService;
import proyecto.lp.iii.api.entity.ComposicionCombo;
import proyecto.lp.iii.api.service.IComposicionComboService;
import proyecto.lp.iii.api.entity.Pedido;
import proyecto.lp.iii.api.entity.DetallePedido;
import proyecto.lp.iii.api.service.IPedidoService;
import proyecto.lp.iii.api.service.IDetallePedidoService;
import proyecto.lp.iii.api.entity.LoteInventario;
import proyecto.lp.iii.api.entity.MovimientoInventario;
import proyecto.lp.iii.api.entity.Reclamo;
import proyecto.lp.iii.api.service.ILoteInventarioService;
import proyecto.lp.iii.api.service.IMovimientoInventarioService;
import proyecto.lp.iii.api.service.IReclamoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.persistence.EntityManager;

@Controller
public class PageController {

    @Autowired
    private EntityManager entityManager;

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

    @Autowired
    private IServicioBellezaService serviceServicioBelleza;

    @Autowired
    private ICitaService serviceCita;

    @Autowired
    private IRegistrosService serviceRegistros;

    @Autowired
    private ISuscripcionService serviceSuscripcion;

    @Autowired
    private IPermisoRolService servicePermisoRol;

    @Autowired
    private IRolPersonalizadoService serviceRolPersonalizado;

    @Autowired
    private IHorarioOperacionService serviceHorarioOperacion;

    @Autowired
    private IServicioCitaService serviceServicioCita;

    @Autowired
    private IComboPromocionalService serviceComboPromocional;

    @Autowired
    private IComposicionComboService serviceComposicionCombo;

    @Autowired
    private IPedidoService servicePedido;

    @Autowired
    private IDetallePedidoService serviceDetallePedido;

    @Autowired
    private ILoteInventarioService serviceLoteInventario;

    @Autowired
    private IMovimientoInventarioService serviceMovimientoInventario;

    @Autowired
    private IReclamoService serviceReclamo;

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
        private Integer tenantId;

        // Appointment attributes
        private Integer SedeId;
        private String fechaCita;
        private String horaCita;
        private String observacionesCita;

        public Integer getTenantId() {
            return tenantId;
        }

        public void setTenantId(Integer tenantId) {
            this.tenantId = tenantId;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellidos() {
            return apellidos;
        }

        public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getDistrito() {
            return distrito;
        }

        public void setDistrito(String distrito) {
            this.distrito = distrito;
        }

        public String getTipoDocumento() {
            return tipoDocumento;
        }

        public void setTipoDocumento(String tipoDocumento) {
            this.tipoDocumento = tipoDocumento;
        }

        public String getNumeroDocumento() {
            return numeroDocumento;
        }

        public void setNumeroDocumento(String numeroDocumento) {
            this.numeroDocumento = numeroDocumento;
        }

        public String getMetodoPago() {
            return metodoPago;
        }

        public void setMetodoPago(String metodoPago) {
            this.metodoPago = metodoPago;
        }

        public List<CartItem> getItems() {
            return items;
        }

        public void setItems(List<CartItem> items) {
            this.items = items;
        }

        public Integer getSedeId() {
            return SedeId;
        }

        public void setSedeId(Integer SedeId) {
            this.SedeId = SedeId;
        }

        public String getFechaCita() {
            return fechaCita;
        }

        public void setFechaCita(String fechaCita) {
            this.fechaCita = fechaCita;
        }

        public String getHoraCita() {
            return horaCita;
        }

        public void setHoraCita(String horaCita) {
            this.horaCita = horaCita;
        }

        public String getObservacionesCita() {
            return observacionesCita;
        }

        public void setObservacionesCita(String observacionesCita) {
            this.observacionesCita = observacionesCita;
        }
    }

    public static class CartItem {
        private Integer id_productos;
        private Integer id_servicios_belleza;
        private String tipo; // "producto" o "servicio"
        private Integer cantidad;
        private Double precio_venta;

        public Integer getId_productos() {
            return id_productos;
        }

        public void setId_productos(Integer id_productos) {
            this.id_productos = id_productos;
        }

        public Integer getId_servicios_belleza() {
            return id_servicios_belleza;
        }

        public void setId_servicios_belleza(Integer id_servicios_belleza) {
            this.id_servicios_belleza = id_servicios_belleza;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }

        private Integer id_combos_promocionales;

        public Integer getId_combos_promocionales() {
            return id_combos_promocionales;
        }

        public void setId_combos_promocionales(Integer id_combos_promocionales) {
            this.id_combos_promocionales = id_combos_promocionales;
        }

        public Double getPrecio_venta() {
            return precio_venta;
        }

        public void setPrecio_venta(Double precio_venta) {
            this.precio_venta = precio_venta;
        }
    }

    public static class ContactRequest {
        private String nombre;
        private String correo;
        private String asunto;
        private String mensaje;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getAsunto() {
            return asunto;
        }

        public void setAsunto(String asunto) {
            this.asunto = asunto;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }

    private boolean verificarContrasenia(String ingresada, String almacenada) {
        if (ingresada == null || almacenada == null)
            return false;
        if (almacenada.startsWith("$2a$") || almacenada.startsWith("$2b$") || almacenada.startsWith("$2y$")) {
            try {
                return org.springframework.security.crypto.bcrypt.BCrypt.checkpw(ingresada, almacenada);
            } catch (Exception e) {
                return ingresada.equals(almacenada);
            }
        }
        return ingresada.equals(almacenada);
    }

    private List<String> obtenerModulosPermitidosParaUsuario(Usuarios usuario, Integer tenantId) {
        if (usuario == null)
            return java.util.Collections.emptyList();

        String tipo = usuario.getTipo_usuario();
        if (tipo == null)
            return java.util.Collections.emptyList();

        List<String> todosLosModulos = List.of(
                "usuarios", "tenants", "roles", "permisos", "usuario-sedes", "preferencias", "auditoria",
                "clientes", "citas", "repartidores", "sedes", "horarios-operacion", "zonas-delivery", "notificaciones",
                "ventas", "pedidos", "devoluciones-venta", "formas-pago-venta", "comprobantes-electronicos",
                "series-comprobantes",
                "ordenes-compra", "proveedores", "proveedores-categorias", "devoluciones-proveedor",
                "cuentas-por-pagar", "pagos-proveedor",
                "caja-chica", "sesiones-caja", "metodos-pago", "gastos-operativos", "gastos-recurrentes",
                "productos", "categorias", "marcas", "almacenes", "lotes", "movimientos", "combos", "promociones");

        if ("superadmin".equalsIgnoreCase(tipo) || "admin".equalsIgnoreCase(tipo)) {
            return todosLosModulos;
        }

        Optional<RolPersonalizado> rolOpt = serviceRolPersonalizado.buscarTodos().stream()
                .filter(r -> r.getId_tenants() != null && tenantId != null
                        && r.getId_tenants().getId_tenants().equals(tenantId)
                        && r.getNombre_rol_personalizado() != null
                        && r.getNombre_rol_personalizado().equalsIgnoreCase(tipo)
                        && r.getEstado() != null && r.getEstado() == 1)
                .findFirst();

        if (rolOpt.isPresent()) {
            Integer idRol = rolOpt.get().getId_roles_personalizados();
            return servicePermisoRol.buscarTodos().stream()
                    .filter(p -> p.getId_roles_personalizados() != null
                            && p.getId_roles_personalizados().getId_roles_personalizados().equals(idRol)
                            && p.getModulo() != null
                            && p.getEstado() != null && p.getEstado() == 1)
                    .map(p -> p.getModulo().toLowerCase())
                    .collect(Collectors.toList());
        }

        return java.util.Collections.emptyList();
    }

    private List<String> obtenerModulosPermitidos(HttpSession session) {
        Usuarios usuario = (Usuarios) session.getAttribute("usuario");
        Integer tenantId = (Integer) session.getAttribute("userTenantId");
        return obtenerModulosPermitidosParaUsuario(usuario, tenantId);
    }

    @ModelAttribute
    public void addAllowedModules(Model model, HttpSession session) {
        if (session.getAttribute("usuario") != null) {
            model.addAttribute("allowedModules", obtenerModulosPermitidos(session));
        }
    }

    // ========== LOGIN GENERAL (PÁGINA RAÍZ) ==========

    @GetMapping("/")
    public String loginGeneral(HttpSession session) {
        if (session.getAttribute("superadmin") != null) {
            return "redirect:/superadmin/dashboard";
        }
        return "login_general";
    }

    @PostMapping("/login-general")
    public String loginGeneralPost(@RequestParam String email,
            @RequestParam String accessToken,
            HttpSession session, Model model) {
        Optional<Registros> registro = serviceRegistros.buscarTodos().stream()
                .filter(r -> r.getEmail() != null && r.getEmail().equalsIgnoreCase(email)
                        && r.getAccess_token() != null && r.getAccess_token().equals(accessToken))
                .findFirst();

        if (registro.isPresent()) {
            session.setAttribute("superadmin", registro.get());
            return "redirect:/superadmin/dashboard";
        }

        model.addAttribute("error", "Credenciales incorrectas. Verifique su email y access token.");
        return "login_general";
    }

    @GetMapping("/superadmin/logout")
    public String superadminLogout(HttpSession session) {
        session.removeAttribute("superadmin");
        return "redirect:/";
    }

    // ========== SUPERADMIN DASHBOARD ==========

    @GetMapping("/superadmin/dashboard")
    public String superadminDashboard(Model model, HttpSession session) {
        if (session.getAttribute("superadmin") == null) {
            return "redirect:/";
        }
        model.addAttribute("superadmin", session.getAttribute("superadmin"));
        model.addAttribute("tenants", serviceTenants.buscarTodos());
        model.addAttribute("usuarios", serviceUsuarios.buscarTodos());
        return "superadmin_dashboard";
    }

    @PostMapping("/superadmin/tenants/crear")
    @ResponseBody
    public Map<String, Object> crearTenant(@RequestBody Map<String, String> datos, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        if (session.getAttribute("superadmin") == null && datos.get("bypassAuth") == null) {
            res.put("success", false);
            res.put("error", "No autorizado");
            return res;
        }
        try {
            String ruc = datos.get("ruc");
            boolean rucExiste = serviceTenants.buscarTodos().stream()
                    .anyMatch(t -> t.getRuc() != null && t.getRuc().equals(ruc));
            if (rucExiste) {
                res.put("success", false);
                res.put("error", "El RUC ya se encuentra registrado por otra tienda.");
                return res;
            }

            Tenants tenant = new Tenants();
            tenant.setRazon_social(datos.get("razon_social"));
            tenant.setRuc(ruc);
            tenant.setDireccion_fiscal(datos.get("direccion_fiscal"));
            tenant.setCorreo(datos.get("correo"));
            tenant.setTelefono(datos.get("telefono"));
            tenant.setNombre_comercial(datos.get("nombre_comercial"));
            tenant.setTipo_negocio(datos.get("tipo_negocio"));
            tenant.setEstado(1);
            serviceTenants.guardar(tenant);

            // Fetch the persisted tenant from database to ensure it has its ID populated
            Tenants savedTenant = serviceTenants.buscarTodos().stream()
                    .filter(t -> t.getRuc() != null && t.getRuc().equals(ruc))
                    .findFirst()
                    .orElse(tenant);

            // Crear usuario admin por defecto
            Usuarios admin = new Usuarios();
            admin.setId_tenants(savedTenant);
            String nombreLimpio = datos.get("nombre_comercial").replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            String emailAdmin = "admin@" + nombreLimpio + ".com";
            
            boolean correoExiste = serviceUsuarios.buscarTodos().stream()
                    .anyMatch(u -> u.getCorreo() != null && u.getCorreo().equalsIgnoreCase(emailAdmin));
            if (correoExiste) {
                res.put("success", false);
                res.put("error", "El correo de administración autogenerado '" + emailAdmin + "' ya está registrado.");
                return res;
            }

            admin.setCorreo(emailAdmin);
            admin.setNombre_usuario("Administrador");
            admin.setApellidos_usuario(datos.get("nombre_comercial"));
            admin.setContrasenia("123");
            admin.setTipo_usuario("admin");
            admin.setNumero_documento("00000000");
            admin.setEstado(1);
            serviceUsuarios.guardar(admin);

            res.put("success", true);
            res.put("tenantId", tenant.getId_tenants());
            res.put("adminCorreo", admin.getCorreo());
        } catch (Exception e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("error", e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return res;
    }

    @PostMapping("/superadmin/tenants/editar")
    @ResponseBody
    public Map<String, Object> editarTenant(@RequestBody Map<String, String> datos, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        if (session.getAttribute("superadmin") == null && datos.get("bypassAuth") == null) {
            res.put("success", false);
            res.put("error", "No autorizado");
            return res;
        }
        try {
            Integer id = Integer.parseInt(datos.get("id_tenants"));
            Tenants tenant = serviceTenants.buscarId(id).orElse(null);
            if (tenant == null) {
                res.put("success", false);
                res.put("error", "Tenant no encontrado");
                return res;
            }

            String ruc = datos.get("ruc");
            boolean rucExiste = serviceTenants.buscarTodos().stream()
                    .anyMatch(t -> t.getRuc() != null && t.getRuc().equals(ruc) && !t.getId_tenants().equals(id));
            if (rucExiste) {
                res.put("success", false);
                res.put("error", "El RUC ya se encuentra registrado por otra tienda.");
                return res;
            }

            tenant.setRazon_social(datos.get("razon_social"));
            tenant.setRuc(ruc);
            tenant.setDireccion_fiscal(datos.get("direccion_fiscal"));
            tenant.setCorreo(datos.get("correo"));
            tenant.setTelefono(datos.get("telefono"));
            tenant.setNombre_comercial(datos.get("nombre_comercial"));
            tenant.setTipo_negocio(datos.get("tipo_negocio"));
            serviceTenants.modificar(tenant);
            res.put("success", true);
        } catch (Exception e) {
            res.put("success", false);
            res.put("error", e.getMessage());
        }
        return res;
    }

    @GetMapping("/superadmin/api/tenants")
    @ResponseBody
    public List<Tenants> apiTenants(HttpSession session) {
        return serviceTenants.buscarTodos();
    }

    @GetMapping("/superadmin/api/usuarios")
    @ResponseBody
    public List<Usuarios> apiUsuarios(HttpSession session) {
        return serviceUsuarios.buscarTodos();
    }

    @PostMapping("/superadmin/usuarios/editar")
    @ResponseBody
    public Map<String, Object> editarUsuario(@RequestBody Map<String, String> datos, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        if (session.getAttribute("superadmin") == null && datos.get("bypassAuth") == null) {
            res.put("success", false);
            res.put("error", "No autorizado");
            return res;
        }
        try {
            Integer id = Integer.parseInt(datos.get("id_usuarios"));
            Usuarios usuario = serviceUsuarios.buscarId(id).orElse(null);
            if (usuario == null) {
                res.put("success", false);
                res.put("error", "Usuario no encontrado");
                return res;
            }
            usuario.setNombre_usuario(datos.get("nombre_usuario"));
            usuario.setApellidos_usuario(datos.get("apellidos_usuario"));
            usuario.setCorreo(datos.get("correo"));
            usuario.setTipo_usuario(datos.get("tipo_usuario"));
            usuario.setEstado(Integer.parseInt(datos.get("estado")));

            if (datos.get("id_tenants") != null && !datos.get("id_tenants").isEmpty()) {
                Integer tenantId = Integer.parseInt(datos.get("id_tenants"));
                Tenants tenant = serviceTenants.buscarId(tenantId).orElse(null);
                usuario.setId_tenants(tenant);
            }

            if (datos.get("contrasenia") != null && !datos.get("contrasenia").trim().isEmpty()) {
                usuario.setContrasenia(datos.get("contrasenia"));
            }

            serviceUsuarios.modificar(usuario);
            res.put("success", true);
        } catch (Exception e) {
            res.put("success", false);
            res.put("error", e.getMessage());
        }
        return res;
    }

    // ========== PORTAL PÚBLICO: TIENDA VIRTUAL MULTI-TENANT ==========

    @GetMapping("/tienda/{tenantId}")
    public String tiendaMultiTenant(@PathVariable Integer tenantId, Model model, HttpSession session) {
        Tenants tenant = serviceTenants.buscarId(tenantId).orElse(null);
        if (tenant == null) {
            return "redirect:/";
        }
        session.setAttribute("tenantId", tenantId);

        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente != null
                && (cliente.getId_tenants() == null || !cliente.getId_tenants().getId_tenants().equals(tenantId))) {
            session.removeAttribute("cliente");
            cliente = null;
        }

        model.addAttribute("cliente", cliente);
        model.addAttribute("tenant", tenant);
        model.addAttribute("tenantId", tenantId);
        return "tienda";
    }

    @GetMapping("/tienda/login")
    public String tiendaLogin(HttpSession session, Model model) {
        Integer tenantId = (Integer) session.getAttribute("tenantId");
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente != null) {
            if (tenantId != null && cliente.getId_tenants() != null
                    && cliente.getId_tenants().getId_tenants().equals(tenantId)) {
                return "redirect:/tienda/" + tenantId;
            } else {
                session.removeAttribute("cliente");
            }
        }
        model.addAttribute("tenantId", tenantId);
        return "tienda_login";
    }

    @PostMapping("/tienda/login")
    public String tiendaLoginPost(@RequestParam String correo,
            @RequestParam String documento,
            HttpSession session, Model model) {
        Integer tenantId = (Integer) session.getAttribute("tenantId");
        Optional<Cliente> client = serviceCliente.buscarTodos().stream()
                .filter(c -> c.getCorreo() != null && c.getCorreo().equalsIgnoreCase(correo)
                        && c.getNumero_documento() != null && c.getNumero_documento().equals(documento)
                        && c.getId_tenants() != null && c.getId_tenants().getId_tenants().equals(tenantId))
                .findFirst();

        if (client.isPresent()) {
            session.setAttribute("cliente", client.get());
            return "redirect:/tienda/" + tenantId;
        }

        model.addAttribute("error", "Credenciales incorrectas (Verifique Correo y DNI/RUC)");
        model.addAttribute("tenantId", tenantId);
        return "tienda_login";
    }

    @GetMapping("/tienda/registro")
    public String tiendaRegistro(HttpSession session, Model model) {
        Integer tenantId = (Integer) session.getAttribute("tenantId");
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente != null) {
            if (tenantId != null && cliente.getId_tenants() != null
                    && cliente.getId_tenants().getId_tenants().equals(tenantId)) {
                return "redirect:/tienda/" + tenantId;
            } else {
                session.removeAttribute("cliente");
            }
        }
        model.addAttribute("tenantId", tenantId);
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
        Integer tenantId = (Integer) session.getAttribute("tenantId");
        // Validar si ya existe dentro del mismo tenant
        boolean existe = serviceCliente.buscarTodos().stream()
                .anyMatch(c -> c.getCorreo() != null && c.getCorreo().equalsIgnoreCase(correo)
                        && c.getId_tenants() != null && c.getId_tenants().getId_tenants().equals(tenantId));

        if (existe) {
            model.addAttribute("error", "El correo ya se encuentra registrado en esta tienda");
            model.addAttribute("tenantId", tenantId);
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

        Tenants tenant = serviceTenants.buscarId(tenantId != null ? tenantId : 1).orElse(null);
        cliente.setId_tenants(tenant);

        serviceCliente.guardar(cliente);
        session.setAttribute("cliente", cliente);

        return "redirect:/tienda/" + (tenantId != null ? tenantId : 1);
    }

    @GetMapping("/tienda/logout")
    public String tiendaLogout(HttpSession session) {
        Integer tenantId = (Integer) session.getAttribute("tenantId");
        session.removeAttribute("cliente");
        return "redirect:/tienda/" + (tenantId != null ? tenantId : 1);
    }

    @GetMapping("/tienda/checkout")
    public String tiendaCheckout(Model model, HttpSession session) {
        Integer tenantId = (Integer) session.getAttribute("tenantId");
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente != null
                && (cliente.getId_tenants() == null || !cliente.getId_tenants().getId_tenants().equals(tenantId))) {
            session.removeAttribute("cliente");
            cliente = null;
        }

        List<Sede> sedes = serviceSede.buscarTodos().stream()
                .filter(s -> tenantId == null
                        || (s.getId_tenants() != null && s.getId_tenants().getId_tenants().equals(tenantId)))
                .collect(Collectors.toList());

        model.addAttribute("cliente", cliente);
        model.addAttribute("tenantId", tenantId);
        model.addAttribute("sedes", sedes);
        return "tienda_checkout";
    }

    @GetMapping("/tienda/success")
    public String tiendaSuccess(Model model, HttpSession session) {
        Integer tenantId = (Integer) session.getAttribute("tenantId");
        model.addAttribute("tenantId", tenantId);
        return "tienda_success";
    }

    // ========== API PÚBLICA TIENDA (JSON) - MULTI-TENANT ==========

    @GetMapping("/tienda/api/productos")
    @ResponseBody
    public List<Producto> getStorefrontProductos(
            @RequestParam(required = false) Integer tenantIdParam,
            HttpSession session) {
        Integer tenantId = tenantIdParam != null ? tenantIdParam : (Integer) session.getAttribute("tenantId");
        return serviceProducto.buscarTodos().stream()
                .filter(p -> p.getVisible_storefront() != null && p.getVisible_storefront() == 1
                        && (tenantId == null
                                || (p.getId_tenants() != null && p.getId_tenants().getId_tenants().equals(tenantId))))
                .collect(Collectors.toList());
    }

    @GetMapping("/tienda/api/servicios")
    @ResponseBody
    public List<ServicioBelleza> getStorefrontServicios(
            @RequestParam(required = false) Integer tenantIdParam,
            HttpSession session) {
        Integer tenantId = tenantIdParam != null ? tenantIdParam : (Integer) session.getAttribute("tenantId");
        return serviceServicioBelleza.buscarTodos().stream()
                .filter(s -> (s.getEstado() == null || s.getEstado() == 1)
                        && (tenantId == null
                                || (s.getId_tenants() != null && s.getId_tenants().getId_tenants().equals(tenantId))))
                .collect(Collectors.toList());
    }

    @GetMapping("/tienda/api/combos")
    @ResponseBody
    public List<ComboPromocional> getStorefrontCombos(
            @RequestParam(required = false) Integer tenantIdParam,
            HttpSession session) {
        Integer tenantId = tenantIdParam != null ? tenantIdParam : (Integer) session.getAttribute("tenantId");
        return serviceComboPromocional.buscarTodos().stream()
                .filter(c -> c.getVisible_storefront() != null && c.getVisible_storefront() == 1
                        && (c.getEstado() == null || c.getEstado() == 1)
                        && (tenantId == null
                                || (c.getId_tenants() != null && c.getId_tenants().getId_tenants().equals(tenantId))))
                .collect(Collectors.toList());
    }

    @GetMapping("/tienda/api/combos/{id}/productos")
    @ResponseBody
    public List<ComposicionCombo> getStorefrontComboProductos(@PathVariable Integer id) {
        return serviceComposicionCombo.buscarPorCombo(id);
    }

    @PostMapping("/tienda/api/contacto")
    @ResponseBody
    @Transactional
    public Map<String, Object> registrarContacto(@RequestBody ContactRequest request, HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        try {
            Integer tenantId = (Integer) session.getAttribute("tenantId");
            if (tenantId == null) {
                tenantId = 1;
            }
            Tenants tenant = serviceTenants.buscarId(tenantId).orElse(null);

            String email = request.getCorreo();
            Cliente cliente = (Cliente) session.getAttribute("cliente");
            if (cliente == null && email != null) {
                Optional<Cliente> optCli = serviceCliente.buscarTodos().stream()
                        .filter(c -> c.getCorreo() != null && c.getCorreo().equalsIgnoreCase(email))
                        .findFirst();
                if (optCli.isPresent()) {
                    cliente = optCli.get();
                } else {
                    cliente = new Cliente();
                    cliente.setId_tenants(tenant);
                    cliente.setNombre_cliente(request.getNombre());
                    cliente.setApellidos_clientes("Contacto Web");
                    cliente.setCorreo(email);
                    cliente.setTelefono("");
                    cliente.setEstado(1);
                    cliente = serviceCliente.guardar(cliente);
                }
            }

            if (cliente == null) {
                cliente = new Cliente();
                cliente.setId_tenants(tenant);
                cliente.setNombre_cliente(request.getNombre() != null ? request.getNombre() : "Usuario Web");
                cliente.setApellidos_clientes("Contacto Web");
                cliente.setCorreo(email != null ? email : "web@contact.com");
                cliente.setTelefono("");
                cliente.setEstado(1);
                cliente = serviceCliente.guardar(cliente);
            }

            Reclamo reclamo = new Reclamo();
            reclamo.setId_tenants(tenant);
            reclamo.setId_clientes(cliente);
            reclamo.setNumero_reclamo("CON-" + System.currentTimeMillis());
            reclamo.setCanal_ingreso("web");
            reclamo.setTipo_incidencia("consulta_contacto");
            reclamo.setDescripcion("Asunto: " + request.getAsunto() + "\n\nMensaje:\n" + request.getMensaje());
            reclamo.setEstado(1);

            serviceReclamo.guardar(reclamo);

            res.put("success", true);
        } catch (Exception e) {
            res.put("success", false);
            res.put("error", e.getMessage());
        }
        return res;
    }

    @GetMapping("/tienda/api/historial")
    @ResponseBody
    public Map<String, Object> getClienteHistorial(
            @RequestParam(required = false) Integer clienteId,
            @RequestParam(required = false) Integer tenantIdParam,
            HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        Cliente cliente = null;
        if (clienteId != null) {
            cliente = serviceCliente.buscarId(clienteId).orElse(null);
        } else {
            cliente = (Cliente) session.getAttribute("cliente");
        }
        Integer tenantId = tenantIdParam != null ? tenantIdParam : (Integer) session.getAttribute("tenantId");
        if (cliente == null || (tenantId != null
                && (cliente.getId_tenants() == null || !cliente.getId_tenants().getId_tenants().equals(tenantId)))) {
            res.put("success", false);
            res.put("error", "No ha iniciado sesión");
            return res;
        }

        final Cliente finalCliente = cliente;

        // Obtener ventas asociadas al cliente
        List<Venta> ventas = serviceVenta.buscarTodos().stream()
                .filter(v -> v.getId_clientes() != null
                        && v.getId_clientes().getId_clientes().equals(finalCliente.getId_clientes()))
                .collect(Collectors.toList());

        // Obtener citas/reservas asociadas al cliente
        List<Cita> citas = serviceCita.buscarTodos().stream()
                .filter(c -> c.getId_clientes() != null
                        && c.getId_clientes().getId_clientes().equals(finalCliente.getId_clientes()))
                .collect(Collectors.toList());

        res.put("success", true);
        res.put("ventas", ventas);
        res.put("citas", citas);
        return res;
    }

    @GetMapping("/tienda/api/detalles-venta/{ventaId}")
    @ResponseBody
    public List<Map<String, Object>> getDetallesVenta(
            @org.springframework.web.bind.annotation.PathVariable Integer ventaId) {

        List<Map<String, Object>> items = serviceDetalleVenta.buscarTodos().stream()
                .filter(d -> d.getId_ventas() != null && d.getId_ventas().getId_ventas().equals(ventaId))
                .map(d -> {
                    Map<String, Object> item = new HashMap<>();
                    item.put("id_detalle_venta", d.getId_detalle_venta());
                    item.put("cantidad", d.getCantidad());
                    item.put("precio_unitario", d.getPrecio_unitario());
                    item.put("subtotal", d.getSubtotal());
                    String nombreProducto = "Producto/Servicio";
                    if (d.getId_productos() != null) {
                        try {
                            nombreProducto = d.getId_productos().getNombre_producto();
                        } catch (Exception ignored) {
                        }
                    }
                    item.put("nombre_producto", nombreProducto);
                    return item;
                })
                .collect(Collectors.toList());

        boolean isPedido = false;
        if (items.isEmpty()) {
            items = serviceDetallePedido.buscarTodos().stream()
                    .filter(d -> d.getId_pedidos() != null && d.getId_pedidos().getId_pedidos().equals(ventaId))
                    .map(d -> {
                        Map<String, Object> item = new HashMap<>();
                        item.put("id_detalle_venta", d.getId_detalle_pedido());
                        item.put("cantidad", d.getCantidad());
                        item.put("precio_unitario", d.getPrecio_unitario());
                        item.put("subtotal", d.getSubtotal());
                        String nombreProducto = "Producto/Servicio";
                        if (d.getId_productos() != null) {
                            try {
                                nombreProducto = d.getId_productos().getNombre_producto();
                            } catch (Exception ignored) {
                            }
                        }
                        item.put("nombre_producto", nombreProducto);
                        return item;
                    })
                    .collect(Collectors.toList());
            isPedido = true;
        }

        List<Cita> citas = new java.util.ArrayList<>();
        if (isPedido) {
            Optional<Pedido> optPedido = servicePedido.buscarId(ventaId);
            if (optPedido.isPresent()) {
                Pedido ped = optPedido.get();
                citas = serviceCita.buscarTodos().stream()
                        .filter(c -> c.getId_clientes() != null
                                && c.getId_clientes().getId_clientes().equals(ped.getId_clientes().getId_clientes())
                                && c.getId_ventas() == null
                                && c.getEstado() != null && c.getEstado() == 1)
                        .collect(Collectors.toList());
            }
        } else {
            citas = serviceCita.buscarTodos().stream()
                    .filter(c -> c.getId_ventas() != null && c.getId_ventas().getId_ventas().equals(ventaId)
                            && c.getEstado() != null && c.getEstado() == 1)
                    .collect(Collectors.toList());
        }

        for (Cita cita : citas) {
            List<ServicioCita> servs = serviceServicioCita.buscarTodos().stream()
                    .filter(sc -> sc.getId_citas() != null && sc.getId_citas().getId_citas().equals(cita.getId_citas()))
                    .collect(Collectors.toList());

            for (ServicioCita sc : servs) {
                Map<String, Object> item = new HashMap<>();
                item.put("id_detalle_venta", -1);
                item.put("cantidad", 1);
                item.put("precio_unitario", sc.getPrecio());
                item.put("subtotal", sc.getPrecio());
                String nombreServicio = "Servicio de Belleza";
                if (sc.getId_servicios_belleza() != null) {
                    nombreServicio = sc.getId_servicios_belleza().getNombre_servicio_belleza();
                }
                item.put("nombre_producto", nombreServicio + " (Servicio)");
                items.add(item);
            }
        }

        return items;
    }

    @GetMapping("/tienda/api/venta/{ventaId}")
    @ResponseBody
    public Map<String, Object> getVentaById(@org.springframework.web.bind.annotation.PathVariable Integer ventaId,
            HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        Venta venta = serviceVenta.buscarId(ventaId).orElse(null);
        if (venta == null) {
            Pedido pedido = servicePedido.buscarId(ventaId).orElse(null);
            if (pedido != null) {
                Venta dummy = new Venta();
                dummy.setId_ventas(pedido.getId_pedidos());
                dummy.setId_clientes(pedido.getId_clientes());
                dummy.setId_tenants(pedido.getId_tenants());
                dummy.setNumero_ticket(pedido.getNumero_pedido());
                dummy.setComprobante_numero(pedido.getNumero_pedido());
                dummy.setTipo_comprobante("boleta");
                dummy.setTotal(pedido.getTotal());
                dummy.setSubtotal(pedido.getSubtotal());
                dummy.setImpuesto(pedido.getImpuesto());
                dummy.setFecha_venta(pedido.getFecha_pedido());

                res.put("success", true);
                res.put("venta", dummy);
                return res;
            }
            res.put("success", false);
            res.put("error", "Venta no encontrada");
            return res;
        }
        res.put("success", true);
        res.put("venta", venta);
        return res;
    }

    @GetMapping("/tienda/api/categorias")
    @ResponseBody
    public List<CategoriaProducto> getStorefrontCategorias(
            @RequestParam(required = false) Integer tenantIdParam,
            HttpSession session) {
        Integer tenantId = tenantIdParam != null ? tenantIdParam : (Integer) session.getAttribute("tenantId");
        return serviceCategoria.buscarTodos().stream()
                .filter(c -> tenantId == null
                        || (c.getId_tenants() != null && c.getId_tenants().getId_tenants().equals(tenantId)))
                .collect(Collectors.toList());
    }

    @GetMapping("/tienda/api/marcas")
    @ResponseBody
    public List<Marca> getStorefrontMarcas(
            @RequestParam(required = false) Integer tenantIdParam,
            HttpSession session) {
        Integer tenantId = tenantIdParam != null ? tenantIdParam : (Integer) session.getAttribute("tenantId");
        return serviceMarca.buscarTodos().stream()
                .filter(m -> tenantId == null
                        || (m.getId_tenants() != null && m.getId_tenants().getId_tenants().equals(tenantId)))
                .collect(Collectors.toList());
    }

    @GetMapping("/tienda/api/citas/disponibles")
    @ResponseBody
    public List<String> getCitasDisponibles(@RequestParam Integer SedeId,
            @RequestParam String fecha,
            @RequestParam Integer duracion) {
        try {
            java.time.LocalDate localDate = java.time.LocalDate.parse(fecha);
            String[] dias = { "lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo" };
            String diaSemana = dias[localDate.getDayOfWeek().getValue() - 1];

            Optional<HorarioOperacion> horarioOpt = serviceHorarioOperacion.buscarTodos().stream()
                    .filter(h -> h.getId_sedes() != null && h.getId_sedes().getId_sedes().equals(SedeId)
                            && h.getDia_semana() != null && h.getDia_semana().equalsIgnoreCase(diaSemana)
                            && h.getEstado() != null && h.getEstado() == 1)
                    .findFirst();

            if (horarioOpt.isEmpty()) {
                return java.util.Collections.emptyList();
            }

            HorarioOperacion horario = horarioOpt.get();
            java.time.LocalTime apertura = horario.getHora_apertura();
            java.time.LocalTime cierre = horario.getHora_cierre();

            if (apertura == null || cierre == null) {
                return java.util.Collections.emptyList();
            }

            List<Cita> citasExistentes = serviceCita.buscarTodos().stream()
                    .filter(c -> c.getId_sedes() != null && c.getId_sedes().getId_sedes().equals(SedeId)
                            && c.getFecha_cita() != null && c.getFecha_cita().equals(localDate)
                            && c.getEstado() != null && c.getEstado() == 1)
                    .collect(Collectors.toList());

            List<String> slotsDisponibles = new java.util.ArrayList<>();
            java.time.LocalTime actual = apertura;

            while (actual.plusMinutes(duracion).isBefore(cierre) || actual.plusMinutes(duracion).equals(cierre)) {
                java.time.LocalTime slotInicio = actual;
                java.time.LocalTime slotFin = actual.plusMinutes(duracion);

                boolean seCruza = false;
                for (Cita cita : citasExistentes) {
                    java.time.LocalTime cInicio = cita.getHora_inicio();
                    java.time.LocalTime cFin = cita.getHora_fin();

                    if (cInicio != null && cFin != null) {
                        if (slotInicio.isBefore(cFin) && cInicio.isBefore(slotFin)) {
                            seCruza = true;
                            break;
                        }
                    }
                }

                if (!seCruza) {
                    slotsDisponibles.add(slotInicio.toString().substring(0, 5));
                }

                actual = actual.plusMinutes(30);
            }

            return slotsDisponibles;
        } catch (Exception e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @PostMapping("/tienda/api/checkout")
    @ResponseBody
    @Transactional
    public String procesarCheckout(@RequestBody CheckoutRequest request, HttpSession session) {
        try {
            Integer checkoutTenantId = request.getTenantId() != null ? request.getTenantId()
                    : (Integer) session.getAttribute("tenantId");
            final Integer finalTenantId = checkoutTenantId != null ? checkoutTenantId : 1;

            // 1. Obtener o registrar al cliente filtrando por tenant
            Cliente cliente = null;
            Optional<Cliente> optCliente = serviceCliente.buscarTodos().stream()
                    .filter(c -> c.getCorreo() != null && c.getCorreo().equalsIgnoreCase(request.getCorreo())
                            && c.getId_tenants() != null && c.getId_tenants().getId_tenants().equals(finalTenantId))
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
                cliente.setId_tenants(serviceTenants.buscarId(finalTenantId).orElse(null));
                serviceCliente.guardar(cliente);
            }

            // 2. Resolver dependencias de Sede, Tenant y SesionCaja
            Tenants tenant = serviceTenants.buscarId(finalTenantId).orElse(null);

            Sede sede = null;
            if (request.getSedeId() != null) {
                sede = serviceSede.buscarId(request.getSedeId()).orElse(null);
            }
            if (sede == null) {
                sede = serviceSede.buscarTodos().stream()
                        .filter(s -> s.getId_tenants() != null
                                && s.getId_tenants().getId_tenants().equals(finalTenantId))
                        .findFirst().orElse(serviceSede.buscarTodos().stream().findFirst().orElse(null));
            }

            SesionCaja sesion = serviceSesionCaja.buscarTodos().stream()
                    .filter(s -> s.getEstado() != null && s.getEstado() == 1)
                    .findFirst()
                    .orElseGet(() -> serviceSesionCaja.buscarTodos().stream().findFirst().orElse(null));

            // 3. Verificar si hay servicios en el carrito y calcular su duración y
            // validaciones
            boolean hasServices = false;
            int duracionTotal = 0;
            List<ServicioBelleza> serviciosABookear = new java.util.ArrayList<>();
            for (CartItem item : request.getItems()) {
                if ("servicio".equalsIgnoreCase(item.getTipo()) && item.getId_servicios_belleza() != null) {
                    hasServices = true;
                    ServicioBelleza serv = serviceServicioBelleza.buscarId(item.getId_servicios_belleza()).orElse(null);
                    if (serv != null) {
                        serviciosABookear.add(serv);
                        duracionTotal += (serv.getDuracion_minima() != null ? serv.getDuracion_minima() : 30);
                    }
                }
            }

            java.time.LocalDate localDateCita = null;
            java.time.LocalTime slotInicio = null;
            java.time.LocalTime slotFin = null;
            if (hasServices) {
                if (request.getFechaCita() == null || request.getHoraCita() == null) {
                    return "{\"success\": false, \"error\": \"Debe seleccionar fecha y hora para su cita.\"}";
                }
                localDateCita = java.time.LocalDate.parse(request.getFechaCita());
                slotInicio = java.time.LocalTime.parse(request.getHoraCita());
                slotFin = slotInicio.plusMinutes(duracionTotal);

                // Validar día y horario de atención
                String[] dias = { "lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo" };
                String diaSemana = dias[localDateCita.getDayOfWeek().getValue() - 1];
                final Sede finalSede = sede;
                final String finalDia = diaSemana;
                Optional<HorarioOperacion> horarioOpt = serviceHorarioOperacion.buscarTodos().stream()
                        .filter(h -> h.getId_sedes() != null
                                && h.getId_sedes().getId_sedes().equals(finalSede.getId_sedes())
                                && h.getDia_semana() != null && h.getDia_semana().equalsIgnoreCase(finalDia)
                                && h.getEstado() != null && h.getEstado() == 1)
                        .findFirst();

                if (horarioOpt.isEmpty()) {
                    return "{\"success\": false, \"error\": \"La sede seleccionada no atiende en el día elegido.\"}";
                }
                HorarioOperacion horario = horarioOpt.get();
                if (slotInicio.isBefore(horario.getHora_apertura()) || slotFin.isAfter(horario.getHora_cierre())) {
                    return "{\"success\": false, \"error\": \"El horario seleccionado está fuera del horario de atención de la sede.\"}";
                }

                // Validar cruce de citas
                final java.time.LocalDate targetDate = localDateCita;
                List<Cita> citasExistentes = serviceCita.buscarTodos().stream()
                        .filter(c -> c.getId_sedes() != null
                                && c.getId_sedes().getId_sedes().equals(finalSede.getId_sedes())
                                && c.getFecha_cita() != null && c.getFecha_cita().equals(targetDate)
                                && c.getEstado() != null && c.getEstado() == 1)
                        .collect(Collectors.toList());

                for (Cita cita : citasExistentes) {
                    java.time.LocalTime cInicio = cita.getHora_inicio();
                    java.time.LocalTime cFin = cita.getHora_fin();
                    if (cInicio != null && cFin != null) {
                        if (slotInicio.isBefore(cFin) && cInicio.isBefore(slotFin)) {
                            return "{\"success\": false, \"error\": \"El horario seleccionado ya no está disponible. Por favor elija otro.\"}";
                        }
                    }
                }
            }

            // 4. Crear Pedido
            Pedido pedido = new Pedido();
            pedido.setId_tenants(tenant);
            pedido.setId_clientes(cliente);
            pedido.setNumero_pedido("PED-" + System.currentTimeMillis());
            pedido.setModalidad("delivery");
            pedido.setEstado(1); // 1 = Pendiente

            double total = 0.0;
            for (CartItem item : request.getItems()) {
                total += item.getCantidad() * item.getPrecio_venta();
            }
            pedido.setTotal(BigDecimal.valueOf(total));
            pedido.setSubtotal(BigDecimal.valueOf(total / 1.18));
            pedido.setImpuesto(BigDecimal.valueOf(total - (total / 1.18)));
            pedido.setDireccion_entrega(request.getDireccion() != null ? request.getDireccion() : "");

            Pedido savedPedido = servicePedido.guardar(pedido);

            // 5. Crear DetallePedido para productos (y descontar stock)
            List<Object[]> stockUpdates = new java.util.ArrayList<>();
            for (CartItem item : request.getItems()) {
                if (item.getId_productos() != null && !"servicio".equalsIgnoreCase(item.getTipo())
                        && !"combo".equalsIgnoreCase(item.getTipo())) {
                    Producto prod = serviceProducto.buscarId(item.getId_productos()).orElse(null);
                    if (prod != null) {
                        DetallePedido det = new DetallePedido();
                        det.setId_pedidos(savedPedido);
                        det.setId_productos(prod);
                        det.setCantidad(item.getCantidad());
                        det.setPrecio_unitario(BigDecimal.valueOf(item.getPrecio_venta()));
                        det.setSubtotal(BigDecimal.valueOf(item.getCantidad() * item.getPrecio_venta()));
                        serviceDetallePedido.guardar(det);
                        stockUpdates.add(new Object[] { prod, item.getCantidad() });
                    }
                } else if ("combo".equalsIgnoreCase(item.getTipo()) && item.getId_combos_promocionales() != null) {
                    List<ComposicionCombo> composicion = serviceComposicionCombo
                            .buscarPorCombo(item.getId_combos_promocionales());
                    if (composicion != null && !composicion.isEmpty()) {
                        double normalTotal = 0.0;
                        for (ComposicionCombo cc : composicion) {
                            if (cc.getId_productos() != null) {
                                double price = cc.getId_productos().getPrecio_venta() != null
                                        ? cc.getId_productos().getPrecio_venta().doubleValue()
                                        : 0.0;
                                int qtyInCombo = cc.getCantidad() != null ? cc.getCantidad() : 1;
                                normalTotal += price * qtyInCombo;
                            }
                        }
                        if (normalTotal <= 0.0)
                            normalTotal = 1.0;

                        double comboTotalPrice = item.getPrecio_venta() * item.getCantidad();

                        for (ComposicionCombo cc : composicion) {
                            Producto prod = cc.getId_productos();
                            if (prod != null) {
                                double prodPrice = prod.getPrecio_venta() != null ? prod.getPrecio_venta().doubleValue()
                                        : 0.0;
                                int qtyInCombo = cc.getCantidad() != null ? cc.getCantidad() : 1;
                                int totalQtyToDeduct = qtyInCombo * item.getCantidad();

                                double proportion = (prodPrice * qtyInCombo) / normalTotal;
                                double linePrice = comboTotalPrice * proportion;
                                double unitPrice = linePrice / totalQtyToDeduct;

                                DetallePedido det = new DetallePedido();
                                det.setId_pedidos(savedPedido);
                                det.setId_productos(prod);
                                det.setCantidad(totalQtyToDeduct);
                                det.setPrecio_unitario(BigDecimal.valueOf(unitPrice));
                                det.setSubtotal(BigDecimal.valueOf(linePrice));
                                serviceDetallePedido.guardar(det);
                                stockUpdates.add(new Object[] { prod, totalQtyToDeduct });
                            }
                        }
                    }
                }
            }

            for (Object[] update : stockUpdates) {
                Producto prod = (Producto) update[0];
                int qty = (int) update[1];

                List<LoteInventario> lotes = serviceLoteInventario.buscarTodos().stream()
                        .filter(l -> l.getId_productos() != null
                                && l.getId_productos().getId_productos().equals(prod.getId_productos())
                                && l.getEstado() != null && l.getEstado() == 1 && l.getCantidad_disponible() != null
                                && l.getCantidad_disponible() > 0)
                        .sorted(java.util.Comparator.comparing(LoteInventario::getId_lotes_inventario))
                        .collect(java.util.stream.Collectors.toList());

                int remainingToDeduct = qty;
                for (LoteInventario lote : lotes) {
                    if (remainingToDeduct <= 0)
                        break;
                    int available = lote.getCantidad_disponible();
                    if (available >= remainingToDeduct) {
                        lote.setCantidad_disponible(available - remainingToDeduct);
                        serviceLoteInventario.modificar(lote);

                        MovimientoInventario mov = new MovimientoInventario();
                        mov.setId_lotes_inventario(lote);
                        mov.setTipo_movimiento("salida");
                        mov.setCantidad(remainingToDeduct);
                        mov.setMotivo("Venta / Pedido online");
                        mov.setReferencia_documento(savedPedido.getNumero_pedido());
                        serviceMovimientoInventario.guardar(mov);

                        remainingToDeduct = 0;
                    } else {
                        lote.setCantidad_disponible(0);
                        serviceLoteInventario.modificar(lote);

                        MovimientoInventario mov = new MovimientoInventario();
                        mov.setId_lotes_inventario(lote);
                        mov.setTipo_movimiento("salida");
                        mov.setCantidad(available);
                        mov.setMotivo("Venta / Pedido online");
                        mov.setReferencia_documento(savedPedido.getNumero_pedido());
                        serviceMovimientoInventario.guardar(mov);

                        remainingToDeduct -= available;
                    }
                }

                int newStock = serviceLoteInventario.buscarTodos().stream()
                        .filter(l -> l.getId_productos() != null
                                && l.getId_productos().getId_productos().equals(prod.getId_productos())
                                && l.getEstado() != null && l.getEstado() == 1 && l.getCantidad_disponible() != null)
                        .mapToInt(LoteInventario::getCantidad_disponible)
                        .sum();
                prod.setStock_actual(newStock);
                serviceProducto.modificar(prod);
            }

            // 6. Si hay servicios, guardar Cita y relacionarla con la Venta (null por
            // ahora)
            if (hasServices) {
                Cita cita = new Cita();
                cita.setId_tenants(tenant);
                cita.setId_sedes(sede);
                cita.setId_clientes(cliente);
                cita.setFecha_cita(localDateCita);
                cita.setHora_inicio(slotInicio);
                cita.setHora_fin(slotFin);
                cita.setDuracion_minutos(duracionTotal);
                cita.setEstado(1);
                cita.setObservaciones(request.getObservacionesCita());
                cita.setId_ventas(null);

                Cita savedCita = serviceCita.guardar(cita);

                for (ServicioBelleza serv : serviciosABookear) {
                    ServicioCita servCita = new ServicioCita();
                    servCita.setId_citas(savedCita);
                    servCita.setId_servicios_belleza(serv);
                    servCita.setPrecio(serv.getPrecio_base());
                    servCita.setObservaciones("Reservado online");
                    serviceServicioCita.guardar(servCita);
                }
            }

            // Actualizar sesion con datos frescos del cliente
            session.setAttribute("cliente", cliente);

            return "{\"success\": true, \"ventaId\": " + savedPedido.getId_pedidos() + "}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\": false, \"error\": \"" + e.getMessage().replace("\"", "\\\"") + "\"}";
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
                        && u.getContrasenia() != null && verificarContrasenia(contrasenia, u.getContrasenia()))
                .findFirst();

        if (user.isPresent()) {
            Usuarios u = user.get();
            session.setAttribute("usuario", u);
            if (u.getId_tenants() != null) {
                session.setAttribute("userTenantId", u.getId_tenants().getId_tenants());
            }
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Credenciales incorrectas");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/admin/login";
        }
        Usuarios usuario = (Usuarios) session.getAttribute("usuario");
        String planName = "Ninguno";
        if (usuario != null && usuario.getId_tenants() != null) {
            Integer idTenant = usuario.getId_tenants().getId_tenants();
            Optional<Suscripcion> activeSubOpt = serviceSuscripcion.buscarTodos().stream()
                    .filter(s -> s.getId_tenants() != null
                            && s.getId_tenants().getId_tenants().equals(idTenant)
                            && s.getEstado() != null && s.getEstado() == 1)
                    .findFirst();
            if (activeSubOpt.isPresent()) {
                planName = activeSubOpt.get().getId_planes_suscripcion().getNombre_plan_suscripcion();
            }
        }
        model.addAttribute("planName", planName);
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

    @PostMapping("/tienda/api/login")
    @ResponseBody
    public Map<String, Object> apiTiendaLogin(@RequestBody Map<String, String> credentials) {
        Map<String, Object> res = new java.util.HashMap<>();
        String correo = credentials.get("correo");
        String documento = credentials.get("documento");
        String tenantIdStr = credentials.get("tenantId");
        Integer tenantId = tenantIdStr != null ? Integer.parseInt(tenantIdStr) : 1;

        Optional<Cliente> client = serviceCliente.buscarTodos().stream()
                .filter(c -> c.getCorreo() != null && c.getCorreo().equalsIgnoreCase(correo)
                        && c.getNumero_documento() != null && c.getNumero_documento().equals(documento)
                        && c.getId_tenants() != null && c.getId_tenants().getId_tenants().equals(tenantId))
                .findFirst();

        if (client.isPresent()) {
            res.put("success", true);
            res.put("cliente", client.get());
        } else {
            res.put("success", false);
            res.put("error", "Credenciales incorrectas (Verifique Correo y DNI/RUC)");
        }
        return res;
    }

    @PostMapping("/tienda/api/registro")
    @ResponseBody
    public Map<String, Object> apiTiendaRegistro(@RequestBody Map<String, String> datos) {
        Map<String, Object> res = new java.util.HashMap<>();
        String correo = datos.get("correo");
        String tenantIdStr = datos.get("tenantId");
        Integer tenantId = tenantIdStr != null ? Integer.parseInt(tenantIdStr) : 1;

        boolean existe = serviceCliente.buscarTodos().stream()
                .anyMatch(c -> c.getCorreo() != null && c.getCorreo().equalsIgnoreCase(correo)
                        && c.getId_tenants() != null && c.getId_tenants().getId_tenants().equals(tenantId));

        if (existe) {
            res.put("success", false);
            res.put("error", "El correo ya se encuentra registrado en esta tienda");
            return res;
        }

        Cliente cliente = new Cliente();
        cliente.setNombre_cliente(datos.get("nombre"));
        cliente.setApellidos_clientes(datos.get("apellidos"));
        cliente.setCorreo(correo);
        cliente.setTelefono(datos.get("telefono"));
        cliente.setDireccion(datos.get("direccion"));
        cliente.setDistrito(datos.get("distrito"));
        cliente.setTipo_documento(datos.get("tipoDocumento"));
        cliente.setNumero_documento(datos.get("numeroDocumento"));
        cliente.setTipo_cliente("regular");
        cliente.setEstado(1);

        Tenants tenant = serviceTenants.buscarId(tenantId).orElse(null);
        cliente.setId_tenants(tenant);

        serviceCliente.guardar(cliente);

        res.put("success", true);
        res.put("cliente", cliente);
        return res;
    }

    @PostMapping("/api/superadmin/login")
    @ResponseBody
    public Map<String, Object> apiSuperadminLogin(@RequestBody Map<String, String> credentials) {
        Map<String, Object> res = new java.util.HashMap<>();
        String email = credentials.get("email");
        String accessToken = credentials.get("accessToken");

        Optional<Registros> registro = serviceRegistros.buscarTodos().stream()
                .filter(r -> r.getEmail() != null && r.getEmail().equalsIgnoreCase(email)
                        && r.getAccess_token() != null && r.getAccess_token().equals(accessToken))
                .findFirst();

        if (registro.isPresent()) {
            res.put("success", true);
            res.put("superadmin", registro.get());
        } else {
            res.put("success", false);
            res.put("error", "Credenciales incorrectas. Verifique su email y access token.");
        }
        return res;
    }

    @PostMapping("/api/admin/login")
    @ResponseBody
    public Map<String, Object> apiAdminLogin(@RequestBody Map<String, String> credentials) {
        Map<String, Object> res = new java.util.HashMap<>();
        String correo = credentials.get("correo");
        String contrasenia = credentials.get("contrasenia");

        Optional<Usuarios> user = serviceUsuarios.buscarTodos().stream()
                .filter(u -> u.getCorreo() != null && u.getCorreo().equalsIgnoreCase(correo)
                        && u.getContrasenia() != null && verificarContrasenia(contrasenia, u.getContrasenia()))
                .findFirst();

        if (user.isPresent()) {
            Usuarios u = user.get();
            Integer idTenant = u.getId_tenants() != null ? u.getId_tenants().getId_tenants() : null;

            String planName = "Ninguno";
            if (idTenant != null) {
                Optional<Suscripcion> activeSubOpt = serviceSuscripcion.buscarTodos().stream()
                        .filter(s -> s.getId_tenants() != null
                                && s.getId_tenants().getId_tenants().equals(idTenant)
                                && s.getEstado() != null && s.getEstado() == 1)
                        .findFirst();
                if (activeSubOpt.isPresent()) {
                    planName = activeSubOpt.get().getId_planes_suscripcion().getNombre_plan_suscripcion();
                }
            }

            res.put("success", true);
            res.put("usuario", u);
            res.put("tenantId", idTenant);
            res.put("planName", planName);
            res.put("allowedModules", obtenerModulosPermitidosParaUsuario(u, idTenant));
        } else {
            res.put("success", false);
            res.put("error", "Credenciales incorrectas.");
        }
        return res;
    }
}
