package CONTROLLER;

import MODEL.Aeropuerto;
import MODEL.Pais;
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
public class AeropuertoController extends HttpServlet {

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
                    response.sendRedirect(request.getContextPath() + "/Aeropuerto");
                else {
                    request.setCharacterEncoding("UTF-8");
                    switch(action) {
                        case "Aeropuerto":
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
        request.setAttribute("aeropuertos", Aeropuerto.get());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aeropuerto/index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("paises", Pais.get());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aeropuerto/create.jsp");
        dispatcher.forward(request, response);
    }
    
    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String iata = request.getParameter("iata").trim();
        String nombre = request.getParameter("nombre").trim();
        int pais_id = Integer.parseInt(request.getParameter("pais_id").trim());
        Aeropuerto aeropuerto = new Aeropuerto(iata, nombre, pais_id);
        aeropuerto.commit();
        request.getSession().setAttribute("aeropuerto", aeropuerto);
        response.sendRedirect(request.getContextPath() + "/Aeropuerto/View");
    }
    
    private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String iata = request.getParameter("iata");
        Aeropuerto aeropuerto = iata != null ? new Aeropuerto(iata) : (Aeropuerto)request.getSession().getAttribute("aeropuerto");
        if(aeropuerto.getIata() != null) {
            request.getSession().removeAttribute("aeropuerto");
            request.setAttribute("aeropuerto", aeropuerto);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aeropuerto/view.jsp");
            dispatcher.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath());
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String iata = request.getParameter("iata");
        Aeropuerto aeropuerto = new Aeropuerto(iata);
        if(aeropuerto.getIata() != null) {
            request.setAttribute("aeropuerto", aeropuerto);
            request.setAttribute("paises", Pais.get());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aeropuerto/edit.jsp");
            dispatcher.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath());
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String u_iata = request.getParameter("u_iata").trim();
        String iata = request.getParameter("iata").trim();
        String nombre = request.getParameter("nombre").trim();
        int pais_id = Integer.parseInt(request.getParameter("pais_id").trim());
        Aeropuerto aeropuerto = new Aeropuerto(u_iata);
        aeropuerto.update(u_iata, iata, nombre, pais_id);
        request.getSession().setAttribute("aeropuerto", aeropuerto);
        response.sendRedirect(request.getContextPath() + "/Aeropuerto/View");
    }
    
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("data");
        request.setAttribute("aeropuertos", Aeropuerto.search(data));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/aeropuerto/search.jsp");
        dispatcher.forward(request, response);
    }

    private void destroy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new Aeropuerto(request.getParameter("iata")).destroy();
        response.sendRedirect(request.getContextPath() + "/Aeropuerto");
    }
    
}
