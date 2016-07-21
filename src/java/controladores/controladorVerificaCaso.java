/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DaoLocal;
import dao.SumaPesoTecnico;
import entidades.AsignarTecnico;
import entidades.AsignarTecnicoPK;
import entidades.CategoriaServicios;
import entidades.DetaRequerimientos;
import entidades.Documentos;
import entidades.EncaRequerimientos;
import entidades.Estados;
import entidades.Servicios;
import entidades.Tecnicos;
import entidades.Usuarios;
import entidades.ViaSolicitudes;
import entidades.RequeVerificados;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import util.FacesUtil;

/**
 *
 * @author Claudia
 */
@ManagedBean
@SessionScoped
public class controladorVerificaCaso implements ValueChangeListener, Serializable {

    private List<Usuarios> listUsuarios = new ArrayList<Usuarios>();
    private List<ViaSolicitudes> listViaSolic = new ArrayList<ViaSolicitudes>();
    private List<Servicios> listServ = new ArrayList<Servicios>();
    private List<CategoriaServicios> listCatServ = new ArrayList<CategoriaServicios>();
    private EncaRequerimientos nuevoReque = new EncaRequerimientos();
    private Usuarios usuarioSeleccionado = new Usuarios();
    private List<EncaRequerimientos> listRequeriPendien = new ArrayList<EncaRequerimientos>();
    private List<EncaRequerimientos> listaRequeVerificar = new ArrayList<EncaRequerimientos>();
    
    
    private List<Tecnicos> listTecnicos = new ArrayList<Tecnicos>();
    private EncaRequerimientos requeSeleccionado = new EncaRequerimientos();
    private AsignarTecnico nuevaAsignacion = new AsignarTecnico();
    private Date fechaActual = new Date();
    private int viaSelec, catServ, tecSelec;
    private int idSum;
    private List<EncaRequerimientos> listEncaRequerimientoAModificar = new ArrayList<EncaRequerimientos>();
    private EncaRequerimientos requerimientoSeleccionadoAModificar = new EncaRequerimientos();
    private Usuarios usuarioSolicitanteSelecionadoAModificar = new Usuarios();
    private Tecnicos tecnicoSeleccionadoPorSuperAdmin = new Tecnicos();
    
    private List<EncaRequerimientos> listAllRequeri = new ArrayList<EncaRequerimientos>();
    private EncaRequerimientos encaElejidoDetodos = new EncaRequerimientos();
    private AsignarTecnico asignaDelElejiReque = new AsignarTecnico();
    private Date fechaSeguimiento = new Date();
    SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

    @ManagedProperty(value = "#{appSession}")
    private AppSession appSession;
    
    public AppSession getAppSession() {
        return appSession;
    }

    public void setAppSession(AppSession appSession) {
        this.appSession = appSession;
    }

    /**
     * Creates a new instance of controladorVerificaCaso
     */
    public controladorVerificaCaso() {
    }

