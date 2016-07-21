/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.Dao;
import dao.DaoLocal;
import entidades.Dependencias;
import entidades.EncaRequerimientos;
import entidades.Estados;
import entidades.Roles;
import entidades.Tecnicos;
import entidades.TecnicosPK;
import entidades.TiposUsuarios;
import entidades.UnidadesEjecutoras;
import entidades.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import util.FacesUtil;

/**
 *
 * @author Usuario SEC
 */
@ManagedBean
@SessionScoped
public class ControladorCatalogo implements Serializable, ValueChangeListener{
    private List<Usuarios> listaUsua=new ArrayList<Usuarios>();
    private Usuarios usuarioSeleccionado=new Usuarios();
    private Usuarios nuevoUsuario=new Usuarios();
    private List<Roles> listaRoles=new ArrayList<Roles>();
    private List<TiposUsuarios> listaTipoUsuario=new ArrayList<TiposUsuarios>();
    private List<Estados> listaEstado=new ArrayList<Estados>();
    private List<Dependencias> listaDependencia=new ArrayList<Dependencias>();
    private boolean estado;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private int indice;
    private int indice2;
    private int indice3;
    private int rolSelec;
    private int tipoUsu;
    private int idDepe;
    public String claveInicial;
    private String tipoOperacion;
    private List<Tecnicos> listaTec= new ArrayList<Tecnicos>();
    
    @ManagedProperty(value="#{appSession}")
    private AppSession appSession;

    public AppSession getAppSession() {
        return appSession;
    }

    public void setAppSession(AppSession appSession) {
        this.appSession = appSession;
    }
   
    
    //TiposUsuarios tiUsu=new TiposUsuarios();
    private String vari="hugo2";
    
    public ControladorCatalogo(){
        this.usuarioSeleccionado.setIdTipoUsuario(new TiposUsuarios());
        this.usuarioSeleccionado.setIdRol(new Roles());
       // this.usuarioSeleccionado.setIdEstado(new Estados());
        this.usuarioSeleccionado.setIdDependencia(new Dependencias());
        this.nuevoUsuario.setIdDependencia(new Dependencias());
        this.nuevoUsuario.setIdRol(new Roles());
        this.nuevoUsuario.setIdTipoUsuario(new TiposUsuarios());
        this.nuevoUsuario.setIdUnidadEjecutora(new UnidadesEjecutoras());
    }

    public int getIdDepe() {
        return idDepe;
    }

    public void setIdDepe(int idDepe) {
        this.idDepe = idDepe;
    }

    public int getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(int tipoUsu) {
        this.tipoUsu = tipoUsu;
    }

    public String getVari() {
        return vari;
    }

