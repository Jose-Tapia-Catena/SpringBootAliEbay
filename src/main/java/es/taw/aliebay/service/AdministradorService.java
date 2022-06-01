package es.taw.aliebay.service;


import es.taw.aliebay.dao.AdministradorRepository;
import es.taw.aliebay.dto.AdministradorDTO;
import es.taw.aliebay.entity.Administrador;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AdministradorService {
    public AdministradorRepository getAdministradorRepository() {
        return administradorRepository;
    }

    @Autowired
    public void setAdministradorRepository(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    private AdministradorRepository administradorRepository;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<AdministradorDTO> listarAdministrador(){
        List<Administrador> administradores = administradorRepository.findAll();
        return this.listaEntityADTO(administradores);
    }

    private List<AdministradorDTO> listaEntityADTO (List<Administrador> lista) {
        List<AdministradorDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Administrador a : lista) {
                listaDTO.add(a.toDTO());
            }
        }
        return listaDTO;
    }
}
