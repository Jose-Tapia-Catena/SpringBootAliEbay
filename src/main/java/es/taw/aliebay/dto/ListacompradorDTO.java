package es.taw.aliebay.dto;

import java.util.List;

public class ListacompradorDTO {
    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CompradorDTO> getCompradorList() {
        return compradorList;
    }

    public void setCompradorList(List<CompradorDTO> compradorList) {
        this.compradorList = compradorList;
    }

    public List<MensajeDTO> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<MensajeDTO> mensajeList) {
        this.mensajeList = mensajeList;
    }

    private Integer idLista;
    private String nombre;
    private List<CompradorDTO> compradorList;
    private List<MensajeDTO> mensajeList;
}
