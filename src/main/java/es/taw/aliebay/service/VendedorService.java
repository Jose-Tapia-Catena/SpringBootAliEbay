package es.taw.aliebay.service;

import es.taw.aliebay.dao.CompradorRepository;
import es.taw.aliebay.dao.VendedorRepository;
import es.taw.aliebay.dto.CompradorDTO;
import es.taw.aliebay.dto.VendedorDTO;
import es.taw.aliebay.entity.Comprador;
import es.taw.aliebay.entity.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VendedorService {
    public VendedorRepository getVendedorRepository() {
        return vendedorRepository;
    }
    @Autowired
    public void setVendedorRepository(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    private VendedorRepository vendedorRepository;

    public List<VendedorDTO> listarVendedores(){
        List<Vendedor> vendedores = vendedorRepository.findAll();
        return this.listaEntityADTO(vendedores);
    }

    private List<VendedorDTO> listaEntityADTO (List<Vendedor> lista) {
        List<VendedorDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Vendedor v : lista) {
                listaDTO.add(v.toDTO());
            }
        }
        return listaDTO;
    }
}
