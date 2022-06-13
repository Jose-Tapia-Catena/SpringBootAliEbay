package es.taw.aliebay.service;

import es.taw.aliebay.dao.CompradorRepository;
import es.taw.aliebay.dao.ListacompradorRepository;
import es.taw.aliebay.dao.MensajeRepository;
import es.taw.aliebay.dto.CompradorDTO;
import es.taw.aliebay.dto.ListacompradorDTO;
import es.taw.aliebay.entity.Comprador;
import es.taw.aliebay.entity.Listacomprador;
import es.taw.aliebay.entity.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaCompradorService {


    private ListacompradorRepository listacompradorRepository;
    private CompradorRepository compradorRepository;
    private MensajeRepository mensajeRepository;

    public ListacompradorRepository getListacompradorRepository() {
        return listacompradorRepository;
    }

    @Autowired
    public void setListacompradorRepository(ListacompradorRepository listacompradorRepository) {
        this.listacompradorRepository = listacompradorRepository;
    }

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

    public List<ListacompradorDTO> listarListaCompradores(){
        List<Listacomprador> list = listacompradorRepository.findAll();
        return this.listaEntityADTO(list);
    }

    private List<ListacompradorDTO> listaEntityADTO (List<Listacomprador> lista) {
        List<ListacompradorDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Listacomprador lc : lista) {
                listaDTO.add(lc.toDTO());
            }
        }
        return listaDTO;
    }

    public ListacompradorDTO buscarListacomprador(Integer idLista) {
        Listacomprador lc = this.listacompradorRepository.findById(idLista).orElse(null);
        return lc.toDTO();
    }

    public void guardar(ListacompradorDTO dto) {
        Listacomprador listacomprador = new Listacomprador(dto);

        //Actualizo en comprador
        List<Comprador> compradores = converListIntegerToListComprador(dto.getCompradorList());

        for (Comprador c : compradores){
            List<Listacomprador> lista = c.getListacompradorList();
            lista.add(listacomprador);
            c.setListacompradorList(lista);
            this.compradorRepository.save(c);
        }

        //Actualizo la referencia en listaComprador
        listacomprador.setCompradorList(compradores);
        listacomprador.setMensajeList(converListIntegerToListMensaje(dto.getMensajeList()));

        this.listacompradorRepository.save(listacomprador);
    }

    private List<Comprador> converListIntegerToListComprador (List<Integer> compradoresId){
        List<Comprador> result = new ArrayList<>();
        if (compradoresId != null){
            for (Integer i : compradoresId){
                result.add(this.compradorRepository.findById(i).orElse(null));
            }
        }
        return result;
    }

    private List<Mensaje> converListIntegerToListMensaje (List<Integer> mensajesId){
        List<Mensaje> result = new ArrayList<>();
        if (mensajesId != null){
            for (Integer i : mensajesId){
                result.add(this.mensajeRepository.findById(i).orElse(null));
            }
        }
        return result;
    }
}

