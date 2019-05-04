<%-- 
    Document   : search
    Created on : 26/04/2019, 06:35:28 PM
    Author     : Armando
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Aeropuerto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    for(Aeropuerto aeropuerto : (ArrayList<Aeropuerto>)request.getAttribute("aeropuertos")) {
%>
<tr>
    <td><%= aeropuerto.getIata() %></td>
    <td><%= aeropuerto.getNombre() %></td>
    <td>
        <form action="Aeropuerto/View" method="POST" style="display: inline;">
            <input type="hidden" name="iata" value="<%= aeropuerto.getIata() %>">
            <button type="submit" class="btn btn-sm btn-primary">
                <strong><i class="fa fa-eye"></i> Ver</strong>
            </button>
        </form>
        <form action="Aeropuerto/Edit" method="POST" style="display: inline;">
            <input type="hidden" name="iata" value="<%= aeropuerto.getIata() %>">
            <button type="submit" class="btn btn-sm btn-warning">
                <strong><i class="fa fa-pencil"></i> Editar</strong>
            </button>
        </form>
        <form action="Aeropuerto/Destroy" method="POST" style="display: inline;">
            <input type="hidden" name="iata" value="<%= aeropuerto.getIata() %>">
            <button type="submit" class="btn btn-sm btn-danger">
                <strong><i class="fa fa-times"></i> Eliminar</strong>
            </button>
        </form>
    </td>
</tr>
<% } %>