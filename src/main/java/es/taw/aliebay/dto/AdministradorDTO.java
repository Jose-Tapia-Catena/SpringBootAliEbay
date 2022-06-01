package es.taw.aliebay.dto;

public class AdministradorDTO {
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    private UsuarioDTO usuario;
}
