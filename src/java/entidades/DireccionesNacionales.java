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
@Table(name = "direcciones_nacionales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DireccionesNacionales.findAll", query = "SELECT d FROM DireccionesNacionales d ORDER BY d.idDirNacional"),
    @NamedQuery(name = "DireccionesNacionales.findIdMax", query = "SELECT MAX(d.idDirNacional) FROM DireccionesNacionales d"),
    @NamedQuery(name = "DireccionesNacionales.findByIdDirNacional", query = "SELECT d FROM DireccionesNacionales d WHERE d.idDirNacional = :idDirNacional"),
    @NamedQuery(name = "DireccionesNacionales.findByNombreDirNacional", query = "SELECT d FROM DireccionesNacionales d WHERE d.nombreDirNacional = :nombreDirNacional"),
    @NamedQuery(name = "DireccionesNacionales.findByDirectorNacional", query = "SELECT d FROM DireccionesNacionales d WHERE d.directorNacional = :directorNacional"),
    @NamedQuery(name = "DireccionesNacionales.findByDescripcionDirNacional", query = "SELECT d FROM DireccionesNacionales d WHERE d.descripcionDirNacional = :descripcionDirNacional")})
public class DireccionesNacionales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dir_nacional")
    private Integer idDirNacional;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 125)
    @Column(name = "nombre_dir_nacional")
    private String nombreDirNacional;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 100)
    @Column(name = "director_nacional")
    private String directorNacional;
//    @Size(max = 400)
    @Column(name = "descripcion_dir_nacional")
    private String descripcionDirNacional;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDirNacional")
    private List<DireccionesInter> direccionesInterList;

    public DireccionesNacionales() {
    }

    public DireccionesNacionales(Integer idDirNacional) {
        this.idDirNacional = idDirNacional;
    }

    public DireccionesNacionales(Integer idDirNacional, String nombreDirNacional, String directorNacional) {
        this.idDirNacional = idDirNacional;
        this.nombreDirNacional = nombreDirNacional;
        this.directorNacional = directorNacional;
    }

    public Integer getIdDirNacional() {
        return idDirNacional;
    }

    public void setIdDirNacional(Integer idDirNacional) {
        this.idDirNacional = idDirNacional;
    }

    public String getNombreDirNacional() {
        return nombreDirNacional;
    }

    public void setNombreDirNacional(String nombreDirNacional) {
        this.nombreDirNacional = nombreDirNacional;
    }

    public String getDirectorNacional() {
        return directorNacional;
    }

    public void setDirectorNacional(String directorNacional) {
        this.directorNacional = directorNacional;
    }

    public String getDescripcionDirNacional() {
        return descripcionDirNacional;
    }

    public void setDescripcionDirNacional(String descripcionDirNacional) {
        this.descripcionDirNacional = descripcionDirNacional;
    }

    @XmlTransient
    public List<DireccionesInter> getDireccionesInterList() {
        return direccionesInterList;
    }

    public void setDireccionesInterList(List<DireccionesInter> direccionesInterList) {
        this.direccionesInterList = direccionesInterList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDirNacional != null ? idDirNacional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DireccionesNacionales)) {
            return false;
        }
        DireccionesNacionales other = (DireccionesNacionales) object;
        if ((this.idDirNacional == null && other.idDirNacional != null) || (this.idDirNacional != null && !this.idDirNacional.equals(other.idDirNacional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DireccionesNacionales[ idDirNacional=" + idDirNacional + " ]";
    }
    
}
