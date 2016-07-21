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
@Table(name = "via_solicitudes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViaSolicitudes.findAll", query = "SELECT v FROM ViaSolicitudes v ORDER BY v.idViaSolicitud"),
    @NamedQuery(name = "ViaSolicitudes.findByIdViaSolicitud", query = "SELECT v FROM ViaSolicitudes v WHERE v.idViaSolicitud = :idViaSolicitud"),
    @NamedQuery(name = "ViaSolicitudes.findByNombreViaSolicitud", query = "SELECT v FROM ViaSolicitudes v WHERE v.nombreViaSolicitud = :nombreViaSolicitud"),
    @NamedQuery(name = "ViaSolicitudes.findByDescripcionViaSolicitud", query = "SELECT v FROM ViaSolicitudes v WHERE v.descripcionViaSolicitud = :descripcionViaSolicitud")})
public class ViaSolicitudes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_via_solicitud")
    private Integer idViaSolicitud;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 100)
    @Column(name = "nombre_via_solicitud")
    private String nombreViaSolicitud;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 400)
    @Column(name = "descripcion_via_solicitud")
    private String descripcionViaSolicitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idViaSolicitud")
    private List<EncaRequerimientos> encaRequerimientosList;

    public ViaSolicitudes() {
    }

    public ViaSolicitudes(Integer idViaSolicitud) {
        this.idViaSolicitud = idViaSolicitud;
    }

    public ViaSolicitudes(Integer idViaSolicitud, String nombreViaSolicitud, String descripcionViaSolicitud) {
        this.idViaSolicitud = idViaSolicitud;
        this.nombreViaSolicitud = nombreViaSolicitud;
        this.descripcionViaSolicitud = descripcionViaSolicitud;
    }

    public Integer getIdViaSolicitud() {
        return idViaSolicitud;
    }

    public void setIdViaSolicitud(Integer idViaSolicitud) {
        this.idViaSolicitud = idViaSolicitud;
    }

    public String getNombreViaSolicitud() {
        return nombreViaSolicitud;
    }

    public void setNombreViaSolicitud(String nombreViaSolicitud) {
        this.nombreViaSolicitud = nombreViaSolicitud;
    }

    public String getDescripcionViaSolicitud() {
        return descripcionViaSolicitud;
    }

    public void setDescripcionViaSolicitud(String descripcionViaSolicitud) {
        this.descripcionViaSolicitud = descripcionViaSolicitud;
    }

    @XmlTransient
    public List<EncaRequerimientos> getEncaRequerimientosList() {
        return encaRequerimientosList;
    }

    public void setEncaRequerimientosList(List<EncaRequerimientos> encaRequerimientosList) {
        this.encaRequerimientosList = encaRequerimientosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idViaSolicitud != null ? idViaSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ViaSolicitudes)) {
            return false;
        }
        ViaSolicitudes other = (ViaSolicitudes) object;
        if ((this.idViaSolicitud == null && other.idViaSolicitud != null) || (this.idViaSolicitud != null && !this.idViaSolicitud.equals(other.idViaSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ViaSolicitudes[ idViaSolicitud=" + idViaSolicitud + " ]";
    }
    
}
