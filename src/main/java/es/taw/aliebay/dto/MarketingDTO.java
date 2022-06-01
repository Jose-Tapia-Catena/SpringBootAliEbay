package es.taw.aliebay.dto;

import java.util.List;

public class MarketingDTO {
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public List<MensajeDTO> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<MensajeDTO> mensajeList) {
        this.mensajeList = mensajeList;
    }

    private UsuarioDTO usuario;
    private List<MensajeDTO> mensajeList;
}
