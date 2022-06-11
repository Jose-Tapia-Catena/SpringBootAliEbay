package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }
    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    private UsuarioService usuarioService;

    @GetMapping("/")
    public String doInit(Model model){
        model.addAttribute("usuario",new UsuarioDTO());
        return "login";
    }

    @PostMapping("/autentica/")
    public String doAutentica(Model model, HttpSession session, @ModelAttribute("usuario") UsuarioDTO usuario){
        String goTo;
        UsuarioDTO userChecked = this.usuarioService.findUserByUserNameAndPassword(usuario.getUserName(),usuario.getPassword());
        if(userChecked == null){
            model.addAttribute("error","Usuario o contraseña incorrectos");
            goTo = "login";
        }else{
            session.setAttribute("user", userChecked);
            goTo = "redirect:/" + userChecked.getTipoUsuario() + "/";
        }
        return goTo ;
    }

    @GetMapping("/logout/")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
