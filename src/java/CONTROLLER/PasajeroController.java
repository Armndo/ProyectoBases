package CONTROLLER;

import MODEL.Pais;
import MODEL.Pasajero;
import MODEL.Persona;
import MODEL.Usuario;
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
public class PasajeroController extends HttpServlet {

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
        String actions[] = request.getRequestURI().split("/");
        String action = actions[actions.length-1];
        request.setCharacterEncoding("UTF-8");
        if(session.getAttribute("persona") == null) {
            if(new Halter(actions, request).validateMethod())
                response.sendRedirect(request.getContextPath() + "/Login");
            else
                switch(action) {
                    case "Register":
                        register(request, response);
                        break;
                    case "Store":
                        store(request, response);
                        break;
                    default:
                        response.sendRedirect(request.getContextPath());
                        break;
                }
        } else {
            Persona persona = (Persona)session.getAttribute("persona");
            if(persona.empleado() != null) {
                if(new Halter(actions, request).validateMethod())
                    response.sendRedirect(request.getContextPath() + "/Pasajero");
                else {
                    switch(action) {
                        case "Pasajero":
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
        request.setAttribute("pasajeros", Pasajero.get());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pasajero/index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Persona persona = new Persona(0, "", "", "", "", "", 0);
        Pasajero pasajero = new Pasajero(0, "");
        Usuario usuario = new Usuario(0, "", "");
        if(request.getSession().getAttribute("Persona") != null || request.getSession().getAttribute("Pasajero") != null || request.getSession().getAttribute("Usuario") != null) {
            persona = (Persona)request.getSession().getAttribute("Persona");
            pasajero = (Pasajero)request.getSession().getAttribute("Pasajero");
            usuario = (Usuario)request.getSession().getAttribute("Usuario");
        }
        request.getSession().removeAttribute("Persona");
        request.getSession().removeAttribute("Pasajero");
        request.getSession().removeAttribute("Usuario");
        request.setAttribute("Persona", persona);
        request.setAttribute("Pasajero", pasajero);
        request.setAttribute("Usuario", usuario);
        request.setAttribute("paises", Pais.get());
        request.getSession().setAttribute("origen", "Create");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pasajero/create.jsp");
        dispatcher.forward(request, response);
    }
    
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Persona persona = new Persona("", "", "", "", "", 0);
        Pasajero pasajero = new Pasajero("");
        Usuario usuario = new Usuario("", "");
        if(request.getSession().getAttribute("Persona") != null || request.getSession().getAttribute("Pasajero") != null || request.getSession().getAttribute("Usuario") != null) {
            persona = (Persona)request.getSession().getAttribute("Persona");
            pasajero = (Pasajero)request.getSession().getAttribute("Pasajero");
            usuario = (Usuario)request.getSession().getAttribute("Usuario");
        }
        request.getSession().removeAttribute("Persona");
        request.getSession().removeAttribute("Pasajero");
        request.getSession().removeAttribute("Usuario");
        request.setAttribute("Persona", persona);
        request.setAttribute("Pasajero", pasajero);
        request.setAttribute("Usuario", usuario);
        request.setAttribute("paises", Pais.get());
        request.getSession().setAttribute("origen", "Register");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pasajero/register.jsp");
        dispatcher.forward(request, response);
    }
    
    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String origen = (String)request.getSession().getAttribute("origen");
        String nombre = request.getParameter("nombre").trim();
        String appaterno = request.getParameter("appaterno").trim();
        String apmaterno = !request.getParameter("apmaterno").equals("") ? request.getParameter("apmaterno").trim() : null;
        String direccion = !request.getParameter("direccion").equals("") ? request.getParameter("direccion").trim() : null;
        String sexo = !request.getParameter("sexo").equals("") ? request.getParameter("sexo").trim() : null;
        int pais_id = Integer.parseInt(request.getParameter("pais_id").trim());
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String pasaporte = request.getParameter("pasaporte").trim();
        ArrayList<Integer> errors = new ArrayList<>();
        boolean err = false;
        for(Usuario usuario : Usuario.get())
            if(usuario.getEmail().equals(email)) {
                err = true;
                errors.add(1062);
            }
        for(Pasajero pasajero : Pasajero.get())
            if(pasajero.getPasaporte().equals(pasaporte)) {
                err = true;
                errors.add(1001);
            }
        if(err) {
            String url = "";
            request.getSession().setAttribute("errors", errors);
            if(origen.equals("Create") || origen.equals("Register")) {
                url = "/Pasajero/" + origen;
                request.getSession().setAttribute("Persona", new Persona(nombre, appaterno, apmaterno, direccion, sexo, pais_id));
                request.getSession().setAttribute("Pasajero", new Pasajero(pasaporte));
                request.getSession().setAttribute("Usuario", new Usuario(email, password));
            }
            response.sendRedirect(request.getContextPath() + url);
            return ;
        }
        Persona persona = new Persona(nombre, appaterno, apmaterno, direccion, sexo, pais_id);
        persona.commit();
        Usuario usuario = new Usuario(persona.getId(), email, password);
        usuario.commit();
        Pasajero pasajero = new Pasajero(persona.getId(), pasaporte);
        pasajero.commit();
        request.getSession().setAttribute("pasajero", pasajero);
        response.sendRedirect(request.getContextPath() + "/Pasajero/View");
    }
    
    private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0;
        Pasajero pasajero = id != 0 ? new Pasajero(id) : (Pasajero)request.getSession().getAttribute("pasajero");
        if(pasajero.getId() != 0) {
            request.getSession().removeAttribute("pasajero");
            request.setAttribute("pasajero", pasajero);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pasajero/view.jsp");
            dispatcher.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath());
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Persona per = new Persona(null, null, null, null, null, 0);
        Pasajero pas = new Pasajero(null);
        Usuario user = new Usuario(null, null);
        if(request.getSession().getAttribute("Persona") != null || request.getSession().getAttribute("Pasajero") != null || request.getSession().getAttribute("Usuario") != null) {
            per = (Persona)request.getSession().getAttribute("Persona");
            pas = (Pasajero)request.getSession().getAttribute("Pasajero");
            user = (Usuario)request.getSession().getAttribute("Usuario");
        }
        int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : request.getSession().getAttribute("id") != null ? (Integer)request.getSession().getAttribute("id") : 0;
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("Persona");
        request.getSession().removeAttribute("Pasajero");
        request.getSession().removeAttribute("Usuario");
        Pasajero pasajero = new Pasajero(id);
        if(pasajero.getId() != 0) {
            request.setAttribute("Persona", per);
            request.setAttribute("Pasajero", pas);
            request.setAttribute("Usuario", user);
            request.setAttribute("pasajero", pasajero);
            request.setAttribute("paises", Pais.get());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pasajero/edit.jsp");
            dispatcher.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath());
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id").trim());
        String nombre = request.getParameter("nombre").trim();
        String appaterno = request.getParameter("appaterno").trim();
        String apmaterno = !request.getParameter("apmaterno").equals("") ? request.getParameter("apmaterno").trim() : null;
        String direccion = !request.getParameter("direccion").equals("") ? request.getParameter("direccion").trim() : null;
        String sexo = !request.getParameter("sexo").equals("") ? request.getParameter("sexo").trim() : null;
        int pais_id = Integer.parseInt(request.getParameter("pais_id").trim());
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String pasaporte = request.getParameter("pasaporte").trim();
        ArrayList<Integer> errors = new ArrayList<>();
        boolean err = false;
        for(Usuario usuario : Usuario.get())
            if(usuario.getEmail().equals(email) && usuario.getId() != id) {
                err = true;
                errors.add(1062);
            }
        for(Pasajero pasajero : Pasajero.get())
            if(pasajero.getPasaporte().equals(pasaporte) && pasajero.getId() != id) {
                err = true;
                errors.add(1001);
            }
        if(err) {
            request.getSession().setAttribute("id", id);
            request.getSession().setAttribute("errors", errors);
            request.getSession().setAttribute("Persona", new Persona(nombre, appaterno, apmaterno == null ? "" : apmaterno, direccion == null ? "" : direccion, sexo == null ? "" : sexo, pais_id));
            request.getSession().setAttribute("Pasajero", new Pasajero(pasaporte));
            request.getSession().setAttribute("Usuario", new Usuario(email, password));
            response.sendRedirect(request.getContextPath() + "/Pasajero/Edit");
            return ;
        }
        Persona persona = new Persona(id);
        persona.update(id, nombre, appaterno, apmaterno, direccion, sexo, pais_id);
        Pasajero pasajero = new Pasajero(id);
        pasajero.update(id, pasaporte);
        Usuario usuario = new Usuario(id);
        usuario.update(id, email, password);
        request.getSession().setAttribute("pasajero", pasajero);
        response.sendRedirect(request.getContextPath() + "/Pasajero/View");
    }
    
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("data");
        request.setAttribute("pasajeros", Pasajero.search(data));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pasajero/search.jsp");
        dispatcher.forward(request, response);
    }

    private void destroy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pasajero pasajero = new Pasajero(Integer.parseInt(request.getParameter("id")));
        pasajero.persona().destroy();
        response.sendRedirect(request.getContextPath() + "/Pasajero");
    }
    
}
