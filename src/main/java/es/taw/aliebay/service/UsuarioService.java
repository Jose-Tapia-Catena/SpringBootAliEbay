package es.taw.aliebay.service;

import es.taw.aliebay.dao.UsuarioRepository;
import es.taw.aliebay.dto.UsuarioDTO;
import es.taw.aliebay.entity.Usuario;
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
}
