/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.Dao;
import dao.DaoLocal;
import entidades.UnidadesEjecutoras;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import util.FacesUtil;

/**
 *
 * @author Hugo Aguilar
 */
@ManagedBean
@SessionScoped
public class ControladorCatalogoUnidadEjecutora implements Serializable{
    private UnidadesEjecutoras nuevaUnidadEje=new UnidadesEjecutoras();
    private UnidadesEjecutoras unidadEjeSeleccionada=new UnidadesEjecutoras();
    private List<UnidadesEjecutoras> listaUnidades=new ArrayList<UnidadesEjecutoras>();
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Creates a new instance of ControladorCatalogoUnidadEjecutora
     */
    public ControladorCatalogoUnidadEjecutora() {
    }

    public UnidadesEjecutoras getNuevaUnidadEje() {
        return nuevaUnidadEje;
    }

    public void setNuevaUnidadEje(UnidadesEjecutoras nuevaUnidadEje) {
        this.nuevaUnidadEje = nuevaUnidadEje;
    }

    public UnidadesEjecutoras getUnidadEjeSeleccionada() {
        return unidadEjeSeleccionada;
    }

    public void setUnidadEjeSeleccionada(UnidadesEjecutoras unidadEjeSeleccionada) {
        this.unidadEjeSeleccionada = unidadEjeSeleccionada;
    }

    public List<UnidadesEjecutoras> getListaUnidades() {
        listaUnidades=getD().traeUnidadesEje();
        return listaUnidades;
    }

    public void setListaUnidades(List<UnidadesEjecutoras> listaUnidades) {
        this.listaUnidades = listaUnidades;
    }
    
    public void almacenarUnidadEjecutora(){
        if (nuevaUnidadEje.getJefeUndEjecutora().equals("") || nuevaUnidadEje.getNombreUndEjecutora().equals("") ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Campos Vacios!"));
        }else{
            if (validateEmail(nuevaUnidadEje.getCorreoJefeUndEjecutora()) || nuevaUnidadEje.getCorreoJefeUndEjecutora().equals("")) {
                try {
                getD().insertUnidadEjecutora(nuevaUnidadEje);
                
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Almacenado Exitosamente!"));
            } catch (Exception e) {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error!" + e.toString()));
            }
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correo Invalido!"));
            }
            
        }
        nuevaUnidadEje=new UnidadesEjecutoras();
        
    }
    public static boolean validateEmail(String email) {
	try{
	    
	    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	  
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}catch(Exception e){
		e.printStackTrace();
	}
	return false;
}
    public void modificarUnidadEje(){
         if (unidadEjeSeleccionada.getJefeUndEjecutora().equals("") || unidadEjeSeleccionada.getNombreUndEjecutora().equals("") ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Campos Vacios!"));
        }else{
             if (validateEmail(unidadEjeSeleccionada.getCorreoJefeUndEjecutora()) || unidadEjeSeleccionada.getCorreoJefeUndEjecutora().equals("")) {
                 try {
                 //int u=unidadEjeSeleccionada.getIdUndEjecutora();
                 //UnidadesEjecutoras uniTemp=d.traeUnidadEjecutora(id);
                  getD().updateUnidadEjecutora(unidadEjeSeleccionada);
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado exitosamente!"));
             } catch (Exception e) {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error! "+e.toString()));
             } 
             }else{
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correo Invalido"));
             }
            
            
         }
         unidadEjeSeleccionada=new UnidadesEjecutoras();
        
    }
    public int id;
    public void ver(){
        try{
    id=unidadEjeSeleccionada.getIdUndEjecutora();
        System.out.println(id);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        }
    
    public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }
}
