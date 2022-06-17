package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.service.ProductoService;
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


    public ProductoService getProductoService() {
        return productoService;
    }
    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    private ProductoService productoService;

    @GetMapping("/")
    public String doInit(Model model){
        productoService.ajustarVentas();
        model.addAttribute("usuario",new UsuarioDTO());
        return "login";
    }

    @GetMapping("/login/error/")
    public String doInitError(Model model){
        model.addAttribute("usuario",new UsuarioDTO());
        model.addAttribute("error","Acceso Denegado");
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

    @GetMapping("/usuario/crear/")
    public String doCrearUsuario(HttpSession session,Model model){
            UsuarioDTO u = new UsuarioDTO();
            model.addAttribute("usuario", u);
            return "nuevoUsuario";
    }

    @PostMapping("/usuario/guardar/")
    public String doGuardarUsuario(HttpSession session, Model model,
                                   @ModelAttribute("usuario") UsuarioDTO usuario){
        usuarioService.crearUsuario(usuario);
        return "redirect:/";
    }

}
