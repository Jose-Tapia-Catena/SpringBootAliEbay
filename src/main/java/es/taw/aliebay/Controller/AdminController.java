package es.taw.aliebay.Controller;


import es.taw.aliebay.dto.CategoriaDTO;
import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController extends AliEbaySessionController{

    public CompradorService getCompradorService() {
        return compradorService;
    }
    @Autowired
    public void setCompradorService(CompradorService compradorService) {
        this.compradorService = compradorService;
    }
    private CompradorService compradorService;

    public MarketingService getMarketingService() {
        return marketingService;
    }
    @Autowired
    public void setMarketingService(MarketingService marketingService) {
        this.marketingService = marketingService;
    }

    private MarketingService marketingService;

    public VendedorService getVendedorService() {
        return vendedorService;
    }
    @Autowired
    public void setVendedorService(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    private VendedorService vendedorService;

    public CategoriaService getCategoriaService() {
        return categoriaService;
    }
    @Autowired
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    private CategoriaService categoriaService;

    public ProductoService getProductoService() { return productoService;}
    @Autowired
    public void setProductoService(ProductoService productoService) { this.productoService = productoService;}
    private ProductoService productoService;

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }
    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    private UsuarioService usuarioService;


    @GetMapping("/administrador/")
    public String doInit (HttpSession session,Model model){
        String goTo = "admin";
        if(this.comprobarAdmin(session,model)){
                model.addAttribute("compradores", this.compradorService.listarCompradores());
                model.addAttribute("vendedores", this.vendedorService.listarVendedores());
                model.addAttribute("marketings", this.marketingService.listarMarketings());
        }else{
            goTo = "redirect:/login/error/";
        }
        return goTo;
    }

    @GetMapping("/administrador/categorias/")
    public String doVerCategorias(Model model){
        List<CategoriaDTO> categoriaList = this.categoriaService.listarCategorias();
        model.addAttribute("categorias", categoriaList);
        return "categorias";
    }

    @GetMapping("/administrador/categorias/nuevo/")
    public String doCrearCategoria(Model model){
        model.addAttribute("categoria", new CategoriaDTO());
        return "nuevaCategoria";
    }

    @PostMapping("/administrador/categorias/guardar/")
    public String doGuardarCategoria(@ModelAttribute("categoria") CategoriaDTO categoria, Model model){
        categoriaService.guardarCategoria(categoria.getIdCategoria());
        return "redirect:/administrador/categorias/";
    }

    @GetMapping("/administrador/categorias/borrar/{idCategoria}/")
    public String doBorrarCategoria(@PathVariable("idCategoria") String idCategoria, Model model){
        categoriaService.borrarCategoria(idCategoria);
        return "redirect:/administrador/categorias/";
    }

    @GetMapping("/administrador/categorias/{idCategoria}/productos/")
    public String doVerProductosCategoria(@PathVariable("idCategoria") String idCategoria, Model model){

        List<ProductoDTO> productos = productoService.listarProductosCategoria(idCategoria);

        List<ProductoDTO> productosVendidos = new ArrayList<>();
        List<ProductoDTO> productosNoVendidos  = new ArrayList<>();
        List<ProductoDTO> productosNoVendidosTerminados  = new ArrayList<>();

        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        for(ProductoDTO p:productos){
            if(p.getVenta() == null){
                Date date = new Date();
                try{
                    Date fin = sdf.parse(p.getFechaFin());
                    if(date.before(fin)){
                        productosNoVendidos.add(p);
                    }else{
                        productosNoVendidosTerminados.add(p);
                    }
                }catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                productosVendidos.add(p);
            }
        }

        model.addAttribute("productosVendidos", productosVendidos);
        model.addAttribute("productosNoVendidos", productosNoVendidos);
        model.addAttribute("productosNoVendidosTerminados", productosNoVendidosTerminados);
        model.addAttribute("categoria",idCategoria);

        return "productos";
    }

    @GetMapping("/administrador/comprador/{idComprador}/productos/")
    public String doVerProductosComprador(@PathVariable("idComprador") Integer idComprador, Model model){


        List<ProductoDTO> productosVendidos = productoService.listarProductosComprador(idComprador);
        model.addAttribute("productosConVentas",productosVendidos);
        return "productosComprador";
    }

    @GetMapping("/administrador/comprador/productos/{idProducto}/borrar/")
    public String doBorrarProductoComprador(@PathVariable("idProducto") Integer idProducto, Model model){
        Integer comprador = productoService.borrarProductoComprador(idProducto);

        return "redirect:/administrador/comprador/"+comprador+ "/productos/";
    }

    @GetMapping("/administrador/vendedor/productos/{idProducto}/borrar/")
    public String doBorrarProductoVendedor(@PathVariable("idProducto") Integer idProducto, Model model){
        Integer vendedor = productoService.borrarProductoVendedor(idProducto);

        return "redirect:/administrador/vendedor/"+vendedor+"/productos/";
    }


    @GetMapping("/administrador/vendedor/{idVendedor}/productos/")
    public String doVerProductosVendedor(@PathVariable("idVendedor") Integer idVendedor, Model model){

        List<ProductoDTO> productos = productoService.listarProductosVendedor(idVendedor);

        List<ProductoDTO> productosVendidos = new ArrayList<>();
        List<ProductoDTO> productosNoVendidos  = new ArrayList<>();
        List<ProductoDTO> productosNoVendidosTerminados  = new ArrayList<>();

        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        for(ProductoDTO p:productos){
            if(p.getVenta() == null){
                Date date = new Date();
                try{
                    Date fin = sdf.parse(p.getFechaFin());
                    if(date.before(fin)){
                        productosNoVendidos.add(p);
                    }else{
                        productosNoVendidosTerminados.add(p);
                    }
                }catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                productosVendidos.add(p);
            }
        }

        model.addAttribute("productosVendidos", productosVendidos);
        model.addAttribute("productosNoVendidos", productosNoVendidos);
        model.addAttribute("productosNoVendidosTerminados", productosNoVendidosTerminados);
        model.addAttribute("vendedor",idVendedor);

        return "productos";
    }

    @GetMapping("/administrador/usuario/{tipoUsuario}/crear/")
    public String doCrearUsuario(@PathVariable("tipoUsuario") String tipoUsuario, Model model){
        UsuarioDTO u = new UsuarioDTO();
        u.setTipoUsuario(tipoUsuario);
        model.addAttribute("usuario", u);
        return "nuevoUsuario";
    }

    @GetMapping("/administrador/usuario/crear/")
    public String doCrearUsuario( Model model){
        UsuarioDTO u = new UsuarioDTO();
        model.addAttribute("usuario", u);
        return "nuevoUsuario";
    }

    @GetMapping("/administrador/usuario/{idUsuario}/editar/")
    public String doEditarUsuario(@PathVariable("idUsuario") Integer idUsuario, Model model){
        UsuarioDTO u = usuarioService.buscarUsuario(idUsuario);
        model.addAttribute("usuario", u);
        return "nuevoUsuario";
    }


    @PostMapping("/administrador/usuario/guardar/")
    public String doGuardarUsuario(@ModelAttribute("usuario") UsuarioDTO usuario, Model model){
        if(usuario.getIdUsuario() != null){
            usuarioService.modificarUsuario(usuario);
        }else{
            usuarioService.crearUsuario(usuario);
        }

        if(usuario.isLogin())
            return "redirect:/";
        else
            return "redirect:/administrador/";
    }

    @GetMapping("/administrador/usuario/borrar/{idUsuario}")
    public String doBorrarUsuario(@PathVariable("idUsuario") Integer idUsuario){
        usuarioService.borrarUsuario(idUsuario);
        return "redirect:/administrador/";
    }
}
