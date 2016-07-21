/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario Desarrollo
 */
@Entity
@Table(name = "documentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documentos.findAll", query = "SELECT d FROM Documentos d"),
    @NamedQuery(name = "Documentos.findByIdDocumento", query = "SELECT d FROM Documentos d WHERE d.idDocumento = :idDocumento"),
    @NamedQuery(name = "Documentos.findByNombreDocumento", query = "SELECT d FROM Documentos d WHERE d.nombreDocumento = :nombreDocumento"),
    @NamedQuery(name = "Documentos.findByDescripcionDocumento", query = "SELECT d FROM Documentos d WHERE d.descripcionDocumento = :descripcionDocumento"),
    @NamedQuery(name = "Documentos.findByUrlDocumento", query = "SELECT d FROM Documentos d WHERE d.urlDocumento = :urlDocumento"),
    @NamedQuery(name = "Documentos.findByIdEncarequerimiento", query = "SELECT d FROM Documentos d WHERE d.idEncaRequerimiento = :idEncaRequerimiento")})
public class Documentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_documento")
    private Integer idDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_documento")
    private String nombreDocumento;
    @Size(max = 400)
    @Column(name = "descripcion_documento")
    private String descripcionDocumento;
    @Size(max = 400)
    @Column(name = "url_documento")
    private String urlDocumento;
    @JoinColumn(name = "id_enca_requerimiento", referencedColumnName = "id_enca_requerimiento")
    @ManyToOne(optional = false)
    private EncaRequerimientos idEncaRequerimiento;
    @Column(name = "nombre_unico")
    private String nombreUnico;
    
    
    public Documentos() {
    }

    public Documentos(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNombreUnico() {
        return nombreUnico;
    }

    public void setNombreUnico(String nombreUnico) {
        this.nombreUnico = nombreUnico;
    }

    public Documentos(Integer idDocumento, String nombreDocumento) {
        this.idDocumento = idDocumento;
        this.nombreDocumento = nombreDocumento;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getDescripcionDocumento() {
        return descripcionDocumento;
    }

    public void setDescripcionDocumento(String descripcionDocumento) {
        this.descripcionDocumento = descripcionDocumento;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
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
        hash += (idDocumento != null ? idDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documentos)) {
            return false;
        }
        Documentos other = (Documentos) object;
        if ((this.idDocumento == null && other.idDocumento != null) || (this.idDocumento != null && !this.idDocumento.equals(other.idDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Documentos[ idDocumento=" + idDocumento + " ]";
    }
    
}
