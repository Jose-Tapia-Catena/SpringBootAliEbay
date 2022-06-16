package es.taw.aliebay.service;

import es.taw.aliebay.dao.CategoriaRepository;
import es.taw.aliebay.dto.CategoriaDTO;
import es.taw.aliebay.entity.Categoria;

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

    public void guardarCategoria(String idCategoria) {
        Categoria categoria = new Categoria(idCategoria);
        this.categoriaRepository.save(categoria);
    }

    public void borrarCategoria(String idCategoria) {
        Categoria cat = this.categoriaRepository.findById(idCategoria).orElse(null);
        if(cat!= null)
            this.categoriaRepository.delete(cat);
    }
}
