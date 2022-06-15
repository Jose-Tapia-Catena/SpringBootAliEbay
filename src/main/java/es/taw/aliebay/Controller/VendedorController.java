package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class VendedorController extends AliEbaySessionController{
    public ProductoService getProductoService() {
        return productoService;
    }
    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    private ProductoService productoService;

    //Productos vendedor
    @GetMapping("/vendedor/")
    public String doVerProductosVendedor(HttpSession session, Model model) {

        if (this.comprobarVendedor(session, model)) {

            UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");

            List<ProductoDTO> productos = productoService.listarProductosVendedor(user.getIdUsuario());

            List<ProductoDTO> productosVendidos = new ArrayList<>();
            List<ProductoDTO> productosNoVendidos = new ArrayList<>();
            List<ProductoDTO> productosNoVendidosTerminados = new ArrayList<>();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            for (ProductoDTO p : productos) {
                if (p.getVenta() == null) {
                    Date date = new Date();
                    try {
                        Date fin = sdf.parse(p.getFechaFin());
                        if (date.before(fin)) {
                            productosNoVendidos.add(p);
                        } else {
                            productosNoVendidosTerminados.add(p);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    productosVendidos.add(p);
                }
            }

            model.addAttribute("productosVendidos", productosVendidos);
            model.addAttribute("productosNoVendidos", productosNoVendidos);
            model.addAttribute("productosNoVendidosTerminados", productosNoVendidosTerminados);
            return "Vproductos";

        } else {
            return "redirect:/login/error/";
        }
    }
}
