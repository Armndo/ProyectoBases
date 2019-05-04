<%-- 
    Document   : search
    Created on : 27/04/2019, 08:02:59 PM
    Author     : Armando
--%>

<%@page import="MODEL.Persona"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Pasajero"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% for(Pasajero pasajero : (ArrayList<Pasajero>)request.getAttribute("pasajeros")) { %>
<% Persona per = pasajero.persona(); %>
<tr>
    <td><%= per.getNombre()%></td>
    <td><%= per.getAppaterno()%></td>
    <td><%= per.getSexo() != null ? per.getSexo() : "N/A" %></td>
    <td><%= pasajero.getPasaporte()%></td>
    <td>
        <form action="Pasajero/View" method="POST" style="display: inline;">
            <input type="hidden" name="id" value="<%= pasajero.getId() %>">
            <button type="submit" class="btn btn-sm btn-primary">
                <strong><i class="fa fa-eye"></i> Ver</strong>
            </button>
        </form>
        <form action="Pasajero/Edit" method="POST" style="display: inline;">
            <input type="hidden" name="id" value="<%= pasajero.getId() %>">
            <button type="submit" class="btn btn-sm btn-warning">
                <strong><i class="fa fa-pencil"></i> Editar</strong>
            </button>
        </form>
        <form action="Pasajero/Destroy" method="POST" style="display: inline;">
            <input type="hidden" name="id" value="<%= pasajero.getId() %>">
            <button type="submit" class="btn btn-sm btn-danger">
                <strong><i class="fa fa-times"></i> Eliminar</strong>
            </button>
        </form>
    </td>
</tr>
<% } %>