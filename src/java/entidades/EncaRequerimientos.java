/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Table(name = "enca_requerimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncaRequerimientos.findAll", query = "SELECT e FROM EncaRequerimientos e ORDER BY e.idEncaRequerimiento ASC"),
    @NamedQuery(name = "EncaRequerimientos.findByIdEncaRequerimiento", query = "SELECT e  FROM EncaRequerimientos e WHERE e.idEncaRequerimiento = :idEncaRequerimiento"),
    @NamedQuery(name = "EncaRequerimientos.findByFechaRequerimiento", query = "SELECT e FROM EncaRequerimientos e WHERE e.fechaRequerimiento = :fechaRequerimiento"),
    @NamedQuery(name = "EncaRequerimientos.findByHoraRequerimiento", query = "SELECT e FROM EncaRequerimientos e WHERE e.horaRequerimiento = :horaRequerimiento"),
    @NamedQuery(name = "EncaRequerimientos.findByFechaIngreso", query = "SELECT e FROM EncaRequerimientos e WHERE e.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "EncaRequerimientos.findByDocumentacionCompleta", query = "SELECT e FROM EncaRequerimientos e WHERE e.documentacionCompleta = :documentacionCompleta"),
    @NamedQuery(name = "EncaRequerimientos.findByEquipoPropiedad", query = "SELECT e FROM EncaRequerimientos e WHERE e.equipoPropiedad = :equipoPropiedad"),
    @NamedQuery(name = "EncaRequerimientos.findByDescripcionRequerimiento", query = "SELECT e FROM EncaRequerimientos e WHERE e.descripcionRequerimiento = :descripcionRequerimiento"),
    @NamedQuery(name = "EncaRequerimientos.findBySolicitante", query = "SELECT e FROM EncaRequerimientos e WHERE e.solicitante = :solicitante"),
    @NamedQuery(name = "EncaRequerimientos.findByAnio", query = "SELECT e FROM EncaRequerimientos e WHERE e.anio = :anio"),
    @NamedQuery(name = "EncaRequerimientos.findByEstado1", query = "SELECT e FROM EncaRequerimientos e WHERE e.idEstado.idEstado=1 AND e.usuarioUsuario.idUnidadEjecutora.idUndEjecutora = :idUnidadEje ORDER BY e.idEncaRequerimiento DESC"),
    @NamedQuery(name = "EncaRequerimientos.findBytodo", query = "SELECT DISTINCT(e), a FROM EncaRequerimientos e LEFT OUTER JOIN e.asignarTecnicoList a where (a.estadoDelTecnico <> 'pausado' or a.estadoDelTecnico is null or e.idEstado.idEstado=5 )  AND e.usuarioUsuario.idUnidadEjecutora.idUndEjecutora = :idu ORDER BY e.numCaso DESC"),
    @NamedQuery(name = "EncaRequerimientos.findBytodo2", query = "SELECT DISTINCT(e), a FROM EncaRequerimientos e LEFT OUTER JOIN e.asignarTecnicoList a where (a.estadoDelTecnico <> 'pausado' or a.estadoDelTecnico is null or e.idEstado.idEstado=5 )  AND e.usuarioUsuario.idUnidadEjecutora.idUndEjecutora = :idu ORDER BY e.fechaRequerimiento DESC"),
    @NamedQuery(name = "EncaRequerimientos.findByNumCaso", query = "SELECT e FROM EncaRequerimientos e WHERE e.numCaso = :numCaso"),
    @NamedQuery(name = "EncaRequerimientos.findByLosDelTecnico", query = "SELECT e FROM EncaRequerimientos e, AsignarTecnico a WHERE e.idEncaRequerimiento=a.asignarTecnicoPK.idEncaRequerimiento AND a.asignarTecnicoPK.idTecnico = :idTec AND a.asignarTecnicoPK.idUndEjecutora=:idUniEje AND (a.estadoDelTecnico='asignado' OR a.estadoDelTecnico='reasignado' OR a.estadoDelTecnico='pausado') AND e.fechaFin IS NULL ORDER BY a.fechaAsigna DESC"),
    @NamedQuery(name = "EncaRequerimientos.findByTodosLosDelTecnico", query = "SELECT e FROM EncaRequerimientos e, AsignarTecnico a WHERE e.idEncaRequerimiento=a.asignarTecnicoPK.idEncaRequerimiento AND a.asignarTecnicoPK.idTecnico = :idTec AND a.asignarTecnicoPK.idUndEjecutora=:idUniEje AND (a.estadoDelTecnico='asignado' OR a.estadoDelTecnico='reasignado' OR a.estadoDelTecnico='finalizado' OR a.estadoDelTecnico='pausado')"),
    
    @NamedQuery(name = "EncaRequerimientos.findByEstado", query = "SELECT e FROM EncaRequerimientos e WHERE (e.idEstado.idEstado = :idEstado OR e.idEstado.idEstado= :idEstado2 ) AND e.usuarioUsuario.idUnidadEjecutora.idUndEjecutora  = :id  ORDER BY e.idEncaRequerimiento ASC"),
    @NamedQuery(name = "EncaRequerimientos.findByEstado3", query = "SELECT e FROM EncaRequerimientos e WHERE e.idEstado.idEstado=3 AND e.usuarioUsuario.idUnidadEjecutora.idUndEjecutora = :iUnd  ORDER BY e.idEncaRequerimiento ASC"),
    @NamedQuery(name = "EncaRequerimientos.findByNumeroDeCaso2", query = "SELECT COUNT(e.idEncaRequerimiento) FROM EncaRequerimientos e, Usuarios u WHERE e.usuarioUsuario.usuarioUsuario=u.usuarioUsuario AND u.idUnidadEjecutora.idUndEjecutora=:idunide" )})

