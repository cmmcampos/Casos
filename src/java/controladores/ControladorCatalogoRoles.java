/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.Dao;
import dao.DaoLocal;
import entidades.Roles;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import util.FacesUtil;

/**
 *
 * 
 */
@ManagedBean
@SessionScoped
public class ControladorCatalogoRoles implements Serializable{
    private Roles rolSeleccionado=new Roles();
    private Roles rolNuevo=new Roles();
    private List<Roles> listaRoles=new ArrayList<Roles>();

    /**
     * Creates a new instance of ControladorCatalogoRoles
     */
    public ControladorCatalogoRoles() {
    }

    public Roles getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Roles rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public Roles getRolNuevo() {
        return rolNuevo;
    }

    public void setRolNuevo(Roles rolNuevo) {
        this.rolNuevo = rolNuevo;
    }

    public List<Roles> getListaRoles() {
        listaRoles=getD().traeRoles();
        return listaRoles;
    }

    public void setListaRoles(List<Roles> listaRoles) {
        this.listaRoles = listaRoles;
    }
    
    public void agregarRol(){
        try{
        if (rolNuevo.getNombreRol().equals("") || rolNuevo.getDescripcionRol().equals("")) {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Complete todos los campos!"));
        }else{
            getD().insertRoles(rolNuevo);
            rolNuevo=new Roles();
                       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Almacenado exitosamente!"));

        }
    }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error!: "+ex.toString()));
    }

}
    public void modificar(){
        try {
            if (rolSeleccionado.getNombreRol().equals("") || rolSeleccionado.getDescripcionRol().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Complete todos los campos!"));
            }else{
                getD().updateRoles(rolSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado exitosamente!"));
            }
        } catch (Exception e) {
        }
    }
    
    public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }
}
