package es.taw.aliebay.service;

import es.taw.aliebay.dao.*;
import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private UsuarioRepository usuarioRepository;

    private CompradorRepository compradorRepository;
    private VendedorRepository vendedorRepository;

    public CompradorRepository getCompradorRepository() {
        return compradorRepository;
    }
    @Autowired
    public void setCompradorRepository(CompradorRepository compradorRepository) {
        this.compradorRepository = compradorRepository;
    }

    public VendedorRepository getVendedorRepository() {
        return vendedorRepository;
    }
    @Autowired
    public void setVendedorRepository(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public MarketingRepository getMarketingRepository() {
        return marketingRepository;
    }
    @Autowired
    public void setMarketingRepository(MarketingRepository marketingRepository) {
        this.marketingRepository = marketingRepository;
    }

    private MarketingRepository marketingRepository;

    public ProductoRepository getProductoRepository() {
        return productoRepository;
    }

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    private ProductoRepository productoRepository;

    public VentaRepository getVentaRepository() {
        return ventaRepository;
    }

    @Autowired
    public void setVentaRepository(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    private VentaRepository ventaRepository;

    public List<UsuarioDTO> listarUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        return this.listaEntityADTO(usuarios);
    }

    public List<UsuarioDTO> listaEntityADTO (List<Usuario> lista) {
        List<UsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Usuario u : lista) {
                listaDTO.add(u.toDTO());
            }
        }
        return listaDTO;
    }

    public UsuarioDTO findUserByUserNameAndPassword(String userName,String password){
        Usuario usuario = this.usuarioRepository.findUsuarioByUserAndPassword(userName,password);
        return usuario==null?null:usuario.toDTO();
    }

    public void crearUsuario(UsuarioDTO usuario) {
        Usuario user = new Usuario();
        user.setNombre(usuario.getNombre());
        user.setApellidos(usuario.getApellidos());
        user.setUserName(usuario.getUserName());
        user.setDomicilio(usuario.getDomicilio());
        user.setCiudadResidencia(usuario.getCiudadResidencia());
        user.setEdad(usuario.getEdad());
        user.setSexo(usuario.getSexo());
        user.setPassword(usuario.getPassword());

        usuarioRepository.save(user);
        usuarioRepository.flush();

        switch (usuario.getTipoUsuario()) {
            case "comprador":
                Comprador comprador = new Comprador(user.getIdUsuario());
                comprador.setUsuario(user);
                user.setComprador(comprador);
                usuarioRepository.save(user);
                break;
            case "vendedor":
                Vendedor vendedor = new Vendedor(user.getIdUsuario());
                vendedor.setUsuario(user);
                user.setVendedor(vendedor);
                usuarioRepository.save(user);
                break;
            case "marketing":
                Marketing marketing = new Marketing(user.getIdUsuario());
                user.setMarketing(marketing);
                marketing.setUsuario(user);
                usuarioRepository.save(user);
                break;
        }
    }

    public void modificarUsuario(UsuarioDTO usuario) {
        Usuario user = new Usuario();
        user.setIdUsuario(usuario.getIdUsuario());
        user.setNombre(usuario.getNombre());
        user.setApellidos(usuario.getApellidos());
        user.setUserName(usuario.getUserName());
        user.setDomicilio(usuario.getDomicilio());
        user.setCiudadResidencia(usuario.getCiudadResidencia());
        user.setEdad(usuario.getEdad());
        user.setSexo(usuario.getSexo());
        user.setPassword(usuario.getPassword());

        usuarioRepository.save(user);
    }
    public void borrarUsuario(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        usuarioRepository.delete(usuario);
    }

    public UsuarioDTO buscarUsuario(Integer idUsuario) {
        Usuario u = usuarioRepository.findById(idUsuario).orElse(null);
        return u.toDTO();
    }

    public void ajustarVentas() {
        List<Producto> productosFinalizados = productoRepository.getProductosConPujaYFinalizados();
        for(Producto p : productosFinalizados) {
            /*
            Puja puja = p.getPujaList().get(p.getPujaList().size() - 1);
            Comprador comprador = compradorRepository.findById(puja.getIdComprador().getIdUsuario()).orElse(null);

            Venta venta = new Venta();
            venta.setIdProducto(puja.getIdProducto().getIdProducto());
            venta.setIdComprador(comprador);
            venta.setProducto(puja.getIdProducto());
            venta.setFecha(p.getFechaFin());
            venta.setPrecioVenta(puja.getPuja());
            ventaRepository.save(venta);
             */
        }
    }
}
