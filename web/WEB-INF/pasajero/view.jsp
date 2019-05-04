<%-- 
    Document   : view
    Created on : 27/04/2019, 07:40:44 PM
    Author     : Armando
--%>

<%@page import="MODEL.Pasajero"%>
<%@page import="MODEL.Usuario"%>
<%@page import="MODEL.Persona"%>
<%@page import="MODEL.Pasajero"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../head.jsp"%>
    </head>
    <body>
        <%
            Persona persona = (Persona)request.getSession().getAttribute("persona");
            Pasajero pasajero = (Pasajero)request.getAttribute("pasajero");
        %>
        <%@include file="../header.jsp"%>
        <div class="container-fluid" style="position: absolute; top: 106px; padding-top: 20px; padding-bottom: 20px;">
            <div class="container">
                <div class="card">
                    <div class="card-header">
                        <div class="row">
                            <div class="col-sm-4">
                                <h4>Datos del Pasajero</h4>
                            </div>
                            <div class="col-sm-4 text-center">
                                <a href="../Pasajero" class="btn btn-primary"><strong><i class="fa fa-bars"></i> Lista de Pasajeros</strong></a>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-4">
                                <label for="nombre" class="col-form-label">Nombre:</label>
                                <dd><%= pasajero.persona().getNombre() %></dd>
                            </div>
                            <div class="col-sm-4">
                                <label for="appaterno" class="col-form-label">Apellido Paterno:</label>
                                <dd><%= pasajero.persona().getAppaterno()%></dd>
                            </div>
                            <div class="col-sm-4">
                                <label for="apmaterno" class="col-form-label">Apellido Materno:</label>
                                <dd><%= pasajero.persona().getApmaterno() != null ? pasajero.persona().getApmaterno() : "N/A" %></dd>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4 form-group">
                                <label for="direccion" class="col-form-label">Dirección:</label>
                                <dd><%= pasajero.persona().getDireccion() != null ? pasajero.persona().getDireccion() : "N/A" %></dd>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label for="sexo" class="col-form-label">Sexo:</label>
                                <dd><%= pasajero.persona().getSexo() != null ? pasajero.persona().getSexo() : "N/A" %></dd>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label for="pais_id" class="col-form-label">Nacionalidad:</label>
                                <dd><%= pasajero.persona().pais().getNombre() %></dd>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4">
                                <label for="pasaporte" class="col-form-label">Pasaporte:</label>
                                <dd><%= pasajero.getPasaporte()%></dd>
                            </div>
                        </div>
                    </div>
                    <div class="card-header">
                        <div class="row">
                            <div class="col-sm-4">
                                <h4>Datos del Usuario</h4>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-4 form-group">
                                <label for="email" class="col-form-label">Email:</label>
                                <dd><%= pasajero.persona().usuario().getEmail() %></dd>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label for="password" class="col-form-label">Contraseña:</label>
                                <dd><%= pasajero.persona().usuario().getPassword() %></dd>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="col-sm-4 offset-sm-4 text-center">
                            <form action="../Pasajero/Edit" method="POST">
                                <input type="hidden" name="id" value="<%= pasajero.getId()%>">
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