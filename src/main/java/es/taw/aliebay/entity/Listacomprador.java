/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.taw.aliebay.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author felip
 */
@Entity
@Table(name = "listacomprador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Listacomprador.findAll", query = "SELECT l FROM Listacomprador l"),
    @NamedQuery(name = "Listacomprador.findByIdLista", query = "SELECT l FROM Listacomprador l WHERE l.idLista = :idLista"),
    @NamedQuery(name = "Listacomprador.findByNombre", query = "SELECT l FROM Listacomprador l WHERE l.nombre = :nombre")})
public class Listacomprador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlista")
    private Integer idLista;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(mappedBy = "listacompradorList")
    private List<Comprador> compradorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idListaComprador")
    private List<Mensaje> mensajeList;

    public Listacomprador() {
    }

    public Listacomprador(Integer idLista) {
        this.idLista = idLista;
    }

    public Listacomprador(Integer idLista, String nombre) {
        this.idLista = idLista;
        this.nombre = nombre;
    }

    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Lisata de compradores en una lista
    @XmlTransient
    public List<Comprador> getCompradorList() {
        return compradorList;
    }

    public void setCompradorList(List<Comprador> compradorList) {
        this.compradorList = compradorList;
    }

    //Lista de mensajes enviados a la lista
    @XmlTransient
    public List<Mensaje> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<Mensaje> mensajeList) {
        this.mensajeList = mensajeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLista != null ? idLista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listacomprador)) {
            return false;
        }
        Listacomprador other = (Listacomprador) object;
        if ((this.idLista == null && other.idLista != null) || (this.idLista != null && !this.idLista.equals(other.idLista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "a.entity.Listacomprador[ idLista=" + idLista + " ]";
    }
    
}
