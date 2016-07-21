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
import entidades.UnidadesEjecutoras;
import entidades.Usuarios;
import entidades.ViaSolicitudes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import util.FacesUtil;

/**
 *
 * @author
 */
@ManagedBean
@SessionScoped
public class controladorAdicionCaso implements ValueChangeListener, Serializable {

    private List<Usuarios> listUsuarios = new ArrayList<Usuarios>();
    private List<ViaSolicitudes> listViaSolic = new ArrayList<ViaSolicitudes>();
    private List<Servicios> listServ = new ArrayList<Servicios>();
    private List<CategoriaServicios> listCatServ = new ArrayList<CategoriaServicios>();
    private EncaRequerimientos nuevoReque = new EncaRequerimientos();
    private Usuarios usuarioSeleccionado = new Usuarios();
    private List<EncaRequerimientos> listRequeriPendien = new ArrayList<EncaRequerimientos>();
    private List<Tecnicos> listTecnicos = new ArrayList<Tecnicos>();
    private EncaRequerimientos requeSeleccionado = new EncaRequerimientos();
    private AsignarTecnico nuevaAsignacion = new AsignarTecnico();
    private AsignarTecnico reasignacion = new AsignarTecnico();
    private Date fechaActual = new Date();
    private int viaSelec, catServ, tecSelec, tecActual, numCaso;
    private List<Documentos> docComple = new ArrayList<Documentos>();
    private Documentos newDocumento = new Documentos();
    private String detino = "C://temporalArchivos/";
    private String destinoTemp = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");

    private UploadedFile file21;
    private static final int BUFFER_SIZE = 6124;

    private int idSum;
    private List<EncaRequerimientos> listEncaRequerimientoAModificar = new ArrayList<EncaRequerimientos>();
    private EncaRequerimientos requerimientoSeleccionadoAModificar = new EncaRequerimientos();
    private Usuarios usuarioSolicitanteSelecionadoAModificar = new Usuarios();
    private Tecnicos tecnicoSeleccionadoPorSuperAdmin = new Tecnicos();
    private List<EncaRequerimientos> listAllRequeri = new ArrayList<EncaRequerimientos>();
    private List<EncaRequerimientos> listaTodosXTecnico = new ArrayList<EncaRequerimientos>();
    private UnidadesEjecutoras correlCasoXUndidad = new UnidadesEjecutoras();
    private EncaRequerimientos encaElejidoDetodos = new EncaRequerimientos();
    private AsignarTecnico asignaDelElejiReque = new AsignarTecnico();
    private Date fechaSeguimiento = new Date();
    SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
    @ManagedProperty(value = "#{appSession}")
    private AppSession appSession;
    private int totalCasos;

    public AppSession getAppSession() {
        return appSession;
    }

    public void setAppSession(AppSession appSession) {
        this.appSession = appSession;
    }
    /**
     * para servicio de javaMail
     */
    private String correoSolicitante, correoTec, descripcion, nombTecnico, nombSolicitante, telTecnico;
    private Date fecSoli;
    private Tecnicos destinatario = new Tecnicos();
    private String fechaFormateada;
    private String correoJefe;

    /**
     * Creates a new instance of controladorAdicionCaso
     */
    public controladorAdicionCaso() {
    }