    public void setVari(String vari) {
        this.vari = vari;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Estados> getListaEstado() {
        listaEstado=getD().listaEstados();
        return listaEstado;
    }

    public List<Dependencias> getListaDependencia() {
        listaDependencia=getD().traeDependencias();
        return listaDependencia;
    }

    public void setListaDependencia(List<Dependencias> listaDependencia) {
        this.listaDependencia = listaDependencia;
    }

    public void setListaEstado(List<Estados> listaEstado) {
        this.listaEstado = listaEstado;
    }

    public List<Usuarios> getListaUsua() {
        if (appSession.getUsuario().getIdRol().getNombreRol().equals("Super administrador") || appSession.getUsuario().getIdRol().getNombreRol().equals("ROL_ADMIN")) {
             listaUsua=getD().traeUsuarios(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        }else{
            listaUsua=getD().traeUsuariosActivosDeUnidadEjecutora(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        }
       
        getListaTipoUsuario();
        return listaUsua;
    }

    public void setListaUsua(List<Usuarios> listaUsua) {
        this.listaUsua = listaUsua;
    }


    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getIndice2() {
        return indice2;
    }

    public void setIndice2(int indice2) {
        this.indice2 = indice2;
    }

    public Usuarios getUsuarioSeleccionado() {
       
        return usuarioSeleccionado;
    }
    public String tipoUsuario;
public void asignar(){
     indice=usuarioSeleccionado.getIIdTipoUsuario().getIdTipoUsuario();
     indice2=usuarioSeleccionado.getIdRol().getIdRol();
     indice3=usuarioSeleccionado.getIdDependencia().getIdDependencia();
     estado=usuarioSeleccionado.getEstado();
     tipoUsuario=usuarioSeleccionado.getIdRol().getNombreRol();
     claveInicial=usuarioSeleccionado.getUsuarioUsuario();
}
    public void setUsuarioSeleccionado(Usuarios usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public Usuarios getNuevoUsuario() {
        this.nuevoUsuario.setIdRol(new Roles());
        return nuevoUsuario;
    }

    public void setNuevoUsuario(Usuarios nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public List<Roles> getListaRoles() {
        listaRoles=getD().traeRoles();
        return listaRoles;
    }

    public int getIndice3() {
        return indice3;
    }

    public void setIndice3(int indice3) {
        this.indice3 = indice3;
    }

    public void setListaRoles(List<Roles> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List<TiposUsuarios> getListaTipoUsuario() {
        listaTipoUsuario=getD().traeTipoUsuarios();
        return listaTipoUsuario;
    }

    public void setListaTipoUsuario(List<TiposUsuarios> listaTipoUsuario) {
        this.listaTipoUsuario = listaTipoUsuario;
    }
      public void cambioNombre(ValueChangeEvent e){
          //System.out.println(String.valueOf(e.getNewValue()+"hugo"));
    }
      public void cambioApellido(ValueChangeEvent e){
    }
      public void cambioCorro(ValueChangeEvent e){
    }
      public void cambioDependencia(ValueChangeEvent e){
          indice3=Integer.parseInt(String.valueOf(e.getNewValue()));
    }
      public void cambioTelefono(ValueChangeEvent e){
    }
      public void cambioUsuario(ValueChangeEvent e){
    }
      public void cambioContrasena(ValueChangeEvent e){
    }
      public void cambioTipoUusario(ValueChangeEvent e){
          indice=Integer.parseInt(String.valueOf(e.getNewValue()));
    }
      public void cambioRolUsuario(ValueChangeEvent e){
         indice2=Integer.parseInt(String.valueOf(e.getNewValue()));
    }
      public void cambioObservaciones(ValueChangeEvent e){
    }
      public void cambioEstado(ValueChangeEvent e){ 
          estado=Boolean.parseBoolean(String.valueOf(e.getNewValue()));
    }

      
      
    public List<Tecnicos> listaTec() {
        return getD().traeTecnic(appSession);
    }

    public void setListaTec(List<Tecnicos> listaTec) {
        this.listaTec = listaTec;
    }
   
      
      
 
//valida correo electronico
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
        public boolean tieneCasos(Usuarios u){
            List<EncaRequerimientos> listaEnca=new ArrayList<EncaRequerimientos>();
            try {
                Tecnicos tec=getD().traeTecnico(usuarioSeleccionado.getUsuarioUsuario());
                listaEnca=getD().traeRequrimientosDelTecnico(tec.getTecnicosPK().getIdTecnico(), tec.getTecnicosPK().getIdUndEjecutora());
                if (listaEnca.size()>0) {
                    return true;
                }else{
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
      public void ver(){
          if (usuarioSeleccionado.getApellidoUsuario().equals("") || usuarioSeleccionado.getNombreUsuario().equals("") || usuarioSeleccionado.getContraseniaUsuario().equals("") || usuarioSeleccionado.getCorreoUsuario().equals("") || usuarioSeleccionado.getTelefonoUsuario().equals("")|| usuarioSeleccionado.getUsuarioUsuario().equals("")) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Complete los campos!"));
          }else{
              try{
          Roles r=getD().traeRol(indice2);
          
          usuarioSeleccionado.setIdRol(r);
          TiposUsuarios ti=getD().traeTipoUsuario1(indice);
          usuarioSeleccionado.setIdTipoUsuario(ti);
          Dependencias de=getD().traeDependencia2(indice3);
          usuarioSeleccionado.setIdDependencia(de);
          usuarioSeleccionado.setEstado(estado);
          usuarioSeleccionado.setIdUnidadEjecutora(getD().traeUnidadEjecutora(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora()));
              if (validateEmail(usuarioSeleccionado.getCorreoUsuario()) ) {
                   if (!usuarioSeleccionado.getTelefonoUsuario().equals("")) {
                       if (tipoUsuario.equals("Tecnico")) {
                           if (tipoUsuario.equals(usuarioSeleccionado.getIdRol().getNombreRol())) {
                                Tecnicos t=new Tecnicos();
                                t=getD().traeTecnico(usuarioSeleccionado.getUsuarioUsuario());
                                t.setNombreTecnico(usuarioSeleccionado.getNombreUsuario());
                                t.setCorreoTecnico(usuarioSeleccionado.getCorreoUsuario());
                                getD().operacionTecnicoYUsuario(usuarioSeleccionado, t);
                                //d.updateTecnico(t);
                                //d.updateUsuario(usuarioSeleccionado);
                                usuarioSeleccionado=new Usuarios();
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado Exitosamente!"));
                
                           }else{
                               if (tieneCasos(usuarioSeleccionado)) {
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puede cambiar de ROL porque el tecnico tiene casos asignados o pausados actualmente!"));
             
                               }else{
                                Tecnicos t=new Tecnicos();
                                t=  getD().traeTecnico(usuarioSeleccionado.getUsuarioUsuario());
                                t.setNombreTecnico(usuarioSeleccionado.getNombreUsuario());
                                t.setCorreoTecnico(usuarioSeleccionado.getCorreoUsuario());
                                
                                //d.updateTecnico(t);
                                //d.updateUsuario(usuarioSeleccionado);
                                    getD().operacionTecnicoYUsuario(usuarioSeleccionado, t);
                                usuarioSeleccionado=new Usuarios();
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado Exitosamente!"));
                  
                               }
                               //validar si el susuario no tiene casos pendientes
                               
                           }
                       }else{
                           if (usuarioSeleccionado.getIdRol().getNombreRol().equals("Tecnico")) {
                                try {
                                    //buscarlo en la tabla tecnicos
                                    int tecn=getD().existeTecnico(usuarioSeleccionado.getUsuarioUsuario());
                                    if (tecn==0) {
                                        TecnicosPK tpk=new TecnicosPK();
                                        tpk.setIdTecnico(getD().traeUltimoIdTecnico());
                                        tpk.setIdUndEjecutora(usuarioSeleccionado.getIdUnidadEjecutora().getIdUndEjecutora());
                                        Tecnicos tecni=new Tecnicos();
                                        tecni.setTecnicosPK(tpk);
                                        tecni.setCorreoTecnico(usuarioSeleccionado.getCorreoUsuario());
                                        tecni.setNombreTecnico(usuarioSeleccionado.getNombreUsuario());
                                        tecni.setUsuarioUsuario(usuarioSeleccionado);
                                        getD().operacionTecnicoYUsuario(usuarioSeleccionado, tecni);
                                        //d.updateUsuario(usuarioSeleccionado);
                                        usuarioSeleccionado=new Usuarios();
                                        //d.insertTecnico(tecni);
                                        
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado Exitosamente!"));
                                    }else{
                                        Tecnicos t=getD().traeTecnico(usuarioSeleccionado.getUsuarioUsuario());
                                        t.setUsuarioUsuario(usuarioSeleccionado);
                                        t.setNombreTecnico(usuarioSeleccionado.getNombreUsuario());
                                        t.setCorreoTecnico(usuarioSeleccionado.getCorreoUsuario());
                                        getD().operacionTecnicoYUsuario(usuarioSeleccionado, t);
                                        //d.updateUsuario(usuarioSeleccionado);
                                        usuarioSeleccionado=new Usuarios();
                                        //d.updateTecnico(t);
                                        
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado Exitosamente!"));
                                    }
                           
                           
                           //por aqui deberia ir un insert ala tabla tecnico.
                           } catch (Exception e) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error! "+e.toString()));
         
                           }
                           }else{
                                getD().updateUsuario(usuarioSeleccionado);
                             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado Exitosamente!"));

                           }
                          
                           
                       }
                    
                  }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Numero telefonico invalido!"));
            
                   }
                 }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correo electronico invalido!"));
  
              }
          
        
          }catch(Exception ex){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se pudo Modificar:! "+ex.toString()));
          }
          }          
          
      }
     

    public int getRolSelec() {
        return rolSelec;
    }

    public void setRolSelec(int rolSelec) {
        this.rolSelec = rolSelec;
    }
      
      public void almacenar(){
          Date fecha=new Date();
          nuevoUsuario.setIdDependencia(new Dependencias(idDepe));
          nuevoUsuario.setIdTipoUsuario(new TiposUsuarios(tipoUsu));
          nuevoUsuario.setIdRol(new Roles(rolSelec));
          if (nuevoUsuario.getApellidoUsuario().equals("")|| nuevoUsuario.getNombreUsuario().equals("") || nuevoUsuario.getContraseniaUsuario().equals("") || nuevoUsuario.getCorreoUsuario().equals("") || nuevoUsuario.getTelefonoUsuario().equals("") || nuevoUsuario.getUsuarioUsuario().equals("") || rolSelec==0 || idDepe==0 || tipoUsu==0) {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debé de llenar todos lo campos"));
     
          }else{
              if (rolSelec==2) {
                     if (validateEmail(nuevoUsuario.getCorreoUsuario())) {
                          if (nuevoUsuario.getTelefonoUsuario().equals("")) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Numero de telefono invalido")); 
                         }else{
                     
                  try {
                      nuevoUsuario.setFechaCreaUsuario(fecha);
                      nuevoUsuario.setIdUnidadEjecutora(appSession.getUsuario().getIdUnidadEjecutora());
                            getD().insertUsuario(nuevoUsuario);
                      TecnicosPK tpk=new TecnicosPK();
                      Tecnicos t=new Tecnicos();
                      tpk.setIdTecnico(getD().traeUltimoIdTecnico());
                      tpk.setIdUndEjecutora(nuevoUsuario.getIdUnidadEjecutora().getIdUndEjecutora());
                      t.setTecnicosPK(tpk);
                      t.setNombreTecnico(nuevoUsuario.getNombreUsuario());
                      t.setCorreoTecnico(nuevoUsuario.getCorreoUsuario());
                      t.setUsuarioUsuario(nuevoUsuario);
                            getD().insertTecnico(t);
                      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Almacenado Exitosamente!"));
                  } catch (Exception e) {
                      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: " + e.toString()));
                  }
                          }
                   }else{
                       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correo invalido"));  
                     }
              }else{
                  if (validateEmail(nuevoUsuario.getCorreoUsuario())) {
                      if (nuevoUsuario.getTelefonoUsuario().equals("")) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Numero de telefono invalido")); 
                         }else{
                          
                           try {
                      nuevoUsuario.setFechaCreaUsuario(fecha);
                      nuevoUsuario.setIdUnidadEjecutora(appSession.getUsuario().getIdUnidadEjecutora());
                            getD().insertUsuario(nuevoUsuario);
                      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Almacenado Exitosamente!"));
                  } catch (Exception e) {
                      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: "+ e.toString()));
                  }
                          
                      }
                  }else{
                      
                      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correo no valido!"));
                  }
                 
              }
          }
          idDepe=0;
          tipoUsu=0;
          rolSelec=0;
          nuevoUsuario=new Usuarios();
      }

    public String getTipoOperacion() {
        if (appSession.getUsuario().getIdRol().getNombreRol().equals("Super administrador")) {
            tipoOperacion="Modificar Usuario";
        }else{
            tipoOperacion="Modificar Tecnico";
        }
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }
      
    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the d
     */
    public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }

   
}