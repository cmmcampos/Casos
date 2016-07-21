/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.Dao;
import dao.DaoLocal;
import entidades.DireccionesNacionales;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import util.FacesUtil;

/**
 *
 * @author Hugo Aguilar
 */
@ManagedBean
@SessionScoped
public class ControladorCatalogoDirecNaci implements Serializable{
    private DireccionesNacionales nuevoDirecNac=new DireccionesNacionales();
    private DireccionesNacionales DirecNacioSeleccionada=new DireccionesNacionales();
    private List<DireccionesNacionales> listaDirecNacio=new ArrayList<DireccionesNacionales>();
  
    /**
     * Creates a new instance of ControladorCatalogoDirecNaci
     */
    public ControladorCatalogoDirecNaci() {
    }

    public DireccionesNacionales getNuevoDirecNac() {
        return nuevoDirecNac;
    }

    public void setNuevoDirecNac(DireccionesNacionales nuevoDirecNac) {
        this.nuevoDirecNac = nuevoDirecNac;
    }

    public DireccionesNacionales getDirecNacioSeleccionada() {
        return DirecNacioSeleccionada;
    }

    public void setDirecNacioSeleccionada(DireccionesNacionales DirecNacioSeleccionada) {
        this.DirecNacioSeleccionada = DirecNacioSeleccionada;
    }

    public List<DireccionesNacionales> getListaDirecNacio() {
        listaDirecNacio=getD().traeDireccionesNacionales();
        return listaDirecNacio;
    }

    public void setListaDirecNacio(List<DireccionesNacionales> listaDirecNacio) {
        this.listaDirecNacio = listaDirecNacio;
    }
    
    
  
    
    public void almacenarDireNac(){
        try{
        if (nuevoDirecNac.getDirectorNacional().equals("") || nuevoDirecNac.getNombreDirNacional().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Campos vacios!"));
        }else{
            System.out.println(nuevoDirecNac.getDirectorNacional());
            System.out.println(nuevoDirecNac.getNombreDirNacional());
                        
            getD().insertDireccionesNacionales(nuevoDirecNac);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Almacenado exitosamente!"));
        }
        }catch(Exception ex){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error! "+ ex.toString()));
        }
        nuevoDirecNac=new DireccionesNacionales();
    }
    public void modificarDirecNa(){
        try{
        if (DirecNacioSeleccionada.getDirectorNacional().equals("") || DirecNacioSeleccionada.getNombreDirNacional().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Campos Vacios!"));
        }else{
            
            getD().updateDireccionesNacionales(DirecNacioSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado exitosamente!"));
        }
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error! "+ ex.toString()));
        }
        DirecNacioSeleccionada=new DireccionesNacionales();
    }
    
    public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }
}
