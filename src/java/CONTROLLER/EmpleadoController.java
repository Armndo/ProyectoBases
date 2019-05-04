package CONTROLLER;

import MODEL.Aerolinea;
import MODEL.Empleado;
import MODEL.Pais;
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
public class EmpleadoController extends HttpServlet {

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
                    response.sendRedirect(request.getContextPath() + "/Empleado");
                else {
                    request.setCharacterEncoding("UTF-8");
                    switch(action) {
                        case "Empleado":
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
        request.setAttribute("empleados", Empleado.get());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/empleado/index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("paises", Pais.get());
        request.setAttribute("aerolineas", Aerolinea.get());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/empleado/create.jsp");
        dispatcher.forward(request, response);
    }
    
    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre").trim();
        String appaterno = request.getParameter("appaterno").trim();
        String apmaterno = !request.getParameter("apmaterno").equals("") ? request.getParameter("apmaterno").trim() : null;
        String direccion = !request.getParameter("direccion").equals("") ? request.getParameter("direccion").trim() : null;
        String sexo = !request.getParameter("sexo").equals("") ? request.getParameter("sexo").trim() : null;
        int pais_id = Integer.parseInt(request.getParameter("pais_id").trim());
        String email = request.getParameter("email") != null ? request.getParameter("email").trim() : null;
        String password = request.getParameter("password") != null ? request.getParameter("password").trim() : null;
        String puesto = request.getParameter("puesto").trim();
        int aerolinea_id = !request.getParameter("aerolinea_id").equals("") ? Integer.parseInt(request.getParameter("aerolinea_id").trim()) : 0;
        ArrayList<Integer> errors = new ArrayList<>();
        for(Usuario usuario : Usuario.get())
            if(usuario.getEmail().equals(email)) {
                errors.add(1062);
                request.getSession().setAttribute("errors", errors);
                response.sendRedirect(request.getContextPath() + "/Empleado/Create");
                return ;
            }
        Persona persona = new Persona(nombre, appaterno, apmaterno, direccion, sexo, pais_id);
        persona.commit();
        if(email != null && password != null) {
            Usuario usuario = new Usuario(persona.getId(), email, password);
            usuario.commit();
        }
        Empleado empleado = new Empleado(persona.getId(), puesto, aerolinea_id);
        empleado.commit();
        request.getSession().setAttribute("empleado", empleado);
        response.sendRedirect(request.getContextPath() + "/Empleado/View");
    }
    
    private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0;
        Empleado empleado = id != 0 ? new Empleado(id) : (Empleado)request.getSession().getAttribute("empleado");
        if(empleado.getId() != 0) {
            request.getSession().removeAttribute("empleado");
            request.setAttribute("empleado", empleado);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/empleado/view.jsp");
            dispatcher.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath());
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Persona per = new Persona(null, null, null, null, null, 0);
        Empleado emp = new Empleado(null, 0);
        Usuario user = new Usuario(null, null);
        if(request.getSession().getAttribute("Persona") != null || request.getSession().getAttribute("Empleado") != null) {
            per = (Persona)request.getSession().getAttribute("Persona");
            emp = (Empleado)request.getSession().getAttribute("Empleado");
            if(request.getSession().getAttribute("Usuario") != null)
                user = (Usuario)request.getSession().getAttribute("Usuario");
        }
        int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : request.getSession().getAttribute("id") != null ? (Integer)request.getSession().getAttribute("id") : 0;
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("Persona");
        request.getSession().removeAttribute("Empleado");
        request.getSession().removeAttribute("Usuario");
        Empleado empleado = new Empleado(id);
        if(empleado.getId() != 0) {
            request.setAttribute("Persona", per);
            request.setAttribute("Empleado", emp);
            request.setAttribute("Usuario", user);
            request.setAttribute("empleado", empleado);
            request.setAttribute("paises", Pais.get());
            request.setAttribute("aerolineas", Aerolinea.get());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/empleado/edit.jsp");
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
        String email = request.getParameter("email") != null ? !request.getParameter("email").equals("") ? request.getParameter("email").trim() : null : null;
        String password = request.getParameter("password") != null ? !request.getParameter("password").equals("") ? request.getParameter("password").trim() : null : null;
        String puesto = request.getParameter("puesto").trim();
        int aerolinea_id = !request.getParameter("aerolinea_id").equals("") ? Integer.parseInt(request.getParameter("aerolinea_id").trim()) : 0;
        ArrayList<Integer> errors = new ArrayList<>();
        for(Usuario usuario : Usuario.get())
            if(usuario.getEmail().equals(email) && id != usuario.getId()) {
                errors.add(1062);
                request.getSession().setAttribute("id", id);
                request.getSession().setAttribute("Persona", new Persona(nombre, appaterno, apmaterno == null ? "" : apmaterno, direccion == null ? "" : direccion, sexo == null ? "" : sexo, pais_id));
                request.getSession().setAttribute("Empleado", new Empleado(puesto, aerolinea_id));
                request.getSession().setAttribute("Usuario", new Usuario(email, password));
                request.getSession().setAttribute("errors", errors);
                response.sendRedirect(request.getContextPath() + "/Empleado/Edit");
                return ;
            }
        Persona persona = new Persona(id);
        persona.update(id, nombre, appaterno, apmaterno, direccion, sexo, pais_id);
        Empleado empleado = new Empleado(id);
        empleado.update(id, puesto, aerolinea_id);
        if(!(puesto.equals("Administrador") || puesto.equals("Gerente")) && persona.usuario() != null) {
            persona.usuario().destroy();
        } else if((puesto.equals("Administrador") || puesto.equals("Gerente")) && persona.usuario() == null) {
            Usuario usuario = new Usuario(id, email, password);
            usuario.commit();
        } else if((puesto.equals("Administrador") || puesto.equals("Gerente")) && persona.usuario() != null) {
            Usuario usuario = new Usuario(id);
            usuario.update(id, email, password);
        }
        request.getSession().setAttribute("empleado", empleado);
        response.sendRedirect(request.getContextPath() + "/Empleado/View");
    }
    
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("data");
        request.setAttribute("empleados", Empleado.search(data));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/empleado/search.jsp");
        dispatcher.forward(request, response);
    }

    private void destroy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Empleado empleado = new Empleado(Integer.parseInt(request.getParameter("id")));
        if(!empleado.getPuesto().equals("Administrador"))
            empleado.persona().destroy();
        response.sendRedirect(request.getContextPath() + "/Empleado");
    }
    
}