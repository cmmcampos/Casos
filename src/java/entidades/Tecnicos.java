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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "tecnicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tecnicos.findAll", query = "SELECT t FROM Tecnicos t, Usuarios u WHERE u.usuarioUsuario=t.usuarioUsuario.usuarioUsuario AND t.unidadesEjecutoras.idUndEjecutora = :idUni AND u.idRol.idRol=2 ORDER BY t.tecnicosPK.idTecnico"),
    @NamedQuery(name = "Tecnicos.findIdMayor", query = "SELECT MAX(t.tecnicosPK.idTecnico) FROM Tecnicos t"),
    @NamedQuery(name = "Tecnicos.fiIdCompuesto", query = "SELECT t FROM Tecnicos t WHERE t.tecnicosPK.idTecnico = :idTecnico AND t.tecnicosPK.idUndEjecutora = :idUnidadEjecutora"),
    @NamedQuery(name = "Tecnicos.findByIdTecnico", query = "SELECT t FROM Tecnicos t WHERE t.tecnicosPK.idTecnico = :idTecnico"),
    @NamedQuery(name = "Tecnicos.findByIdUndEjecutora", query = "SELECT t FROM Tecnicos t WHERE t.tecnicosPK.idUndEjecutora = :idUndEjecutora"),
    @NamedQuery(name = "Tecnicos.findByNombreTecnico", query = "SELECT t FROM Tecnicos t WHERE t.nombreTecnico = :nombreTecnico"),
    @NamedQuery(name = "Tecnicos.findByCorreoTecnico", query = "SELECT t FROM Tecnicos t WHERE t.correoTecnico = :correoTecnico"),
    @NamedQuery(name = "Tecnicos.findByUsuarioUsuario", query = "SELECT t FROM Tecnicos t WHERE t.usuarioUsuario.usuarioUsuario = :Usuario"),
    @NamedQuery(name = "Tecnicos.findByTelefonoTecnico", query = "SELECT t FROM Tecnicos t WHERE t.telefonoTecnico = :telefonoTecnico")})
public class Tecnicos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TecnicosPK tecnicosPK;
    @Basic(optional = false)
    
    @Size(min = 1, max = 100)
    @Column(name = "nombre_tecnico")
    private String nombreTecnico;
    @Basic(optional = false)
    
    @Size(min = 1, max = 100)
    @Column(name = "correo_tecnico")
    private String correoTecnico;
    @Size(max = 10)
    @Column(name = "telefono_tecnico")
    private String telefonoTecnico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tecnicos")
    private List<AsignarTecnico> asignarTecnicoList;
    @JoinColumn(name = "id_und_ejecutora", referencedColumnName = "id_und_ejecutora", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UnidadesEjecutoras unidadesEjecutoras;
    @NotNull
    @JoinColumn(name = "usuario_usuario", referencedColumnName = "usuario_usuario")
    @ManyToOne(optional = false)
    private Usuarios usuarioUsuario;
    

    public Tecnicos(){
    }

    public Tecnicos(TecnicosPK tecnicosPK) {
        this.tecnicosPK = tecnicosPK;
    }

    public Tecnicos(TecnicosPK tecnicosPK, String nombreTecnico, String correoTecnico) {
        this.tecnicosPK = tecnicosPK;
        this.nombreTecnico = nombreTecnico;
        this.correoTecnico = correoTecnico;
    }

    public Tecnicos(int idTecnico, int idUndEjecutora) {
        this.tecnicosPK = new TecnicosPK(idTecnico, idUndEjecutora);
    }

    public TecnicosPK getTecnicosPK() {
        return tecnicosPK;
    }

    public Usuarios getUsuarioUsuario() {
        return usuarioUsuario;
    }

    public void setUsuarioUsuario(Usuarios usuarioUsuario) {
        this.usuarioUsuario = usuarioUsuario;
    }
    
    public void setTecnicosPK(TecnicosPK tecnicosPK) {
        this.tecnicosPK = tecnicosPK;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        this.nombreTecnico = nombreTecnico;
    }

    public String getCorreoTecnico() {
        return correoTecnico;
    }

    public void setCorreoTecnico(String correoTecnico) {
        this.correoTecnico = correoTecnico;
    }

    public String getTelefonoTecnico() {
        return telefonoTecnico;
    }

    public void setTelefonoTecnico(String telefonoTecnico) {
        this.telefonoTecnico = telefonoTecnico;
    }

    @XmlTransient
    public List<AsignarTecnico> getAsignarTecnicoList() {
        return asignarTecnicoList;
    }

    public void setAsignarTecnicoList(List<AsignarTecnico> asignarTecnicoList) {
        this.asignarTecnicoList = asignarTecnicoList;
    }

    public UnidadesEjecutoras getUnidadesEjecutoras() {
        return unidadesEjecutoras;
    }

    public void setUnidadesEjecutoras(UnidadesEjecutoras unidadesEjecutoras) {
        this.unidadesEjecutoras = unidadesEjecutoras;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tecnicosPK != null ? tecnicosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tecnicos)) {
            return false;
        }
        Tecnicos other = (Tecnicos) object;
        if ((this.tecnicosPK == null && other.tecnicosPK != null) || (this.tecnicosPK != null && !this.tecnicosPK.equals(other.tecnicosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tecnicos[ tecnicosPK=" + tecnicosPK + " ]";
    }
    
}
