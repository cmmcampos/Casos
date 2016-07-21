/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.Dao;
import dao.DaoLocal;
import entidades.Estados;
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
public class ControladorCatalogoEstado implements Serializable{
    private Estados nuevoEstado=new Estados();
    private Estados estadoSeleccionado=new Estados();
    private List<Estados> listaDeEstado=new ArrayList<Estados>();

    /**
     * Creates a new instance of ControladorCatalogoEstado
     */
    public ControladorCatalogoEstado() {
    }

    public Estados getNuevoEstado() {
        return nuevoEstado;
    }

    public void setNuevoEstado(Estados nuevoEstado) {
        this.nuevoEstado = nuevoEstado;
    }

    public Estados getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(Estados estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

    public List<Estados> getListaDeEstado() {
        try {
           listaDeEstado= getD().traeEstados();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return listaDeEstado;
    }

    public void setListaDeEstado(List<Estados> listaDeEstado) {
        this.listaDeEstado = listaDeEstado;
    }
    
    public void almacenar(){
        try {
            if (nuevoEstado.getNombreEstado().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El campo nombre está vacio!"));
            }else{
                getD().insertEstados(nuevoEstado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Almacenado Exitosamente!"));
                
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: "+ e.toString()));
        }
        nuevoEstado=new Estados();
    }
    
    public void modificar(){
        try {
            if (estadoSeleccionado.getNombreEstado().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El campo nombre está vacio!"));
            }else{
                getD().updateEstados(estadoSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado Exitosamente!"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: "+e.toString()));
        }
        estadoSeleccionado=new Estados();
    }
    
    public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }
}
