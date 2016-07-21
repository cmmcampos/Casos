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

/**
 *
 * @author Claudia de Campos
 */
@Entity
@Table(name = "reque_verificados")
@NamedQueries({
    @NamedQuery(name = "RequeVerificados.findAll", query = "SELECT r FROM RequeVerificados r"),
    @NamedQuery(name = "RequeVerificados.findByIdRequeVerificado", query = "SELECT r FROM RequeVerificados r WHERE r.idRequeVerificado = :idRequeVerificado"),
    @NamedQuery(name = "RequeVerificados.findByFechaRequeVerificado", query = "SELECT r FROM RequeVerificados r WHERE r.fechaRequeVerificado = :fechaRequeVerificado"),
    @NamedQuery(name = "RequeVerificados.findByDescripRequeVerificado", query = "SELECT r FROM RequeVerificados r WHERE r.descripRequeVerificado = :descripRequeVerificado"),
    @NamedQuery(name = "RequeVerificados.findByVerificado", query = "SELECT r FROM RequeVerificados r WHERE r.verificado = :verificado"),
    @NamedQuery(name = "RequeVerificados.findByUsuarioVerifica", query = "SELECT r FROM RequeVerificados r WHERE r.usuarioVerifica = :usuarioVerifica"),
    @NamedQuery(name = "RequeVerificados.findByIdUndEjecutora", query = "SELECT r FROM RequeVerificados r WHERE r.idUndEjecutora = :idUndEjecutora")})
public class RequeVerificados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reque_verificado")
    private Integer idRequeVerificado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_reque_verificado")
    @Temporal(TemporalType.DATE)
    private Date fechaRequeVerificado;
    @Size(max = 500)
    @Column(name = "descrip_reque_verificado")
    private String descripRequeVerificado;
    @Column(name = "verificado")
    private Boolean verificado;
    @Size(max = 2147483647)
    @Column(name = "usuario_verifica")
    private String usuarioVerifica;
    @Column(name = "id_und_ejecutora")
    private Integer idUndEjecutora;
    @JoinColumn(name = "id_enca_requerimiento", referencedColumnName = "id_enca_requerimiento")
    @ManyToOne(optional = false)
    private EncaRequerimientos idEncaRequerimiento;

    public RequeVerificados() {
    }

    public RequeVerificados(Integer idRequeVerificado) {
        this.idRequeVerificado = idRequeVerificado;
    }

    public RequeVerificados(Integer idRequeVerificado, Date fechaRequeVerificado) {
        this.idRequeVerificado = idRequeVerificado;
        this.fechaRequeVerificado = fechaRequeVerificado;
    }

    public Integer getIdRequeVerificado() {
        return idRequeVerificado;
    }

    public void setIdRequeVerificado(Integer idRequeVerificado) {
        this.idRequeVerificado = idRequeVerificado;
    }

    public Date getFechaRequeVerificado() {
        return fechaRequeVerificado;
    }

    public void setFechaRequeVerificado(Date fechaRequeVerificado) {
        this.fechaRequeVerificado = fechaRequeVerificado;
    }

    public String getDescripRequeVerificado() {
        return descripRequeVerificado;
    }

    public void setDescripRequeVerificado(String descripRequeVerificado) {
        this.descripRequeVerificado = descripRequeVerificado;
    }

    public Boolean getVerificado() {
        return verificado;
    }

    public void setVerificado(Boolean verificado) {
        this.verificado = verificado;
    }

    public String getUsuarioVerifica() {
        return usuarioVerifica;
    }

    public void setUsuarioVerifica(String usuarioVerifica) {
        this.usuarioVerifica = usuarioVerifica;
    }

    public Integer getIdUndEjecutora() {
        return idUndEjecutora;
    }

    public void setIdUndEjecutora(Integer idUndEjecutora) {
        this.idUndEjecutora = idUndEjecutora;
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
        hash += (idRequeVerificado != null ? idRequeVerificado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequeVerificados)) {
            return false;
        }
        RequeVerificados other = (RequeVerificados) object;
        if ((this.idRequeVerificado == null && other.idRequeVerificado != null) || (this.idRequeVerificado != null && !this.idRequeVerificado.equals(other.idRequeVerificado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RequeVerificados[ idRequeVerificado=" + idRequeVerificado + " ]";
    }
    
}
