package es.taw.aliebay.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class FotoController extends AliEbaySessionController{
    @PostMapping("/verFoto/")
    public String doVerFoto(HttpSession session,Model model,
                            @RequestParam("url") String url,
                            @RequestParam("previous") String previous){
        if(this.comprobarSesion(session)){
            model.addAttribute("url",url);
            model.addAttribute("previous", previous);
            return "verFoto";
        }else{
            return "redirect:/login/error/";
        }

    }
}
