/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.sun.tools.ws.wsdl.document.Output;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author Usuario Desarrollo
 */
@ManagedBean
@SessionScoped
public class subirController {

    /**
     * Creates a new instance of subirController
     */
    public subirController() {
    }

 private String detino=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Documentos");
    private UploadedFile file21=null;

     

    public String getDetino() {
        return detino;
    }

    public void setDetino(String detino) {
        this.detino = detino;
    }

    public UploadedFile getFile() {
        return file21;
    }

    public void setFile(UploadedFile file) {
        this.file21 = file;
    }
    
    public void TransFerFile(String fileName,InputStream in){
        try {
            OutputStream out=new FileOutputStream(new File(this.detino +"\\" + fileName));
            int reader=0;
            byte [] bytes=new byte[(int) getFile().getSize()];
            while((reader= in.read(bytes)) != -1){
                out.write(bytes,0,reader);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
          public void handleFileUpload(FileUploadEvent event) {  
             try{
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
             }catch(Exception e){
                 System.out.println(e.toString());
                 
             }
    }
    
    
    public void upload(){
        String a="asd";
        String extValidate;
        if(getFile()!=null){
            String ext=getFile().getFileName();
            if(ext!=null){
            extValidate=ext.substring(ext.indexOf(".")+1);
            }else{
                extValidate="null";
            }
            if(extValidate.equals("pdf")){
                try {
                    TransFerFile(getFile().getFileName(), getFile().getInputstream());
                } catch (IOException e) {
                Logger.getLogger(subirController.class.getName()).log(Level.SEVERE,null,e);
                FacesContext context=FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Wrong","Error de subir"));
                }
                FacesContext context=FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Succesful",getFile().getFileName()));
                
            }else{
                FacesContext context=FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Wrong_ext","solo pdf"));
            }
            }else{
                FacesContext context=FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Wrong_ext","seleccione archivo"));
            
        }
    }
    
    
 
    
    


}
