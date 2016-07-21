/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.Dao;
import dao.DaoLocal;
import entidades.Tecnicos;
import entidades.UnidadesEjecutoras;
import entidades.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import util.FacesUtil;


/**
 *
 * @author Claudia de Campos
 */
@ManagedBean
@SessionScoped
public class manejadorGestionCatalogo implements ValueChangeListener, Serializable {


    private UnidadesEjecutoras undEjecutoraSeleccionada = new UnidadesEjecutoras();
    private List<UnidadesEjecutoras> listaUndEjecutora = new ArrayList<UnidadesEjecutoras>();

    private List<Tecnicos> listaTecnicos = new ArrayList<Tecnicos>();
    private Tecnicos tecnicoSeleccionado = new Tecnicos();
    
    
    @ManagedProperty(value = "#{appSession}")
    private AppSession appSession;
    
    public AppSession getAppSession() {
        return appSession;
    }

    public void setAppSession(AppSession appSession) {
        this.appSession = appSession;
    }
    
       
    /**
     * Creates a new instance of manejadorGestionCatalogo
     */
    public manejadorGestionCatalogo() {
    }

    
 
    /**
     * Catálogo de Tecnicos
     */
    
    public List<Tecnicos> getListaTecnicos() {
        listaTecnicos = getD().traeTecnicos(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        return listaTecnicos;
    }

    public void setListaTecnicos(List<Tecnicos> listaTecnicos) {
        this.listaTecnicos = listaTecnicos;
    }
    
    public Tecnicos getTecnicoSeleccionado() {
        return tecnicoSeleccionado;
    }

    public void setTecnicoSeleccionado(Tecnicos tecnicoSeleccionado) {
        this.tecnicoSeleccionado = tecnicoSeleccionado;
    }

    


    /**
     * Catálogo de Unidades Ejecutoras
     */
    
    public UnidadesEjecutoras getUndEjecutoraSeleccionada() {
        return undEjecutoraSeleccionada;
    }

    public void setUndEjecutoraSeleccionada(UnidadesEjecutoras undEjecutoraSeleccionada) {
        this.undEjecutoraSeleccionada = undEjecutoraSeleccionada;
    }

    public List<UnidadesEjecutoras> getListaUndEjecutora() {
        return listaUndEjecutora;
    }

    public void setListaUndEjecutora(List<UnidadesEjecutoras> listaUndEjecutora) {
        this.listaUndEjecutora = listaUndEjecutora;
    }
   
    
    public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }

    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
       
    
    
}
