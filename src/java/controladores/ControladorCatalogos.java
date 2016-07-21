/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.Dao;
import dao.DaoLocal;
import entidades.CategoriaServicios;
import entidades.Dependencias;
import entidades.DireccionesInter;
import entidades.DireccionesNacionales;
import entidades.Estados;
import entidades.UnidadesEjecutoras;
import entidades.Usuarios;
import entidades.ViaSolicitudes;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import util.FacesUtil;

/**
 *
 * 
 */
@ManagedBean
@SessionScoped
public class ControladorCatalogos implements Serializable{

    /**
     * Creates a new instance of ControladorCatalogos
     */
    public ControladorCatalogos() {
    }

    private List<Estados> listaEstados=new ArrayList<Estados>();
    private List<Dependencias> listaDependencias=new ArrayList<Dependencias>();
    private List<Usuarios> listaUsuarios=new ArrayList<Usuarios>();
    private List<UnidadesEjecutoras> listaUnidadesEjecutoras=new ArrayList<UnidadesEjecutoras>();
    private List<DireccionesInter> listDireccionesInter=new ArrayList<DireccionesInter>();
    private List<DireccionesNacionales> listaDireccionesNacionales=new ArrayList<DireccionesNacionales>();
    private List<CategoriaServicios> listCategoriaServicios=new ArrayList<CategoriaServicios>();
    private List<ViaSolicitudes> listViaSolicitudes=new ArrayList<ViaSolicitudes>();
    private DireccionesInter nuevoDirecionInter=new DireccionesInter();

    public DireccionesInter getNuevoDirecionInter() {
        return nuevoDirecionInter;
    }

    public void setNuevoDirecionInter(DireccionesInter nuevoDirecionInter) {
        this.nuevoDirecionInter = nuevoDirecionInter;
    }
    
    public List<Estados> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Estados> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<Dependencias> getListaDependencias() {
        return listaDependencias;
    }

    public void setListaDependencias(List<Dependencias> listaDependencias) {
        this.listaDependencias = listaDependencias;
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<UnidadesEjecutoras> getListaUnidadesEjecutoras() {
        return listaUnidadesEjecutoras;
    }

    public void setListaUnidadesEjecutoras(List<UnidadesEjecutoras> listaUnidadesEjecutoras) {
        this.listaUnidadesEjecutoras = listaUnidadesEjecutoras;
    }

    public List<DireccionesInter> getListDireccionesInter() {
        listDireccionesInter=getD().traeDireccionesInter();
        return listDireccionesInter;
    }
    
    private DireccionesInter direcionesInterSeleccionado=new DireccionesInter();

    public DireccionesInter getDirecionesInterSeleccionado() {
        return direcionesInterSeleccionado;
    }

    public void setDirecionesInterSeleccionado(DireccionesInter direcionesInterSeleccionado) {
        this.direcionesInterSeleccionado = direcionesInterSeleccionado;
    }
    
   
    public void setListDireccionesInter(List<DireccionesInter> listDireccionesInter) {
        this.listDireccionesInter = listDireccionesInter;
    }

    public List<DireccionesNacionales> getListaDireccionesNacionales() {
       listaDireccionesNacionales=getD().traeDireccionesNacionales();
        return listaDireccionesNacionales;
    }
    private Integer direccionNacionalSeleccionada;

    public Integer getDireccionNacionalSeleccionada() {
        return direccionNacionalSeleccionada;
    }

    public void setDireccionNacionalSeleccionada(Integer direccionNacionalSeleccionada) {
        this.direccionNacionalSeleccionada = direccionNacionalSeleccionada;
    }
    
    
    
    
    
    public void setListaDireccionesNacionales(List<DireccionesNacionales> listaDireccionesNacionales) {
        this.listaDireccionesNacionales = listaDireccionesNacionales;
    }

    public List<CategoriaServicios> getListCategoriaServicios() {
        return listCategoriaServicios;
    }

    public void setListCategoriaServicios(List<CategoriaServicios> listCategoriaServicios) {
        this.listCategoriaServicios = listCategoriaServicios;
    }

    public List<ViaSolicitudes> getListViaSolicitudes() {
        return listViaSolicitudes;
    }

    public void setListViaSolicitudes(List<ViaSolicitudes> listViaSolicitudes) {
        this.listViaSolicitudes = listViaSolicitudes;
    }
    
    public void InsertarDireccionInter(){
        try{
            nuevoDirecionInter.setIdDirNacional(new DireccionesNacionales(direccionNacionalSeleccionada));
        getD().insertDireccionInter(nuevoDirecionInter);
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    private boolean estaPausado;

    public boolean isEstaPausado() {
        return estaPausado;
    }

    public void setEstaPausado(boolean estaPausado) {
        this.estaPausado = estaPausado;
    }
    
public DaoLocal getD() {
        return (DaoLocal) FacesUtil.getEjb("java:global/casosSec1.0/Dao!dao.DaoLocal");
    }
   
}
