<%-- 
    Document   : home
    Created on : 17/03/2019, 03:50:00 PM
    Author     : Armando
--%>
<%@page import="MODEL.Persona"%>
<%@page import="MODEL.Usuario"%>
<%@page import="MODEL.Pasajero"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="head.jsp"%>
    </head>
    <body>
        <%
            Persona persona = (Persona)request.getSession().getAttribute("persona");
        %>
        <%@include file="header.jsp"%>
        <div class="container-fluid" style="position: absolute; top: 136px; padding-top: 20px; padding-bottom: 20px;">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12 text-center">
                        ¡Bienvenid<%= persona.getSexo().equals("Mujer") ? "a" : persona.getSexo().equals("Hombre") ? "o" : "@" %>, <%= persona.getNombre() + " " + persona.getAppaterno() %>!
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>