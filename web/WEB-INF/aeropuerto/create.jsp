<%-- 
    Document   : create
    Created on : 26/04/2019, 04:56:22 AM
    Author     : Armando
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Pais"%>
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
        %>
        <%@include file="../header.jsp"%>
        <div class="container-fluid" style="position: absolute; top: 106px; padding-top: 20px; padding-bottom: 20px;">
            <div class="container">
                <div class="card">
                    <form action="../Aeropuerto/Store" method="POST">
                        <div class="card-header">
                            <div class="row">
                                <div class="col-sm-4">
                                    <h4>Datos del Aeropuerto</h4>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-4">
                                    <label for="iata" class="col-form-label required">IATA:</label>
                                    <input type="text" class="form-control" name="iata" id="iata" required="">
                                </div>
                                <div class="col-sm-4">
                                    <label for="nombre" class="col-form-label required">Nombre:</label>
                                    <input type="text" class="form-control" name="nombre" id="nombre" required="">
                                </div>
                                <div class="col-sm-4">
                                    <label for="pais_id" class="col-form-label required">País:</label>
                                    <select name="pais_id" id="pais_id" class="form-control" required="">
                                        <option value="">Seleccionar</option>
                                        <% for(Pais pais : (ArrayList<Pais>)request.getAttribute("paises")) { %>
                                        <option value="<%= pais.getId() %>"><%= pais.getNombre() %></option>
                                        <% } %>
                                    </select>
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