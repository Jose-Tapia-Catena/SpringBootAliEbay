package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String doInit(){
        return "login";
    }

    @PostMapping("/autentica")
    public String doAutentica(Model model, HttpSession session, @RequestParam("usuario") String user,
                              @RequestParam("clave") String password){
        String goTo = "admin";
        UsuarioDTO usuario = this.usuarioService.findUserByUserNameAndPassword(user,password);
        if(usuario == null){
            model.addAttribute("error","Usuario o contraseña incorrectos");
            goTo = "login";
        }
        return "admin";
    }
}
