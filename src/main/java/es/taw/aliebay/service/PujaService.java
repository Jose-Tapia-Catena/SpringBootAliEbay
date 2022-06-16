package es.taw.aliebay.service;

import es.taw.aliebay.dao.CompradorRepository;
import es.taw.aliebay.dao.ProductoRepository;
import es.taw.aliebay.dao.PujaRepository;
import es.taw.aliebay.dto.PujaDTO;
import es.taw.aliebay.entity.Comprador;
import es.taw.aliebay.entity.Producto;
import es.taw.aliebay.entity.Puja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PujaService {
    public PujaRepository getPujaRepository() {
        return pujaRepository;
    }

    @Autowired
    public void setPujaRepository(PujaRepository pujaRepository) {
        this.pujaRepository = pujaRepository;
    }

    private PujaRepository pujaRepository;

    public ProductoRepository getProductoRepository() {
        return productoRepository;
    }

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    private ProductoRepository productoRepository;

    public CompradorRepository getCompradorRepository() {
        return compradorRepository;
    }

    @Autowired
    public void setCompradorRepository(CompradorRepository compradorRepository) {
        this.compradorRepository = compradorRepository;
    }

    private CompradorRepository compradorRepository;


    public void anyadirPuja(PujaDTO puja, Integer idUsuario) {
        Puja nuevaPuja = new Puja();

        nuevaPuja.setPuja(puja.getPuja());

        Producto producto = productoRepository.findById(puja.getProducto()).orElse(null);
        nuevaPuja.setIdProducto(producto);

        nuevaPuja.setFecha(new Date());

        Comprador comprador = compradorRepository.findById(idUsuario).orElse(null);
        nuevaPuja.setIdComprador(comprador);

        pujaRepository.save(nuevaPuja);

    }
}
