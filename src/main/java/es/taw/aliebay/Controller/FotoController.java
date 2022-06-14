package es.taw.aliebay.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FotoController {
    @PostMapping("/verFoto/")
    public String doVerFoto(@RequestParam("url") String url, Model model){
        model.addAttribute("url",url);
        return "verFoto";
    }
}
