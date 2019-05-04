<%-- 
    Document   : view
    Created on : 27/04/2019, 08:29:11 PM
    Author     : Armando
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Empleado"%>
<%@page import="MODEL.Usuario"%>
<%@page import="MODEL.Persona"%>
<%@page import="MODEL.Aeronave"%>
<%@page import="MODEL.Aerolinea"%>
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
            ArrayList<Aerolinea> aerolineas = (ArrayList<Aerolinea>)request.getAttribute("aerolineas");
        %>
        <%@include file="../header.jsp"%>
        <div class="container-fluid" style="position: absolute; top: 106px; padding-top: 20px; padding-bottom: 20px;">
            <div class="container">
                <div class="card">
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
                    </div>
                    <% if(!aerolineas.isEmpty()) { %>
                    <div class="card-header">
                        <div class="row">
                            <div class="col-sm-4">
                                <h4>Aeronaves Ligadas</h4>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-12 text-center">
                                <table class="table table-sm table-stripped table-bordered table-hover" style="margin-bottom: 0;">
                                    <thead class="thead-light">
                                        <tr class="info">
                                            <th style="width: 50%;">Aerolinea</th>
                                            <th>Cantidad de Aviones</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tbody">
                                        <% for(Aerolinea aerolinea : aerolineas) { %>
                                        <tr>
                                            <td><%= aerolinea.getNombre()%></td>
                                            <td><%= aerolinea.count(aeronave.getId()) %></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <% } %>
                    <div class="card-footer">
                        <div class="col-sm-4 offset-sm-4 text-center">
                            <form action="../Aeronave/Edit" method="POST">
                                <input type="hidden" name="id" value="<%= aeronave.getId()%>">
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