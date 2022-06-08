/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.taw.aliebay.entity;

import es.taw.aliebay.dto.AdministradorDTO;
import es.taw.aliebay.dto.CompradorDTO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author felip
 */
@Entity
@Table(name = "comprador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comprador.findAll", query = "SELECT c FROM Comprador c"),
    @NamedQuery(name = "Comprador.findByIdUsuario", query = "SELECT c FROM Comprador c WHERE c.idUsuario = :idUsuario")})
public class Comprador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idUsuario;
    @JoinTable(name = "favorito", joinColumns = {
        @JoinColumn(name = "idcomprador", referencedColumnName = "idusuario")}, inverseJoinColumns = {
        @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")})
    @ManyToMany
    private List<Producto> productoList;
    @JoinTable(name = "pertenencialista", joinColumns = {
        @JoinColumn(name = "idcomprador", referencedColumnName = "idusuario")}, inverseJoinColumns = {
        @JoinColumn(name = "idlista", referencedColumnName = "idlista")})
    @ManyToMany
    private List<Listacomprador> listacompradorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComprador")
    private List<Venta> ventaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComprador")
    private List<Puja> pujaList;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public Comprador() {
    }

    public Comprador(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    //Lista de productos favoritos
    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    //Lista de listacompradores a las que pertenece
    @XmlTransient
    public List<Listacomprador> getListacompradorList() {
        return listacompradorList;
    }

    public void setListacompradorList(List<Listacomprador> listacompradorList) {
        this.listacompradorList = listacompradorList;
    }

    //Lista de sus ventas (productos comprados)
    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    //Lista de pujas realizadas
    @XmlTransient
    public List<Puja> getPujaList() {
        return pujaList;
    }

    public void setPujaList(List<Puja> pujaList) {
        this.pujaList = pujaList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comprador)) {
            return false;
        }
        Comprador other = (Comprador) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "a.entity.Comprador[ idUsuario=" + idUsuario + " ]";
    }

    public CompradorDTO toDTO() {
        CompradorDTO dto = new CompradorDTO();
        dto.setUsuario(this.getUsuario().toDTO());
        return dto;
    }
}
