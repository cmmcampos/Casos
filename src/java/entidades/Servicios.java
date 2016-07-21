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
@Table(name = "servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicios.findAll", query = "SELECT s FROM Servicios s WHERE s.idUndEjecutora.idUndEjecutora=:idUnidadEjecutora ORDER BY s.nombreServicio ASC"),
    @NamedQuery(name = "Servicios.findByUnidad", query = "SELECT s FROM Servicios s WHERE s.idUndEjecutora.idUndEjecutora=:idUnidadEjecutora ORDER BY s.idServicio ASC"),
    @NamedQuery(name = "Servicios.findByIdServicio", query = "SELECT s FROM Servicios s WHERE s.idServicio = :idServicio"),
    @NamedQuery(name = "Servicios.findByNombreServicio", query = "SELECT s FROM Servicios s WHERE s.nombreServicio = :nombreServicio"),
    @NamedQuery(name = "Servicios.findByDescripcionServicio", query = "SELECT s FROM Servicios s WHERE s.descripcionServicio = :descripcionServicio"),
    @NamedQuery(name = "Servicios.findByTiempoAtencionServicio", query = "SELECT s FROM Servicios s WHERE s.tiempoAtencionServicio = :tiempoAtencionServicio")})
public class Servicios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_servicio")
    private Integer idServicio;
    @Basic(optional = false)
  //  @NotNull
 //   @Size(min = 1, max = 250)
    @Column(name = "nombre_servicio")
    private String nombreServicio;
 //   @Size(max = 400)
    @Column(name = "descripcion_servicio")
    private String descripcionServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tiempo_atencion_servicio")
    private String tiempoAtencionServicio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServicio")
    private List<DetaRequerimientos> detaRequerimientosList;
    @JoinColumn(name = "id_und_ejecutora", referencedColumnName = "id_und_ejecutora")
    @ManyToOne(optional = false)
    private UnidadesEjecutoras idUndEjecutora;
    @JoinColumn(name = "id_cat_serv", referencedColumnName = "id_cat_serv")
    @ManyToOne(optional = false)
    private CategoriaServicios idCatServ;

    public Servicios() {
    }

    public Servicios(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Servicios(Integer idServicio, String nombreServicio, String tiempoAtencionServicio) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.tiempoAtencionServicio = tiempoAtencionServicio;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public String getTiempoAtencionServicio() {
        return tiempoAtencionServicio;
    }

    public void setTiempoAtencionServicio(String tiempoAtencionServicio) {
        this.tiempoAtencionServicio = tiempoAtencionServicio;
    }

    @XmlTransient
    public List<DetaRequerimientos> getDetaRequerimientosList() {
        return detaRequerimientosList;
    }

    public void setDetaRequerimientosList(List<DetaRequerimientos> detaRequerimientosList) {
        this.detaRequerimientosList = detaRequerimientosList;
    }

    public UnidadesEjecutoras getIdUndEjecutora() {
        return idUndEjecutora;
    }

    public void setIdUndEjecutora(UnidadesEjecutoras idUndEjecutora) {
        this.idUndEjecutora = idUndEjecutora;
    }

    public CategoriaServicios getIdCatServ() {
        return idCatServ;
    }

    public void setIdCatServ(CategoriaServicios idCatServ) {
        this.idCatServ = idCatServ;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.idServicio == null && other.idServicio != null) || (this.idServicio != null && !this.idServicio.equals(other.idServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Servicios[ idServicio=" + idServicio + " ]";
    }
    
}
