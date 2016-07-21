/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.Dao;
import dao.DaoLocal;
import entidades.ViaSolicitudes;
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
public class ControladorCatalogoViaSolicitudes implements Serializable{
    
    private ViaSolicitudes nuevaViaSolicitud=new ViaSolicitudes();
    private List<ViaSolicitudes> listaDeViaSolicitudes=new ArrayList<ViaSolicitudes>();
    private ViaSolicitudes viaSolicitudSeleccionada=new ViaSolicitudes();

    /**
     * Creates a new instance of ControladorCatalogoViaSolicitudes
     */
    public ControladorCatalogoViaSolicitudes() {
    }

    public ViaSolicitudes getNuevaViaSolicitud() {
        return nuevaViaSolicitud;
    }

    public void setNuevaViaSolicitud(ViaSolicitudes nuevaViaSolicitud) {
        this.nuevaViaSolicitud = nuevaViaSolicitud;
    }

    public List<ViaSolicitudes> getListaDeViaSolicitudes() {
        try{
        listaDeViaSolicitudes=getD().traeViaSolicitudes();
        }catch(Exception e){   
            
            System.out.println(e.toString());   
        }
        return listaDeViaSolicitudes;
    }

    public void setListaDeViaSolicitudes(List<ViaSolicitudes> listaDeViaSolicitudes) {
        this.listaDeViaSolicitudes = listaDeViaSolicitudes;
    }

    public ViaSolicitudes getViaSolicitudSeleccionada() {
        return viaSolicitudSeleccionada;
    }

    public void setViaSolicitudSeleccionada(ViaSolicitudes viaSolicitudSeleccionada) {
        this.viaSolicitudSeleccionada = viaSolicitudSeleccionada;
    }
    public void almacenar(){
        if (nuevaViaSolicitud.getNombreViaSolicitud().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El campo via solicitud está vacio!"));
        }else{
            try {
                getD().insertViaSolicitudes(nuevaViaSolicitud);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Almacenado exitosamente!"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: "+e.toString()));
            }
        }
        nuevaViaSolicitud=new ViaSolicitudes();
    }
    public void modificar(){
        if (viaSolicitudSeleccionada.getNombreViaSolicitud().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El campo via solicitud está vacio!"));
        }else{
            try {
                getD().updateViaSolicitudes(viaSolicitudSeleccionada);
            } catch (Exception e) {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: "+e.toString()));
            }
            
        }
        viaSolicitudSeleccionada=new ViaSolicitudes();
        
    }

    public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }

}
