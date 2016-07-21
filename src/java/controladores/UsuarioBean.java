/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.Dao;
import dao.DaoLocal;
import entidades.Usuarios;

import java.io.Serializable;
import util.FacesUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


/**
 *
 * @author dionicio
 */

@ManagedBean
@ViewScoped
public class UsuarioBean  implements Serializable {

    private String usuario;
    private String password;
  
    private String urlActual;
    private String usuarioSH;
    private List  roles;
   
    
    @ManagedProperty(value="#{appSession}")
    private AppSession appSession;

    
    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
        
    }

    public String logiAction() throws NamingException {
        String accion = null;
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(usuario, password);
            Authentication result = getAuthenticationManager().authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            //return "/contenido/cat_listaCasosAsignados.xhtml";
            //accion = "/contenido/cat_listaCasosAsignados.xhtml";
           // accion="/inicio.xhtml";
           //accion="contenido/principalBorrar.xhtml?faces-redirect=true";
            String rol=postLogin();
            if (rol!=null) {
                if (rol.equals("Asistente")) {
                    accion="contenido/for_consultaCasos.xhtml?faces-redirect=true";
                }
                if (rol.equals("Coordinador") || rol.equals("Super administrador") || rol.equals("ROL_ADMIN")) {
                    accion="contenido/for_listaCasos.xhtml?faces-redirect=true";
                }
                if (rol.equals("Tecnico")) {
                    accion="contenido/cat_listaCasosAsignados.xhtml?faces-redirect=true";
                }
                 
            }
            
        } catch (AuthenticationException e) {
            util.FacesUtil.addMensaje("Credenciales incorrectas, verifique...");
            e.printStackTrace();
        }
        return accion;

    }

     private String postLogin() {
        
//         forma uno 
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        name = auth.getName(); 
//        obtenemos el usuario logueado

        //   forma dos
         String rol=null;
       
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername(); //obtenemos el usuario logueado    
       
        //guardar el nombre de la session del usuario
        
        try{
        Usuarios usuario =getD().traeUsuarioLogeado(name);
         System.out.println("usuario  autenticado "+usuario.getNombreUsuario());
        appSession.setUsuario(usuario);
        
        //appSession.setUsuario(usuario);
        }catch(Exception e){
            System.out.println(e.toString());
        }
        if(appSession.getUsuario() !=null){
        rol=appSession.getUsuario().getIdRol().getNombreRol();
        }
        
        return rol;
        


    }

    
    protected AuthenticationManager getAuthenticationManager() {
        return (AuthenticationManager) FacesUtil.getSpringBean("authenticationManager");
    }
    
    
   

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

  
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

   
    /**
     * @return the urlActual
     */
    public String getUrlActual() {
        return urlActual;
    }

    /**
     * @param urlActual the urlActual to set
     */
    public void setUrlActual(String urlActual) {        
        this.urlActual = urlActual;
    }

    /**
     * @return the usuarioSH
     */
    public String getUsuarioSH() {
        return usuarioSH;
    }

    /**
     * @param usuarioSH the usuarioSH to set
     */
    public void setUsuarioSH(String usuarioSH) {
        this.usuarioSH = usuarioSH;
    }

    /**
     * @return the roles
     */
    public List getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List roles) {
        this.roles = roles;
    }
    
    public AppSession getAppSession() {
        return appSession;
    }

    public void setAppSession(AppSession appSession) {
        this.appSession = appSession;
    }

    public DaoLocal getD() throws NamingException{
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }
    
}
