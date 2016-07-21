/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario Desarrollo
 */
@Entity
@Table(name = "deta_requerimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetaRequerimientos.findAll", query = "SELECT d FROM DetaRequerimientos d"),
    @NamedQuery(name = "DetaRequerimientos.findByIdDetaRequerimiento", query = "SELECT d FROM DetaRequerimientos d WHERE d.idDetaRequerimiento = :idDetaRequerimiento"),
    @NamedQuery(name = "DetaRequerimientos.findByFechaAtencion", query = "SELECT d FROM DetaRequerimientos d WHERE d.fechaAtencion = :fechaAtencion"),
    @NamedQuery(name = "DetaRequerimientos.findByHoraInicio", query = "SELECT d FROM DetaRequerimientos d WHERE d.horaInicio = :horaInicio"),
    @NamedQuery(name = "DetaRequerimientos.findByHoraFin", query = "SELECT d FROM DetaRequerimientos d WHERE d.horaFin = :horaFin"),
    @NamedQuery(name = "DetaRequerimientos.findByDiagnostico", query = "SELECT d FROM DetaRequerimientos d WHERE d.diagnostico = :diagnostico"),
    @NamedQuery(name = "DetaRequerimientos.findByDetaDescripcionServicio", query = "SELECT d FROM DetaRequerimientos d WHERE d.detaDescripcionServicio = :detaDescripcionServicio"),
    @NamedQuery(name = "DetaRequerimientos.findByDetaMateriales", query = "SELECT d FROM DetaRequerimientos d WHERE d.detaMateriales = :detaMateriales"),
    @NamedQuery(name = "DetaRequerimientos.findByBienIntervenido", query = "SELECT d FROM DetaRequerimientos d WHERE d.bienIntervenido = :bienIntervenido"),
    @NamedQuery(name = "DetaRequerimientos.findBySistemUser", query = "SELECT d FROM DetaRequerimientos d WHERE d.sistemUser = :sistemUser"),
    @NamedQuery(name = "DetaRequerimientos.findByFechaUser", query = "SELECT d FROM DetaRequerimientos d WHERE d.fechaUser = :fechaUser"),
 @NamedQuery(name = "DetaRequerimientos.findByEncaRequeri", query = "SELECT d FROM DetaRequerimientos d WHERE d.idEncaRequerimiento.idEncaRequerimiento = :idEncaRe ORDER BY d.fechaAtencion DESC" )})
public class DetaRequerimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_deta_requerimiento")
    private Integer idDetaRequerimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_atencion")
    @Temporal(TemporalType.DATE)
    private Date fechaAtencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @Size(max = 500)
    @Column(name = "diagnostico")
    private String diagnostico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "deta_descripcion_servicio")
    private String detaDescripcionServicio;
    @Size(max = 400)
    @Column(name = "deta_materiales")
    private String detaMateriales;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "bien_intervenido")
    private String bienIntervenido;
    @Size(max = 30)
    @Column(name = "sistem_user")
    private String sistemUser;
    @Column(name = "fecha_user")
    @Temporal(TemporalType.DATE)
    private Date fechaUser;
    @JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio")
    @ManyToOne(optional = false)
    private Servicios idServicio;
    @JoinColumn(name = "id_enca_requerimiento", referencedColumnName = "id_enca_requerimiento")
    @ManyToOne(optional = false)
    private EncaRequerimientos idEncaRequerimiento;

    public DetaRequerimientos() {
    }

    public DetaRequerimientos(Integer idDetaRequerimiento) {
        this.idDetaRequerimiento = idDetaRequerimiento;
    }

    public DetaRequerimientos(Integer idDetaRequerimiento, Date fechaAtencion, Date horaInicio, Date horaFin, String detaDescripcionServicio, String bienIntervenido) {
        this.idDetaRequerimiento = idDetaRequerimiento;
        this.fechaAtencion = fechaAtencion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.detaDescripcionServicio = detaDescripcionServicio;
        this.bienIntervenido = bienIntervenido;
    }

    public Integer getIdDetaRequerimiento() {
        return idDetaRequerimiento;
    }

    public void setIdDetaRequerimiento(Integer idDetaRequerimiento) {
        this.idDetaRequerimiento = idDetaRequerimiento;
    }

    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getDetaDescripcionServicio() {
        return detaDescripcionServicio;
    }

    public void setDetaDescripcionServicio(String detaDescripcionServicio) {
        this.detaDescripcionServicio = detaDescripcionServicio;
    }

    public String getDetaMateriales() {
        return detaMateriales;
    }

    public void setDetaMateriales(String detaMateriales) {
        this.detaMateriales = detaMateriales;
    }

    public String getBienIntervenido() {
        return bienIntervenido;
    }

    public void setBienIntervenido(String bienIntervenido) {
        this.bienIntervenido = bienIntervenido;
    }

    public String getSistemUser() {
        return sistemUser;
    }

    public void setSistemUser(String sistemUser) {
        this.sistemUser = sistemUser;
    }

    public Date getFechaUser() {
        return fechaUser;
    }

    public void setFechaUser(Date fechaUser) {
        this.fechaUser = fechaUser;
    }

    public Servicios getPIdServicio() {
        return idServicio;
    }

    public void setIdPServicio(Servicios idServicio) {
        this.idServicio = idServicio;
    }

    public EncaRequerimientos getIdEncaRequerimiento() {
        return idEncaRequerimiento;
    }

    public void setIdEncaRequerimiento(EncaRequerimientos idEncaRequerimiento) {
        this.idEncaRequerimiento = idEncaRequerimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetaRequerimiento != null ? idDetaRequerimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetaRequerimientos)) {
            return false;
        }
        DetaRequerimientos other = (DetaRequerimientos) object;
        if ((this.idDetaRequerimiento == null && other.idDetaRequerimiento != null) || (this.idDetaRequerimiento != null && !this.idDetaRequerimiento.equals(other.idDetaRequerimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetaRequerimientos[ idDetaRequerimiento=" + idDetaRequerimiento + " ]";
    }
    
}
