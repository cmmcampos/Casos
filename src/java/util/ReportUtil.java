/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

public class ReportUtil {

    public static JasperPrint fillReport(File reportFile, Map<String, Object> parameters, JRDataSource jrDataSource) throws JRException {
        parameters.put("BaseDir", reportFile.getParentFile());
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), parameters, jrDataSource);
        return jasperPrint;
    }
 
    public static String getJasperFilePath(ServletContext context, String reportDir, String jasperFile) {
    	
        return context.getRealPath(reportDir + jasperFile);
    }

    private static void exportReport(JRAbstractExporter exporter, JasperPrint jasperPrint, PrintWriter out) throws JRException {
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);

        exporter.exportReport();
    }

    public static void exportReportAsHtml(JasperPrint jasperPrint, PrintWriter out) throws JRException {
        JRHtmlExporter exporter = new JRHtmlExporter();
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
        exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING, "ISO-8859-9");
        exportReport(exporter, jasperPrint, out);
    }

    public static void exportReportAsPDFFile(JasperPrint jasperPrint, String url) throws JRException, FileNotFoundException {
        OutputStream output = new FileOutputStream(new File(url));
        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
    }

    public static void guardarEImprimirPDF(JasperPrint jasperPrint, String file) throws FileNotFoundException, JRException, IOException {
        // url del sistema operativo
    	// C:\Users\Teo de Renderos\Downloads (esta debería ser: consultar con Dionicio)
        String url = "C:/Users/Claudia de Campos/Downloads/" + file;
        ReportUtil.exportReportAsPDFFile(jasperPrint, url);
        // ejecutamos el comando al sistema operativo
        Runtime.getRuntime().exec("lp " + url);
    }
}