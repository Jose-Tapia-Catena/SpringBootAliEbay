package es.taw.aliebay.Controller;


import es.taw.aliebay.dto.CompradorDTO;
import es.taw.aliebay.service.CompradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    public CompradorService getCompradorService() {
        return compradorService;
    }
    @Autowired
    public void setCompradorService(CompradorService compradorService) {
        this.compradorService = compradorService;
    }

    private CompradorService compradorService;

    @GetMapping("/administrador/")
    public String doInit (Model model){
        List<CompradorDTO> compradorList = this.compradorService.listarCompradores();
        model.addAttribute("compradores", compradorList);
        model.addAttribute("vendedores", compradorList);
        model.addAttribute("marketings", compradorList);
        return "admin";
    }

}
