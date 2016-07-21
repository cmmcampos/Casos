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
@Table(name = "unidades_ejecutoras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadesEjecutoras.findAll", query = "SELECT u FROM UnidadesEjecutoras u ORDER BY u.idUndEjecutora"),
    @NamedQuery(name = "UnidadesEjecutoras.findMaxIdUnidadEjec", query = "SELECT MAX(u.idUndEjecutora) FROM UnidadesEjecutoras u"),
    @NamedQuery(name = "UnidadesEjecutoras.findByIdUndEjecutora", query = "SELECT u FROM UnidadesEjecutoras u WHERE u.idUndEjecutora = :idUndEjecutora"),
    @NamedQuery(name = "UnidadesEjecutoras.findByNombreUndEjecutora", query = "SELECT u FROM UnidadesEjecutoras u WHERE u.nombreUndEjecutora = :nombreUndEjecutora"),
    @NamedQuery(name = "UnidadesEjecutoras.findByJefeUndEjecutora", query = "SELECT u FROM UnidadesEjecutoras u WHERE u.jefeUndEjecutora = :jefeUndEjecutora"),
    @NamedQuery(name = "UnidadesEjecutoras.findByCorreoJefeUndEjecutora", query = "SELECT u FROM UnidadesEjecutoras u WHERE u.correoJefeUndEjecutora = :correoJefeUndEjecutora")})
public class UnidadesEjecutoras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_und_ejecutora")
    private Integer idUndEjecutora;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 125)
    @Column(name = "nombre_und_ejecutora")
    private String nombreUndEjecutora;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 100)
    @Column(name = "jefe_und_ejecutora")
    private String jefeUndEjecutora;
//    @Size(max = 100)
    @Column(name = "correo_jefe_und_ejecutora")
    private String correoJefeUndEjecutora;
    @Column(name = "correl_caso_und_eje")
    private Integer correlCasoUndEje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUndEjecutora")
    private List<Servicios> serviciosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadesEjecutoras")
    private List<Tecnicos> tecnicosList;

    public UnidadesEjecutoras() {
    }

    public UnidadesEjecutoras(Integer idUndEjecutora) {
        this.idUndEjecutora = idUndEjecutora;
    }

    public UnidadesEjecutoras(Integer idUndEjecutora, String nombreUndEjecutora, String jefeUndEjecutora) {
        this.idUndEjecutora = idUndEjecutora;
        this.nombreUndEjecutora = nombreUndEjecutora;
        this.jefeUndEjecutora = jefeUndEjecutora;
    }

    public Integer getIdUndEjecutora() {
        return idUndEjecutora;
    }

    public void setIdUndEjecutora(Integer idUndEjecutora) {
        this.idUndEjecutora = idUndEjecutora;
    }

    public String getNombreUndEjecutora() {
        return nombreUndEjecutora;
    }

    public void setNombreUndEjecutora(String nombreUndEjecutora) {
        this.nombreUndEjecutora = nombreUndEjecutora;
    }

    public String getJefeUndEjecutora() {
        return jefeUndEjecutora;
    }

    public void setJefeUndEjecutora(String jefeUndEjecutora) {
        this.jefeUndEjecutora = jefeUndEjecutora;
    }

    public String getCorreoJefeUndEjecutora() {
        return correoJefeUndEjecutora;
    }

    public void setCorreoJefeUndEjecutora(String correoJefeUndEjecutora) {
        this.correoJefeUndEjecutora = correoJefeUndEjecutora;
    }

    public Integer getCorrelCasoUndEje() {
        return correlCasoUndEje;
    }

    public void setCorrelCasoUndEje(Integer correlCasoUndEje) {
        this.correlCasoUndEje = correlCasoUndEje;
    }

        
    @XmlTransient
    public List<Servicios> getServiciosList() {
        return serviciosList;
    }

    public void setServiciosList(List<Servicios> serviciosList) {
        this.serviciosList = serviciosList;
    }

    @XmlTransient
    public List<Tecnicos> getTecnicosList() {
        return tecnicosList;
    }

    public void setTecnicosList(List<Tecnicos> tecnicosList) {
        this.tecnicosList = tecnicosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUndEjecutora != null ? idUndEjecutora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadesEjecutoras)) {
            return false;
        }
        UnidadesEjecutoras other = (UnidadesEjecutoras) object;
        if ((this.idUndEjecutora == null && other.idUndEjecutora != null) || (this.idUndEjecutora != null && !this.idUndEjecutora.equals(other.idUndEjecutora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UnidadesEjecutoras[ idUndEjecutora=" + idUndEjecutora + " ]";
    }
    
}
