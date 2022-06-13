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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
    private String fecha;
    private String asunto;
    private List<Integer> productoList;
    private ListacompradorDTO listaComprador;
    private MarketingDTO marketing;
}
