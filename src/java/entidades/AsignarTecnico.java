/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "asignar_tecnico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsignarTecnico.findAll", query = "SELECT a FROM AsignarTecnico a"),
    @NamedQuery(name = "AsignarTecnico.numeroDeCasosDeUnidadEjecutora", query = "SELECT  COUNT (a.asignarTecnicoPK.idUndEjecutora) FROM AsignarTecnico a WHERE a.asignarTecnicoPK.idUndEjecutora = :idUnidadEjecutora"),
    @NamedQuery(name = "AsignarTecnico.findByIdEncaRequerimiento", query = "SELECT a FROM AsignarTecnico a WHERE a.asignarTecnicoPK.idEncaRequerimiento = :idEncaRequerimiento"),
    @NamedQuery(name = "AsignarTecnico.findByIdTecnico", query = "SELECT a FROM AsignarTecnico a WHERE a.asignarTecnicoPK.idTecnico = :idTecnico"),
    @NamedQuery(name = "AsignarTecnico.findByFechaAsigna", query = "SELECT a FROM AsignarTecnico a WHERE a.fechaAsigna = :fechaAsigna"),
    @NamedQuery(name = "AsignarTecnico.findByTiempoAtencion", query = "SELECT a FROM AsignarTecnico a WHERE a.tiempoAtencion = :tiempoAtencion"),
    @NamedQuery(name = "AsignarTecnico.findByDescripcionAsigna", query = "SELECT a FROM AsignarTecnico a WHERE a.descripcionAsigna = :descripcionAsigna"),
    @NamedQuery(name = "AsignarTecnico.findByUsuarioAsigna", query = "SELECT a FROM AsignarTecnico a WHERE a.usuarioAsigna = :usuarioAsigna"),
    @NamedQuery(name = "AsignarTecnico.findByPk" ,query = "SELECT a FROM AsignarTecnico a WHERE a.asignarTecnicoPK.idEncaRequerimiento = :idEncaRequeri AND a.asignarTecnicoPK.idTecnico = :idTec AND a.asignarTecnicoPK.idUndEjecutora = :idUnidadEje")})
public class AsignarTecnico implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsignarTecnicoPK asignarTecnicoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_asigna")
    @Temporal(TemporalType.DATE)
    public Date fechaAsigna;
    //@Size(max = 50)
    @Column(name = "tiempo_atencion")
    private float tiempoAtencion;
    @Size(max = 400)
    @Column(name = "descripcion_asigna")
    private String descripcionAsigna;
    @Size(max = 30)
    @Column(name = "usuario_asigna")
    private String usuarioAsigna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "peso")
    private Integer peso;
    @JoinColumns({
        @JoinColumn(name = "id_tecnico", referencedColumnName = "id_tecnico", insertable = false, updatable =false),
        @JoinColumn(name = "id_und_ejecutora", referencedColumnName = "id_und_ejecutora", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Tecnicos tecnicos;
    @JoinColumn(name = "id_enca_requerimiento", referencedColumnName = "id_enca_requerimiento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EncaRequerimientos encaRequerimientos;
    @Size(max = 90)
    @Column(name = "estado_del_tecnico")
    private String estadoDelTecnico;
    @Size(max = 90)
    @Column(name = "pausado")
    private String pausado;
    @Column(name = "require_transporte")
    private boolean requiereTransporte;
    @Size(max = 90)
    @Column(name = "tipo_de_tiempo")
    private String tipoDeTiempo;

    public String getTipoDeTiempo() {
        return tipoDeTiempo;
    }

    public void setTipoDeTiempo(String tipoDeTiempo) {
        this.tipoDeTiempo = tipoDeTiempo;
    }

    public boolean isRequiereTransporte() {
        return requiereTransporte;
    }

    public void setRequiereTransporte(boolean requiereTransporte) {
        this.requiereTransporte = requiereTransporte;
    }
  
    
    
    public String getEstadoDelTecnico() {
        return estadoDelTecnico;
    }
    
    
    public void setEstadoDelTecnico(String estadoDelTecnico) {
        this.estadoDelTecnico = estadoDelTecnico;
    }

    public String getPausado() {
        return pausado;
    }

    public void setPausado(String pausado) {
        this.pausado = pausado;
    }
    
    
    public AsignarTecnico() {
    }

    public AsignarTecnico(AsignarTecnicoPK asignarTecnicoPK) {
        this.asignarTecnicoPK = asignarTecnicoPK;
    }

    public AsignarTecnico(AsignarTecnicoPK asignarTecnicoPK, Date fechaAsigna, Integer peso) {
        this.asignarTecnicoPK = asignarTecnicoPK;
        this.fechaAsigna = fechaAsigna;
        this.peso = peso;
    }

    public AsignarTecnico(int idEncaRequerimiento, int idTecnico, int idUndEjecutora) {
        this.asignarTecnicoPK = new AsignarTecnicoPK(idEncaRequerimiento, idTecnico, idUndEjecutora);
    }

    public AsignarTecnicoPK getAsignarTecnicoPK() {
        return asignarTecnicoPK;
    }

    public void setAsignarTecnicoPK(AsignarTecnicoPK asignarTecnicoPK) {
        this.asignarTecnicoPK = asignarTecnicoPK;
    }

    public Date getFechaAsigna() {
        return fechaAsigna;
    }

    public void setFechaAsigna(Date fechaAsigna) {
        this.fechaAsigna = fechaAsigna;
    }

    public float getTiempoAtencion() {
        return tiempoAtencion;
    }

    public void setTiempoAtencion(float tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    public String getDescripcionAsigna() {
        return descripcionAsigna;
    }

    public void setDescripcionAsigna(String descripcionAsigna) {
        this.descripcionAsigna = descripcionAsigna;
    }

    public String getUsuarioAsigna() {
        return usuarioAsigna;
    }

    public void setUsuarioAsigna(String usuarioAsigna) {
        this.usuarioAsigna = usuarioAsigna;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Tecnicos getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(Tecnicos tecnicos) {
        this.tecnicos = tecnicos;
    }

    public EncaRequerimientos getEncaRequerimientos() {
        return encaRequerimientos;
    }

    public void setEncaRequerimientos(EncaRequerimientos encaRequerimientos) {
        this.encaRequerimientos = encaRequerimientos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asignarTecnicoPK != null ? asignarTecnicoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignarTecnico)) {
            return false;
        }
        AsignarTecnico other = (AsignarTecnico) object;
        if ((this.asignarTecnicoPK == null && other.asignarTecnicoPK != null) || (this.asignarTecnicoPK != null && !this.asignarTecnicoPK.equals(other.asignarTecnicoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AsignarTecnico[ asignarTecnicoPK=" + asignarTecnicoPK + " ]";
    }
    
}
