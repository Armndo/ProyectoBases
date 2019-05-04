package CONTROLLER;

import MODEL.Aerolinea;
import MODEL.Aeronave;
import MODEL.Avion;
import MODEL.Fabricante;
import MODEL.Persona;
import java.io.IOException;
import java.util.ArrayList;
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
public class AeronaveController extends HttpServlet {

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
            if(persona.empleado() != null) {
                String actions[] = request.getRequestURI().split("/");
                String action = actions[actions.length-1];
                if(new Halter(actions, request).validateMethod())
                    response.sendRedirect(request.getContextPath() + "/Aeronave");
                else {
                    request.setCharacterEncoding("UTF-8");
                    switch(action) {
                        case "Aeronave":
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
                        case "Bind":
                            bind(request, response);
                            break;
                        case "Link":
                            link(request, response);
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
        request.setAttribute("aeronaves", Aeronave.get());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aeronave/index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("fabricantes", Fabricante.get());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aeronave/create.jsp");
        dispatcher.forward(request, response);
    }
    
    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String modelo = request.getParameter("modelo").trim();
        int capacidad = Integer.parseInt(request.getParameter("capacidad").trim());
        int fabricante_id = Integer.parseInt(request.getParameter("fabricante_id").trim());
        Aeronave aeronave = new Aeronave(modelo, capacidad, fabricante_id);
        aeronave.commit();
        request.getSession().setAttribute("aeronave", aeronave);
        response.sendRedirect(request.getContextPath() + "/Aeronave/View");
    }
    
    private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0;
        Aeronave aeronave = id != 0  ? new Aeronave(id) : (Aeronave)request.getSession().getAttribute("aeronave");
        if(aeronave.getId() != 0) {
            request.getSession().removeAttribute("aeronave");
            request.setAttribute("aeronave", aeronave);
            request.setAttribute("aerolineas", aeronave.aerolineas());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aeronave/view.jsp");
            dispatcher.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath());
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Aeronave aeronave = new Aeronave(id);
        if(aeronave.getId() != 0) {
            request.setAttribute("aeronave", aeronave);
            request.setAttribute("fabricantes", Fabricante.get());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aeronave/edit.jsp");
            dispatcher.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath());
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String modelo = request.getParameter("modelo");
        int capacidad = Integer.parseInt(request.getParameter("capacidad"));
        int fabricante_id = Integer.parseInt(request.getParameter("fabricante_id").trim());
        Aeronave aeronave = new Aeronave(id);
        aeronave.update(id, modelo, capacidad, fabricante_id);
        request.getSession().setAttribute("aeronave", aeronave);
        response.sendRedirect(request.getContextPath() + "/Aeronave/View");
    }
    
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("data");
        request.setAttribute("aeronaves", Aeronave.search(data));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aeronave/search.jsp");
        dispatcher.forward(request, response);
    }
    
    private void bind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Aeronave aeronave = new Aeronave(id);
        if(aeronave.getId() != 0) {
            request.setAttribute("aeronave", aeronave);
            request.setAttribute("aerolineas", Aerolinea.get());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aeronave/bind.jsp");
            dispatcher.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath());
    }
    
    private void link(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int aeronave_id = Integer.parseInt(request.getParameter("aeronave_id"));
        int aerolinea_id = Integer.parseInt(request.getParameter("aerolinea_id"));
        new Avion(aeronave_id, aerolinea_id).commit();
        Aeronave aeronave = new Aeronave(aeronave_id);
        request.getSession().setAttribute("aeronave", aeronave);
        response.sendRedirect(request.getContextPath() + "/Aeronave/View");
    }

    private void destroy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new Aeronave(Integer.parseInt(request.getParameter("id"))).destroy();
        response.sendRedirect(request.getContextPath() + "/Aeronave");
    }
    
}