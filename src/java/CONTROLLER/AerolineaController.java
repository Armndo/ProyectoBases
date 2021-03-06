package CONTROLLER;

import MODEL.Aerolinea;
import MODEL.Persona;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Armando
 */
public class AerolineaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("persona") == null)
            response.sendRedirect(request.getContextPath() + "/Login");
        else {
            Persona persona = (Persona)session.getAttribute("persona");
            if(persona.empleado() != null && persona.empleado().getPuesto().equals("Administrador")) {
                String actions[] = request.getRequestURI().split("/");
                String action = actions[actions.length-1];
                if(new Halter(actions, request).validateMethod())
                    response.sendRedirect(request.getContextPath() + "/Aerolinea");
                else {
                    switch(action) {
                        case "Aerolinea":
                            index(request, response);
                            break;
                        case "Create":
                            create(request, response);
                            break;
                        case "Store":
                            store(request, response);
                            break;
                        case "View":
                            view(request, response);
                            break;
                        case "Edit":
                            edit(request, response);
                            break;
                        case "Update":
                            update(request, response);
                            break;
                        case "Search":
                            search(request, response);
                            break;
                        case "Destroy":
                            destroy(request, response);
                            break;
                        default:
                            response.sendRedirect(request.getContextPath());
                    }
                }
            } else
                response.sendRedirect(request.getContextPath());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("aerolineas", Aerolinea.get());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aerolinea/index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aerolinea/create.jsp");
        dispatcher.forward(request, response);
    }
    
    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre").trim();
        Aerolinea aerolinea = new Aerolinea(nombre);
        aerolinea.commit();
        request.getSession().setAttribute("aerolinea", aerolinea);
        response.sendRedirect(request.getContextPath() + "/Aerolinea/View");
    }
    
    private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0;
        Aerolinea aerolinea = id != 0 ? new Aerolinea(id) : (Aerolinea)request.getSession().getAttribute("aerolinea");
        if(aerolinea.getId() != 0) {
            request.getSession().removeAttribute("aerolinea");
            request.setAttribute("aerolinea", aerolinea);
            request.setAttribute("aeronaves", aerolinea.aeronaves());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aerolinea/view.jsp");
            dispatcher.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath());
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Aerolinea aerolinea = new Aerolinea(id);
        if(aerolinea.getId() != 0) {
            request.setAttribute("aerolinea", aerolinea);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aerolinea/edit.jsp");
            dispatcher.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath());
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        Aerolinea aerolinea = new Aerolinea(id);
        aerolinea.update(id, nombre);
        request.getSession().setAttribute("aerolinea", aerolinea);
        response.sendRedirect(request.getContextPath() + "/Aerolinea/View");
    }
    
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("data");
        request.setAttribute("aerolineas", Aerolinea.search(data));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aerolinea/search.jsp");
        dispatcher.forward(request, response);
    }

    private void destroy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new Aerolinea(Integer.parseInt(request.getParameter("id"))).destroy();
        response.sendRedirect(request.getContextPath() + "/Aerolinea");
    }
    
}