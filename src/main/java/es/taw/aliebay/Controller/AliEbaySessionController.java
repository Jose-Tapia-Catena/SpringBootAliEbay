package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.UsuarioDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Controller
public class AliEbaySessionController {

    protected boolean comprobarSesion (HttpSession session) {
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
        return user != null;
    }

    protected boolean comprobarAdmin (HttpSession session) {
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
        return user != null && user.getTipoUsuario().equals("administrador");
    }

    protected boolean comprobarMarketing(HttpSession session) {
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
        return user != null && user.getTipoUsuario().equals("marketing");
    }

    protected boolean comprobarVendedor(HttpSession session) {
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
        return user != null && user.getTipoUsuario().equals("vendedor");
    }

    protected boolean comprobarComprador(HttpSession session) {
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
        return user != null && user.getTipoUsuario().equals("comprador");
    }

}
