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
@Table(name = "dependencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dependencias.findAll", query = "SELECT d FROM Dependencias d"),
    @NamedQuery(name = "Dependencias.findIdDInter", query = "SELECT d FROM Dependencias d WHERE d.idDirecInter.idDirecInter=:idDir ORDER BY d.idDependencia"),
    @NamedQuery(name = "Dependencias.findByIdDependencia", query = "SELECT d FROM Dependencias d WHERE d.idDependencia = :idDependencia"),
    @NamedQuery(name = "Dependencias.findByNombreDependencia", query = "SELECT d FROM Dependencias d WHERE d.nombreDependencia = :nombreDependencia"),
    @NamedQuery(name = "Dependencias.findByDireccionDependencia", query = "SELECT d FROM Dependencias d WHERE d.direccionDependencia = :direccionDependencia"),
    @NamedQuery(name = "Dependencias.findByTelefonoDependencia", query = "SELECT d FROM Dependencias d WHERE d.telefonoDependencia = :telefonoDependencia"),
    @NamedQuery(name = "Dependencias.findByDescripcionDependencia", query = "SELECT d FROM Dependencias d WHERE d.descripcionDependencia = :descripcionDependencia"),
    @NamedQuery(name = "Dependencias.findByJefeDependencia", query = "SELECT d FROM Dependencias d WHERE d.jefeDependencia = :jefeDependencia")})
public class Dependencias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dependencia")
    private Integer idDependencia;
    @Basic(optional = false)
 //   @NotNull
 //   @Size(min = 1, max = 125)
    @Column(name = "nombre_dependencia")
    private String nombreDependencia;
 //   @Size(max = 250)
    @Column(name = "direccion_dependencia")
    private String direccionDependencia;
    @Basic(optional = false)
    @NotNull
//    @Size(min = 1, max = 10)
    @Column(name = "telefono_dependencia")
    private String telefonoDependencia;
//    @Size(max = 400)
    @Column(name = "descripcion_dependencia")
    private String descripcionDependencia;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 125)
    @Column(name = "jefe_dependencia")
    private String jefeDependencia;
    @JoinColumn(name = "id_direc_inter", referencedColumnName = "id_direc_inter")
    @ManyToOne(optional = false)
    private DireccionesInter idDirecInter;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDependencia")
    private List<Usuarios> usuariosList;

    public Dependencias() {
    }

    public Dependencias(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Dependencias(Integer idDependencia, String nombreDependencia, String telefonoDependencia, String jefeDependencia) {
        this.idDependencia = idDependencia;
        this.nombreDependencia = nombreDependencia;
        this.telefonoDependencia = telefonoDependencia;
        this.jefeDependencia = jefeDependencia;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public String getNombreDependencia() {
        return nombreDependencia;
    }

    public void setNombreDependencia(String nombreDependencia) {
        this.nombreDependencia = nombreDependencia;
    }

    public String getDireccionDependencia() {
        return direccionDependencia;
    }

    public void setDireccionDependencia(String direccionDependencia) {
        this.direccionDependencia = direccionDependencia;
    }

    public String getTelefonoDependencia() {
        return telefonoDependencia;
    }

    public void setTelefonoDependencia(String telefonoDependencia) {
        this.telefonoDependencia = telefonoDependencia;
    }

    public String getDescripcionDependencia() {
        return descripcionDependencia;
    }

    public void setDescripcionDependencia(String descripcionDependencia) {
        this.descripcionDependencia = descripcionDependencia;
    }

    public String getJefeDependencia() {
        return jefeDependencia;
    }

    public void setJefeDependencia(String jefeDependencia) {
        this.jefeDependencia = jefeDependencia;
    }

    public DireccionesInter getIdDirecInter() {
        return idDirecInter;
    }

    public void setIdDirecInter(DireccionesInter idDirecInter) {
        this.idDirecInter = idDirecInter;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDependencia != null ? idDependencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dependencias)) {
            return false;
        }
        Dependencias other = (Dependencias) object;
        if ((this.idDependencia == null && other.idDependencia != null) || (this.idDependencia != null && !this.idDependencia.equals(other.idDependencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Dependencias[ idDependencia=" + idDependencia + " ]";
    }
    
}
