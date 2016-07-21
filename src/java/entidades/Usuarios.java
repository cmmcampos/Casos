/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario Desarrollo
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u WHERE u.idUnidadEjecutora.idUndEjecutora = :idu ORDER BY u.idUsuario"),
    @NamedQuery(name = "Usuarios.findAllAtive", query = "SELECT u FROM Usuarios u WHERE u.idUnidadEjecutora.idUndEjecutora = :idu AND u.estado= :estado ORDER BY u.idUsuario"),
    @NamedQuery(name = "Usuarios.findTecnico", query = "SELECT u FROM Usuarios u WHERE u.idUnidadEjecutora.idUndEjecutora = :idu AND u.idRol.idRol=2 ORDER BY u.idUsuario"),
    @NamedQuery(name = "Usuarios.findTecnicoActive", query = "SELECT u FROM Usuarios u WHERE u.idUnidadEjecutora.idUndEjecutora = :idu AND u.idRol.idRol=2 AND u.estado= :estado ORDER BY u.nombreUsuario"),
    @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuarios.findByIdMayorr", query = "SELECT MAX(u.idUsuario) FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByNombreUsuario", query = "SELECT u FROM Usuarios u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Usuarios.findByApellidoUsuario", query = "SELECT u FROM Usuarios u WHERE u.apellidoUsuario = :apellidoUsuario"),
    @NamedQuery(name = "Usuarios.findByUsuarioUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuarioUsuario = :usuarioUsuario"),
    @NamedQuery(name = "Usuarios.findByContraseniaUsuario", query = "SELECT u FROM Usuarios u WHERE u.contraseniaUsuario = :contraseniaUsuario"),
    @NamedQuery(name = "Usuarios.findByCorreoUsuario", query = "SELECT u FROM Usuarios u WHERE u.correoUsuario = :correoUsuario"),
    @NamedQuery(name = "Usuarios.findByTelefonoUsuario", query = "SELECT u FROM Usuarios u WHERE u.telefonoUsuario = :telefonoUsuario"),
    @NamedQuery(name = "Usuarios.findByFechaCreaUsuario", query = "SELECT u FROM Usuarios u WHERE u.fechaCreaUsuario = :fechaCreaUsuario"),
    @NamedQuery(name = "Usuarios.findByObservacionesUsuario", query = "SELECT  u FROM Usuarios u WHERE u.observacionesUsuario = :observacionesUsuario")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 70)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 70)
    @Column(name = "apellido_usuario")
    private String apellidoUsuario;
    @Id
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 30)
    @Column(name = "usuario_usuario")
    private String usuarioUsuario;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 30)
    @Column(name = "contrasenia_usuario")
    private String contraseniaUsuario;
//    @Size(max = 100)
    @Column(name = "correo_usuario")
    private String correoUsuario;
//    @Size(max = 20)
    @Column(name = "telefono_usuario")
    private String telefonoUsuario;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "fecha_crea_usuario")
    @Temporal(TemporalType.DATE)
    private Date fechaCreaUsuario;
//    @Size(max = 2147483647)
    @Column(name = "observaciones_usuario")
    private String observacionesUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioUsuario")
    private List<EncaRequerimientos> encaRequerimientosList;
    @JoinColumn(name = "id_tipo_usuario", referencedColumnName = "id_tipo_usuario")
    @ManyToOne(optional = false)
    private TiposUsuarios idTipoUsuario;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false, fetch= FetchType.EAGER)
    private Roles idRol;
//    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
//    @ManyToOne(optional = false)
//    private Estados idEstado;
    @JoinColumn(name = "id_dependencia", referencedColumnName = "id_dependencia")
    @ManyToOne(optional = false)
    private Dependencias idDependencia;
    @JoinColumn(name = "id_und_ejecutora", referencedColumnName = "id_und_ejecutora")
    @ManyToOne(optional = false)
    private UnidadesEjecutoras idUnidadEjecutora;
    @Column(name = "estado")
    private Boolean estado;
    public Usuarios() {
    }

    public Usuarios(String usuarioUsuario) {
        this.usuarioUsuario = usuarioUsuario;
    }

    public Usuarios(String usuarioUsuario, int idUsuario, String nombreUsuario, String apellidoUsuario, String contraseniaUsuario, Date fechaCreaUsuario) {
        this.usuarioUsuario = usuarioUsuario;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.contraseniaUsuario = contraseniaUsuario;
        this.fechaCreaUsuario = fechaCreaUsuario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UnidadesEjecutoras getIdUnidadEjecutora() {
        return idUnidadEjecutora;
    }

    public void setIdUnidadEjecutora(UnidadesEjecutoras idUnidadEjecutora) {
        this.idUnidadEjecutora = idUnidadEjecutora;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getUsuarioUsuario() {
        return usuarioUsuario;
    }

    public void setUsuarioUsuario(String usuarioUsuario) {
        this.usuarioUsuario = usuarioUsuario;
    }

    public String getContraseniaUsuario() {
        return contraseniaUsuario;
    }

    public void setContraseniaUsuario(String contraseniaUsuario) {
        this.contraseniaUsuario = contraseniaUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public Date getFechaCreaUsuario() {
        return fechaCreaUsuario;
    }

    public void setFechaCreaUsuario(Date fechaCreaUsuario) {
        this.fechaCreaUsuario = fechaCreaUsuario;
    }

    public String getObservacionesUsuario() {
        return observacionesUsuario;
    }

    public void setObservacionesUsuario(String observacionesUsuario) {
        this.observacionesUsuario = observacionesUsuario;
    }

    @XmlTransient
    public List<EncaRequerimientos> getEncaRequerimientosList() {
        return encaRequerimientosList;
    }

    public void setEncaRequerimientosList(List<EncaRequerimientos> encaRequerimientosList) {
        this.encaRequerimientosList = encaRequerimientosList;
    }

    public TiposUsuarios getIIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(TiposUsuarios idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public Roles getIdRol() {
        return idRol;
    }

    public void setIdRol(Roles idRol) {
        this.idRol = idRol;
    }

//    public Estados getIdEstado() {
//        return idEstado;
//    }
//
//    public void setIdEstado(Estados idEstado) {
//        this.idEstado = idEstado;
//    }

    public Dependencias getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Dependencias idDependencia) {
        this.idDependencia = idDependencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioUsuario != null ? usuarioUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usuarioUsuario == null && other.usuarioUsuario != null) || (this.usuarioUsuario != null && !this.usuarioUsuario.equals(other.usuarioUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuarios[ usuarioUsuario=" + usuarioUsuario + " ]";
    }
    
}
