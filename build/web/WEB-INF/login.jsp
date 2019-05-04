<%-- 
    Document   : login
    Created on : 18/03/2019, 03:45:11 PM
    Author     : Armando
--%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Integer> errors = session.getAttribute("errors") != null ? (ArrayList<Integer>)session.getAttribute("errors") : new ArrayList<Integer>();
    session.removeAttribute("errors");
    boolean f1 = errors.contains(1);
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="head.jsp"%>
    </head>
    <body>
        <header style="position: fixed; width: 100%; z-index: 1;">
            <div style="background-image: url('img/header.jpg'); width: 100%; height: 40px;"></div>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-image: url('img/header.jpg'); background-position-y: -150px; background-size: auto; height: 56px;">
            </nav>
        </header>
        <div class="container-fluid" style="position: absolute; top: 136px; padding-top: 20px; padding-bottom: 20px;">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 offset-md-3 col-sm-12">
                        <div class="card">
                            <div class="card-header">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <h4>Iniciar Sesión</h4>
                                    </div>
                                </div>
                            </div>
                            <form method="POST" action="Session">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-sm-10 offset-sm-1 form-group">
                                            <label for="email" class="col-form-label">Correo electrónico:</label>
                                            <input id="email" type="email" class="form-control<%= f1 ? " is-invalid" : ""%>" name="email" value="" required autofocus>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-10 offset-sm-1 form-group">
                                            <label for="password" class="col-form-label text-md-right">Contraseña:</label>
                                            <input id="password" type="password" class="form-control<%= f1? " is-invalid" : ""%>" name="password" required>
                                            <% if(f1) { %>
                                            <span class="invalid-feedback" role="alert">
                                                <strong>Las credenciales no concuerdan con nuestra base de datos.</strong>
                                            </span>
                                            <% } %>
                                        </div>
                                    </div>
                                    <div class="row text-center">
                                    </div>
                                </div>
                                <div class="card-footer">
                                    <div class="row">
                                        <div class="col-sm-4 offset-sm-4 text-center">
                                            <button type="submit" class="btn btn-primary">
                                                <strong>Iniciar sesión</strong>
                                            </button>
                                        </div>
                                        <div class="col-sm-4 text-right">
                                            ¿No tienes cuenta? Regístrate <a href="Pasajero/Register">aquí</a>.
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div> 
       </div>
    </body>
</html>
