/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.Dao;
import dao.DaoLocal;
import entidades.TiposUsuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import util.FacesUtil;

/**
 *
 * @author Hugo Aguilar
 */
@ManagedBean
@SessionScoped
public class ControladorCatalogoTipoUsuario implements Serializable{
    private List<TiposUsuarios> listaTipoUsua=new ArrayList<TiposUsuarios>();
    private TiposUsuarios tipoUsuaSelecionado=new TiposUsuarios();
    private TiposUsuarios nuevoTipoUsua=new TiposUsuarios();

    /**
     * Creates a new instance of ControladorCatalogoTipoUsuario
     */
    public ControladorCatalogoTipoUsuario() {
    }

    public TiposUsuarios getNuevoTipoUsua() {
        return nuevoTipoUsua;
    }

    public void setNuevoTipoUsua(TiposUsuarios nuevoTipoUsua) {
        this.nuevoTipoUsua = nuevoTipoUsua;
    }

    public List<TiposUsuarios> getListaTipoUsua() {
        listaTipoUsua=getD().traeTipoUsuarios();
        return listaTipoUsua;
    }

    public void setListaTipoUsua(List<TiposUsuarios> listaTipoUsua) {
        this.listaTipoUsua = listaTipoUsua;
    }

    public TiposUsuarios getTipoUsuaSelecionado() {
        return tipoUsuaSelecionado;
    }

    public void setTipoUsuaSelecionado(TiposUsuarios tipoUsuaSelecionado) {
        this.tipoUsuaSelecionado = tipoUsuaSelecionado;
    }
    
    public void cambioNombre(ValueChangeEvent e){
    } 
    
     public void cambioDescripcion(ValueChangeEvent e){
    }
     
     public void modificar(){
         if (tipoUsuaSelecionado.getNombreTipoUsuario().equals("") || tipoUsuaSelecionado.getDescripcionTipoUsuario().equals("")) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No deben de haber campos vacios!"));
         }else{
         try {
             
             getD().updateTipoUsuario(tipoUsuaSelecionado);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado Exitosamente!"));
         } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado Fallida! "+ e.toString()));
         }
     }
        tipoUsuaSelecionado=new TiposUsuarios();   
     }
     
     public void insert(){
         try {
             if (nuevoTipoUsua.getNombreTipoUsuario().equals("") || nuevoTipoUsua.getDescripcionTipoUsuario().equals("")) {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No deben de haber campos vacios!"));
             
             }else{
                 getD().insertTipoUsuario(nuevoTipoUsua);
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Almacenado exitosamente!"));
                 
             }
             
         } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error! " + e.toString()));
         
         }
         nuevoTipoUsua=new TiposUsuarios();
     }
     
     public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }
}
