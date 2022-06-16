package es.taw.aliebay.service;

import es.taw.aliebay.dao.CompradorRepository;
import es.taw.aliebay.dao.ProductoRepository;
import es.taw.aliebay.dto.AdministradorDTO;
import es.taw.aliebay.dto.CompradorDTO;
import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.entity.Administrador;
import es.taw.aliebay.entity.Comprador;
import es.taw.aliebay.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompradorService {
    public CompradorRepository getCompradorRepository() {
        return compradorRepository;
    }
    @Autowired
    public void setCompradorRepository(CompradorRepository compradorRepository) {
        this.compradorRepository = compradorRepository;
    }

    private CompradorRepository compradorRepository;

    public ProductoRepository getProductoRepository() {
        return productoRepository;
    }

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    private ProductoRepository productoRepository;

    public List<CompradorDTO> listarCompradores(){
        List<Comprador> compradores = compradorRepository.findAll();
        return this.listaEntityADTO(compradores);
    }

    public CompradorDTO getCompradorByID(Integer idUsuario) {
        Comprador comprador = compradorRepository.findById(idUsuario).orElse(null);
        return comprador.toDTO();
    }

    private List<CompradorDTO> listaEntityADTO (List<Comprador> lista) {
        List<CompradorDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Comprador c : lista) {
                listaDTO.add(c.toDTO());
            }
        }
        return listaDTO;
    }

    public void anyadirFavorito(Integer idUsuario, Integer idProducto) {
        Comprador comprador = compradorRepository.findById(idUsuario).orElse(null);
        Producto producto = productoRepository.findById(idProducto).orElse(null);

        List<Producto> productosFavoritos = comprador.getProductoList();
        productosFavoritos.add(producto);
        comprador.setProductoList(productosFavoritos);
        compradorRepository.save(comprador);

        List<Comprador> compradorFavoritos = producto.getCompradorList();
        compradorFavoritos.add(comprador);
        producto.setCompradorList(compradorFavoritos);
        productoRepository.save(producto);
    }

    public void borrarFavorito(Integer idUsuario, Integer idProducto) {
        Comprador comprador = compradorRepository.findById(idUsuario).orElse(null);
        Producto producto = productoRepository.findById(idProducto).orElse(null);

        List<Producto> productosFavoritos = comprador.getProductoList();
        productosFavoritos.remove(producto);
        comprador.setProductoList(productosFavoritos);
        compradorRepository.save(comprador);

        List<Comprador> compradorFavoritos = producto.getCompradorList();
        compradorFavoritos.remove(comprador);
        producto.setCompradorList(compradorFavoritos);
        productoRepository.save(producto);
    }
}
