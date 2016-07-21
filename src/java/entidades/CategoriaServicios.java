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
@Table(name = "categoria_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaServicios.findAll", query = "SELECT c FROM CategoriaServicios c ORDER BY c.idCatServ"),
    @NamedQuery(name = "CategoriaServicios.findByIdCatServ", query = "SELECT c FROM CategoriaServicios c WHERE c.idCatServ = :idCatServ"),
    @NamedQuery(name = "CategoriaServicios.findByNombreCatServ", query = "SELECT c FROM CategoriaServicios c WHERE c.nombreCatServ = :nombreCatServ"),
    @NamedQuery(name = "CategoriaServicios.findByDescripcionCatServ", query = "SELECT c FROM CategoriaServicios c WHERE c.descripcionCatServ = :descripcionCatServ"),
    @NamedQuery(name = "CategoriaServicios.findByIdUndEjecutora", query = "SELECT c FROM CategoriaServicios c WHERE c.idUndEjecutora = :idUndEjecutora"),})
public class CategoriaServicios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cat_serv")
    private Integer idCatServ;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 150)
    @Column(name = "nombre_cat_serv")
    private String nombreCatServ;
//    @Size(max = 400)
    @Column(name = "descripcion_cat_serv")
    private String descripcionCatServ;
    @Basic(optional = false)
    @Column(name = "id_und_ejecutora")
    private Integer idUndEjecutora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCatServ")
    private List<EncaRequerimientos> encaRequerimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCatServ")
    private List<Servicios> serviciosList;

    public CategoriaServicios() {
    }

    public CategoriaServicios(Integer idCatServ) {
        this.idCatServ = idCatServ;
    }

    public CategoriaServicios(Integer idCatServ, String nombreCatServ) {
        this.idCatServ = idCatServ;
        this.nombreCatServ = nombreCatServ;
    }

    public Integer getIdCatServ() {
        return idCatServ;
    }

    public void setIdCatServ(Integer idCatServ) {
        this.idCatServ = idCatServ;
    }

    public String getNombreCatServ() {
        return nombreCatServ;
    }

    public void setNombreCatServ(String nombreCatServ) {
        this.nombreCatServ = nombreCatServ;
    }

    public String getDescripcionCatServ() {
        return descripcionCatServ;
    }

    public void setDescripcionCatServ(String descripcionCatServ) {
        this.descripcionCatServ = descripcionCatServ;
    }

    public Integer getIdUndEjecutora() {
        return idUndEjecutora;
    }

    public void setIdUndEjecutora(Integer idUndEjecutora) {
        this.idUndEjecutora = idUndEjecutora;
    }
    

    @XmlTransient
    public List<EncaRequerimientos> getEncaRequerimientosList() {
        return encaRequerimientosList;
    }

    public void setEncaRequerimientosList(List<EncaRequerimientos> encaRequerimientosList) {
        this.encaRequerimientosList = encaRequerimientosList;
    }

    @XmlTransient
    public List<Servicios> getServiciosList() {
        return serviciosList;
    }

    public void setServiciosList(List<Servicios> serviciosList) {
        this.serviciosList = serviciosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatServ != null ? idCatServ.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaServicios)) {
            return false;
        }
        CategoriaServicios other = (CategoriaServicios) object;
        if ((this.idCatServ == null && other.idCatServ != null) || (this.idCatServ != null && !this.idCatServ.equals(other.idCatServ))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CategoriaServicios[ idCatServ=" + idCatServ + " ]";
    }
    
}
