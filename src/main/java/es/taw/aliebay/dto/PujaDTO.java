package es.taw.aliebay.dto;


import java.util.Date;

public class PujaDTO {
    public Integer getIdPuja() {
        return idPuja;
    }

    public void setIdPuja(Integer idPuja) {
        this.idPuja = idPuja;
    }

    public Float getPuja() {
        return puja;
    }

    public void setPuja(Float puja) {
        this.puja = puja;
    }

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    public Integer getCompador() {
        return comprador;
    }

    public void setCompador(Integer comprador) {
        this.comprador = comprador;
    }

    private Integer idPuja;
    private Float puja;
    private Integer producto;
    private Integer comprador;
}