public class EncaRequerimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_enca_requerimiento")
    private Integer idEncaRequerimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_requerimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaRequerimiento;
    @Column(name = "hora_requerimiento")
    @Temporal(TemporalType.TIME)
    private Date horaRequerimiento;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "documentacion_completa")
    private Boolean documentacionCompleta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "equipo_propiedad")
    private boolean equipoPropiedad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1200)
    @Column(name = "descripcion_requerimiento")
    private String descripcionRequerimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 130)
    @Column(name = "solicitante")
    private String solicitante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio")
    private int anio;
    @Column(name = "num_caso")
    private Integer numCaso;
    @JoinColumn(name = "id_via_solicitud", referencedColumnName = "id_via_solicitud")
    @ManyToOne(optional = false)
    private ViaSolicitudes idViaSolicitud;
    @JoinColumn(name = "usuario_usuario", referencedColumnName = "usuario_usuario")
    @ManyToOne(optional = false)
    private Usuarios usuarioUsuario;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estados idEstado;
    @JoinColumn(name = "id_cat_serv", referencedColumnName = "id_cat_serv")
    @ManyToOne(optional = false)
    private CategoriaServicios idCatServ;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEncaRequerimiento")
    private List<Documentos> documentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEncaRequerimiento")
    private List<DetaRequerimientos> detaRequerimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "encaRequerimientos")
    private List<AsignarTecnico> asignarTecnicoList;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "usuario_usuario_solicitante", referencedColumnName = "usuario_usuario")
    @ManyToOne(optional = false)
    private Usuarios usuarioUsuarioSolicitante;

    public EncaRequerimientos() {
    }

    public EncaRequerimientos(Integer idEncaRequerimiento) {
        this.idEncaRequerimiento = idEncaRequerimiento;
    }

    public EncaRequerimientos(Integer idEncaRequerimiento, Date fechaRequerimiento, boolean equipoPropiedad, String descripcionRequerimiento, String solicitante, int anio) {
        this.idEncaRequerimiento = idEncaRequerimiento;
        this.fechaRequerimiento = fechaRequerimiento;
        this.equipoPropiedad = equipoPropiedad;
        this.descripcionRequerimiento = descripcionRequerimiento;
        this.solicitante = solicitante;
        this.anio = anio;
    }

    public Usuarios getUsuarioUsuarioSolicitante() {
        return usuarioUsuarioSolicitante;
    }

    public void setUsuarioUsuarioSolicitante(Usuarios usuarioUsuarioSolicitante) {
        this.usuarioUsuarioSolicitante = usuarioUsuarioSolicitante;
    }
    
    
    public Integer getIdEncaRequerimiento() {
        return idEncaRequerimiento;
    }

    public void setIdEncaRequerimiento(Integer idEncaRequerimiento) {
        this.idEncaRequerimiento = idEncaRequerimiento;
    }

    public Date getFechaRequerimiento() {
        SimpleDateFormat sdf=new SimpleDateFormat("dd/M/yyyy");
        Date d=null;
        try {
        d=sdf.parse(sdf.format(fechaRequerimiento).toString());    
        } catch (Exception e) {
        }
        return d;
    }

    public void setFechaRequerimiento(Date fechaRequerimiento) {
        this.fechaRequerimiento = fechaRequerimiento;
    }

    public Date getHoraRequerimiento() {
        return horaRequerimiento;
    }

    public void setHoraRequerimiento(Date horaRequerimiento) {
        this.horaRequerimiento = horaRequerimiento;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Boolean getDocumentacionCompleta() {
        return documentacionCompleta;
    }

    public void setDocumentacionCompleta(Boolean documentacionCompleta) {
        this.documentacionCompleta = documentacionCompleta;
    }

    public boolean getEquipoPropiedad() {
        return equipoPropiedad;
    }

    public void setEquipoPropiedad(boolean equipoPropiedad) {
        this.equipoPropiedad = equipoPropiedad;
    }

    public String getDescripcionRequerimiento() {
        return descripcionRequerimiento;
    }

    public void setDescripcionRequerimiento(String descripcionRequerimiento) {
        this.descripcionRequerimiento = descripcionRequerimiento;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Integer getNumCaso() {
        return numCaso;
    }

    public void setNumCaso(Integer numCaso) {
        this.numCaso = numCaso;
    }

    public ViaSolicitudes getIdViaSolicitud() {
        return idViaSolicitud;
    }

    public void setIdViaSolicitud(ViaSolicitudes idViaSolicitud) {
        this.idViaSolicitud = idViaSolicitud;
    }

    public Usuarios getUsuarioUsuario() {
        return usuarioUsuario;
    }

    public void setUsuarioUsuario(Usuarios usuarioUsuario) {
        this.usuarioUsuario = usuarioUsuario;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public CategoriaServicios getIdCatServ() {
        return idCatServ;
    }

    public void setIdCatServ(CategoriaServicios idCatServ) {
        this.idCatServ = idCatServ;
    }
    
        public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    

    @XmlTransient
    public List<Documentos> getDocumentosList() {
        return documentosList;
    }

    public void setDocumentosList(List<Documentos> documentosList) {
        this.documentosList = documentosList;
    }

    @XmlTransient
    public List<DetaRequerimientos> getDetaRequerimientosList() {
        return detaRequerimientosList;
    }

    public void setDetaRequerimientosList(List<DetaRequerimientos> detaRequerimientosList) {
        this.detaRequerimientosList = detaRequerimientosList;
    }

    @XmlTransient
    public List<AsignarTecnico> getAsignarTecnicoList() {
        return asignarTecnicoList;
    }

    public void setAsignarTecnicoList(List<AsignarTecnico> asignarTecnicoList) {
        this.asignarTecnicoList = asignarTecnicoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncaRequerimiento != null ? idEncaRequerimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncaRequerimientos)) {
            return false;
        }
        EncaRequerimientos other = (EncaRequerimientos) object;
        if ((this.idEncaRequerimiento == null && other.idEncaRequerimiento != null) || (this.idEncaRequerimiento != null && !this.idEncaRequerimiento.equals(other.idEncaRequerimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EncaRequerimientos[ idEncaRequerimiento=" + idEncaRequerimiento + " ]";
    }
    
}
