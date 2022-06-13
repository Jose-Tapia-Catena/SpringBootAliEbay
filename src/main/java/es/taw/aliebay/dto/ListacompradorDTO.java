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

    public List<Integer> getCompradorList() {
        return compradorList;
    }

    public void setCompradorList(List<Integer> compradorList) {
        this.compradorList = compradorList;
    }

    public List<Integer> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<Integer> mensajeList) {
        this.mensajeList = mensajeList;
    }

    private Integer idLista;
    private String nombre;
    private List<Integer> compradorList;
    private List<Integer> mensajeList;
}
