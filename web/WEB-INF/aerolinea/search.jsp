<%-- 
    Document   : search
    Created on : 27/04/2019, 03:42:44 AM
    Author     : Armando
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Aerolinea"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    for(Aerolinea aerolinea : (ArrayList<Aerolinea>)request.getAttribute("aerolineas")) {
%>
<tr>
    <td><%= aerolinea.getId() %></td>
    <td><%= aerolinea.getNombre() %></td>
    <td>
        <form action="Aerolinea/View" method="POST" style="display: inline;">
            <input type="hidden" name="id" value="<%= aerolinea.getId() %>">
            <button type="submit" class="btn btn-sm btn-primary">
                <strong><i class="fa fa-eye"></i> Ver</strong>
            </button>
        </form>
        <form action="Aerolinea/Edit" method="POST" style="display: inline;">
            <input type="hidden" name="id" value="<%= aerolinea.getId() %>">
            <button type="submit" class="btn btn-sm btn-warning">
                <strong><i class="fa fa-pencil"></i> Editar</strong>
            </button>
        </form>
        <form action="Aerolinea/Destroy" method="POST" style="display: inline;">
            <input type="hidden" name="id" value="<%= aerolinea.getId() %>">
            <button type="submit" class="btn btn-sm btn-danger">
                <strong><i class="fa fa-times"></i> Eliminar</strong>
            </button>
        </form>
    </td>
</tr>
<% } %>