package es.taw.aliebay.service;

import es.taw.aliebay.dao.CompradorRepository;
import es.taw.aliebay.dto.AdministradorDTO;
import es.taw.aliebay.dto.CompradorDTO;
import es.taw.aliebay.entity.Administrador;
import es.taw.aliebay.entity.Comprador;
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

    public List<CompradorDTO> listarCompradores(){
        List<Comprador> compradores = compradorRepository.findAll();
        return this.listaEntityADTO(compradores);
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
}
