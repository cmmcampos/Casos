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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "direcciones_inter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DireccionesInter.findAll", query = "SELECT d FROM DireccionesInter d"),
    @NamedQuery(name = "DireccionesInter.findIdNacional", query = "SELECT d FROM DireccionesInter d WHERE d.idDirNacional.idDirNacional=:idDire ORDER BY d.idDirecInter"),
    @NamedQuery(name = "DireccionesInter.findByIdDirecInter", query = "SELECT d FROM DireccionesInter d WHERE d.idDirecInter = :idDirecInter"),
    @NamedQuery(name = "DireccionesInter.findByNombreDirecInter", query = "SELECT d FROM DireccionesInter d WHERE d.nombreDirecInter = :nombreDirecInter"),
    @NamedQuery(name = "DireccionesInter.findByDirectorInter", query = "SELECT d FROM DireccionesInter d WHERE d.directorInter = :directorInter"),
    @NamedQuery(name = "DireccionesInter.findByTelefonoDirecInter", query = "SELECT d FROM DireccionesInter d WHERE d.telefonoDirecInter = :telefonoDirecInter"),
    @NamedQuery(name = "DireccionesInter.findByDescripcionDirecInter", query = "SELECT d FROM DireccionesInter d WHERE d.descripcionDirecInter = :descripcionDirecInter")})
public class DireccionesInter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_direc_inter")
    private Integer idDirecInter;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 150)
    @Column(name = "nombre_direc_inter")
    private String nombreDirecInter;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 150)
    @Column(name = "director_inter")
    private String directorInter;
//    @Size(max = 10)
    @Column(name = "telefono_direc_inter")
    private String telefonoDirecInter;
//    @Size(max = 400)
    @Column(name = "descripcion_direc_inter")
    private String descripcionDirecInter;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDirecInter")
    private List<Dependencias> dependenciasList;
    @JoinColumn(name = "id_dir_nacional", referencedColumnName = "id_dir_nacional")
    @ManyToOne(optional = false)
    private DireccionesNacionales idDirNacional;

    public DireccionesInter() {
    }

    public DireccionesInter(Integer idDirecInter) {
        this.idDirecInter = idDirecInter;
    }

    public DireccionesInter(Integer idDirecInter, String nombreDirecInter, String directorInter) {
        this.idDirecInter = idDirecInter;
        this.nombreDirecInter = nombreDirecInter;
        this.directorInter = directorInter;
    }

    public Integer getIdDirecInter() {
        return idDirecInter;
    }

    public void setIdDirecInter(Integer idDirecInter) {
        this.idDirecInter = idDirecInter;
    }

    public String getNombreDirecInter() {
        return nombreDirecInter;
    }

    public void setNombreDirecInter(String nombreDirecInter) {
        this.nombreDirecInter = nombreDirecInter;
    }

    public String getDirectorInter() {
        return directorInter;
    }

    public void setDirectorInter(String directorInter) {
        this.directorInter = directorInter;
    }

    public String getTelefonoDirecInter() {
        return telefonoDirecInter;
    }

    public void setTelefonoDirecInter(String telefonoDirecInter) {
        this.telefonoDirecInter = telefonoDirecInter;
    }

    public String getDescripcionDirecInter() {
        return descripcionDirecInter;
    }

    public void setDescripcionDirecInter(String descripcionDirecInter) {
        this.descripcionDirecInter = descripcionDirecInter;
    }

    @XmlTransient
    public List<Dependencias> getDependenciasList() {
        return dependenciasList;
    }

    public void setDependenciasList(List<Dependencias> dependenciasList) {
        this.dependenciasList = dependenciasList;
    }

    public DireccionesNacionales getIdDirNacional() {
        return idDirNacional;
    }

    public void setIdDirNacional(DireccionesNacionales idDirNacional) {
        this.idDirNacional = idDirNacional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDirecInter != null ? idDirecInter.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DireccionesInter)) {
            return false;
        }
        DireccionesInter other = (DireccionesInter) object;
        if ((this.idDirecInter == null && other.idDirecInter != null) || (this.idDirecInter != null && !this.idDirecInter.equals(other.idDirecInter))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DireccionesInter[ idDirecInter=" + idDirecInter + " ]";
    }
    
}
