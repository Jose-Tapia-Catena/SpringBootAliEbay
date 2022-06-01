package es.taw.aliebay.Controller;

import es.taw.aliebay.dao.AdministradorRepository;
import es.taw.aliebay.entity.Administrador;
import es.taw.aliebay.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private AdministradorService administradorService;

/*    @GetMapping("/")
    public String doInit (Model model){
        List<Administrador> administradorList = this.administradorRepository.findAll();
        model.addAttribute("admin", administradorList);
        return "usuarios";
    }*/

}
