/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Usuario Desarrollo
 */
@Embeddable
public class AsignarTecnicoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_enca_requerimiento")
    private int idEncaRequerimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tecnico")
    private int idTecnico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_und_ejecutora")
    private int idUndEjecutora;

    public AsignarTecnicoPK() {
    }

    public AsignarTecnicoPK(int idEncaRequerimiento, int idTecnico, int idUndEjecutora) {
        this.idEncaRequerimiento = idEncaRequerimiento;
        this.idTecnico = idTecnico;
        this.idUndEjecutora = idUndEjecutora;
    }

    public int getIdEncaRequerimiento() {
        return idEncaRequerimiento;
    }

    public void setIdEncaRequerimiento(int idEncaRequerimiento) {
        this.idEncaRequerimiento = idEncaRequerimiento;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public int getIdUndEjecutora() {
        return idUndEjecutora;
    }

    public void setIdUndEjecutora(int idUndEjecutora) {
        this.idUndEjecutora = idUndEjecutora;
    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (int) idEncaRequerimiento;
//        hash += (int) idTecnico;
//        hash += (int) idUndEjecutora;
//        return hash;
//    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignarTecnicoPK)) {
            return false;
        }
        AsignarTecnicoPK other = (AsignarTecnicoPK) object;
        if (this.idEncaRequerimiento != other.idEncaRequerimiento) {
            return false;
        }
        if (this.idTecnico != other.idTecnico) {
            return false;
        }
        if (this.idUndEjecutora != other.idUndEjecutora) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AsignarTecnicoPK[ idEncaRequerimiento=" + idEncaRequerimiento + ", idTecnico=" + idTecnico + ", idUndEjecutora=" + idUndEjecutora + " ]";
    }
    
}
