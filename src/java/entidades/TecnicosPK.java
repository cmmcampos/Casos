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
public class TecnicosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_tecnico")
    private int idTecnico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_und_ejecutora")
    private int idUndEjecutora;

    public TecnicosPK() {
    }

    public TecnicosPK(int idTecnico, int idUndEjecutora) {
        this.idTecnico = idTecnico;
        this.idUndEjecutora = idUndEjecutora;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTecnico;
        hash += (int) idUndEjecutora;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TecnicosPK)) {
            return false;
        }
        TecnicosPK other = (TecnicosPK) object;
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
        return "entidades.TecnicosPK[ idTecnico=" + idTecnico + ", idUndEjecutora=" + idUndEjecutora + " ]";
    }
    
}
