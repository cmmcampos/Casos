/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contex;

import org.springframework.context.ApplicationContext;


/**
 * This class provides application-wide access to the Spring ApplicationContext.
 * The ApplicationContext is injected by the class "ApplicationContextProvider".
 *
 * @author Dionicio Calles
 */
public class AppContext {

    private static ApplicationContext ctx;

    /**
     * Injected from the class "ApplicationContextProvider" which is automatically
     * loaded during Spring-Initialization.
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    /**
     * Get access to the Spring ApplicationContext from everywhere in your Application.
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    public static Object getBeanSpring(String clase) {
        return AppContext.getApplicationContext().getBean(clase);
    }
   


} 