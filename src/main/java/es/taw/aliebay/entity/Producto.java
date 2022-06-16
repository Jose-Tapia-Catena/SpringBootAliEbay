/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.taw.aliebay.entity;

import es.taw.aliebay.dto.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author felip
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Producto.findByTitulo", query = "SELECT p FROM Producto p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Producto.findByPrecioSalida", query = "SELECT p FROM Producto p WHERE p.precioSalida = :precioSalida"),
    @NamedQuery(name = "Producto.findByURLFoto", query = "SELECT p FROM Producto p WHERE p.uRLFoto = :uRLFoto"),
    @NamedQuery(name = "Producto.findByFechaSalida", query = "SELECT p FROM Producto p WHERE p.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "Producto.findByFechaFin", query = "SELECT p FROM Producto p WHERE p.fechaFin = :fechaFin")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproducto")
    private Integer idProducto;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "preciosalida")
    private float precioSalida;
    @Column(name = "urlfoto")
    private String uRLFoto;
    @Column(name = "fechasalida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @Column(name = "fechafin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @ManyToMany(mappedBy = "productoList")
    private List<Comprador> compradorList;
    @ManyToMany(mappedBy = "productoList")
    private List<Mensaje> mensajeList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "producto")
    private Venta venta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<Puja> pujaList;
    @JoinColumn(name = "categoria", referencedColumnName = "idcategoria")
    @ManyToOne
    private Categoria categoria;
    @JoinColumn(name = "idvendedor", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Vendedor idVendedor;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Integer idProducto, float precioSalida) {
        this.idProducto = idProducto;
        this.precioSalida = precioSalida;
    }

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

    public String getURLFoto() {
        return uRLFoto;
    }

    public void setURLFoto(String uRLFoto) {
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

    //Lista de compradores con el producto en favorito
    @XmlTransient
    public List<Comprador> getCompradorList() {
        return compradorList;
    }

    public void setCompradorList(List<Comprador> compradorList) {
        this.compradorList = compradorList;
    }

    //Lista de mensajes sobre el producto
    @XmlTransient
    public List<Mensaje> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<Mensaje> mensajeList) {
        this.mensajeList = mensajeList;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    //Lista de pujas realizadas al producto
    @XmlTransient
    public List<Puja> getPujaList() {
        return pujaList;
    }

    public void setPujaList(List<Puja> pujaList) {
        this.pujaList = pujaList;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Vendedor getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Vendedor idVendedor) {
        this.idVendedor = idVendedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "a.entity.Producto[ idProducto=" + idProducto + " ]";
    }

    public ProductoDTO toDTO() {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(this.idProducto);
        dto.setTitulo(this.titulo);
        dto.setDescripcion(this.descripcion);
        dto.setPrecioSalida(this.precioSalida);
        dto.setuRLFoto(this.uRLFoto);
        dto.setCategoria(this.categoria.getIdCategoria());
        dto.setVendedor(this.idVendedor.getIdUsuario());

        SimpleDateFormat date = new SimpleDateFormat  ("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        dto.setFechaSalidaDia(date.format(this.getFechaSalida()));
        dto.setFechaSalidaHora(time.format(this.getFechaSalida()));
        dto.setFechaFinDia(date.format(this.getFechaFin()));
        dto.setFechaFinHora(time.format(this.getFechaFin()));

        if(this.venta != null)
            dto.setVenta(this.venta.toDTO());

        List<Puja> pujas = this.getPujaList();
        if(pujas != null && !pujas.isEmpty()) {
            dto.setPuja(pujas.get(pujas.size()-1).toDTO());
        }

        List<Comprador> compradores = this.getCompradorList();
        if(compradores != null && !compradores.isEmpty()) {
            List<CompradorDTO> compradoresDTO = new ArrayList<>();
            for(Comprador c : compradores) {
                compradoresDTO.add(c.toDTO());
            }
            dto.setCompradorFavorito(compradoresDTO);
        }

        return dto;
    }
}
