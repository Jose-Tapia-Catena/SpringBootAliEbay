package es.taw.aliebay.service;

import es.taw.aliebay.dao.CategoriaRepository;
import es.taw.aliebay.dao.ProductoRepository;
import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.entity.Categoria;
import es.taw.aliebay.entity.Producto;
import es.taw.aliebay.entity.Usuario;
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
}
