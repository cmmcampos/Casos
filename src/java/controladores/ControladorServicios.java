/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.Dao;
import dao.DaoLocal;
import entidades.CategoriaServicios;
import entidades.Servicios;
import entidades.UnidadesEjecutoras;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import util.FacesUtil;

/**
 *
 * 
 */
@ManagedBean
@SessionScoped
public class ControladorServicios implements Serializable{
    private Servicios nuevoServicios= new Servicios();
    private Servicios servicioSeleccionado= new Servicios();
    private List<Servicios> listaServicios=new ArrayList<Servicios>();
    private List<UnidadesEjecutoras> listaUnEje= new ArrayList<UnidadesEjecutoras>();
    private UnidadesEjecutoras unidadSeleccionada= new UnidadesEjecutoras();
    private int idCat;

    @ManagedProperty(value="#{appSession}")
    private AppSession appSession;

    public AppSession getAppSession() {
        return appSession;
    }

    public void setAppSession(AppSession appSession) {
        this.appSession = appSession;
    }
    
    
    /**
     * Creates a new instance of ControladorCatalogoServicios
     */
    public ControladorServicios() {
    }


       
    /**
     * Para Servicios**********
     */
    
        public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public Servicios getNuevoServicios() {
        return nuevoServicios;
    }

    public void setNuevoServicios(Servicios nuevoServicios) {
        this.nuevoServicios = nuevoServicios;
    }

    public Servicios getServicioSeleccionado() {
        return servicioSeleccionado;
    }

    public void setServicioSeleccionado(Servicios servicioSeleccionado) {
        this.servicioSeleccionado = servicioSeleccionado;
    }

    public List<Servicios> getListaServicios() {
        listaServicios =getD().traeServicios(appSession);
        return listaServicios;
    }

    public void setListaServicios(List<Servicios> listaServicios) {
        this.listaServicios= listaServicios;
                   
    }

    public List<UnidadesEjecutoras> getListaUnEje() {
        listaUnEje = getD().traeUnidadesEje();
        return listaUnEje;
        
    }

    public void setListaUnEje(List<UnidadesEjecutoras> listaUnEje) {
        this.listaUnEje= listaUnEje;
    }

    public UnidadesEjecutoras getUnidadSeleccionada() {
        return unidadSeleccionada;
    }

    public void setUnidadSeleccionada(UnidadesEjecutoras unidadSeleccionada) {
        this.unidadSeleccionada = unidadSeleccionada;
    }
    
       
    
    
    public void almacenarServicio(){
        try {
            if (nuevoServicios.getNombreServicio().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El nombre está vacio!"));
            }else{
                nuevoServicios.setIdCatServ(new CategoriaServicios(idCat));
                nuevoServicios.setIdUndEjecutora(appSession.getUsuario().getIdUnidadEjecutora());
                getD().insertServicios(nuevoServicios);
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Almacenado exitosamente!"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: "+e.toString()));
        }
        nuevoServicios=new Servicios();
    }
    
    
    public void modificarServicio(){
        try {
            if (servicioSeleccionado.getNombreServicio().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El nombre está vacio"));
            }else{
                getD().updateServicio(servicioSeleccionado);
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado Exitosamente"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: "+e.toString()));
        }
        servicioSeleccionado=new Servicios();
    }
    
    public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }
    
}
