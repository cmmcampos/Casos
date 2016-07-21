/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.Dao;
import dao.DaoLocal;
import entidades.Dependencias;
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
public class ControladorCatalogoDependencias implements Serializable{
    private Dependencias nuevaDependencia=new Dependencias();
    private Dependencias dependenciaSeleccionada=new Dependencias();
    private List<Dependencias> listaDependencias=new ArrayList<Dependencias>();
    private List<DireccionesInter> listaDirecInter=new ArrayList<DireccionesInter>();
    
    private List<DireccionesNacionales> listaDNac= new ArrayList<DireccionesNacionales>();
    private DireccionesNacionales direccionSeleccionada=new DireccionesNacionales();
    private Integer idDNacSeleccionada=0;
    
    private Integer idDInterSeleccionada=0;
    int varId2=-1;
    private Integer direccionInSeleccionada=-1;
      
  //  private DireccionesInter direcInterSeleccionada=new DireccionesInter();
    int varId=-1;
    
    
    
    /**
     * Creates a new instance of ControladorCatalogoDedendencias
     */
    public ControladorCatalogoDependencias() {
    }

    
    public Dependencias getNuevaDependencia() {
        return nuevaDependencia;
    }

    public void setNuevaDependencia(Dependencias nuevaDependencia) {
        this.nuevaDependencia = nuevaDependencia;
    }

    public Dependencias getDependenciaSeleccionada() {
        return dependenciaSeleccionada;
    }

    public void setDependenciaSeleccionada(Dependencias dependenciaSeleccionada) {
        this.dependenciaSeleccionada = dependenciaSeleccionada;
    }

    public List<Dependencias> getListaDependencias() {
        listaDependencias=getD().traeDepenDeIdDInter(direccionInSeleccionada);
        return listaDependencias;
    }

    public Integer getDireccionInSeleccionada() {
        return direccionInSeleccionada;
    }

    public void setDireccionInSeleccionada(Integer direccionInSeleccionada) {
        this.direccionInSeleccionada = direccionInSeleccionada;
    }

        
    public void setListaDependencias(List<Dependencias> listaDependencias) {
        this.listaDependencias = listaDependencias;
    }

    public List<DireccionesInter> getListaDirecInter() {
        listaDirecInter=getD().traeDireccionesInter();
        return listaDirecInter;
     }
    
    public void setListaDirecInter(List<DireccionesInter> listaDirecInter) {
        this.listaDirecInter = listaDirecInter;
    }
    
    public Integer getIdDInterSeleccionada() {
        return idDInterSeleccionada;
    }

    public void setIdDInterSeleccionada(Integer idDInterSeleccionada) {
        this.idDInterSeleccionada = idDInterSeleccionada;
    }

    public List<DireccionesNacionales> getListaDNac() {
        listaDNac=getD().traeDireccionesNacionales();
        return listaDNac;
    }

    public void setListaDNac(List<DireccionesNacionales> listaDNac) {
        this.listaDNac = listaDNac;
    }

    public DireccionesNacionales getDireccionSeleccionada() {
        return direccionSeleccionada;
    }

    public void setDireccionSeleccionada(DireccionesNacionales direccionSeleccionada) {
        this.direccionSeleccionada = direccionSeleccionada;
    }

    public Integer getIdDNacSeleccionada() {
        return idDNacSeleccionada;
    }

    public void setIdDNacSeleccionada(Integer idDNacSeleccionada) {
        this.idDNacSeleccionada = idDNacSeleccionada;
    }

    
    
              
    //
    
        
    public void almacenar(){
        try {
                //nuevaDependencia.setIdDirecInter(new DireccionesInter(direccionInSeleccionada));
                System.out.println("valores de nueva Dependencia" + nuevaDependencia);
                getD().insertDependencias(nuevaDependencia);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Almacenado exitosamente!"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error!: "+e.toString()));
            }
        
//        if (nuevaDependencia.getNombreDependencia().equals("")|| nuevaDependencia.getJefeDependencia().equals("")|| direccionInSeleccionada==-1) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hay campos vacios!"));
//            System.out.println("campo de dir inter" +nuevaDependencia.getIdDirecInter());
//        }else{
//            try {
//                nuevaDependencia.setIdDirecInter(new DireccionesInter(direccionInSeleccionada));
//                getD().insertDependencias(nuevaDependencia);
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Almacenado exitosamente!"));
//            } catch (Exception e) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error!: "+e.toString()));
//            }
//        }
        nuevaDependencia= new Dependencias();
        direccionInSeleccionada=-1;
    }
    
    public void modificar(){
        if (dependenciaSeleccionada.getNombreDependencia().equals("")|| dependenciaSeleccionada.getJefeDependencia().equals("")|| idDInterSeleccionada==-1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hay campos vacios!"));
        }else{
            try{
            dependenciaSeleccionada.setIdDirecInter(new DireccionesInter(idDInterSeleccionada));
                
            getD().updateDependencias(dependenciaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado Exitosamente!"));
            }catch(Exception e){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error! "+e.toString()));
            }
        }
        idDInterSeleccionada=-1;
        dependenciaSeleccionada=new Dependencias();
        
    }
    
    public void setearId(){
        idDInterSeleccionada=dependenciaSeleccionada.getIdDirecInter().getIdDirecInter();
                
    }
    
    
    public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }
    
}
