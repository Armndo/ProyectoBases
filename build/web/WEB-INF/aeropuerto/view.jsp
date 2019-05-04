<%-- 
    Document   : view
    Created on : 26/04/2019, 04:38:07 AM
    Author     : Armando
--%>

<%@page import="MODEL.Empleado"%>
<%@page import="MODEL.Usuario"%>
<%@page import="MODEL.Persona"%>
<%@page import="MODEL.Aeropuerto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../head.jsp"%>
    </head>
    <body>
        <%
            Persona persona = (Persona)request.getSession().getAttribute("persona");
            Aeropuerto aeropuerto = (Aeropuerto)request.getAttribute("aeropuerto");
        %>
        <%@include file="../header.jsp"%>
        <div class="container-fluid" style="position: absolute; top: 106px; padding-top: 20px; padding-bottom: 20px;">
            <div class="container">
                <div class="card">
                    <div class="card-header">
                        <div class="row">
                            <div class="col-sm-4">
                                <h4>Datos del Aeropuerto</h4>
                            </div>
                            <div class="col-sm-4 text-center">
                                <a href="../Aeropuerto" class="btn btn-primary"><strong><i class="fa fa-bars"></i> Lista de Aeropuertos</strong></a>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-4">
                                <label for="" class="col-form-label">IATA:</label>
                                <dd><%= aeropuerto.getIata() %></dd>
                            </div>
                            <div class="col-sm-4">
                                <label for="" class="col-form-label">Nombre:</label>
                                <dd><%= aeropuerto.getNombre()%></dd>
                            </div>
                            <div class="col-sm-4">
                                <label for="" class="col-form-label">País:</label>
                                <dd><%= aeropuerto.pais().getNombre() %></dd>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="col-sm-4 offset-sm-4 text-center">
                            <form action="../Aeropuerto/Edit" method="POST">
                                <input type="hidden" name="iata" value="<%= aeropuerto.getIata() %>">
                                <button class="btn btn-warning" type="submit">
                                    <strong><i class="fa fa-pencil"></i> Editar</strong>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>