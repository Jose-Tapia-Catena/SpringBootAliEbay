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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CompradorDTO getComprador() {
        return comprador;
    }

    public void setComprador(CompradorDTO comprador) {
        this.comprador = comprador;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    private Integer idPuja;
    private Float puja;
    private Date fecha;
    private CompradorDTO comprador;
    private ProductoDTO producto;
}
