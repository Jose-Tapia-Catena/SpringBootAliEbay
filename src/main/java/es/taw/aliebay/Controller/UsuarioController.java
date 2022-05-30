package es.taw.aliebay.Controller;

import es.taw.aliebay.dao.UsuarioRepository;
import es.taw.aliebay.entity.Administrador;
import es.taw.aliebay.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsuarioController {

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String doInit (Model model){
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

}
