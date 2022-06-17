package es.taw.aliebay.Controller;

import es.taw.aliebay.dto.CategoriaDTO;
import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.entity.Puja;
import es.taw.aliebay.service.CategoriaService;
import es.taw.aliebay.service.ProductoService;
import es.taw.aliebay.dao.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    private ProductoRepository productoRepository;

    private CategoriaService categoriaService;

    public CategoriaService getCategoriaService() {
        return categoriaService;
    }
    @Autowired
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    //Productos vendedor
    @GetMapping("/vendedor/")
    public String doVerProductosVendedor(HttpSession session, Model model) {

        if (this.comprobarVendedor(session)) {

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
                        SimpleDateFormat fecha = new SimpleDateFormat  ("yyyy-MM-dd HH:mm");
                        Date fin  = fecha.parse(p.getFechaSalidaDia() + " " + p.getFechaSalidaHora());
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

    @GetMapping("/vendedor/productos/crear/")
    public String doCrearProductoVendedor(HttpSession session, Model model){
        ProductoDTO p = new ProductoDTO();
        model.addAttribute("producto", p);
        List<CategoriaDTO> categorias = categoriaService.listarCategorias();
        model.addAttribute("categorias", categorias);
        return "nuevoProducto";
    }

    @PostMapping("vendedor/productos/guardar/")
    public String doGuardarProductoVendedor(HttpSession session,
                                          @ModelAttribute("producto") ProductoDTO producto) throws ParseException {
        productoService.crearProductoVendedor(producto);
        return "redirect:/vendedor/";
    }

    @GetMapping("/vendedor/productos/{idProducto}/borrar/")
    public String doBorrarProductoVendedor(HttpSession session, Model model,
                                           @PathVariable("idProducto") Integer idProducto){
        productoService.borrarProductoVendedor(idProducto);
        return "redirect:/vendedor/";
    }

    @GetMapping("/vendedor/productos/{idProducto}/editar/")
    public String doEditarProductoVendedor(HttpSession session, Model model,
                                           @PathVariable("idProducto") Integer idProducto){
        ProductoDTO p = productoService.getProductoByID(idProducto);
        Integer vendedor = p.getVendedor();
        model.addAttribute("vendedor", vendedor);
        model.addAttribute("producto", p);
        List<CategoriaDTO> categorias = categoriaService.listarCategorias();
        model.addAttribute("categorias", categorias);

        return "editarProducto";
    }

    @PostMapping("/vendedor/productos/{idProducto}/editar/guardar/")
    public String doEditarProductoVendedorGuardar(HttpSession session,
                                            @ModelAttribute("producto") ProductoDTO producto) throws ParseException {
        productoService.editarProducto(producto);
        return "redirect:/vendedor/";
    }

}
