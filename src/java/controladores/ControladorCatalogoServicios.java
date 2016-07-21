/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.Dao;
import dao.DaoLocal;
import entidades.CategoriaServicios;
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
public class ControladorCatalogoServicios implements Serializable{
    private CategoriaServicios nuevaCategoriaDeServicio=new CategoriaServicios();
    private CategoriaServicios categoriaDeServiSeleccionada=new CategoriaServicios();
    private List<CategoriaServicios> listaCategoriasServicios=new ArrayList<CategoriaServicios>();
    private List<UnidadesEjecutoras> listaUnEje= new ArrayList<UnidadesEjecutoras>();
    private UnidadesEjecutoras unidadSeleccionada= new UnidadesEjecutoras();

    
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
    public ControladorCatalogoServicios() {
    }

    
    /**
     * Para Categoria de Servicios
     */
    public CategoriaServicios getNuevaCategoriaDeServicio() {
        return nuevaCategoriaDeServicio;
    }

    public void setNuevaCategoriaDeServicio(CategoriaServicios nuevaCategoriaDeServicio) {
        this.nuevaCategoriaDeServicio = nuevaCategoriaDeServicio;
    }

    public CategoriaServicios getCategoriaDeServiSeleccionada() {
        return categoriaDeServiSeleccionada;
    }

    public void setCategoriaDeServiSeleccionada(CategoriaServicios categoriaDeServiSeleccionada) {
        this.categoriaDeServiSeleccionada = categoriaDeServiSeleccionada;
    }

    public List<CategoriaServicios> getListaCategoriasServicios() {
        listaCategoriasServicios= getD().traeCategoriaServicios(appSession);
        return listaCategoriasServicios;
    }

    public void setListaCategoriasServicios(List<CategoriaServicios> listaCategoriasServicios) {
        this.listaCategoriasServicios = listaCategoriasServicios;
    }

    public void almacenar(){
        try {
            if (nuevaCategoriaDeServicio.getNombreCatServ().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El nombre está vacio!"));
            }else{
                
                nuevaCategoriaDeServicio.setIdUndEjecutora(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
                getD().insertCategoriaServicio(nuevaCategoriaDeServicio);
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Almacenado exitosamente!"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: "+e.toString()));
        }
        nuevaCategoriaDeServicio=new CategoriaServicios();
    }
    
    public void modificar(){
        try {
            if (categoriaDeServiSeleccionada.getNombreCatServ().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El nombre está vacio"));
            }else{
                getD().updateCategoriaServicio(categoriaDeServiSeleccionada);
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado Exitosamente"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: "+e.toString()));
        }
        categoriaDeServiSeleccionada=new CategoriaServicios();
    }

    
    
     public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }
    
}
