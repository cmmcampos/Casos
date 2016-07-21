package util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import contex.AppContext;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * 
 * @author dionicio
 */
public class FacesUtil {

	public static boolean eventoReal(ValueChangeEvent vce) {
		return (vce.getNewValue() != null && !vce.getNewValue().equals(""));
	}

	public static ArrayList getListToSelect(List listado) {
		ArrayList select = new ArrayList();
		Iterator iterator;
		Object objecto;
		iterator = listado.iterator();
		while (iterator.hasNext()) {
			objecto = iterator.next();
			select.add(new SelectItem(objecto, objecto.toString()));
		}
		return select;
	}

	public static void addMensajeError(String mensaje) {
		getFacesContext().addMessage("",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null));
	}

	public static void addMensaje(String mensaje) {
		getFacesContext().addMessage("",
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null));
	}

	private static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static Object getEjb(String remoteEjb) {	
            Object instance = null;
        try {
            Context context = new InitialContext();
            instance= context.lookup(remoteEjb);
        } catch (NamingException ex) {
            Logger.getLogger(FacesUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instance;
	}
	
	public static Object getSpringBean(String springBean) {
	        return AppContext.getBeanSpring(springBean);
	    }

}
