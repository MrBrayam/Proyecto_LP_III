package proyecto.lp.iii.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import proyecto.lp.iii.api.entity.Usuarios;
import proyecto.lp.iii.api.service.IUsuariosService;

@Controller
public class PageController {

    @Autowired
    private IUsuariosService serviceUsuarios;

    @GetMapping({"/", "/login"})
    public String login(HttpSession session) {
        if (session.getAttribute("usuario") != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String correo,
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
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Dashboard");
        model.addAttribute("contentTemplate", "dashboard");
        return "base";
    }

    // ========== MÓDULO 1: Usuarios y Seguridad ==========

    @GetMapping("/modulos/usuarios")
    public String usuarios(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Usuarios");
        model.addAttribute("contentTemplate", "modulos/usuarios");
        return "base";
    }

    @GetMapping("/modulos/tenants")
    public String tenants(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Tenants");
        model.addAttribute("contentTemplate", "modulos/tenants");
        return "base";
    }

    @GetMapping("/modulos/roles")
    public String roles(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Roles Personalizados");
        model.addAttribute("contentTemplate", "modulos/roles");
        return "base";
    }

    @GetMapping("/modulos/permisos")
    public String permisos(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Permisos de Rol");
        model.addAttribute("contentTemplate", "modulos/permisos");
        return "base";
    }

    @GetMapping("/modulos/usuario-sedes")
    public String usuarioSedes(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Asignacion Usuario-Sede");
        model.addAttribute("contentTemplate", "modulos/usuario_sedes");
        return "base";
    }

    @GetMapping("/modulos/preferencias")
    public String preferencias(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Preferencias de Usuario");
        model.addAttribute("contentTemplate", "modulos/preferencias");
        return "base";
    }

    @GetMapping("/modulos/auditoria")
    public String auditoria(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Auditoria");
        model.addAttribute("contentTemplate", "modulos/auditoria");
        return "base";
    }

    // ========== MÓDULO 2: Productos e Inventario ==========

    @GetMapping("/modulos/productos")
    public String productos(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Productos");
        model.addAttribute("contentTemplate", "modulos/productos");
        return "base";
    }

    @GetMapping("/modulos/categorias")
    public String categorias(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Categorias de Productos");
        model.addAttribute("contentTemplate", "modulos/categorias");
        return "base";
    }

    @GetMapping("/modulos/marcas")
    public String marcas(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Marcas");
        model.addAttribute("contentTemplate", "modulos/marcas");
        return "base";
    }

    @GetMapping("/modulos/almacenes")
    public String almacenes(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Almacenes");
        model.addAttribute("contentTemplate", "modulos/almacenes");
        return "base";
    }

    @GetMapping("/modulos/lotes")
    public String lotes(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Lotes de Inventario");
        model.addAttribute("contentTemplate", "modulos/lotes");
        return "base";
    }

    @GetMapping("/modulos/movimientos")
    public String movimientos(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Movimientos de Inventario");
        model.addAttribute("contentTemplate", "modulos/movimientos");
        return "base";
    }

    @GetMapping("/modulos/combos")
    public String combos(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Combos Promocionales");
        model.addAttribute("contentTemplate", "modulos/combos");
        return "base";
    }

    @GetMapping("/modulos/promociones")
    public String promociones(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Promociones");
        model.addAttribute("contentTemplate", "modulos/promociones");
        return "base";
    }

    // ========== MÓDULO 3: Clientes y Contacto ==========

    @GetMapping("/modulos/clientes")
    public String clientes(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Clientes");
        model.addAttribute("contentTemplate", "modulos/clientes");
        return "base";
    }

    @GetMapping("/modulos/repartidores")
    public String repartidores(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Repartidores");
        model.addAttribute("contentTemplate", "modulos/repartidores");
        return "base";
    }

    @GetMapping("/modulos/zonas-delivery")
    public String zonasDelivery(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Zonas de Delivery");
        model.addAttribute("contentTemplate", "modulos/zonas_delivery");
        return "base";
    }

    @GetMapping("/modulos/citas")
    public String citas(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Citas");
        model.addAttribute("contentTemplate", "modulos/citas");
        return "base";
    }

    @GetMapping("/modulos/notificaciones")
    public String notificaciones(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Notificaciones");
        model.addAttribute("contentTemplate", "modulos/notificaciones");
        return "base";
    }

    @GetMapping("/modulos/sedes")
    public String sedes(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Sedes");
        model.addAttribute("contentTemplate", "modulos/sedes");
        return "base";
    }

    @GetMapping("/modulos/horarios-operacion")
    public String horariosOperacion(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Horarios de Operacion");
        model.addAttribute("contentTemplate", "modulos/horarios_operacion");
        return "base";
    }

    // ========== MÓDULO 4: Ventas y Pedidos ==========

    @GetMapping("/modulos/ventas")
    public String ventas(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Ventas");
        model.addAttribute("contentTemplate", "modulos/ventas");
        return "base";
    }

    @GetMapping("/modulos/pedidos")
    public String pedidos(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Pedidos");
        model.addAttribute("contentTemplate", "modulos/pedidos");
        return "base";
    }

    @GetMapping("/modulos/devoluciones-venta")
    public String devolucionesVenta(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Devoluciones de Venta");
        model.addAttribute("contentTemplate", "modulos/devoluciones_venta");
        return "base";
    }

    @GetMapping("/modulos/formas-pago-venta")
    public String formasPagoVenta(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Formas de Pago Venta");
        model.addAttribute("contentTemplate", "modulos/formas_pago_venta");
        return "base";
    }

    @GetMapping("/modulos/comprobantes-electronicos")
    public String comprobantesElectronicos(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Comprobantes Electronicos");
        model.addAttribute("contentTemplate", "modulos/comprobantes_electronicos");
        return "base";
    }

    @GetMapping("/modulos/series-comprobantes")
    public String seriesComprobantes(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Series de Comprobantes");
        model.addAttribute("contentTemplate", "modulos/series_comprobantes");
        return "base";
    }

    // ========== MÓDULO 5: Compras y Finanzas ==========

    @GetMapping("/modulos/ordenes-compra")
    public String ordenesCompra(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Ordenes de Compra");
        model.addAttribute("contentTemplate", "modulos/ordenes_compra");
        return "base";
    }

    @GetMapping("/modulos/proveedores")
    public String proveedores(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Proveedores");
        model.addAttribute("contentTemplate", "modulos/proveedores");
        return "base";
    }

    @GetMapping("/modulos/proveedores-categorias")
    public String proveedoresCategorias(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Categorias de Proveedor");
        model.addAttribute("contentTemplate", "modulos/proveedores_categorias");
        return "base";
    }

    @GetMapping("/modulos/devoluciones-proveedor")
    public String devolucionesProveedor(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Devoluciones a Proveedor");
        model.addAttribute("contentTemplate", "modulos/devoluciones_proveedor");
        return "base";
    }

    @GetMapping("/modulos/pagos-proveedor")
    public String pagosProveedor(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Pagos a Proveedor");
        model.addAttribute("contentTemplate", "modulos/pagos_proveedor");
        return "base";
    }

    @GetMapping("/modulos/cuentas-por-pagar")
    public String cuentasPorPagar(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Cuentas por Pagar");
        model.addAttribute("contentTemplate", "modulos/cuentas_por_pagar");
        return "base";
    }

    @GetMapping("/modulos/gastos-operativos")
    public String gastosOperativos(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Gastos Operativos");
        model.addAttribute("contentTemplate", "modulos/gastos_operativos");
        return "base";
    }

    @GetMapping("/modulos/gastos-recurrentes")
    public String gastosRecurrentes(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Gastos Recurrentes");
        model.addAttribute("contentTemplate", "modulos/gastos_recurrentes");
        return "base";
    }

    @GetMapping("/modulos/caja-chica")
    public String cajaChica(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Caja Chica");
        model.addAttribute("contentTemplate", "modulos/caja_chica");
        return "base";
    }

    @GetMapping("/modulos/sesiones-caja")
    public String sesionesCaja(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Sesiones de Caja");
        model.addAttribute("contentTemplate", "modulos/sesiones_caja");
        return "base";
    }

    @GetMapping("/modulos/metodos-pago")
    public String metodosPago(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Metodos de Pago");
        model.addAttribute("contentTemplate", "modulos/metodos_pago");
        return "base";
    }
}
