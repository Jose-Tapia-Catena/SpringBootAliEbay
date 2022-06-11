package es.taw.aliebay.service;

import es.taw.aliebay.dao.*;
import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {
    public VentaRepository getVentaRepository() {
        return ventaRepository;
    }
    @Autowired
    public void setVentaRepository(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    private VentaRepository ventaRepository;

    public ProductoRepository getProductoRepository() {
        return productoRepository;
    }
    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    private ProductoRepository productoRepository;

    private CategoriaRepository categoriaRepository;

    public CategoriaRepository getCategoriaRepository() {
        return categoriaRepository;
    }
    @Autowired
    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public VendedorRepository getVendedorRepository() {
        return vendedorRepository;
    }
    @Autowired
    public void setVendedorRepository(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    private VendedorRepository vendedorRepository;

    public CompradorRepository getCompradorRepository() {
        return compradorRepository;
    }
    @Autowired
    public void setCompradorRepository(CompradorRepository compradorRepository) {
        this.compradorRepository = compradorRepository;
    }

    private CompradorRepository compradorRepository;
    public List<ProductoDTO> listarProductos(){
        List<Producto> productos = productoRepository.findAll();
        return this.listaEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductosCategoria(String idCategoria){
        Categoria cat = categoriaRepository.findById(idCategoria).orElse(null);
        //List<Producto> productos = productoRepository.findAllCategoria(cat);
        List<Producto> productos = cat.getProductoList();
        return this.listaEntityADTO(productos);
    }

    public List<ProductoDTO> listaEntityADTO (List<Producto> lista) {
        List<ProductoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Producto p: lista) {
                listaDTO.add(p.toDTO());
            }
        }
        return listaDTO;
    }

    public List<ProductoDTO> listarProductosVendedor(Integer idVendedor) {
        Vendedor vendedor = vendedorRepository.findById(idVendedor).orElse(null);
        //List<Producto> productos = productoRepository.findAllVendedor(vendedor);
        List<Producto> productos = vendedor.getProductoList();
        return this.listaEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductosComprador(Integer idComprador) {
        Comprador comprador = compradorRepository.findById(idComprador).orElse(null);
        //List<Producto> productos = productoRepository.findAllComprador(comprador);
        List<Producto> productos = new ArrayList<>();
        for(Venta v:comprador.getVentaList()){
            productos.add(v.getProducto());
        }
        return this.listaEntityADTO(productos);
    }

    public Integer borrarProductoComprador(Integer idProducto) {
        Producto p = this.productoRepository.findById(idProducto).orElse(null);
        Integer compradorId = p.getVenta().getIdComprador().getIdUsuario();
        borrarProducto(p);
        return compradorId;
    }

    public void borrarProducto(Producto p){
        //Para todos los compradores que lo tenian en favorito lo quitamos
        for(Comprador c: p.getCompradorList()){
            List<Producto> prodsCo = c.getProductoList();
            prodsCo.remove(p);
            c.setProductoList(prodsCo);
            compradorRepository.save(c);
        }
        this.productoRepository.delete(p);
    }

    public Integer borrarProductoVendedor(Integer idProducto) {
        Producto p = this.productoRepository.findById(idProducto).orElse(null);
        Integer vendedor = p.getIdVendedor().getIdUsuario();
        borrarProducto(p);
        return vendedor;
    }
}
