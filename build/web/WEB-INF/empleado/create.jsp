<%-- 
    Document   : create
    Created on : 27/04/2019, 05:05:41 AM
    Author     : Armando
--%>

<%@page import="MODEL.Aerolinea"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Pais"%>
<%@page import="MODEL.Empleado"%>
<%@page import="MODEL.Usuario"%>
<%@page import="MODEL.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../head.jsp"%>
        <script type="text/javascript">
            
            function show(e) {
                var val = e.value;
                if(val == 'Administrador' || val == 'Gerente') {
                    $('#usuario').show();
                    $('#email').prop('required', true);
                    $('#password').prop('required', true);
                } else {
                    $('#usuario').hide();
                    $('#email').prop('required', false);
                    $('#password').prop('required', false);
                }
            }
            
        </script>
    </head>
    <body>
        <%
            Persona persona = (Persona)request.getSession().getAttribute("persona");
            ArrayList<Integer> errors = session.getAttribute("errors") != null ? (ArrayList<Integer>)session.getAttribute("errors") : new ArrayList<Integer>();
            boolean f1 = errors.contains(1062);
        %>
        <%@include file="../header.jsp"%>
        <div class="container-fluid" style="position: absolute; top: 106px; padding-top: 20px; padding-bottom: 20px;">
            <div class="container">
                <div class="card">
                    <form action="../Empleado/Store" method="POST">
                        <div class="card-header">
                            <div class="row">
                                <div class="col-sm-4">
                                    <h4>Datos del Empleado</h4>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-4">
                                    <label for="nombre" class="col-form-label required">Nombre:</label>
                                    <input type="text" class="form-control" name="nombre" id="nombre" required="">
                                </div>
                                <div class="col-sm-4">
                                    <label for="appaterno" class="col-form-label required">Apellido Paterno:</label>
                                    <input type="text" class="form-control" name="appaterno" id="appaterno" required="">
                                </div>
                                <div class="col-sm-4">
                                    <label for="apmaterno" class="col-form-label">Apellido Materno:</label>
                                    <input type="text" class="form-control" name="apmaterno" id="apmaterno">
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
                                        <% for(Pais pais : (ArrayList<Pais>)request.getAttribute("paises")) { %>
                                        <option value="<%= pais.getId() %>"><%= pais.getNombre() %></option>
                                        <% } %>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <label for="puesto" class="col-form-label required">Puesto:</label>
                                    <select name="puesto" id="puesto" class="form-control" required="" onchange="show(this)">
                                        <option value="">Seleccionar</option>
                                        <option value="Administrador">Administrador</option>
                                        <option value="Gerente">Gerente</option>
                                        <option value="Piloto">Piloto</option>
                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <label for="aerolinea_id" class="col-form-label">Aerolínea:</label>
                                    <select name="aerolinea_id" id="aerolinea_id" class="form-control">
                                        <option value="">Seleccionar</option>
                                        <% for(Aerolinea aerolinea : (ArrayList<Aerolinea>)request.getAttribute("aerolineas")) { %>
                                        <option value="<%= aerolinea.getId() %>"><%= aerolinea.getNombre() %></option>
                                        <% } %>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div id="usuario" style="display: none;">
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
                                        <input type="email" name="email" class="form-control<%= f1 ? " is-invalid" : ""%>" id="email">
                                        <% if(f1) { %>
                                        <span class="invalid-feedback" role="alert">
                                            <strong>Este email ya está utilizado.</strong>
                                        </span>
                                        <% } %>
                                    </div>
                                    <div class="col-sm-4 form-group">
                                        <label for="password" class="col-form-label required">Contraseña:</label>
                                        <input type="password" name="password" class="form-control" id="password">
                                    </div>
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