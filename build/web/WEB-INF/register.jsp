<%-- 
    Document   : register
    Created on : 17/03/2019, 05:24:37 PM
    Author     : Armando
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Pais"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Integer> errors = session.getAttribute("errors") != null ? (ArrayList<Integer>)session.getAttribute("errors") : new ArrayList<Integer>();
    session.removeAttribute("errors");
    boolean f1 = errors.contains(1001), f2 = errors.contains(1062);
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="head.jsp"%>
    </head>
    <body>
        <header style="position: fixed; width: 100%; z-index: 1;">
            <div style="background-image: url('img/header.jpg'); width: 100%; height: 40px;"></div>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-image: url('img/header.jpg'); background-position-y: -150px; background-size: auto;">
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
                    <form method="POST" action="pasajero/create">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label for="nombre" class="col-form-label required">Nombre:</label>
                                    <input type="text" name="nombre" class="form-control" id="nombre" required="">
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label for="appaterno" class="col-form-label required">Apellido Paterno:</label>
                                    <input type="text" name="appaterno" class="form-control" id="appaterno" required="">
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label for="apmaterno" class="col-form-label">Apellido Materno:</label>
                                    <input type="text" name="apmaterno" class="form-control" id="apmaterno">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label for="direccion" class="col-form-label">Dirección:</label>
                                    <textarea name="direccion" id="direccion" class="form-control"></textarea>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label for="sexo" class="col-form-label">Sexo:</label>
                                    <select name="sexo" id="sexo" class="form-control">
                                        <option value="">Seleccionar</option>
                                        <option value="Hombre">Hombre</option>
                                        <option value="Mujer">Mujer</option>
                                        <option value="Otro">Otro</option>
                                    </select>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label for="pais_id" class="col-form-label required">Nacionalidad:</label>
                                    <select name="pais_id" id="pais_id" class="form-control" required="">
                                        <option value="">Seleccionar</option>
                                        <%
                                            for(Pais pais : Pais.get()) {
                                        %>
                                        <option value="<%= pais.getId() %>"><%= pais.getNombre() %></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <label for="pasaporte" class="col-form-label required">Pasaporte:</label>
                                    <input type="text" name="pasaporte" class="form-control<%= f1 ? " is-invalid" : ""%>" id="pasaporte" required="">
                                    <% if(f1) { %>
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
                                    <input type="email" name="email" class="form-control<%= f2 ? " is-invalid" : ""%>" id="email" required="">
                                    <% if(f2) { %>
                                    <span class="invalid-feedback" role="alert">
                                        <strong>Este email ya está utilizado.</strong>
                                    </span>
                                    <% } %>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label for="password" class="col-form-label required">Contraseña:</label>
                                    <input type="password" name="password" class="form-control" id="password" required="">
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