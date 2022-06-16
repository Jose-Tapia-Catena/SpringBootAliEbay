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

    private String fechaSalidaDia;

    public String getFechaSalidaDia() {
        return fechaSalidaDia;
    }

    public void setFechaSalidaDia(String fechaSalidaDia) {
        this.fechaSalidaDia = fechaSalidaDia;
    }

    public String getFechaSalidaHora() {
        return fechaSalidaHora;
    }

    public void setFechaSalidaHora(String fechaSalidaHora) {
        this.fechaSalidaHora = fechaSalidaHora;
    }

    public String getFechaFinDia() {
        return fechaFinDia;
    }

    public void setFechaFinDia(String fechaFinDia) {
        this.fechaFinDia = fechaFinDia;
    }

    public String getFechaFinHora() {
        return fechaFinHora;
    }

    public void setFechaFinHora(String fechaFinHora) {
        this.fechaFinHora = fechaFinHora;
    }

    private String fechaSalidaHora;
    private String fechaFinDia;
    private String fechaFinHora;

    private VentaDTO venta;
    private String categoria;
    private Integer vendedor;
}