    public List<EncaRequerimientos> getListaRequeVerificar() {
        listaRequeVerificar = getD().traeRequeParaVerificar( 3, appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        System.out.println("lista para verificar");
        return listaRequeVerificar;
    }

    public void setListaRequeVerificar(List<EncaRequerimientos> listaRequeVerificar) {
        this.listaRequeVerificar = listaRequeVerificar;
    }

    
    
    
    public List<EncaRequerimientos> getListRequeriPendien() {
        listRequeriPendien = getD().traeEncaRequrimientos(1, 5, appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        System.out.println("asd");
        return listRequeriPendien;
    }

    public void setListRequeriPendien(List<EncaRequerimientos> listRequeriPendien) {
        this.listRequeriPendien = listRequeriPendien;
    }
    
    
    
    public EncaRequerimientos getEncaElejidoDetodos() {
        System.out.println("prueba");
        System.out.println("pruneba");
        System.out.println(encaElejidoDetodos.getFechaIngreso());
        return encaElejidoDetodos;
    }

    public void setEncaElejidoDetodos(EncaRequerimientos encaElejidoDetodos) {
        this.encaElejidoDetodos = encaElejidoDetodos;
    }

    public AsignarTecnico getAsignaDelElejiReque() {
        try {
            asignaDelElejiReque = getD().traeAsignacionDelRequeri(encaElejidoDetodos, appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return asignaDelElejiReque;
    }

    public String traeTecnico(int i) {
        Tecnicos tec = new Tecnicos();
        try {
            tec = getD().traeTecnico(i);
            return tec.getUsuarioUsuario().getNombreUsuario() + " " + tec.getUsuarioUsuario().getApellidoUsuario();
        } catch (Exception e) {
            return "Sin nombre";

        }

    }

    public void salir() {
        appSession.setUsuario(new Usuarios());
    }
    List<Documentos> listaDocABorrar = new ArrayList<Documentos>();

   

    public void redireccionar2() {
        FacesContext fc = FacesContext.getCurrentInstance();

        try {
            getAsignaDelElejiReque();
            fc.getExternalContext().redirect("for_requeriSeleccionado.xhtml");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public List<EncaRequerimientos> getListAllRequeri() {
        List<Object[]> listAux = new ArrayList<Object[]>();
        List<EncaRequerimientos> listaAux2 = new ArrayList<EncaRequerimientos>();
        try {
            listAux = getD().traeListaDeRequeriYAsigna(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
            for (Object[] item : listAux) {
                listAllRequeri.add((EncaRequerimientos) item[0]);
            }
        } catch (Exception e) {
        }
        listaAux2 = listAllRequeri;
        listAllRequeri = new ArrayList<EncaRequerimientos>();
        return listaAux2;
    }

    public void setListAllRequeri(List<EncaRequerimientos> listAllRequeri) {
        this.listAllRequeri = listAllRequeri;
    }

    public Date getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(Date fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    public Tecnicos getTecnicoSeleccionadoPorSuperAdmin() {
        return tecnicoSeleccionadoPorSuperAdmin;
    }

    public void setTecnicoSeleccionadoPorSuperAdmin(Tecnicos tecnicoSeleccionadoPorSuperAdmin) {
        this.tecnicoSeleccionadoPorSuperAdmin = tecnicoSeleccionadoPorSuperAdmin;
    }

    public void borrame(String usua) {
        this.usuarioSolicitanteSelecionadoAModificar.setNombreUsuario(usua);
    }

    public Usuarios getUsuarioSolicitanteSelecionadoAModificar() {
        return usuarioSolicitanteSelecionadoAModificar;
    }

    public void setUsuarioSolicitanteSelecionadoAModificar(Usuarios usuarioSolicitanteSelecionadoAModificar) {
        this.usuarioSolicitanteSelecionadoAModificar = usuarioSolicitanteSelecionadoAModificar;
    }

    public EncaRequerimientos getRequerimientoSeleccionadoAModificar() {
        return requerimientoSeleccionadoAModificar;
    }

    public void setRequerimientoSeleccionadoAModificar(EncaRequerimientos requerimientoSeleccionadoAModificar) {
        this.requerimientoSeleccionadoAModificar = requerimientoSeleccionadoAModificar;
    }

    public List<EncaRequerimientos> getListEncaRequerimientoAModificar() {
        try {
            listEncaRequerimientoAModificar = getD().traeEncaRequerimientoAModificar(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
            return listEncaRequerimientoAModificar;
        } catch (Exception e) {
            return listEncaRequerimientoAModificar;
        }

    }

    public void setListEncaRequerimientoAModificar(List<EncaRequerimientos> listEncaRequerimientoAModificar) {
        this.listEncaRequerimientoAModificar = listEncaRequerimientoAModificar;
    }

    
    public int getIdSum() {
        return idSum;
    }

    public void setIdSum(int idSum) {
        this.idSum = idSum;
    }
    

    //////////////////////////
    private boolean bandera = false;

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public AsignarTecnico getNuevaAsignacion() {
        return nuevaAsignacion;
    }

    public void setNuevaAsignacion(AsignarTecnico nuevaAsignacion) {
        this.nuevaAsignacion = nuevaAsignacion;
    }

    public List<Tecnicos> getListTecnicos() {
        listTecnicos = getD().traeTecnicos(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        return listTecnicos;
    }

    public void setListTecnicos(List<Tecnicos> listTecnicos) {
        this.listTecnicos = listTecnicos;
    }

    public EncaRequerimientos getRequeSeleccionado() {
        return requeSeleccionado;
    }
    private EncaRequerimientos requerimientoAsigSeleccionado;

    public EncaRequerimientos getRequerimientoAsigSeleccionado() {
        return requerimientoAsigSeleccionado;
    }

    public void setRequerimientoAsigSeleccionado(EncaRequerimientos requerimientoAsigSeleccionado) {
        this.requerimientoAsigSeleccionado = requerimientoAsigSeleccionado;
    }

    public void setRequeSeleccionado(EncaRequerimientos requeSeleccionado) {
        this.requeSeleccionado = requeSeleccionado;
    }

    
    private List<EncaRequerimientos> listaEncaReAsignados;
    private boolean bandera2 = true;
    private List<EncaRequerimientos> listaDeCasosDelTecnico = new ArrayList<EncaRequerimientos>();

    public List<EncaRequerimientos> getListaDeCasosDelTecnico() {
        List<EncaRequerimientos> listaAux = new ArrayList<EncaRequerimientos>();
        try {
            if (appSession.getUsuario().getIdRol().getNombreRol().equals("Tecnico")) {
                Tecnicos tecnico = getD().traeTecnico(appSession.getUsuario().getUsuarioUsuario());
                listaDeCasosDelTecnico = getD().traeRequrimientosDelTecnico(tecnico.getTecnicosPK().getIdTecnico(), tecnico.getTecnicosPK().getIdUndEjecutora());
                AsignarTecnico asT = new AsignarTecnico();
                for (EncaRequerimientos encaRequerimientos : listaDeCasosDelTecnico) {
                    asT = getD().encontrarAsignaTecnico(encaRequerimientos.getIdEncaRequerimiento(), tecnico.getTecnicosPK().getIdTecnico(), tecnico.getTecnicosPK().getIdUndEjecutora());
                    if (asT.getEstadoDelTecnico().equals("pausado")) {
                        listaDeCasosDelTecnico.get(listaDeCasosDelTecnico.indexOf(asT.getEncaRequerimientos())).setIdEstado(new Estados(5, "Pausado"));
                    }

                }
                listaAux = listaDeCasosDelTecnico;
            }
            if (appSession.getUsuario().getIdRol().getNombreRol().equals("Super administrador") || appSession.getUsuario().getIdRol().getNombreRol().equals("Coordinador")) {
//                if (bandera2==true) {
//                      
//                    FacesContext fc=FacesContext.getCurrentInstance();  
//              try{
//                  bandera2=false;
//                    fc.getExternalContext().redirect("listaDeTecnicosConCasos.xhtml");
//                 }catch(Exception e){
//                        System.out.println(e.toString());
//                 }
//                  
//                }else{
                Tecnicos tecnicoElejido = getD().traeTecnico(this.tecnicoSeleccionadoPorSuperAdmin.getUsuarioUsuario().getUsuarioUsuario());
                listaDeCasosDelTecnico = getD().traeRequrimientosDelTecnico(tecnicoElejido.getTecnicosPK().getIdTecnico(), tecnicoElejido.getTecnicosPK().getIdUndEjecutora());
                AsignarTecnico asT = new AsignarTecnico();
                for (EncaRequerimientos encaRequerimientos : listaDeCasosDelTecnico) {
                    asT = getD().encontrarAsignaTecnico(encaRequerimientos.getIdEncaRequerimiento(), tecnicoElejido.getTecnicosPK().getIdTecnico(), tecnicoElejido.getTecnicosPK().getIdUndEjecutora());
                    if (asT.getEstadoDelTecnico().equals("pausado")) {
                        listaDeCasosDelTecnico.get(listaDeCasosDelTecnico.indexOf(asT.getEncaRequerimientos())).setIdEstado(new Estados(5, "Pausado"));
                    }

                }
                listaAux = listaDeCasosDelTecnico;
                //bandera2=true;




            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //this.tecnicoSeleccionadoPorSuperAdmin=new Tecnicos();
        return listaAux;
    }

    public void setListaDeCasosDelTecnico(List<EncaRequerimientos> listaDeCasosDelTecnico) {
        this.listaDeCasosDelTecnico = listaDeCasosDelTecnico;
    }

    public List<EncaRequerimientos> getListaEncaReAsignados() {
        listaEncaReAsignados = getD().traeEncaRequrimientos(2, 2, appSession.getUsuario().getIdDependencia().getIdDependencia());
        return listaEncaReAsignados;
    }

    public void setListaEncaReAsignados(List<EncaRequerimientos> listaEncaReAsignados) {
        this.listaEncaReAsignados = listaEncaReAsignados;
    }

    public List<CategoriaServicios> getListCatServ() {
        this.listCatServ = getD().traeCategoriaServicios(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        return listCatServ;
    }

    public void setListCatServ(List<CategoriaServicios> listCatServ) {
        this.listCatServ = listCatServ;
    }

    public List<Servicios> getListServ() {
        listServ = getD().traeServicios(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        return listServ;
    }

    public void setListServ(List<Servicios> listServ) {
        this.listServ = listServ;
    }

    public List<ViaSolicitudes> getListViaSolic() {
        listViaSolic = getD().traeViaSolicitudes();
        return listViaSolic;
    }

    public void setListViaSolic(List<ViaSolicitudes> listViaSolic) {
        this.listViaSolic = listViaSolic;
    }

    public int getViaSelec() {
        return viaSelec;
    }

    public void setViaSelec(int viaSelec) {
        this.viaSelec = viaSelec;
    }

    public int getCatServ() {
        return catServ;
    }

    public void setCatServ(int catServ) {
        this.catServ = catServ;
    }

    public int getTecSelec() {
        return tecSelec;
    }

    public void setTecSelec(int tecSelec) {
        this.tecSelec = tecSelec;
    }

    public List<Usuarios> getListUsuarios() {
        //this.listUsuarios=d.traeUsuariosActivosDeUnidadEjecutora(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        this.listUsuarios = getD().traeUsuarios(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuarios> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public EncaRequerimientos getNuevoReque() {
        return nuevoReque;
    }

    public void setNuevoReque(EncaRequerimientos nuevoReque) {
        this.nuevoReque = nuevoReque;
    }

    public Usuarios getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuarios usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public void redireccionarAVerCasosDelTecnico() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            //this.bandera2=false;
            fc.getExternalContext().redirect("cat_listaCasosAsignados.xhtml");
        } catch (Exception e) {
        }
    }

    public void requeAsignadoSelec() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            fc.getExternalContext().redirect("for_maestroDetalle.xhtml");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public String requeSelec() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            fc.getExternalContext().redirect("for_asignaCaso.xhtml");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "for_asignaCaso.xhtml";
    }
    private String operacionARealizar;

    public String getOperacionARealizar() {
        return operacionARealizar;
    }

    public void setOperacionARealizar(String operacionARealizar) {
        this.operacionARealizar = operacionARealizar;
    }

    public String redireccionar() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        this.operacionARealizar = params.get("operacion");

        try {
            fc.getExternalContext().redirect("for_modificacionRequerimiento.xhtml");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "for_modificacionRequerimiento.xhtml";
    }

    public void crearAsignacion() {
        if (tecSelec == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Seleccione un tecnico!"));
        } else {
            nuevaAsignacion.setUsuarioAsigna(appSession.getUsuario().getUsuarioUsuario());

            try {
                SimpleDateFormat dc = new SimpleDateFormat("HH:mm");
                nuevaAsignacion.setAsignarTecnicoPK(new AsignarTecnicoPK(requeSeleccionado.getIdEncaRequerimiento(), tecSelec, getD().encontrarTecnico(tecSelec)));
                if (requeSeleccionado.getIdEstado().getIdEstado() == 1) {
                    nuevaAsignacion.setEstadoDelTecnico("asignado");
                }
                if (requeSeleccionado.getIdEstado().getIdEstado() == 5) {
                    nuevaAsignacion.setEstadoDelTecnico("reasignado");
                }
                getD().insertAsignarTecnico(nuevaAsignacion);
                requeSeleccionado.setIdEstado(new Estados(2));
                //requeSeleccionado.setNumCaso(d.encontrarNumCaso(d.encontrarTecnico(tecSelec)));
                getD().updateEncaRequerimientos(requeSeleccionado);
                nuevaAsignacion = new AsignarTecnico();
                this.tecnicoSeleccionadoConPeso = new Tecnicos();
                tecSelec = 0;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se Asigno exitosamente!"));
                //FacesContext fc=FacesContext.getCurrentInstance();
                // fc.getCurrentInstance().addMessage(null, new FacesMessage("Se Asigno exitosamente!"));
                //fc.getExternalContext().redirect("for_listaCasos.xhtml");
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fallo la asignacion: " + e.toString()));
                System.out.println(e.toString());
            }
        }
        nuevaAsignacion = new AsignarTecnico();
        this.tecnicoSeleccionadoConPeso = new Tecnicos();
        tecSelec = 0;

    }

    public void limpiarFormularioCrearReq() {
        nuevoReque = new EncaRequerimientos();
        nuevoReque.setDocumentacionCompleta(false);
        this.usuarioSeleccionado = new Usuarios();
        this.viaSelec = 0;
        this.catServ = 0;
        this.bandera = false;
        this.listaDocASubir = new ArrayList<Documentos>();
      
    }

    public void updateRequerimiento() {
        FacesContext f = FacesContext.getCurrentInstance();
        Map<String, String> params = f.getExternalContext().getRequestParameterMap();
        try {
            if (this.requerimientoSeleccionadoAModificar.getIdViaSolicitud().getIdViaSolicitud() == 0 || requerimientoSeleccionadoAModificar.getIdCatServ().getIdCatServ() == 0 || usuarioSolicitanteSelecionadoAModificar.getNombreUsuario() == "" || requerimientoSeleccionadoAModificar.getDescripcionRequerimiento() == "" || requerimientoSeleccionadoAModificar.getFechaIngreso() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Complete los campos del formulario"));
                System.out.println("as");
            } else {
                if (params.get("operacion").equals("Eliminar")) {
                    getD().eliminarEncaRequerimiento(requerimientoSeleccionadoAModificar.getIdEncaRequerimiento());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Requerimiento eliminado exitosamente!"));
                }
                //Date fecha=new Date();
                if (params.get("operacion").equals("Modificar")) {
                    requerimientoSeleccionadoAModificar.setUsuarioUsuario(new Usuarios(appSession.getUsuario().getUsuarioUsuario().toString()));
                    requerimientoSeleccionadoAModificar.setIdViaSolicitud(new ViaSolicitudes(this.requerimientoSeleccionadoAModificar.getIdViaSolicitud().getIdViaSolicitud()));
                    requerimientoSeleccionadoAModificar.setIdCatServ(new CategoriaServicios(requerimientoSeleccionadoAModificar.getIdCatServ().getIdCatServ()));
                    requerimientoSeleccionadoAModificar.setSolicitante(usuarioSolicitanteSelecionadoAModificar.getNombreUsuario() + " " + usuarioSolicitanteSelecionadoAModificar.getApellidoUsuario());
                    requerimientoSeleccionadoAModificar.setIdEstado(new Estados(1));
                    //nuevoReque.setFechaRequerimiento(new Date());
                    // nuevoReque.setHoraRequerimiento(formatoHora.parse(formatoHora.format(fecha)));
                    // nuevoReque.setAnio(Integer.parseInt(formatoAnio.format(fecha)));
                    requerimientoSeleccionadoAModificar.setUsuarioUsuarioSolicitante(usuarioSolicitanteSelecionadoAModificar);
                    getD().updateEncaRequerimiento(requerimientoSeleccionadoAModificar, requerimientoSeleccionadoAModificar.getDocumentosList());
                    if (listaDocABorrar.size() != 0) {
                        getD().borrarDocu(listaDocABorrar);
                    }
                    if (requerimientoSeleccionadoAModificar.getDocumentacionCompleta() == false) {
                        getD().borrarDocu(requerimientoSeleccionadoAModificar.getDocumentosList());
                    }
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Requerimiento modificado exitosamente!"));
                }
                listaDocABorrar = new ArrayList<Documentos>();
                this.usuarioSolicitanteSelecionadoAModificar = new Usuarios();
                this.viaSelec = 0;
                this.catServ = 0;
                this.bandera = false;
                this.listaDocASubir = new ArrayList<Documentos>();
               
                //requerimientoSeleccionadoAModificar = new EncaRequerimientos();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Complete Correctamente los campos!"));
            System.out.println(e.toString());
        }
    }

    public void crearRequerimiento() {
        try {
            System.out.println("");
            if (viaSelec == 0 || catServ == 0 || usuarioSeleccionado.getNombreUsuario() == "" || nuevoReque.getDescripcionRequerimiento() == "" || nuevoReque.getFechaIngreso() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Complete los campos del formulario"));
            } else {
                Date fecha = new Date();
                nuevoReque.setUsuarioUsuario(new Usuarios(appSession.getUsuario().getUsuarioUsuario().toString()));
                nuevoReque.setIdViaSolicitud(new ViaSolicitudes(viaSelec));
                nuevoReque.setIdCatServ(new CategoriaServicios(catServ));
                nuevoReque.setSolicitante(usuarioSeleccionado.getNombreUsuario() + " " + usuarioSeleccionado.getApellidoUsuario());
                nuevoReque.setIdEstado(new Estados(1));
                nuevoReque.setFechaRequerimiento(new Date());
                nuevoReque.setHoraRequerimiento(formatoHora.parse(formatoHora.format(fecha)));
                nuevoReque.setAnio(Integer.parseInt(formatoAnio.format(fecha)));
                nuevoReque.setUsuarioUsuarioSolicitante(usuarioSeleccionado);
                getD().insertEncaRequerimientos(nuevoReque, this.listaDocASubir, appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());

                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Requerimiento almacenado exitosamente!"));
                //nuevoReque.setNumCaso(d.encontrarNumCaso2(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora()));
                //d.updateEncaRequerimientos(nuevoReque);

                this.usuarioSeleccionado = new Usuarios();
                this.viaSelec = 0;
                this.catServ = 0;
                this.bandera = false;
                this.listaDocASubir = new ArrayList<Documentos>();
               
                nuevoReque = new EncaRequerimientos();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Requerimiento ingresado exitosamente!"));
//            fc.getExternalContext().redirect("for_consultaCasos.xhtml");


            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Complete Correctamente los campos!"));
            System.out.println(e.toString());
        }
    }

    public String fechaActual12() {
        Date fe = new Date();
        nuevaAsignacion.setFechaAsigna(fe);
        SimpleDateFormat sidf = new SimpleDateFormat("dd/MM/yyyy");
        return sidf.format(fe);
    }

    public String horaActual12() {
        Date ho = new Date();
        SimpleDateFormat sidf = new SimpleDateFormat("HH:mm");
        return sidf.format(ho);
    }

    public List<Usuarios> traeTodosUsuarios() {
        return listUsuarios = getD().traeUsuarios(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
    }
    private boolean band = false;

    public boolean getBand() {
        return band;
    }

    public void setBand(boolean band) {
        this.band = band;
    }

    public void control2() {
        this.requerimientoSeleccionadoAModificar.setDocumentacionCompleta(this.requerimientoSeleccionadoAModificar.getDocumentacionCompleta() ? true : false);
        String summary = requerimientoSeleccionadoAModificar.getDocumentacionCompleta() ? "Seleccione Documentacion" : "No Requiere Documentacion";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public void control() {
        this.nuevoReque.setDocumentacionCompleta(this.nuevoReque.getDocumentacionCompleta() ? true : false);
        String summary = nuevoReque.getDocumentacionCompleta() ? "Seleccione Documentacion" : "No Requiere Documentacion";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }
    private List<SumaPesoTecnico> traeTecnicosConSuPeso1;

    public List<SumaPesoTecnico> getTraeTecnicosConSuPeso1() {
        List<SumaPesoTecnico> listaDePesos = new ArrayList<SumaPesoTecnico>();
        listaDePesos = getD().traeTecnicosConSuPeso(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        return listaDePesos;
    }

    public void setTraeTecnicosConSuPeso1(List<SumaPesoTecnico> l) {
        this.traeTecnicosConSuPeso1 = l;
    }
    private SumaPesoTecnico tecnicoConUnPesoSeleccionado = new SumaPesoTecnico();

    public SumaPesoTecnico getTecnicoConUnPesoSeleccionado() {
        return tecnicoConUnPesoSeleccionado;
    }

    public void setTecnicoConUnPesoSeleccionado(SumaPesoTecnico tecnicoConUnPesoSeleccionado) {
        this.tecnicoConUnPesoSeleccionado = tecnicoConUnPesoSeleccionado;
    }
    private Tecnicos tecnicoSeleccionadoConPeso = new Tecnicos();

    public Tecnicos getTecnicoSeleccionadoConPeso() {
        this.tecnicoSeleccionadoConPeso = getD().devuelveTecnicos(this.tecSelec);
        return tecnicoSeleccionadoConPeso;
    }

    public void setTecnicoSeleccionadoConPeso(Tecnicos tecnicoSeleccionadoConPeso) {
        this.tecnicoSeleccionadoConPeso = tecnicoSeleccionadoConPeso;
    }

    public void asignaIdTecnicoDeTabla() {
        try {
            this.tecSelec = this.tecnicoConUnPesoSeleccionado.getIdTecnico();
            this.tecnicoSeleccionadoConPeso = getD().devuelveTecnicos(this.tecSelec);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tecnico selecionado exitosamente!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se pudo asignar tecnico!"));
        }

    }
    private List<DetaRequerimientos> detallesDelReq = new ArrayList<DetaRequerimientos>();

    public List<DetaRequerimientos> getDetallesDelReq() {
        try {
            detallesDelReq = getD().traeDetaRequerimientosDeUnEncaReque(this.encaElejidoDetodos.getIdEncaRequerimiento());
        } catch (Exception e) {
        }
        return detallesDelReq;
    }

    public void setDetallesDelReq(List<DetaRequerimientos> detallesDelReq) {
        this.detallesDelReq = detallesDelReq;
    }
    public List<DetaRequerimientos> retornaTablaDeDetaRequeri = new ArrayList<DetaRequerimientos>();

    public List<DetaRequerimientos> getRetornaTablaDeDetaRequeri() {
        List<DetaRequerimientos> li = getD().traeDetaRequerimientosDeUnEncaReque(requerimientoAsigSeleccionado.getIdEncaRequerimiento());
        return li;
    }
    private Integer idServicio;

    public Integer getIdServicio() {

        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }
    public List<Servicios> listaServicios = new ArrayList<Servicios>();

    public List<Servicios> getListaServicios() {
        listaServicios = getD().traeServicios(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        return listaServicios;
    }

    public void setListaServicios(List<Servicios> listaServicios) {
        this.listaServicios = listaServicios;
    }
    private DetaRequerimientos detaRequerimientos = new DetaRequerimientos();

    public DetaRequerimientos getDetaRequerimientos() {
        return detaRequerimientos;
    }

    public void setDetaRequerimientos(DetaRequerimientos detaRequerimientos) {
        this.detaRequerimientos = detaRequerimientos;
    }
    private String fechaDeAsignacionDeRequerimiento;
    private AsignarTecnico detalleDeLaAsignacion = new AsignarTecnico();

    public AsignarTecnico getDetalleDeLaAsignacion() {
        AsignarTecnico aT = new AsignarTecnico();
        Tecnicos t = new Tecnicos();
        if (appSession.getUsuario().getIdRol().getNombreRol().equals("Super administrador") || appSession.getUsuario().getIdRol().getNombreRol().equals("Coordinador")) {

            aT = getD().encontrarAsignaTecnico(this.requerimientoAsigSeleccionado.getIdEncaRequerimiento(), this.tecnicoSeleccionadoPorSuperAdmin.getTecnicosPK().getIdTecnico(), this.tecnicoSeleccionadoPorSuperAdmin.getTecnicosPK().getIdUndEjecutora());
            // aT=d.traeAsignarTecnicoEncaRequeri(this.requerimientoAsigSeleccionado.getIdEncaRequerimiento(),this.tecnicoSeleccionadoPorSuperAdmin.getUsuarioUsuario().getUsuarioUsuario().toString());
            detalleDeLaAsignacion = aT;
        } else {
            t = getD().traeTecnico(appSession.getUsuario().getUsuarioUsuario());
            aT = getD().encontrarAsignaTecnico(this.requerimientoAsigSeleccionado.getIdEncaRequerimiento(), t.getTecnicosPK().getIdTecnico(), t.getTecnicosPK().getIdUndEjecutora());
            // aT=d.traeAsignarTecnicoEncaRequeri(this.requerimientoAsigSeleccionado.getIdEncaRequerimiento(),appSession.getUsuario().getUsuarioUsuario());
            detalleDeLaAsignacion = aT;

        }
        return detalleDeLaAsignacion;
    }

    public void setDetalleDeLaAsignacion(AsignarTecnico detalleDeLaAsignacion) {
        this.detalleDeLaAsignacion = detalleDeLaAsignacion;
    }

//    public String getFechaDeAsignacionDeRequerimiento() {
//        AsignarTecnico aT=new AsignarTecnico();
//        aT=d.traeAsignarTecnicoEncaRequeri(this.requerimientoAsigSeleccionado.getIdEncaRequerimiento());
//        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
//        
//        this.fechaDeAsignacionDeRequerimiento=formatoFecha.format(aT.getFechaAsigna());
//        return fechaDeAsignacionDeRequerimiento;
//    }
    //public String 
    public void setFechaDeAsignacionDeRequerimiento(String fechaDeAsignacionDeRequerimiento) {
        this.fechaDeAsignacionDeRequerimiento = fechaDeAsignacionDeRequerimiento;
    }
    private Date horaInicio;
    private Date horaFin;

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public void cambiodeMateriales(ValueChangeEvent e) {
        //detaAModificar.setDetaMateriales();
    }

    public void cambiodeFecha(ValueChangeEvent e) {
        //detaAModificar.setDetaMateriales();
    }

    public void cambiodeHoraInicio(ValueChangeEvent e) {
    }

    public void cambiodeHorafin(ValueChangeEvent e) {
    }

    public void cambiodeIdentificacionDelBien(ValueChangeEvent e) {
    }

    public void cambiodeTipoServicio(ValueChangeEvent e) {
    }

    public void cambiodeDescripcionServicio(ValueChangeEvent e) {
    }

    public void modificarDetaRequerimiento() {
        if (this.detaAModificar.getPIdServicio().getIdServicio() != 0) {
            try {
                getD().updateDetaRequerimiento(detaAModificar);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado exitosamente!"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fallo la modificacion! " + e.toString()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fallo la modificacion seleccione tipo de servicio"));
        }

    }

    public void cancelarRequerimiento() {
        Tecnicos t = new Tecnicos();
        try {

            requerimientoAsigSeleccionado.setIdEstado(new Estados(4));
            getD().updateEncaRequerimientos(requerimientoAsigSeleccionado);
            if (appSession.getUsuario().getIdRol().getNombreRol().equals("Tecnico")) {
                AsignarTecnico asiTec = new AsignarTecnico();
                t = getD().traeTecnico(appSession.getUsuario().getUsuarioUsuario());
                asiTec = getD().encontrarAsignaTecnico(requerimientoAsigSeleccionado.getIdEncaRequerimiento(), t.getTecnicosPK().getIdTecnico(), t.getTecnicosPK().getIdUndEjecutora());
                asiTec.setEstadoDelTecnico("cancelado");
                getD().updateAsignarTecnico(asiTec);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se Cancelo el Requerimiento solicitado por: " + requerimientoAsigSeleccionado.getUsuarioUsuarioSolicitante().getNombreUsuario() + " " + requerimientoAsigSeleccionado.getUsuarioUsuarioSolicitante().getApellidoUsuario()));
            }
            if (appSession.getUsuario().getIdRol().getNombreRol().equals("Coordinador") || appSession.getUsuario().getIdRol().getNombreRol().equals("Super administrador")) {
                AsignarTecnico asiTec = new AsignarTecnico();
                t = getD().traeTecnico(this.tecnicoSeleccionadoPorSuperAdmin.getUsuarioUsuario().getUsuarioUsuario());
                asiTec = getD().encontrarAsignaTecnico(requerimientoAsigSeleccionado.getIdEncaRequerimiento(), t.getTecnicosPK().getIdTecnico(), t.getTecnicosPK().getIdUndEjecutora());
                asiTec.setEstadoDelTecnico("cancelado");
                getD().updateAsignarTecnico(asiTec);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se Cancelo el Requerimiento solicitado por: " + requerimientoAsigSeleccionado.getUsuarioUsuarioSolicitante().getNombreUsuario() + " " + requerimientoAsigSeleccionado.getUsuarioUsuarioSolicitante().getApellidoUsuario()));

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al intentar Cancelar el Requerimiento con ID: " + requerimientoAsigSeleccionado.getIdEncaRequerimiento() + " " + e.toString()));
        }
    }

    public void finalizarEncaRequerimiento() {
        Tecnicos t = new Tecnicos();
        try {
            Date fechaFin = new Date();
            requerimientoAsigSeleccionado.setIdEstado(new Estados(3));
            requerimientoAsigSeleccionado.setFechaFin(fechaFin);
            if (appSession.getUsuario().getIdRol().getNombreRol().equals("Tecnico")) {
                getD().updateEncaRequerimientos(requerimientoAsigSeleccionado);
                t = getD().traeTecnico(appSession.getUsuario().getUsuarioUsuario());
                AsignarTecnico asiTec = new AsignarTecnico();
                asiTec = getD().encontrarAsignaTecnico(requerimientoAsigSeleccionado.getIdEncaRequerimiento(), t.getTecnicosPK().getIdTecnico(), t.getTecnicosPK().getIdUndEjecutora());
                asiTec.setEstadoDelTecnico("finalizado");
                getD().updateAsignarTecnico(asiTec);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se a Finalizado el Requerimiento solicitado por: " + requerimientoAsigSeleccionado.getUsuarioUsuarioSolicitante().getNombreUsuario() + requerimientoAsigSeleccionado.getUsuarioUsuarioSolicitante().getApellidoUsuario()));
            }
            if (appSession.getUsuario().getIdRol().getNombreRol().equals("Super administrador") || appSession.getUsuario().getIdRol().getNombreRol().equals("Coordinador")) {
                getD().updateEncaRequerimientos(requerimientoAsigSeleccionado);
                t = getD().traeTecnico(this.tecnicoSeleccionadoPorSuperAdmin.getUsuarioUsuario().getUsuarioUsuario());
                AsignarTecnico asiTec = new AsignarTecnico();
                asiTec = getD().encontrarAsignaTecnico(requerimientoAsigSeleccionado.getIdEncaRequerimiento(), t.getTecnicosPK().getIdTecnico(), t.getTecnicosPK().getIdUndEjecutora());
                asiTec.setEstadoDelTecnico("finalizado");
                getD().updateAsignarTecnico(asiTec);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se a Finalizado el Requerimiento solicitado por: " + requerimientoAsigSeleccionado.getUsuarioUsuarioSolicitante().getNombreUsuario() + " " + requerimientoAsigSeleccionado.getUsuarioUsuarioSolicitante().getApellidoUsuario()));

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al intentar finalizar el Requerimiento: " + e.toString()));
        }
    }

    public String EstadoParaElTecnico(int id) {
        Tecnicos t = new Tecnicos();
        try {
            t = getD().traeTecnico(appSession.getUsuario().getUsuarioUsuario());
            AsignarTecnico asiTec = new AsignarTecnico();
            asiTec = getD().encontrarAsignaTecnico(id, t.getTecnicosPK().getIdTecnico(), t.getTecnicosPK().getIdUndEjecutora());
            return asiTec.getEstadoDelTecnico();
        } catch (Exception e) {
            return null;
        }
    }

    public void pausarRequerimien() {
        Tecnicos t = new Tecnicos();
        try {
            encaElejidoDetodos.setIdEstado(new Estados(5));
            getD().updateEncaRequerimientos(encaElejidoDetodos);
            asignaDelElejiReque.setEstadoDelTecnico("pausado");
            asignaDelElejiReque.setPausado("si");
            getD().updateAsignarTecnico(asignaDelElejiReque);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se a Pausado el Requerimiento solicitado por: " + encaElejidoDetodos.getUsuarioUsuarioSolicitante().getNombreUsuario() + " " + encaElejidoDetodos.getUsuarioUsuarioSolicitante().getApellidoUsuario()));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se pudo pausar: " + e.toString()));
        }
    }

    public void pausarDetaRequerimiento() {
        Tecnicos t = new Tecnicos();
        try {
            requerimientoAsigSeleccionado.setIdEstado(new Estados(5));
            getD().updateEncaRequerimientos(requerimientoAsigSeleccionado);
            if (appSession.getUsuario().getIdRol().getNombreRol().equals("Tecnico")) {
                t = getD().traeTecnico(appSession.getUsuario().getUsuarioUsuario());
                AsignarTecnico asiTec = new AsignarTecnico();
                asiTec = getD().encontrarAsignaTecnico(requerimientoAsigSeleccionado.getIdEncaRequerimiento(), t.getTecnicosPK().getIdTecnico(), t.getTecnicosPK().getIdUndEjecutora());
                asiTec.setEstadoDelTecnico("pausado");
                asiTec.setPausado("si");
                getD().updateAsignarTecnico(asiTec);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se a Pausado el Requerimiento solicitado por: " + requerimientoAsigSeleccionado.getUsuarioUsuarioSolicitante().getNombreUsuario() + " " + requerimientoAsigSeleccionado.getUsuarioUsuarioSolicitante().getApellidoUsuario()));

            } else {
                t = getD().traeTecnico(this.tecnicoSeleccionadoPorSuperAdmin.getUsuarioUsuario().getUsuarioUsuario());
                AsignarTecnico asiTec = new AsignarTecnico();
                asiTec = getD().encontrarAsignaTecnico(requerimientoAsigSeleccionado.getIdEncaRequerimiento(), t.getTecnicosPK().getIdTecnico(), t.getTecnicosPK().getIdUndEjecutora());
                asiTec.setEstadoDelTecnico("pausado");
                asiTec.setPausado("si");
                getD().updateAsignarTecnico(asiTec);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se a Pausado el Requerimiento solicitado por: " + requerimientoAsigSeleccionado.getUsuarioUsuarioSolicitante().getNombreUsuario() + " " + requerimientoAsigSeleccionado.getUsuarioUsuarioSolicitante().getApellidoUsuario()));

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puede pausar el requerimiento: " + requerimientoAsigSeleccionado.getIdEncaRequerimiento() + " " + e.toString()));
        }

    }

    public void updateDeadlineDate(DateSelectEvent event) {
        horaInicio = event.getDate();

    }

    public void guardarDetaRequerimiento() {
        try {
            Date fecha = new Date();
            SimpleDateFormat fo = new SimpleDateFormat("HH:mm");
            detaRequerimientos.setHoraFin(horaFin);
            detaRequerimientos.setHoraInicio(horaInicio);
            detaRequerimientos.setFechaUser(fecha);
            detaRequerimientos.setSistemUser("tmejia");
            detaRequerimientos.setIdEncaRequerimiento(requerimientoAsigSeleccionado);
            detaRequerimientos.setIdPServicio(new Servicios(idServicio));
            getD().insertDetaRequerimiento(detaRequerimientos);
            detaRequerimientos = new DetaRequerimientos();
            idServicio = 0;
            horaFin = null;
            horaInicio = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se registro exitosamente!"));
        } catch (Exception e) {
            System.out.println("");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fallo el registro!"));
        }

    }

    public String traeDiagnostico() {
        String diagnostico = "reservado";
        for (AsignarTecnico a : this.requerimientoAsigSeleccionado.getAsignarTecnicoList()) {
            if (a.getAsignarTecnicoPK().getIdEncaRequerimiento() == this.requerimientoAsigSeleccionado.getIdEncaRequerimiento() & a.getAsignarTecnicoPK().getIdTecnico() == 4 & a.getAsignarTecnicoPK().getIdUndEjecutora() == 1) {
                diagnostico = a.getDescripcionAsigna();
            }

        }

        return diagnostico;
    }
    private DetaRequerimientos detaAModificar = new DetaRequerimientos();

    public DetaRequerimientos getDetaAModificar() {
        //Servicios s=new Servicios();
        //detaAModificar.setIdPServicio(s);
        return detaAModificar;
    }

    public void setDetaAModificar(DetaRequerimientos detaAModificar) {
        this.detaAModificar = detaAModificar;
    }

    @Override
    public void processValueChange(ValueChangeEvent event) {
        System.out.println("error");
        System.out.println("wqer");
        try {
            detaAModificar.setDetaMateriales("aasdasd" + event.getNewValue().toString()); //To change body of generated methods, choose Tools | Templates. 
        } catch (Exception e) {
        }

    }
    private List<Documentos> listaDocASubir = new ArrayList<Documentos>();

    public List<Documentos> getListaDocASubir() {
        return listaDocASubir;
    }

    public void setListaDocASubir(List<Documentos> listaDocASubir) {
        this.listaDocASubir = listaDocASubir;
    }
    private StreamedContent file;

    public void buscandoArchivo(Documentos docu) throws FileNotFoundException {
        InputStream stream = new FileInputStream(docu.getUrlDocumento());
        if (docu.getDescripcionDocumento().equals("pdf")) {
            file = new DefaultStreamedContent(stream, "aplication/pdf", docu.getNombreDocumento());
        }
        if (docu.getDescripcionDocumento().equals("jpg")) {
            file = new DefaultStreamedContent(stream, "image/jpg", docu.getNombreDocumento());
        }

    }

    public StreamedContent asigFile12(Documentos doc) {
        try {
            buscandoArchivo(doc);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
    private boolean estaPausado;

    public boolean isEstaPausado() {
        return estaPausado;
    }

    public void setEstaPausado(boolean estaPausado) {
        this.estaPausado = estaPausado;
    }

    public boolean traeEstadoAsignadoPorElTecnico() {
        Tecnicos t = new Tecnicos();

        try {
            if (appSession.getUsuario().getIdRol().getNombreRol().equals("Tecnico")) {
                t = getD().traeTecnico(appSession.getUsuario().getUsuarioUsuario());
                this.estaPausado = getD().traeEstadoPorElTecnico(requerimientoAsigSeleccionado.getIdEncaRequerimiento(), t.getTecnicosPK().getIdTecnico(), t.getTecnicosPK().getIdUndEjecutora());
                return this.estaPausado;
            } else {
                t = getD().traeTecnico(this.tecnicoSeleccionadoPorSuperAdmin.getUsuarioUsuario().getUsuarioUsuario());
                this.estaPausado = getD().traeEstadoPorElTecnico(requerimientoAsigSeleccionado.getIdEncaRequerimiento(), t.getTecnicosPK().getIdTecnico(), t.getTecnicosPK().getIdUndEjecutora());
                return this.estaPausado;
            }

        } catch (Exception e) {
            return false;


        }
    }
    private List<Tecnicos> listaDeTecnicosConCasos = new ArrayList<Tecnicos>();

    public List<Tecnicos> getListaDeTecnicosConCasos() {
        listaDeTecnicosConCasos = getD().traeTecnicos(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        return listaDeTecnicosConCasos;
    }

    public void setListaDeTecnicosConCasos(List<Tecnicos> listaDeTecnicosConCasos) {
        this.listaDeTecnicosConCasos = listaDeTecnicosConCasos;
    }

    public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }
}
