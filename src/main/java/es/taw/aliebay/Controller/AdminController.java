package es.taw.aliebay.Controller;

import es.taw.aliebay.dao.AdministradorRepository;
import es.taw.aliebay.entity.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    public AdministradorRepository getAdministradorRepository() {
        return administradorRepository;
    }

    @Autowired
    public void setAdministradorRepository(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    private AdministradorRepository administradorRepository;

/*    @GetMapping("/")
    public String doInit (Model model){
        List<Administrador> administradorList = this.administradorRepository.findAll();
        model.addAttribute("admin", administradorList);
        return "usuarios";
    }*/

}
