package es.taw.aliebay.dto;

import java.util.Date;
import java.util.List;

public class MensajeDTO {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public List<Integer> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Integer> productoList) {
        this.productoList = productoList;
    }

    public ListacompradorDTO getListaComprador() {
        return listaComprador;
    }

    public void setListaComprador(ListacompradorDTO listaComprador) {
        this.listaComprador = listaComprador;
    }

    public MarketingDTO getMarketing() {
        return marketing;
    }

    public void setMarketing(MarketingDTO marketing) {
        this.marketing = marketing;
    }

    private Integer id;
    private String descripcion;
    private String date;
    private String time;
    private String asunto;
    private List<Integer> productoList;
    private ListacompradorDTO listaComprador;
    private MarketingDTO marketing;
}
