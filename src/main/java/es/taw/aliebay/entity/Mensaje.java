/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.taw.aliebay.entity;

import es.taw.aliebay.dto.MensajeDTO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "mensaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m"),
    @NamedQuery(name = "Mensaje.findById", query = "SELECT m FROM Mensaje m WHERE m.id = :id"),
    @NamedQuery(name = "Mensaje.findByDescripcion", query = "SELECT m FROM Mensaje m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Mensaje.findByFecha", query = "SELECT m FROM Mensaje m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Mensaje.findByAsunto", query = "SELECT m FROM Mensaje m WHERE m.asunto = :asunto")})
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "asunto")
    private String asunto;
    @JoinTable(name = "productosmensaje", joinColumns = {
        @JoinColumn(name = "idmensaje", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")})
    @ManyToMany
    private List<Producto> productoList;
    @JoinColumn(name = "idlistacomprador", referencedColumnName = "idlista")
    @ManyToOne(optional = false)
    private Listacomprador idListaComprador;
    @JoinColumn(name = "idmarketing", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Marketing idMarketing;

    public Mensaje() {
    }

    public Mensaje(Integer id) {
        this.id = id;
    }

    public Mensaje(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

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

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public Listacomprador getIdListaComprador() {
        return idListaComprador;
    }

    public void setIdListaComprador(Listacomprador idListaComprador) {
        this.idListaComprador = idListaComprador;
    }

    public Marketing getIdMarketing() {
        return idMarketing;
    }

    public void setIdMarketing(Marketing idMarketing) {
        this.idMarketing = idMarketing;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "a.entity.Mensaje[ id=" + id + " ]";
    }

    public MensajeDTO toDTO() {
        MensajeDTO dto = new MensajeDTO();
        dto.setId(this.id);
        dto.setAsunto(this.asunto);
        dto.setDescripcion(this.descripcion);

        SimpleDateFormat date = new SimpleDateFormat  ("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        dto.setDate(date.format(this.getFecha()));
        dto.setTime(time.format(this.getFecha()));
        dto.setMarketing(this.idMarketing.toDTO());
        dto.setListaComprador(this.idListaComprador.toDTO());

        List<Integer> idProductos = new ArrayList<>();
        if (this.productoList != null){
            for (Producto p : productoList){
                idProductos.add(p.getIdProducto());
            }
        }
        dto.setProductoList(idProductos);

        return dto;
    }
}
