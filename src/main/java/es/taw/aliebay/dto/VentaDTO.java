package es.taw.aliebay.dto;

import java.util.Date;

public class VentaDTO {
    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Float precioVenta) {
        this.precioVenta = precioVenta;
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

    private Integer idProducto;
    private Date fecha;
    private Float precioVenta;
    private CompradorDTO comprador;
    private ProductoDTO producto;
}
