<%-- 
    Document   : search
    Created on : 27/04/2019, 05:01:32 AM
    Author     : Armando
--%>

<%@page import="MODEL.Persona"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% for(Empleado empleado : (ArrayList<Empleado>)request.getAttribute("empleados")) { %>
<% Persona per = empleado.persona(); %>
<tr>
    <td><%= per.getNombre()%></td>
    <td><%= per.getAppaterno()%></td>
    <td><%= per.getSexo() != null ? per.getSexo() : "N/A" %></td>
    <td><%= empleado.getPuesto()%></td>
    <td>
        <form action="Empleado/View" method="POST" style="display: inline;">
            <input type="hidden" name="id" value="<%= empleado.getId() %>">
            <button type="submit" class="btn btn-sm btn-primary">
                <strong><i class="fa fa-eye"></i> Ver</strong>
            </button>
        </form>
        <% if(!empleado.getPuesto().equals("Administrador")) { %>
        <form action="Empleado/Edit" method="POST" style="display: inline;">
            <input type="hidden" name="id" value="<%= empleado.getId() %>">
            <button type="submit" class="btn btn-sm btn-warning">
                <strong><i class="fa fa-pencil"></i> Editar</strong>
            </button>
        </form>
        <form action="Empleado/Destroy" method="POST" style="display: inline;">
            <input type="hidden" name="id" value="<%= empleado.getId() %>">
            <button type="submit" class="btn btn-sm btn-danger">
                <strong><i class="fa fa-times"></i> Eliminar</strong>
            </button>
        </form>
        <% } %>
    </td>
</tr>
<% } %>