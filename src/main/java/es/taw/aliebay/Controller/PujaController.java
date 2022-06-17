package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.dto.PujaDTO;
import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.service.ProductoService;
import es.taw.aliebay.service.PujaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PujaController extends AliEbaySessionController{

    public PujaService getPujaService() {
        return pujaService;
    }

    @Autowired
    public void setPujaService(PujaService pujaService) {
        this.pujaService = pujaService;
    }

    private PujaService pujaService;

    public ProductoService getProductoService() {
        return productoService;
    }

    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    private ProductoService productoService;

    // Enrique Cañadas Cobo
    @GetMapping("/comprador/productos/pujar/{idProducto}/")
    public String doPujar (@PathVariable("idProducto") Integer idProducto, HttpSession session, Model model){
        if(this.comprobarComprador(session)){
            UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
            ProductoDTO productoDTO = productoService.getProductoByID(idProducto);

            model.addAttribute("productoDTO", productoDTO);
            model.addAttribute("puja", new PujaDTO());

            return "/CcompradorPujar";
        }else{
            return "redirect:/login/error/";
        }
    }

    @PostMapping("/guardarPuja/")
    public String doAnyadirPuja(Model model, HttpSession session, @ModelAttribute("puja") PujaDTO puja){
        if(this.comprobarComprador(session)){
            UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");

            ProductoDTO p = productoService.getProductoByID(puja.getProducto());

            if(puja.getPuja() == null || puja.getPuja() < p.getPrecioSalida()) {
                model.addAttribute("productoDTO", p);
                model.addAttribute("puja", new PujaDTO());
                model.addAttribute("error", "La puja debe ser mayor que el precio de salida");
                return "/CcompradorPujar";
            } else {
                PujaDTO pu = p.getPuja();

                if(pu != null && puja.getPuja() <= pu.getPuja()) {
                    model.addAttribute("productoDTO", p);
                    model.addAttribute("puja", new PujaDTO());
                    model.addAttribute("error", "La puja debe ser mayor que la anterior puja");
                    return "/CcompradorPujar";
                } else {
                    pujaService.anyadirPuja(puja, user.getIdUsuario());
                    return "redirect:/comprador/";
                }
            }

        } else{
            return "redirect:/login/error/";
        }
    }
}
