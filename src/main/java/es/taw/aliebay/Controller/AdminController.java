package es.taw.aliebay.Controller;


import es.taw.aliebay.dto.CategoriaDTO;
import es.taw.aliebay.dto.CompradorDTO;
import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.service.CategoriaService;
import es.taw.aliebay.service.CompradorService;
import es.taw.aliebay.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    public CompradorService getCompradorService() {
        return compradorService;
    }
    @Autowired
    public void setCompradorService(CompradorService compradorService) {
        this.compradorService = compradorService;
    }
    private CompradorService compradorService;

    public CategoriaService getCategoriaService() {
        return categoriaService;
    }
    @Autowired
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    private CategoriaService categoriaService;

    public ProductoService getProductoService() {
        return productoService;
    }
    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    private ProductoService productoService;
    @GetMapping("/administrador/")
    public String doInit (Model model){
        List<CompradorDTO> compradorList = this.compradorService.listarCompradores();
        model.addAttribute("compradores", compradorList);
        model.addAttribute("vendedores", compradorList);
        model.addAttribute("marketings", compradorList);
        return "admin";
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
}
