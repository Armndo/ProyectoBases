<%-- 
    Document   : register
    Created on : 30/04/2019, 11:06:47 PM
    Author     : Armando
--%>

<%@page import="MODEL.Usuario"%>
<%@page import="MODEL.Pasajero"%>
<%@page import="MODEL.Persona"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Pais"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Integer> errors = session.getAttribute("errors") != null ? (ArrayList<Integer>)session.getAttribute("errors") : new ArrayList<Integer>();
    session.removeAttribute("errors");
    boolean f1 = errors.contains(1062), f2 = errors.contains(1001);
    Persona per = (Persona)request.getAttribute("Persona");
    Pasajero pas = (Pasajero)request.getAttribute("Pasajero");
    Usuario user = (Usuario)request.getAttribute("Usuario");
    request.removeAttribute("Persona");
    request.removeAttribute("Pasajero");
    request.removeAttribute("Usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../head.jsp"%>
    </head>
    <body>
        <header style="position: fixed; width: 100%; z-index: 1;">
            <div style="background-image: url('../img/header.jpg'); width: 100%; height: 40px;"></div>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-image: url('../img/header.jpg'); background-position-y: -150px; background-size: auto;">
                <div class="" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link" href="Login">
                                Iniciar Sesión
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="container-fluid" style="position: absolute; top: 95px; padding-top: 20px; padding-bottom: 20px;">
            <div class="container">
                <div class="card">
                    <div class="card-header">
                        <div class="row">
                            <div class="col-sm-4">
                                <h4>Datos del Pasajero</h4>
                            </div>
                        </div>
                    </div>
                    <form action="../Pasajero/Store" method="POST">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-4">
                                    <label for="nombre" class="col-form-label required">Nombre:</label>
                                    <input type="text" class="form-control" name="nombre" id="nombre" required="" value="<%= per.getNombre() %>">
                                </div>
                                <div class="col-sm-4">
                                    <label for="appaterno" class="col-form-label required">Apellido Paterno:</label>
                                    <input type="text" class="form-control" name="appaterno" id="appaterno" required="" value="<%= per.getAppaterno() %>">
                                </div>
                                <div class="col-sm-4">
                                    <label for="apmaterno" class="col-form-label">Apellido Materno:</label>
                                    <input type="text" class="form-control" name="apmaterno" id="apmaterno" value="<%= per.getApmaterno() != null ? per.getApmaterno() : "" %>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label for="direccion" class="col-form-label">Dirección:</label>
                                    <textarea name="direccion" id="direccion" class="form-control"><%= per.getDireccion() != null ? per.getDireccion() : "" %></textarea>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label for="sexo" class="col-form-label">Sexo:</label>
                                    <select name="sexo" id="sexo" class="form-control">
                                        <option value="">Seleccionar</option>
                                        <option value="Hombre"<%= per.getSexo() != null ? per.getSexo().equals("Hombre") ? " selected" : "" : "" %>>Hombre</option>
                                        <option value="Mujer"<%= per.getSexo() != null ? per.getSexo().equals("Mujer") ? " selected" : "" : "" %>>Mujer</option>
                                        <option value="Otro"<%= per.getSexo() != null ? per.getSexo().equals("Otro") ? " selected" : "" : "" %>>Otro</option>
                                    </select>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label for="pais_id" class="col-form-label required">Nacionalidad:</label>
                                    <select name="pais_id" id="pais_id" class="form-control" required="">
                                        <option value="">Seleccionar</option>
                                        <% for(Pais pais : (ArrayList<Pais>)request.getAttribute("paises")) { %>
                                        <option value="<%= pais.getId() %>"<%= per.getPais_id() == pais.getId() ? " selected" : "" %>><%= pais.getNombre() %></option>
                                        <% } %>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <label for="pasaporte" class="col-form-label required">Pasaporte:</label>
                                    <input type="text" name="pasaporte" class="form-control<%= f2 ? " is-invalid" : ""%>" id="pasaporte" required="" value="<%= pas.getPasaporte() %>">
                                    <% if(f2) { %>
                                    <span class="invalid-feedback" role="alert">
                                        <strong>Este pasaporte ya está utilizado.</strong>
                                    </span>
                                    <% } %>
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
                                    <label for="email" class="col-form-label required">Email:</label>
                                    <input type="email" name="email" class="form-control<%= f1 ? " is-invalid" : ""%>" id="email" required="" value="<%= user.getEmail() %>">
                                    <% if(f1) { %>
                                    <span class="invalid-feedback" role="alert">
                                        <strong>Este email ya está utilizado.</strong>
                                    </span>
                                    <% } %>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label for="password" class="col-form-label required">Contraseña:</label>
                                    <input type="password" name="password" class="form-control" id="password" required="" value="<%= user.getPassword() %>">
                                </div>
                            </div>
                        </div>
                        <div class="card-footer">
                            <div class="row">
                                <div class="col-sm-4 offset-sm-4 text-center">
                                    <button type="submit" class="btn btn-success">
                                        <strong><i class="fa fa-check"></i> Guardar</strong>
                                    </button>
                                </div>
                                <div class="col-sm-4 text-right text-danger">
                                    Campos Requeridos
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div> 
       </div>
    </body>
</html>