/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.Dao;
import dao.DaoLocal;
import entidades.DireccionesInter;
import entidades.DireccionesNacionales;
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
public class ControladorCatalogoDireInter implements Serializable{
    private DireccionesInter nuevaDirecInter=new DireccionesInter();
    private DireccionesInter direcInterSeleccionada=new DireccionesInter();
    private List<DireccionesInter> listaDirecInter=new ArrayList<DireccionesInter>();
    private List<DireccionesNacionales> listaDireccNacionales=new ArrayList<DireccionesNacionales>();
    private int idDireNacionalSeleccionada=0;
    int varId=-1;
    private int direccionNacionalSeleccionada=-1;
    /**
     * Creates a new instance of ControladorCatalogoDireInter
     */
    public ControladorCatalogoDireInter() {
    }

    public DireccionesInter getNuevaDirecInter() {
        return nuevaDirecInter;
    }

    public void setNuevaDirecInter(DireccionesInter nuevaDirecInter) {
        this.nuevaDirecInter = nuevaDirecInter;
    }

    public DireccionesInter getDirecInterSeleccionada() {
        return direcInterSeleccionada;
    }

    public void setDirecInterSeleccionada(DireccionesInter direcInterSeleccionada) {
        this.direcInterSeleccionada = direcInterSeleccionada;
    }

    public List<DireccionesInter> getListaDirecInter() {
        listaDirecInter=getD().traeDirecInterDeIdNacio(direccionNacionalSeleccionada);
        return listaDirecInter;
    }

    public int getDireccionNacionalSeleccionada() {
        return direccionNacionalSeleccionada;
    }

    public void setDireccionNacionalSeleccionada(int direccionNacionalSeleccionada) {
        this.direccionNacionalSeleccionada = direccionNacionalSeleccionada;
    }

    public void setListaDirecInter(List<DireccionesInter> listaDirecInter) {
        this.listaDirecInter = listaDirecInter;
    }

    public List<DireccionesNacionales> getListaDireccNacionales() {
        listaDireccNacionales=getD().traeDireccionesNacionales();
        return listaDireccNacionales;
    }

    public void setListaDireccNacionales(List<DireccionesNacionales> listaDireccNacionales) {
        this.listaDireccNacionales = listaDireccNacionales;
    }

    public int getIdDireNacionalSeleccionada() {
        return idDireNacionalSeleccionada;
    }

    public void setIdDireNacionalSeleccionada(int idDireNacionalSeleccionada) {
        this.idDireNacionalSeleccionada = idDireNacionalSeleccionada;
    }
    
    public void almacenar(){
        if (nuevaDirecInter.getNombreDirecInter().equals("")|| nuevaDirecInter.getDirectorInter().equals("")|| direccionNacionalSeleccionada==-1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hay campos vacios!"));
        }else{
            try {
                nuevaDirecInter.setIdDirNacional(new DireccionesNacionales(direccionNacionalSeleccionada));
                getD().insertDireccionInter(nuevaDirecInter);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Almacenado exitosamente!"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error!: "+e.toString()));
            }
        }
        nuevaDirecInter=new DireccionesInter();
        direccionNacionalSeleccionada=-1;
    }
    
    public void modificar(){
        if (direcInterSeleccionada.getNombreDirecInter().equals("")||direcInterSeleccionada.getDirectorInter().equals("")|| idDireNacionalSeleccionada==-1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hay campos vacios!"));
        }else{
            try{
            direcInterSeleccionada.setIdDirNacional(new DireccionesNacionales(idDireNacionalSeleccionada));
            getD().updateDireccionInter(direcInterSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado Exitosamente!"));
            }catch(Exception e){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error! "+e.toString()));
            }
        }
        idDireNacionalSeleccionada=-1;
        direcInterSeleccionada=new DireccionesInter();
    }
    
    public void setearId(){
        idDireNacionalSeleccionada=direcInterSeleccionada.getIdDirNacional().getIdDirNacional();
    }
    
    
    public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }
    
}
