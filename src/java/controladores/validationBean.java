/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.Pattern;

/**
 *
 * 
 */

@ManagedBean
@SessionScoped
public class validationBean {
 @Pattern(regexp = "/^[-+]?\\d+$/") // integer  
    private String pattern;  
    /**
     * Creates a new instance of validationBean
     */
    public validationBean() {
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
