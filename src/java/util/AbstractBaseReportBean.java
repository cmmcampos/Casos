/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;


public abstract class AbstractBaseReportBean {
	
	@Resource(mappedName="jdbc/casos")
	DataSource datasource;

    public enum ExportOption {

        PDF, HTML, EXCEL, RTF
    }
    protected List objetosReporte = new ArrayList();;
    protected String nombArch;
    private ExportOption exportOption;
    protected String reportDir;
    private Boolean imprimir = false;
    protected Map<String, Object> reportParameters = new HashMap<String, Object>();

    public AbstractBaseReportBean() {
        super();
        setExportOption(ExportOption.PDF);
    }

    protected void prepareReport() throws JRException, IOException, SQLException, ClassNotFoundException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      //  System.out.println(getReportDir()+ getNombArch() + ".jasper");

        JasperPrint jasperPrint = null;

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        
        File reportFile = new File(ReportUtil.getJasperFilePath(context, getReportDir(), getNombArch() + ".jasper"));

        // verificamos si es un informe con sql puro o con JRDataSource
        
        jasperPrint = objetosReporte.isEmpty() ? generarInforme(reportFile, jasperPrint) : ReportUtil.fillReport(reportFile, getReportParameters(), getJRDataSource());

        if (getExportOption().equals(ExportOption.HTML)) {
            ReportUtil.exportReportAsHtml(jasperPrint, response.getWriter());
        } else {
            request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
            if (imprimir) {
                ReportUtil.guardarEImprimirPDF(jasperPrint, "informe.pdf");
            }
//            else {
//                response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());
//            }

        }

        //  FacesContext.getCurrentInstance().responseComplete();
    }

    private JasperPrint generarInforme(File reportFile, JasperPrint jasperPrint) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
        	
//        	Class.forName("org.postgresql.Driver");
//                @SuppressWarnings("UnusedAssignment")
//        	Connection connection = null;
//        	connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/casos_db","postgres", "server_prueba");
        	
                 connection = datasource.getConnection();
            
           System.out.println("estado de la conexion"+ connection);
            
            jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), getReportParameters(), connection);
          //  conexion.commit();
        } catch (JRException jRException) {
           
			// TODO Auto-generated catch block
			jRException.printStackTrace();
		} finally {
        	try {
            connection.close();
        	}catch(Exception e){
        		System.out.println("conexion error "+ e);
        	}
        }

        return jasperPrint;

    }


    public ExportOption getExportOption() {
        return exportOption;
    }

    public void setExportOption(ExportOption exportOption) {
        this.exportOption = exportOption;
    }

    public String getNombArch() {
        return this.nombArch;
    }

    public void setNombArch(String NOMBRE_ARCHIVO) {
        this.nombArch = NOMBRE_ARCHIVO;
    }

    protected Map<String, Object> getReportParameters() {
        return new HashMap<String, Object>();
    }

    /**
     * @return the reportDir
     */
    public String getReportDir() {
        return reportDir;
    }

    /**
     * @param reportDir the reportDir to set
     */
    public void setReportDir(String reportDir) {
        this.reportDir = reportDir;
    }

    protected abstract JRDataSource getJRDataSource();

    /**
     * @return the imprimir
     */
    public Boolean getImprimir() {
        return imprimir;
    }

    /**
     * @param imprimir the imprimir to set
     */
    public void setImprimir(Boolean imprimir) {
        this.imprimir = imprimir;
    }
}
