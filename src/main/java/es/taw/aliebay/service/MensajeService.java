package es.taw.aliebay.service;

import es.taw.aliebay.dao.ListacompradorRepository;
import es.taw.aliebay.dao.MarketingRepository;
import es.taw.aliebay.dao.MensajeRepository;
import es.taw.aliebay.dao.ProductoRepository;
import es.taw.aliebay.dto.MensajeDTO;
import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private MarketingRepository marketingRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ListacompradorRepository listacompradorRepository;

    public List<MensajeDTO> listarMensajesByIdListaAndIdMarketing(Integer idLista, Integer idMarketing){
        List<Mensaje> mensajes = this.mensajeRepository.findByIdListaAndIdMarketing(idLista, idMarketing);
        return listaEntityADTO(mensajes);
    }


    public List<MensajeDTO> listarMensajesByComprador(List<Integer> idComprador) {
        List<Mensaje> mensajes = this.mensajeRepository.findByIdComprador(idComprador);
        return listaEntityADTO(mensajes);
    }

    private List<MensajeDTO> listaEntityADTO (List<Mensaje> lista) {
        List<MensajeDTO> MensajeDTO = null;
        if (lista != null) {
            MensajeDTO = new ArrayList<>();
            for (Mensaje m : lista) {
                MensajeDTO.add(m.toDTO());
            }
        }
        return MensajeDTO;
    }


    public MensajeDTO buscarMensaje(Integer idMensaje) {
        Mensaje mensaje = this.mensajeRepository.findById(idMensaje).orElse(null);
        MensajeDTO dto = mensaje.toDTO();
        List<Producto> productos = mensaje.getProductoList();
        List<ProductoDTO> productoDTOS = this.productoService.listaEntityADTO(productos);
        dto.setProductoList(toIntegerList(productoDTOS));
        return dto;
    }

    private List<Integer> toIntegerList (List<ProductoDTO> productoDTOS){
        List<Integer> lista = null;
        if (productoDTOS != null) {
            lista = new ArrayList<>();
            for (ProductoDTO p : productoDTOS) {
                lista.add(p.getIdProducto());
            }
        }
        return lista;
    }

    public void modificarMensaje(MensajeDTO dto) throws ParseException {
        Mensaje mensaje = this.mensajeRepository.findById(dto.getId()).orElse(null);
        mensaje.setDescripcion(dto.getDescripcion());
        mensaje.setAsunto(dto.getAsunto());

        SimpleDateFormat fecha = new SimpleDateFormat  ("yyyy-MM-dd HH:mm");
        mensaje.setFecha(fecha.parse(dto.getDate() + " " + dto.getTime()));
        List<Producto> productos = new ArrayList<>();

        for (Producto p : productoRepository.findAll()){
            List<Mensaje> mensajes = p.getMensajeList();
            //Si no ha sido seleccionado
            if (!dto.getProductoList().contains(p.getIdProducto())){
                //Antes estaba seleccionado
                if (mensajes.contains(mensaje)){
                    mensajes.remove(mensaje);
                    p.setMensajeList(mensajes);
                    productoRepository.save(p);
                }
            } else{
                //Si ha sido seleccionado
                productos.add(p);
                if (!mensajes.contains(mensaje)){
                    mensajes.add(mensaje);
                    p.setMensajeList(mensajes);
                    this.productoRepository.save(p);
                }
            }
        }

        mensaje.setProductoList(productos);
        mensajeRepository.save(mensaje);
    }

    public void  crearMensaje(MensajeDTO dto) {
        Mensaje mensaje = new Mensaje();
        mensaje.setDescripcion(dto.getDescripcion());
        mensaje.setAsunto(dto.getAsunto());

        SimpleDateFormat fecha = new SimpleDateFormat  ("yyyy-MM-dd HH:mm");
        try {
            mensaje.setFecha(fecha.parse(dto.getDate() + " " + dto.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Marketing marketing =  this.marketingRepository.findById(dto.getMarketing().getUsuario().getIdUsuario()).orElse(null);
        mensaje.setIdMarketing(marketing);
        mensaje.setIdListaComprador(this.listacompradorRepository.findById(dto.getListaComprador().getIdLista()).orElse(null));

        List<Producto> productos = new ArrayList<>();

        for(Integer i:dto.getProductoList()){
            Producto p = this.productoRepository.findById(i).orElse(null);
            productos.add(p);
        }

        mensaje.setProductoList(productos);

        mensajeRepository.save(mensaje);

        for(Producto p : mensaje.getProductoList()){
            List<Mensaje> mensajes = p.getMensajeList();
            mensajes.add(mensaje);
            p.setMensajeList(mensajes);

            productoRepository.save(p);
        }
    }

    public void borrarMensaje(Integer idMensaje) {
        Mensaje m = this.mensajeRepository.findById(idMensaje).orElse(null);
        for (Producto p : m.getProductoList()){
            List<Mensaje> mensajes = p.getMensajeList();
            mensajes.remove(m);
            p.setMensajeList(mensajes);
            this.productoRepository.save(p);
        }

        Listacomprador lc = m.getIdListaComprador();
        List<Mensaje> mensajes = lc.getMensajeList();
        mensajes.remove(m);
        lc.setMensajeList(mensajes);
        this.listacompradorRepository.save(lc);

        mensajeRepository.delete(m);
    }
}
