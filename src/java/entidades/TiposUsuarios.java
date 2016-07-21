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
@Table(name = "tipos_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposUsuarios.findAll", query = "SELECT t FROM TiposUsuarios t ORDER BY t.idTipoUsuario ASC"),
    @NamedQuery(name = "TiposUsuarios.findIdUltimoTipoUsu", query = "SELECT MAX(t.idTipoUsuario) FROM TiposUsuarios t"),
    @NamedQuery(name = "TiposUsuarios.findByIdTipoUsuario", query = "SELECT t FROM TiposUsuarios t WHERE t.idTipoUsuario = :idTipoUsuario"),
    @NamedQuery(name = "TiposUsuarios.findByNombreTipoUsuario", query = "SELECT t FROM TiposUsuarios t WHERE t.nombreTipoUsuario = :nombreTipoUsuario"),
    @NamedQuery(name = "TiposUsuarios.findByDescripcionTipoUsuario", query = "SELECT t FROM TiposUsuarios t WHERE t.descripcionTipoUsuario = :descripcionTipoUsuario")})
public class TiposUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_usuario")
    private Integer idTipoUsuario;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 50)
    @Column(name = "nombre_tipo_usuario")
    private String nombreTipoUsuario;
    @Basic(optional = false)
    //@Size(min = 1, max = 400)
    @Column(name = "descripcion_tipo_usuario")
    private String descripcionTipoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoUsuario")
    private List<Usuarios> usuariosList;

    public TiposUsuarios() {
    }

    public TiposUsuarios(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public TiposUsuarios(Integer idTipoUsuario, String nombreTipoUsuario, String descripcionTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
        this.nombreTipoUsuario = nombreTipoUsuario;
        this.descripcionTipoUsuario = descripcionTipoUsuario;
    }

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombreTipoUsuario() {
        return nombreTipoUsuario;
    }

    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    public String getDescripcionTipoUsuario() {
        return descripcionTipoUsuario;
    }

    public void setDescripcionTipoUsuario(String descripcionTipoUsuario) {
        this.descripcionTipoUsuario = descripcionTipoUsuario;
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
        hash += (idTipoUsuario != null ? idTipoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposUsuarios)) {
            return false;
        }
        TiposUsuarios other = (TiposUsuarios) object;
        if ((this.idTipoUsuario == null && other.idTipoUsuario != null) || (this.idTipoUsuario != null && !this.idTipoUsuario.equals(other.idTipoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TiposUsuarios[ idTipoUsuario=" + idTipoUsuario + " ]";
    }
    
}
