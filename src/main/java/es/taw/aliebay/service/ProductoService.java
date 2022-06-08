package es.taw.aliebay.service;

import es.taw.aliebay.dao.CategoriaRepository;
import es.taw.aliebay.dao.CompradorRepository;
import es.taw.aliebay.dao.ProductoRepository;
import es.taw.aliebay.dao.VendedorRepository;
import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {
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
        List<Producto> productos = productoRepository.findAllCategoria(cat);
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
        List<Producto> productos = productoRepository.findAllVendedor(vendedor);
        return this.listaEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductosComprador(Integer idComprador) {
        Comprador comprador = compradorRepository.findById(idComprador).orElse(null);
        List<Producto> productos = productoRepository.findAllComprador(comprador);
        return this.listaEntityADTO(productos);
    }
}
