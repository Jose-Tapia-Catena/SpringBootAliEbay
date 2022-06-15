package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CompradorController extends AliEbaySessionController{
    public ProductoService getProductoService() {
        return productoService;
    }
    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    private ProductoService productoService;

    //Listar todos los productos del comprador
    @GetMapping("/comprador/")
    public String doInit (HttpSession session, Model model){
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
        if(this.comprobarComprador(session,model)){
            List<ProductoDTO> productosVendidos = productoService.listarProductosComprador(user.getIdUsuario());
            model.addAttribute("productosConVentas",productosVendidos);
            return "CproductosComprador";
        }else{
            return "redirect:/login/error/";
        }
    }
}
