package CONTROLLER;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Armando
 */
public class Halter {
    
    private String actions[];
    private HttpServletRequest request;

    public Halter(String actions[], HttpServletRequest request) {
        this.actions = actions;
        this.request = request;
    }
    
    public boolean validateMethod() {
        String action = actions[actions.length - 1];
        boolean aux = true;
        if(action.equals("View")) {
            aux = request.getSession().getAttribute(actions[actions.length - 2].toLowerCase()) == null;
        } else if(action.equals("Edit")) {
            aux = request.getSession().getAttribute("Persona") == null || request.getSession().getAttribute(actions[actions.length - 2]) == null || request.getSession().getAttribute("Usuario") == null;
                
        }
        return (action.equals("Store") || action.equals("View") || action.equals("Edit") || action.equals("Update") || action.equals("Destroy")) && !request.getMethod().equals("POST") && aux;
    }
    
}
