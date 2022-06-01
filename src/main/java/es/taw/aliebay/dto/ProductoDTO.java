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

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<CompradorDTO> getCompradorList() {
        return compradorList;
    }

    public void setCompradorList(List<CompradorDTO> compradorList) {
        this.compradorList = compradorList;
    }

    public List<MensajeDTO> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<MensajeDTO> mensajeList) {
        this.mensajeList = mensajeList;
    }

    public VentaDTO getVenta() {
        return venta;
    }

    public void setVenta(VentaDTO venta) {
        this.venta = venta;
    }

    public List<PujaDTO> getPujaList() {
        return pujaList;
    }

    public void setPujaList(List<PujaDTO> pujaList) {
        this.pujaList = pujaList;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public VendedorDTO getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorDTO vendedor) {
        this.vendedor = vendedor;
    }

    private Integer idProducto;
    private String titulo;
    private String descripcion;
    private float precioSalida;
    private String uRLFoto;
    private Date fechaSalida;
    private Date fechaFin;
    private List<CompradorDTO> compradorList;
    private List<MensajeDTO> mensajeList;
    private VentaDTO venta;
    private List<PujaDTO> pujaList;
    private CategoriaDTO categoria;
    private VendedorDTO vendedor;
}
