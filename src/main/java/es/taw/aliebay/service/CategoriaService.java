package es.taw.aliebay.service;

import es.taw.aliebay.dao.CategoriaRepository;
import es.taw.aliebay.dto.CategoriaDTO;
import es.taw.aliebay.dto.CompradorDTO;
import es.taw.aliebay.entity.Categoria;
import es.taw.aliebay.entity.Comprador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
    public CategoriaRepository getCategoriaRepository() {
        return categoriaRepository;
    }
    @Autowired
    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> listarCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        return this.listaEntityADTO(categorias);
    }

    private List<CategoriaDTO> listaEntityADTO (List<Categoria> lista) {
        List<CategoriaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Categoria c : lista) {
                listaDTO.add(c.toDTO());
            }
        }
        return listaDTO;
    }
}
