package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsuarioController{

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String doInit (Model model){
        List<UsuarioDTO> usuarios = this.usuarioService.listarUsuario();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

}
