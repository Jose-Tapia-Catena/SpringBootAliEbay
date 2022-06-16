package es.taw.aliebay.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class FotoController extends AliEbaySessionController{
    @PostMapping("/verFoto/")
    public String doVerFoto(HttpSession session,Model model,
                            @RequestParam("url") String url){
        if(this.comprobarSesion(session)){
            model.addAttribute("url",url);
            return "verFoto";
        }else{
            return "redirect:/login/error/";
        }

    }

    @PostMapping("/verFotoComprador/")
    public String doVerFotoComprador(HttpSession session,Model model,
                                     @RequestParam("url") String url){
        if(this.comprobarSesion(session)){
            model.addAttribute("url",url);
            return "verFotoComprador";
        }else{
            return "redirect:/login/error/";
        }

    }
}
