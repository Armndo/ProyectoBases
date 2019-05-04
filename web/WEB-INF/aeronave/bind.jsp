<%-- 
    Document   : bind
    Created on : 28/04/2019, 07:11:15 AM
    Author     : Armando
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Aerolinea"%>
<%@page import="MODEL.Empleado"%>
<%@page import="MODEL.Usuario"%>
<%@page import="MODEL.Persona"%>
<%@page import="MODEL.Aeronave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../head.jsp"%>
    </head>
    <body>
        <%
            Persona persona = (Persona)request.getSession().getAttribute("persona");
            Aeronave aeronave = (Aeronave)request.getAttribute("aeronave");
        %>
        <%@include file="../header.jsp"%>
        <div class="container-fluid" style="position: absolute; top: 106px; padding-top: 20px; padding-bottom: 20px;">
            <div class="container">
                <div class="card">
                    <form action="../Aeronave/Link" method="POST">
                        <input type="hidden" name="aeronave_id" value="<%= aeronave.getId()%>">
                        <div class="card-header">
                            <div class="row">
                                <div class="col-sm-4">
                                    <h4>Datos de la Aeronave</h4>
                                </div>
                                <div class="col-sm-4 text-center">
                                    <a href="../Aeronave" class="btn btn-primary"><strong><i class="fa fa-bars"></i> Lista de Aeronaves</strong></a>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-4">
                                    <label for="" class="col-form-label">Modelo:</label>
                                    <dd><%= aeronave.getModelo() %></dd>
                                </div>
                                <div class="col-sm-4">
                                    <label for="" class="col-form-label">Capacidad:</label>
                                    <dd><%= aeronave.getCapacidad() %></dd>
                                </div>
                                <div class="col-sm-4">
                                    <label for="" class="col-form-label">Fabricante:</label>
                                    <dd><%= aeronave.fabricante().getNombre() %></dd>
                                </div>
                            </div>
                            <div class="row">
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
                        <div class="card-footer">
                            <div class="row">
                                <div class="col-sm-4 offset-sm-4 text-center">
                                    <button type="submit" class="btn btn-success">
                                        <strong><i class="fa fa-link"></i> Ligar</strong>
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