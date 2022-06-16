package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.CompradorDTO;
import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.entity.Comprador;
import es.taw.aliebay.entity.Producto;
import es.taw.aliebay.service.CompradorService;
import es.taw.aliebay.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Enrique Cañadas Cobo

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

    public CompradorService getCompradorService() {
        return compradorService;
    }

    @Autowired
    public void setCompradorService(CompradorService compradorService) {
        this.compradorService = compradorService;
    }

    private CompradorService compradorService;

    // Enrique Cañadas Cobo
    @GetMapping("/comprador/")
    public String doInit (HttpSession session, Model model){
        if(this.comprobarComprador(session)){
            UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
            CompradorDTO compradorDTO = compradorService.getCompradorByID(user.getIdUsuario());

            List<ProductoDTO> productosDisponibles = productoService.listarProductosDisponibles();
            List<ProductoDTO> productosPujados = new ArrayList<>();
            List<ProductoDTO> productoNoPujados = new ArrayList<>();

            for(ProductoDTO p : productosDisponibles) {
                if(p.getPuja() != null && Objects.equals(p.getPuja().getCompador(), user.getIdUsuario())) {
                    productosPujados.add(p);
                } else {
                    productoNoPujados.add(p);
                }
            }

            model.addAttribute("compradorDTO", compradorDTO);
            model.addAttribute("productosPujados", productosPujados);
            model.addAttribute("productoNoPujados", productoNoPujados);

            return "CcompradorInicio";
        }else{
            return "redirect:/login/error/";
        }
    }

    // Enrique Cañadas Cobo
    @GetMapping("/comprador/productos/anyadir/favorito/{idProducto}/")
    public String doAnyadirFavorito (@PathVariable("idProducto") Integer idProducto, HttpSession session, Model model){
        if(this.comprobarComprador(session)){
            UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");

            compradorService.anyadirFavorito(user.getIdUsuario(), idProducto);

            return "redirect:/comprador/";
        }else{
            return "redirect:/login/error/";
        }
    }

    // Enrique Cañadas Cobo
    @GetMapping("/comprador/productos/borrar/favorito/{idProducto}/")
    public String doBorrarFavorito (@PathVariable("idProducto") Integer idProducto, HttpSession session, Model model){
        if(this.comprobarComprador(session)){
            UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");

            compradorService.borrarFavorito(user.getIdUsuario(), idProducto);

            return "redirect:/comprador/favoritos/";
        }else{
            return "redirect:/login/error/";
        }
    }

    // Enrique Cañadas Cobo
    @GetMapping("/comprador/productos/")
    public String doProductos(HttpSession session, Model model) {
        if(this.comprobarComprador(session)){
            UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
            List<ProductoDTO> productosVendidos = productoService.listarProductosComprador(user.getIdUsuario());
            model.addAttribute("productosConVentas",productosVendidos);
            return "CproductosComprador";
        } else {
            return "redirect:/login/error/";
        }
    }

    // Enrique Cañadas Cobo
    @GetMapping("/comprador/favoritos/")
    public String doFavoritos(HttpSession session, Model model) {
        if(this.comprobarComprador(session)){
            UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
            List<ProductoDTO> productosFavoritos = productoService.getProductosFavoritosByID(user.getIdUsuario());
            model.addAttribute("productosFavoritos", productosFavoritos);
            return "CcompradorFavoritos";
        } else {
            return "redirect:/login/error/";
        }
    }

    @GetMapping("/comprador/productos/borrar/{idProducto}/")
    public String doBorrarProductoComprador(HttpSession session,Model model,
                                            @PathVariable("idProducto") Integer idProducto){
        if(this.comprobarComprador(session)){
            productoService.borrarProductoComprador(idProducto);
            return "redirect:/comprador/productos/";
        }else{
            return "redirect:/login/error/";
        }
    }
}

