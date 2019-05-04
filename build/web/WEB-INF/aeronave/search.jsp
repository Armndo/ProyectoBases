<%-- 
    Document   : search
    Created on : 27/04/2019, 08:28:55 PM
    Author     : Armando
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Aeronave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    for(Aeronave aeronave : (ArrayList<Aeronave>)request.getAttribute("aeronaves")) {
%>
<tr>
    <td><%= aeronave.getModelo() %></td>
    <td><%= aeronave.getCapacidad() %></td>
    <td><%= aeronave.fabricante().getNombre() %></td>
    <td>
        <form action="Aeronave/View" method="POST" style="display: inline;">
            <input type="hidden" name="id" value="<%= aeronave.getId() %>">
            <button type="submit" class="btn btn-sm btn-primary">
                <strong><i class="fa fa-eye"></i> Ver</strong>
            </button>
        </form>
        <form action="Aeronave/Edit" method="POST" style="display: inline;">
            <input type="hidden" name="id" value="<%= aeronave.getId() %>">
            <button type="submit" class="btn btn-sm btn-warning">
                <strong><i class="fa fa-pencil"></i> Editar</strong>
            </button>
        </form>
        <form action="Aeronave/Bind" method="POST" style="display: inline;">
            <input type="hidden" name="id" value="<%= aeronave.getId()%>">
            <button type="submit" class="btn btn-sm btn-success">
                <strong><i class="fa fa-link"></i> Ligar</strong>
            </button>
        </form>
        <form action="Aeronave/Destroy" method="POST" style="display: inline;">
            <input type="hidden" name="id" value="<%= aeronave.getId() %>">
            <button type="submit" class="btn btn-sm btn-danger">
                <strong><i class="fa fa-times"></i> Eliminar</strong>
            </button>
        </form>
    </td>
</tr>
<% } %>