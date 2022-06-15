package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.UsuarioDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Controller
public class AliEbaySessionController {

    protected boolean comprobarSesion (HttpSession session, Model model) {
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
        return user != null;
    }

    protected boolean comprobarAdmin (HttpSession session, Model model) {
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
        return user != null && user.getTipoUsuario().equals("administrador");
    }

    protected boolean comprobarMarketing(HttpSession session, Model model) {
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
        return user != null && user.getTipoUsuario().equals("marketing");
    }

    protected boolean comprobarVendedor(HttpSession session, Model model) {
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
        return user != null && user.getTipoUsuario().equals("vendedor");
    }

    protected boolean comprobarComprador(HttpSession session, Model model) {
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
        return user != null && user.getTipoUsuario().equals("comprador");
    }

}
