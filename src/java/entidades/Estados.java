/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario Desarrollo
 */
@Entity
@Table(name = "estados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estados.findAll", query = "SELECT e FROM Estados e ORDER BY e.idEstado"),
    @NamedQuery(name = "Estados.findMaxId", query = "SELECT MAX(e.idEstado) FROM Estados e"),
    @NamedQuery(name = "Estados.findByIdEstado", query = "SELECT e FROM Estados e WHERE e.idEstado = :idEstado"),
    @NamedQuery(name = "Estados.findByNombreEstado", query = "SELECT e FROM Estados e WHERE e.nombreEstado = :nombreEstado"),
    @NamedQuery(name = "Estados.findByDescripcionEstado", query = "SELECT e FROM Estados e WHERE e.descripcionEstado = :descripcionEstado")})
public class Estados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado")
    private Integer idEstado;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 50)
    @Column(name = "nombre_estado")
    private String nombreEstado;
//    @Size(max = 400)
    @Column(name = "descripcion_estado")
    private String descripcionEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado")
    private List<EncaRequerimientos> encaRequerimientosList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado")
//    private List<Usuarios> usuariosList;

    public Estados() {
    }

    public Estados(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Estados(Integer idEstado, String nombreEstado) {
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    @XmlTransient
    public List<EncaRequerimientos> getEncaRequerimientosList() {
        return encaRequerimientosList;
    }

    public void setEncaRequerimientosList(List<EncaRequerimientos> encaRequerimientosList) {
        this.encaRequerimientosList = encaRequerimientosList;
    }

//    @XmlTransient
//    public List<Usuarios> getUsuariosList() {
//        return usuariosList;
//    }
//
//    public void setUsuariosList(List<Usuarios> usuariosList) {
//        this.usuariosList = usuariosList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estados)) {
            return false;
        }
        Estados other = (Estados) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Estados[ idEstado=" + idEstado + " ]";
    }
    
}
