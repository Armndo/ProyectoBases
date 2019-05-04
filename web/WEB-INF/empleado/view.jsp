<%-- 
    Document   : view
    Created on : 27/04/2019, 05:29:07 AM
    Author     : Armando
--%>

<%@page import="MODEL.Empleado"%>
<%@page import="MODEL.Usuario"%>
<%@page import="MODEL.Persona"%>
<%@page import="MODEL.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../head.jsp"%>
    </head>
    <body>
        <%
            Persona persona = (Persona)request.getSession().getAttribute("persona");
            Empleado empleado = (Empleado)request.getAttribute("empleado");
        %>
        <%@include file="../header.jsp"%>
        <div class="container-fluid" style="position: absolute; top: 106px; padding-top: 20px; padding-bottom: 20px;">
            <div class="container">
                <div class="card">
                    <div class="card-header">
                        <div class="row">
                            <div class="col-sm-4">
                                <h4>Datos del Empleado</h4>
                            </div>
                            <div class="col-sm-4 text-center">
                                <a href="../Empleado" class="btn btn-primary"><strong><i class="fa fa-bars"></i> Lista de Empleados</strong></a>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-4">
                                <label for="nombre" class="col-form-label">Nombre:</label>
                                <dd><%= empleado.persona().getNombre() %></dd>
                            </div>
                            <div class="col-sm-4">
                                <label for="appaterno" class="col-form-label">Apellido Paterno:</label>
                                <dd><%= empleado.persona().getAppaterno()%></dd>
                            </div>
                            <div class="col-sm-4">
                                <label for="apmaterno" class="col-form-label">Apellido Materno:</label>
                                <dd><%= empleado.persona().getApmaterno() != null ? empleado.persona().getApmaterno() : "N/A" %></dd>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4 form-group">
                                <label for="direccion" class="col-form-label">Dirección:</label>
                                <dd><%= empleado.persona().getDireccion() != null ? empleado.persona().getDireccion() : "N/A" %></dd>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label for="sexo" class="col-form-label">Sexo:</label>
                                <dd><%= empleado.persona().getSexo() != null ? empleado.persona().getSexo() : "N/A" %></dd>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label for="pais_id" class="col-form-label">Nacionalidad:</label>
                                <dd><%= empleado.persona().pais().getNombre() %></dd>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4">
                                <label for="puesto" class="col-form-label">Puesto:</label>
                                <dd><%= empleado.getPuesto() %></dd>
                            </div>
                            <div class="col-sm-4">
                                <label for="aerolinea_id" class="col-form-label">Aerolínea:</label>
                                <dd><%= empleado.getAerolinea_id() != 0 ? empleado.aerolinea().getNombre() : "N/A" %></dd>
                            </div>
                        </div>
                    </div>
                    <% if(empleado.persona().usuario() != null) { %>
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
                                <dd><%= empleado.persona().usuario().getEmail() %></dd>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label for="password" class="col-form-label">Contraseña:</label>
                                <dd><%= empleado.persona().usuario().getPassword() %></dd>
                            </div>
                        </div>
                    </div>
                    <% } %>
                    <% if(!empleado.getPuesto().equals("Administrador")) { %>
                    <div class="card-footer">
                        <div class="col-sm-4 offset-sm-4 text-center">
                            <form action="../Empleado/Edit" method="POST">
                                <input type="hidden" name="id" value="<%= empleado.getId()%>">
                                <button class="btn btn-warning" type="submit">
                                    <strong><i class="fa fa-pencil"></i> Editar</strong>
                                </button>
                            </form>
                        </div>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
    </body>
</html>