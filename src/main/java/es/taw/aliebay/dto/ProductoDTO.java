package es.taw.aliebay.dto;

import es.taw.aliebay.entity.*;

import java.util.Date;
import java.util.List;

public class ProductoDTO {
    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecioSalida() {
        return precioSalida;
    }

    public void setPrecioSalida(float precioSalida) {
        this.precioSalida = precioSalida;
    }

    public String getuRLFoto() {
        return uRLFoto;
    }

    public void setuRLFoto(String uRLFoto) {
        this.uRLFoto = uRLFoto;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public VentaDTO getVenta() {
        return venta;
    }

    public void setVenta(VentaDTO venta) {
        this.venta = venta;
    }


    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getVendedor() {
        return vendedor;
    }

    public void setVendedor(Integer vendedor) {
        this.vendedor = vendedor;
    }

    private Integer idProducto;
    private String titulo;
    private String descripcion;
    private float precioSalida;
    private String uRLFoto;
    private String fechaSalida;
    private String fechaFin;
    private VentaDTO venta;
    private String categoria;
    private Integer vendedor;
}
