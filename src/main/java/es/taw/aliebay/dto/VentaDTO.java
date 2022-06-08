package es.taw.aliebay.dto;

import java.util.Date;

public class VentaDTO {
    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getComprador() {
        return comprador;
    }

    public void setComprador(Integer comprador) {
        this.comprador = comprador;
    }

    private Integer idProducto;
    private String fecha;
    private Float precioVenta;
    private Integer comprador;
}
