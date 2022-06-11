package es.taw.aliebay.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FotoController {
    @GetMapping("/verFoto/{url}/")
    public String doVerFoto(@PathVariable("url") String url, Model model){
        model.addAttribute("url",url);
        return "verFoto";
    }
}