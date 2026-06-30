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
}
