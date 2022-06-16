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


    //Listar todos los usuarios
    @GetMapping("/administrador/")
    public String doInit (HttpSession session,Model model){
        if(this.comprobarAdmin(session)){
            model.addAttribute("compradores", this.compradorService.listarCompradores());
            model.addAttribute("vendedores", this.vendedorService.listarVendedores());
            model.addAttribute("marketings", this.marketingService.listarMarketings());
            return "admin";
        }else{
            return "redirect:/login/error/";
        }
    }


    //Categorias
    @GetMapping("/administrador/categorias/")
    public String doVerCategorias(HttpSession session,Model model){
        if(this.comprobarAdmin(session)){
            List<CategoriaDTO> categoriaList = this.categoriaService.listarCategorias();
            model.addAttribute("categorias", categoriaList);
            return "categorias";
        }else{
            return "redirect:/login/error/";
        }
    }


    @GetMapping("/administrador/categorias/nuevo/")
    public String doCrearCategoria(HttpSession session,Model model){
        if(this.comprobarAdmin(session)){
            model.addAttribute("categoria", new CategoriaDTO());
            return "nuevaCategoria";
        }else{
            return "redirect:/login/error/";
        }
    }


    @PostMapping("/administrador/categorias/guardar/")
    public String doGuardarCategoria(HttpSession session,
                                     @ModelAttribute("categoria") CategoriaDTO categoria){
        if(this.comprobarAdmin(session)){
            categoriaService.guardarCategoria(categoria.getIdCategoria());
            return "redirect:/administrador/categorias/";
        }else{
            return "redirect:/login/error/";
        }
    }


    @GetMapping("/administrador/categorias/borrar/{idCategoria}/")
    public String doBorrarCategoria(HttpSession session,
                                    @PathVariable("idCategoria") String idCategoria){
        if(this.comprobarAdmin(session)){
            categoriaService.borrarCategoria(idCategoria);
            return "redirect:/administrador/categorias/";

        }else{
            return "redirect:/login/error/";
        }
    }


    @GetMapping("/administrador/categorias/{idCategoria}/productos/")
    public String doVerProductosCategoria(HttpSession session, Model model,
                                          @PathVariable("idCategoria") String idCategoria){
        if(this.comprobarAdmin(session)){
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
        }else{
            return "redirect:/login/error/";
        }
    }


    //Productos comprador
    @GetMapping("/administrador/comprador/{idComprador}/productos/")
    public String doVerProductosComprador(HttpSession session, Model model,
                                          @PathVariable("idComprador") Integer idComprador){
        if(this.comprobarAdmin(session)){
            List<ProductoDTO> productosVendidos = productoService.listarProductosComprador(idComprador);
            model.addAttribute("productosConVentas",productosVendidos);
            model.addAttribute("comprador", idComprador);
            return "productosComprador";
        }else{
            return "redirect:/login/error/";
        }
    }


    @GetMapping("/administrador/comprador/productos/{idProducto}/borrar/")
    public String doBorrarProductoComprador(HttpSession session,
                                            @PathVariable("idProducto") Integer idProducto){
        if(this.comprobarAdmin(session)){
            Integer comprador = productoService.borrarProductoComprador(idProducto);
            return "redirect:/administrador/comprador/"+comprador+ "/productos/";
        }else{
            return "redirect:/login/error/";
        }
    }


    //Productos vendedor
    @GetMapping("/administrador/vendedor/{idVendedor}/productos/")
    public String doVerProductosVendedor(HttpSession session, Model model,
                                         @PathVariable("idVendedor") Integer idVendedor){
        if(this.comprobarAdmin(session)){
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

        }else{
            return "redirect:/login/error/";
        }
    }


    @GetMapping("/administrador/vendedor/productos/{idProducto}/borrar/")
    public String doBorrarProductoVendedor(HttpSession session,
                                           @PathVariable("idProducto") Integer idProducto){
        if(this.comprobarAdmin(session)){
            Integer vendedor = productoService.borrarProductoVendedor(idProducto);
            return "redirect:/administrador/vendedor/"+vendedor+"/productos/";
        }else{
            return "redirect:/login/error/";
        }
    }


    //Administrar usuarios
    @GetMapping("/administrador/usuario/{tipoUsuario}/crear/")
    public String doCrearUsuario(HttpSession session, Model model,
                                 @PathVariable("tipoUsuario") String tipoUsuario){
        if(this.comprobarAdmin(session)){
            UsuarioDTO u = new UsuarioDTO();
            u.setTipoUsuario(tipoUsuario);
            model.addAttribute("usuario", u);
            return "nuevoUsuario";
        }else{
            return "redirect:/login/error/";
        }
    }


    @GetMapping("/administrador/usuario/{idUsuario}/editar/")
    public String doEditarUsuario(HttpSession session, Model model,
                                  @PathVariable("idUsuario") Integer idUsuario){
        if(this.comprobarAdmin(session)){
            UsuarioDTO u = usuarioService.buscarUsuario(idUsuario);
            model.addAttribute("usuario", u);
            return "nuevoUsuario";
        }else{
            return "redirect:/login/error/";
        }
    }


    @PostMapping("/administrador/usuario/guardar/")
    public String doGuardarUsuario(HttpSession session,
                                   @ModelAttribute("usuario") UsuarioDTO usuario){
        if(this.comprobarAdmin(session)){
            if(usuario.getIdUsuario() != null){
                usuarioService.modificarUsuario(usuario);
            }else{
                usuarioService.crearUsuario(usuario);
            }

            return "redirect:/administrador/";

        }else{
            return "redirect:/login/error/";
        }
    }


    @GetMapping("/administrador/usuario/borrar/{idUsuario}")
    public String doBorrarUsuario(HttpSession session,
                                  @PathVariable("idUsuario") Integer idUsuario){
        if(this.comprobarAdmin(session)){
            usuarioService.borrarUsuario(idUsuario);
            return "redirect:/administrador/";
        }else{
            return "redirect:/login/error/";
        }
    }


}