    public EncaRequerimientos getEncaElejidoDetodos() {
        //System.out.println("prueba2");

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

    public void eliminarAdjunto() {

        FacesContext fc2 = FacesContext.getCurrentInstance();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        String indice = params.get("id");
        String tipo = params.get("tipo");
        if (tipo.equals("nuevo")) {
            try {
                listaDocASubir.remove(Integer.parseInt(indice) - 1);
                if (listaDocASubir.size() == 0) {
                    bandera = false;
                }
                fc2.getExternalContext().redirect("/casosSec1.0/contenido/for_nuevoCaso.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(controladorAdicionCaso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (tipo.equals("modificado")) {
            try {
                Documentos d = requerimientoSeleccionadoAModificar.getDocumentosList().remove(Integer.parseInt(indice) - 1);
                listaDocABorrar.add(d);
                if (requerimientoSeleccionadoAModificar.getDocumentosList().size() == 0) {
                    // bandera=false;
                }
                fc2.getExternalContext().redirect("/casosSec1.0/contenido/for_modificacionRequerimiento.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(controladorAdicionCaso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void rediDoc(Documentos d) {
        FacesContext fc = FacesContext.getCurrentInstance();

        try {

            fc.getExternalContext().redirect("verArchivoPdf.xhtml?variable=" + d.getNombreDocumento());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

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

// para lista de todos lo requerimientos de un técnico logueado
    public List<EncaRequerimientos> getListaTodosXTecnico() {

        List<EncaRequerimientos> listAux = new ArrayList<>();
        List<EncaRequerimientos> listaAux3 = new ArrayList<EncaRequerimientos>();

        try {

            if (appSession.getUsuario().getIdRol().getNombreRol().equals("Tecnico")) {
                Tecnicos tecnico = getD().traeTecnico(appSession.getUsuario().getUsuarioUsuario());

                listAux = getD().traeTodosRequeDelTecnico(tecnico.getTecnicosPK().getIdTecnico(), appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
                for (EncaRequerimientos item : listAux) {
                    listaTodosXTecnico.add(item);
                }
            }
        } catch (Exception e) {
        }
        listaAux3 = listaTodosXTecnico;
        //listaTodosXTecnico = new ArrayList<EncaRequerimientos>();
        return listaAux3;

    }

    public void setListaTodosXTecnico(List<EncaRequerimientos> listaTodosXTecnico) {
        this.listaTodosXTecnico = listaTodosXTecnico;
    }

    ////
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

    ////////////////////////
    public UploadedFile getFile21() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Archivo cargado exitosamente!"));
        return file21;
    }

    public void setFile21(UploadedFile file21) {
        this.file21 = file21;
    }

    private List<Documentos> listaDocASubir = new ArrayList<Documentos>();

    public List<Documentos> getListaDocASubir() {
        return listaDocASubir;
    }

    public void setListaDocASubir(List<Documentos> listaDocASubir) {
        this.listaDocASubir = listaDocASubir;
    }

    public Documentos getNewDocumento() {
        return newDocumento;
    }

    public void setNewDocumento(Documentos newDocumento) {
        this.newDocumento = newDocumento;
    }

    public void handleFileUpload(FileUploadEvent event) {
        System.out.println("Id enca reque handle" + nuevoReque.getIdEncaRequerimiento());
        FacesContext ctx = FacesContext.getCurrentInstance();
        String picture_directory = ctx.getExternalContext().getInitParameter("pictures_directory_path");
        //If directory exists ? do nothing : make directory
        File storage_folder = new File(picture_directory);
    }

    public void uploadFiles() {
        System.out.println(file21.getFileName());
        FacesContext ctx = FacesContext.getCurrentInstance();
        String picture_directory = ctx.getExternalContext().getInitParameter("pictures_directory_path");
        //If directory exists ? do nothing : make directory
        File storage_folder = new File(picture_directory + nuevoReque.getIdEncaRequerimiento());
        if (!storage_folder.exists()) {
            storage_folder.mkdir();
        }

        String fname = file21.getFileName();
        int middle = fname.lastIndexOf(".");
        String ext = fname.substring(middle + 1, fname.length());

        File archivoImagen = new File(picture_directory + nuevoReque.getIdEncaRequerimiento() + "/" + file21.getFileName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(archivoImagen);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = file21.getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();

            newDocumento.setUrlDocumento(picture_directory + nuevoReque.getIdEncaRequerimiento() + "/" + file21.getFileName());
            newDocumento.setNombreDocumento(file21.getFileName());
            newDocumento.setDescripcionDocumento(ext);
            newDocumento.setIdEncaRequerimiento(new EncaRequerimientos(nuevoReque.getIdEncaRequerimiento()));
            getD().insertDocumento(newDocumento);
            newDocumento = new Documentos();

        } catch (IOException e) {
            System.out.println(e);         
            System.out.println("Error al subir documento");
        }
    }

//    public void upload() {
//        FacesContext f = FacesContext.getCurrentInstance();
//        Map<String, String> params = f.getExternalContext().getRequestParameterMap();
//        System.out.println("dentro de upload");
//        String tempExt = null;
//        String extValidate;
//        if (getFile21() != null) {
//            String ext = getFile21().getFileName();
//            if (ext != null) {
//                String[] exte = ext.split("/.");
//                int indice = exte.length;
//                tempExt = exte[indice - 1];
//                extValidate = ext.substring(ext.indexOf(".") + 1);
//            } else {
//                extValidate = "null";
//            }
//            if (extValidate.equals("pdf") || extValidate.equals("jpg") || extValidate.equals("JPG")) {
//                try {
//                    TransFerFile(getFile21().getFileName(), getFile21().getInputstream());
//                    if (params.get("tipo").equals("nuevo")) {
//                        this.bandera = true;
//                    }
//
//                    this.textoSubirArchivo = "Cargar otro Archivo";
//                    FacesContext context = FacesContext.getCurrentInstance();
//                    context.addMessage(null, new FacesMessage("Archivo Cargado Exitosamente"));
//                } catch (Exception e) {
//                    Logger.getLogger(controladorAdicionCaso.class.getName()).log(Level.SEVERE, null, e);
//                    FacesContext context = FacesContext.getCurrentInstance();
//                    context.addMessage(null, new FacesMessage("Error al Subir Archivo"));
//
//                }
//
//
//            } else {
//                FacesContext context = FacesContext.getCurrentInstance();
//                context.addMessage(null, new FacesMessage("Valido para Archivos pdf y JPG"));
//
//            }
//        } else {
//            FacesContext context = FacesContext.getCurrentInstance();
//            context.addMessage(null, new FacesMessage("Seleccione Archivo"));
//
//
//        }
//    }
    public String getDetino() {
        return detino;
    }

    public void setDetino(String detino) {
        this.detino = detino;
    }

    private String textoSubirArchivo = "Cargar Archivo";

    public String getTextoSubirArchivo() {
        return textoSubirArchivo;
    }

    public void setTextoSubirArchivo(String textoSubirArchivo) {
        this.textoSubirArchivo = textoSubirArchivo;
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

    public AsignarTecnico getReasignacion() {
        return reasignacion;
    }

    public void setReasignacion(AsignarTecnico reasignacion) {
        this.reasignacion = reasignacion;
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

    public List<EncaRequerimientos> getListRequeriPendien() {
        listRequeriPendien = getD().traeEncaRequrimientos(1, 5, appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        //System.out.println("asd");
        return listRequeriPendien;
    }

    public void setListRequeriPendien(List<EncaRequerimientos> listRequeriPendien) {
        this.listRequeriPendien = listRequeriPendien;
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
        totalCasos = listaAux.size();
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
        tecnicoSeleccionadoPorSuperAdmin = getD().devuelveTecnicos(sumaPesoTecnicoSelec.getIdTecnico());
        try {
            //this.bandera2=false;
            
            fc.getExternalContext().redirect("cat_listaCasosAsignados.xhtml");
        } catch (Exception e) {
        }
    }

    public void redireccionarCasosDelTecnicoR() {
        FacesContext fc = FacesContext.getCurrentInstance();
        tecnicoSeleccionadoPorSuperAdmin = getD().devuelveTecnicos(sumaPesoTecnicoSelec.getIdTecnico());
        
        try {
            //this.bandera2=false;
            fc.getExternalContext().redirect("cat_listaCasosReasignar.xhtml");
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

    public void requeAsignadoSelecReasignar() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            fc.getExternalContext().redirect("for_reasignarTecnico.xhtml");
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

            // correo y datos del solicitante para enviar notificacion de lo solicitado
            correoSolicitante = requeSeleccionado.getUsuarioUsuarioSolicitante().getCorreoUsuario();
            descripcion = requeSeleccionado.getDescripcionRequerimiento();
            nombSolicitante = requeSeleccionado.getSolicitante();
            numCaso = requeSeleccionado.getNumCaso();
            fecSoli = requeSeleccionado.getFechaIngreso();

            String patron = "dd/MM/yyyy";
            SimpleDateFormat formato = new SimpleDateFormat(patron);
            fechaFormateada = formato.format(fecSoli);
            //System.out.println(correoSolicitante);

            try {
                SimpleDateFormat dc = new SimpleDateFormat("HH:mm");
                nuevaAsignacion.setAsignarTecnicoPK(new AsignarTecnicoPK(requeSeleccionado.getIdEncaRequerimiento(), tecSelec, getD().encontrarTecnico(tecSelec)));

                //System.out.println(tecSelec);
                if (requeSeleccionado.getIdEstado().getIdEstado() == 1) {
                    nuevaAsignacion.setEstadoDelTecnico("asignado");
                }
                if (requeSeleccionado.getIdEstado().getIdEstado() == 5) {
                    nuevaAsignacion.setEstadoDelTecnico("reasignado");
                }

                // correo y nombre del tecnico para enviar notificacion via correo
                correoTec = getD().tecnicoPorId(tecSelec).getCorreoTecnico();
                nombTecnico = getD().tecnicoPorId(tecSelec).getNombreTecnico();
                telTecnico = getD().tecnicoPorId(tecSelec).getTelefonoTecnico();
                correoJefe = getD().tecnicoPorId(tecSelec).getUnidadesEjecutoras().getCorreoJefeUndEjecutora();
                System.out.println(correoJefe);

                getD().insertAsignarTecnico(nuevaAsignacion);
                requeSeleccionado.setIdEstado(new Estados(2));
                //requeSeleccionado.setNumCaso(d.encontrarNumCaso(d.encontrarTecnico(tecSelec)));
                getD().updateEncaRequerimientos(requeSeleccionado);

                //llamada a método para enviar correo de notificación a tecnico y solicitante
                sendMailAsigna();

                nuevaAsignacion = new AsignarTecnico();
                this.tecnicoSeleccionadoConPeso = new Tecnicos();
                tecSelec = 0;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se Asigno exitosamente!"));

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fallo la asignacion: " + e.toString()));
                System.out.println(e.toString());
            }
        }
        nuevaAsignacion = new AsignarTecnico();
        this.tecnicoSeleccionadoConPeso = new Tecnicos();
        tecSelec = 0;

    }

    public void crearReasignacion() throws UnsupportedEncodingException {
        if (tecSelec == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Seleccione un tecnico!"));
        } else {

            System.out.println("dentro de reasignacion " + tecSelec);
            System.out.println("dentro de crear reasigna " + tecActual);
            
            // correo del solicitante para enviar notificacion y descripcion de lo solicitado
            correoSolicitante = requerimientoAsigSeleccionado.getUsuarioUsuarioSolicitante().getCorreoUsuario();
            descripcion = requerimientoAsigSeleccionado.getDescripcionRequerimiento();
            nombSolicitante = requerimientoAsigSeleccionado.getSolicitante();
            numCaso = requerimientoAsigSeleccionado.getNumCaso();
            fecSoli = requerimientoAsigSeleccionado.getFechaIngreso();

            String patron = "dd/MM/yyyy";
            SimpleDateFormat formato = new SimpleDateFormat(patron);
            fechaFormateada = formato.format(fecSoli);
            //System.out.println(correoSolicitante);

            getD().insertAsignarTecnico2(requerimientoAsigSeleccionado.getIdEncaRequerimiento(), tecSelec,
                    requerimientoAsigSeleccionado.getAsignarTecnicoList().get(0).getAsignarTecnicoPK().getIdUndEjecutora(), tecActual);

            // correo y nombre del tecnico para enviar notificacion via correo
            correoTec = getD().tecnicoPorId(tecSelec).getCorreoTecnico();
            nombTecnico = getD().tecnicoPorId(tecSelec).getNombreTecnico();
            telTecnico = getD().tecnicoPorId(tecSelec).getTelefonoTecnico();
            correoJefe = getD().tecnicoPorId(tecSelec).getUnidadesEjecutoras().getCorreoJefeUndEjecutora();  //"rcontreras@cultura.gob.sv"; 
            System.out.println(correoJefe);

            //llamada a método para enviar correo de notificación a tecnico y solicitante
            sendMailAsigna();

            detalleDeLaAsignacion = new AsignarTecnico();
            tecSelec = 0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se Reasigno exitosamente!"));

        }
    }

    public void limpiarFormularioCrearReq() {
        nuevoReque = new EncaRequerimientos();
        nuevoReque.setDocumentacionCompleta(false);
        this.usuarioSeleccionado = new Usuarios();
        this.viaSelec = 0;
        this.catServ = 0;
        this.bandera = false;
        this.listaDocASubir = new ArrayList<Documentos>();
        this.textoSubirArchivo = "Cargar Archivo";
        this.correlCasoXUndidad = new UnidadesEjecutoras();
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
                this.textoSubirArchivo = "Cargar Archivo";
                //requerimientoSeleccionadoAModificar = new EncaRequerimientos();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Complete Correctamente los campos!"));
            System.out.println(e.toString());
        }
    }

    public void crearRequerimiento() {
        try {
            //System.out.println("");
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
                             
                this.usuarioSeleccionado = new Usuarios();
                this.viaSelec = 0;
                this.catServ = 0;
                this.bandera = false;
                                
                System.out.println(file21);
                if (file21.getSize() != 0) {
                    System.out.println("dentro de condición, fichero no es nulo");
                    uploadFiles();
                }
                this.listaDocASubir = new ArrayList<Documentos>();
                this.textoSubirArchivo = "Cargar Archivo";
                nuevoReque = new EncaRequerimientos();
                this.correlCasoXUndidad = new UnidadesEjecutoras();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Requerimiento ingresado exitosamente!"));
//            fc.getExternalContext().redirect("for_consultaCasos.xhtml");

            }
        } catch (Exception e) {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Complete Correctamente los campos!"));
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
        String summary = requerimientoSeleccionadoAModificar.getDocumentacionCompleta() ? "Seleccione Documento de tipo pdf o jpg" : "No Requiere Documentación";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public void control() {
        this.nuevoReque.setDocumentacionCompleta(this.nuevoReque.getDocumentacionCompleta() ? true : false);
        String summary = nuevoReque.getDocumentacionCompleta() ? "Seleccione Documento de tipo pdf o jpg" : "No Requiere Documentación";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }
    private List<SumaPesoTecnico> traeTecnicosConSuPeso1;

    private SumaPesoTecnico sumaPesoTecnicoSelec= new SumaPesoTecnico();

    public SumaPesoTecnico getSumaPesoTecnicoSelec() {
        return sumaPesoTecnicoSelec;
    }

    public void setSumaPesoTecnicoSelec(SumaPesoTecnico sumaPesoTecnicoSelec) {
        this.sumaPesoTecnicoSelec = sumaPesoTecnicoSelec;
    }
        
    
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
            System.out.println("nuevo tecnico" + tecSelec);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tecnico selecionado exitosamente!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se pudo asignar tecnico!"));
        }

    }

    public void asignaIdNuevoTecnico() {
        try {
            this.tecActual = this.tecnicoSeleccionadoPorSuperAdmin.getTecnicosPK().getIdTecnico();
            this.tecSelec = this.tecnicoConUnPesoSeleccionado.getIdTecnico();
            this.tecnicoSeleccionadoConPeso = getD().devuelveTecnicos(this.tecSelec);

//            System.out.println("nuevo tecnico" + tecSelec);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tecnico selecionado exitosamente!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se pudo asignar tecnico!"));
        }

    }

    public int getTecActual() {
        return tecActual;
    }

    public void setTecActual(int tecActual) {
        this.tecActual = tecActual;
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
            detaRequerimientos.setSistemUser(appSession.getUsuario().getUsuarioUsuario());
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

    //Metodo para cambiar formato de fecha
//    
    public String fechaToString(Date fecha) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaConvertida = formato.format(fecha);
        return fechaConvertida;
    }

    private List<Tecnicos> listaDeTecnicosConCasos = new ArrayList<Tecnicos>();

    public List<Tecnicos> getListaDeTecnicosConCasos() {
        listaDeTecnicosConCasos = getD().traeTecnicos(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        return listaDeTecnicosConCasos;
    }

    public void setListaDeTecnicosConCasos(List<Tecnicos> listaDeTecnicosConCasos) {
        this.listaDeTecnicosConCasos = listaDeTecnicosConCasos;
    }

    // Lista para filtrar técnicos por unidad ejecutora que estén activos, de la tabla Usuarios
    
    private List<Usuarios> listaDeTecnicosActivosConCasos = new ArrayList<Usuarios>();
    
    public List<Usuarios> getListaDeTecnicosActivosConCasos() {
        listaDeTecnicosActivosConCasos = getD().traeTecnicosActivos(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        return listaDeTecnicosActivosConCasos;
    }
    
    public void setListaDeTecnicosActivosConCasos(List<Usuarios> listaDeTecnicosActivosConCasos) {
        this.listaDeTecnicosActivosConCasos = listaDeTecnicosActivosConCasos;
    }

   
    public String getCorreoSolicitante() {
        return correoSolicitante;
    }

    public void setCorreoSolicitante(String correoSolicitante) {
        this.correoSolicitante = correoSolicitante;
    }

    public String getCorreoTec() {
        return correoTec;
    }

    public void setCorreoTec(String correoTec) {
        this.correoTec = correoTec;
    }

    public String getCorreoJefe() {
        return correoJefe;
    }

    public void setCorreoJefe(String correoJefe) {
        this.correoJefe = correoJefe;
    }
    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombTecnico() {
        return nombTecnico;
    }

    public void setNombTecnico(String nombTecnico) {
        this.nombTecnico = nombTecnico;
    }

    public String getNombSolicitante() {
        return nombSolicitante;
    }

    public void setNombSolicitante(String nombSolicitante) {
        this.nombSolicitante = nombSolicitante;
    }

    public Tecnicos getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Tecnicos destinatario) {
        this.destinatario = destinatario;
    }

    public int getNumCaso() {
        return numCaso;
    }

    public void setNumCaso(int numCaso) {
        this.numCaso = numCaso;
    }

    public Date getFecSoli() {
        return fecSoli;
    }

    public void setFecSoli(Date fecSoli) {
        this.fecSoli = fecSoli;
    }

    public List<Documentos> getDocComple() {
        return docComple;
    }

    public void setDocComple(List<Documentos> docComple) {
        this.docComple = docComple;
    }

    public void sendMailAsigna() throws UnsupportedEncodingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "correo.cultura.gob.sv");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.password", "GI$noti2014");
        props.setProperty("mail.user", "notificaciones@cultura.gob.sv");

        Session mailSession;
        mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("notificaciones@cultura.gob.sv", "GI$noti2014");
            }
        });

        Message msg = new MimeMessage(mailSession);

        String template = "<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:500px\">"
                + "	<caption>.: Detalle del requerimiento :.</caption>"
                + "	<tbody>"
                + "		<tr>"
                + "			<td>Tecnico asignado:</td>"
                + "			<td>" + nombTecnico + "</td>"
                + "		</tr>"
                + "		<tr>"
                + "			<td>Tel. de contacto:</td>"
                + "			<td>" + telTecnico + "</td>"
                + "		</tr>"
                + "		<tr>"
                + "			<td>Fecha de solicitud:</td>"
                + "			<td>" + fechaFormateada + "</td>"
                + "		</tr>"
                + "		<tr>"
                + "			<td>No. de requerimiento:</td>"
                + "			<td>" + numCaso + "</td>"
                + "		</tr>"
                + "		<tr>"
                + "			<td>Usuario Solicitante:</td>"
                + "			<td>" + nombSolicitante + "</td>"
                + "		</tr>"
                + "		<tr>"
                + "			<td>Detalle de la solicitud:</td>"
                + "			<td>" + descripcion + "</td>"
                + "		</tr>"
                + "	</tbody>"
                + "	<tfoot>"
                + "		<tr>"
                + "                     <td colspan=\"2\">Este es un correo automático, favor no responda a este mensaje.</td>"
                + "             </tr>"
                + "	</tfoot>"
                + "</table>";
        try {
            msg.setSubject(MimeUtility.encodeText("<Sistema de Requerimientos> Notificación de requerimiento asignado", "UTF-8", "B"));
            msg.setFrom(new InternetAddress("notificaciones@cultura.gob.sv"));
            // msg.setRecipients(Message.RecipientType.TO, adrss)
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(correoTec));
            msg.addRecipient(Message.RecipientType.CC, new InternetAddress(correoSolicitante));
            msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(correoJefe));
            msg.setContent(template, "text/html; charset=UTF-8");
            Transport.send(msg);

            correoSolicitante = correoTec = correoJefe = descripcion = nombTecnico = nombSolicitante = telTecnico = "";
            fecSoli = new Date();
            numCaso = 0;
            System.out.println("Correos enviados exitosamente!");

        } catch (MessagingException me) {

            System.out.println(me.toString());
        }
    }

    public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }
}
