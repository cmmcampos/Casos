/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import util.AbstractBaseReportBean;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 */
@ManagedBean
@RequestScoped
public class ReporteBean extends AbstractBaseReportBean implements Serializable {

    @ManagedProperty(value = "#{appSession}")
    private AppSession appSession;
    private Date fechaInicio;
    private Date fechaFin;
    private String mes;
    private Integer anio;
    private Integer numCaso;
    private Integer tecnico;
    private Integer dependencia;

    /**
     * Creates a new instance of UsuarioBean
     */
    public ReporteBean() {
        reportDir = "/reportes/";

    }

    @Override
    protected Map<String, Object> getReportParameters() {

//      reportParameters.put("FechaInicial", getReporteBean().getFechaInicial());
//      reportParameters.put("FechaFinal", getReporteBean().getFechaFinal());
//      reportParameters.put("num_caso", getNum_caso());
//      reportParameters.put("anio",getAnio());
        return reportParameters;
    }

    @Override
    protected JRDataSource getJRDataSource() {
        return new JRBeanCollectionDataSource(objetosReporte);
    }

    public String execute() {
        try {
            super.prepareReport();
        } catch (Exception e) {
            // make your own exception handling
            e.printStackTrace();
        }

        return null;
    }

    public void addParametro(String key, Object valor) {
        reportParameters.put(key, valor);
    }

    public void setObjeto(Object objetos) {
        objetosReporte.clear();
        objetosReporte.add(objetos);
        execute();
    }

    public void repor() {
        this.setReportDir("/reportes/");
        this.addParametro("tecnico", getTecnico());
        this.addParametro("fechaInicial", getFechaInicio());
        this.addParametro("fechaFinal", getFechaFin());
        System.out.println(getFechaInicio());
        execute();
    }

    public void repor2() {
        this.setReportDir("/reportes/");
        this.addParametro("fecha1", getFechaInicio());
        this.addParametro("fecha2", getFechaFin());
        System.out.println(getFechaInicio());
        execute();
    }
    
     public void repor3() {
        this.setReportDir("/reportes/");
        this.addParametro("fecha1", getFechaInicio());
        this.addParametro("fecha2", getFechaFin());
        this.addParametro("undEje", appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        System.out.println(getFechaInicio());
        System.out.println(getFechaFin());
        execute();
               
    }
    
    public void repor4() {
        this.setReportDir("/reportes/");
        this.addParametro("fechaInicial", getFechaInicio());
        this.addParametro("fechaFinal", getFechaFin());
        this.addParametro("tecnicoUsr", appSession.getUsuario().getUsuarioUsuario());
        //System.out.println(getFechaInicio());
        //System.out.println(getFechaFin());
        //System.out.println(appSession.getUsuario().getUsuarioUsuario());
        execute();
               
    }
     
     
              
    public void reporDep() {
        this.setReportDir("/reportes/");
        execute();
    }

    public void reporReque() {
        this.setReportDir("/reportes/");
        if (numCaso != null) {
            this.addParametro("numCaso", getNumCaso());
            this.addParametro("anio", getAnio());
            this.addParametro("undEje", appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        }
        execute();
    }

    public void reporParametros() {
        //System.out.println(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        this.setReportDir("/reportes/");
        this.addParametro("undEje", appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        
        execute();
    }

    public void reporXDep() {
        this.setReportDir("/reportes/");
        this.addParametro("fecha1", getFechaInicio());
        this.addParametro("fecha2", getFechaFin());
        this.addParametro("undEje", appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        this.addParametro("dependencia", getDependencia() );
        System.out.println(getFechaInicio());
        System.out.println(getFechaFin());
        execute();
               
    }
    
    
    
    
    public Integer getNumCaso() {
        return numCaso;
    }

    public void setNumCaso(Integer numCaso) {
        this.numCaso = numCaso;
    }

    public AppSession getAppSession() {
        return appSession;
    }

    public void setAppSession(AppSession appSession) {
        this.appSession = appSession;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getDependencia() {
        return dependencia;
    }

    public void setDependencia(Integer dependencia) {
        this.dependencia = dependencia;
    }
    
    
    
}
