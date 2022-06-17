package es.taw.aliebay.service;

import es.taw.aliebay.dao.*;
import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.entity.*;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    public MensajeRepository getMensajeRepository() {
        return mensajeRepository;
    }
    @Autowired
    public void setMensajeRepository(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    private MensajeRepository mensajeRepository;

    public PujaRepository getPujaRepository() {
        return pujaRepository;
    }
    @Autowired
    public void setPujaRepository(PujaRepository pujaRepository) {
        this.pujaRepository = pujaRepository;
    }

    private PujaRepository pujaRepository;


    private CompradorRepository compradorRepository;

    public ProductoDTO getProductoByID(Integer idProducto) {
        Producto producto = productoRepository.findById(idProducto).orElse(null);
        return producto.toDTO();
    }

    public List<ProductoDTO> listarProductos(){
        List<Producto> productos = productoRepository.findAll();
        return this.listaEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductosCategoria(String idCategoria){
        Categoria cat = categoriaRepository.findById(idCategoria).orElse(null);
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
        List<Producto> productos = vendedor.getProductoList();
        return this.listaEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductosComprador(Integer idComprador) {
        Comprador comprador = compradorRepository.findById(idComprador).orElse(null);
        List<Producto> productos = new ArrayList<>();
        for(Venta v:comprador.getVentaList()){
            productos.add(v.getProducto());
        }
        return this.listaEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductosDisponibles() {
        List<Producto> productos = productoRepository.findProductosDisponibles();

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

        //Borrar venta si tiene
        if(p.getVenta() != null)
            this.ventaRepository.delete(p.getVenta());

        try {
            List<Mensaje> mensajes = p.getMensajeList();
            //Para todos los mensajes que lo tenian lo quitamos
            for (Mensaje m : mensajes) {
                List<Producto> prodsMe = m.getProductoList();
                prodsMe.remove(p);
                m.setProductoList(prodsMe);
                mensajeRepository.save(m);
            }
        } catch(LazyInitializationException e) {
            
        }

        //Eliminamos todas las pujas que tenia el producto
        pujaRepository.deleteAll(p.getPujaList());

        this.productoRepository.delete(p);
    }

    public Integer borrarProductoVendedor(Integer idProducto) {
        Producto p = this.productoRepository.findById(idProducto).orElse(null);
        Integer vendedor = p.getIdVendedor().getIdUsuario();
        borrarProducto(p);
        return vendedor;
    }

    public void crearProductoVendedor(ProductoDTO producto) throws ParseException {
        Producto p = new Producto();
        p.setTitulo(producto.getTitulo());
        p.setDescripcion(producto.getDescripcion());
        p.setPrecioSalida(producto.getPrecioSalida());
        p.setURLFoto(producto.getuRLFoto());
        p.setCategoria(categoriaRepository.findById(producto.getCategoria()).orElse(null));

        SimpleDateFormat fecha = new SimpleDateFormat  ("yyyy-MM-dd HH:mm");
        p.setFechaSalida(fecha.parse(producto.getFechaSalidaDia() + " " + producto.getFechaSalidaHora()));
        p.setFechaFin(fecha.parse(producto.getFechaFinDia() + " " + producto.getFechaFinHora()));

        p.setIdVendedor(vendedorRepository.findById(producto.getVendedor()).orElse(null));

        productoRepository.save(p);
        productoRepository.flush();
    }

    public List<ProductoDTO> getProductosFavoritosByID(Integer idUsuario) {
        Comprador comprador = compradorRepository.findById(idUsuario).orElse(null);
        List<Producto> productos = comprador.getProductoList();
        return this.listaEntityADTO(productos);
    }

    public void ajustarVentas() {
        List<Producto> productosFinalizados = productoRepository.getProductosConPujaYFinalizados();
        for(Producto p : productosFinalizados) {
            if(p.getVenta() == null){
                Puja puja = maxLista(p.getPujaList());
                Comprador comprador = compradorRepository.findById(puja.getIdComprador().getIdUsuario()).orElse(null);

                Venta venta = new Venta();
                venta.setIdProducto(puja.getIdProducto().getIdProducto());
                venta.setProducto(p);
                venta.setIdComprador(comprador);
                venta.setFecha(p.getFechaFin());
                venta.setPrecioVenta(puja.getPuja());

                ventaRepository.save(venta);

                List<Venta> ventas = comprador.getVentaList();
                ventas.add(venta);
                comprador.setVentaList(ventas);

                p.setVenta(venta);
            }
        }
    }

    private Puja maxLista(List<Puja> pujas){
        Puja puja = pujas.get(0);
        for(int i=1;i< pujas.size();i++){
            if(puja.getPuja() < pujas.get(i).getPuja())
                puja = pujas.get(i);
        }

        return puja;
    }
}
