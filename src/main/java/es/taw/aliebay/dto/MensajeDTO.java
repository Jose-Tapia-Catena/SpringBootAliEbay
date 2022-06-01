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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public List<ProductoDTO> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<ProductoDTO> productoList) {
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
    private Date fecha;
    private String asunto;
    private List<ProductoDTO> productoList;
    private ListacompradorDTO listaComprador;
    private MarketingDTO marketing;
}
