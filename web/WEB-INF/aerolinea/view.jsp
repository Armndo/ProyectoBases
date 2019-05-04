<%-- 
    Document   : view
    Created on : 27/04/2019, 03:24:49 AM
    Author     : Armando
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Empleado"%>
<%@page import="MODEL.Usuario"%>
<%@page import="MODEL.Persona"%>
<%@page import="MODEL.Aerolinea"%>
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
            Aerolinea aerolinea = (Aerolinea)request.getAttribute("aerolinea");
            ArrayList<Aeronave> aeronaves = (ArrayList<Aeronave>)request.getAttribute("aeronaves");
        %>
        <%@include file="../header.jsp"%>
        <div class="container-fluid" style="position: absolute; top: 106px; padding-top: 20px; padding-bottom: 20px;">
            <div class="container">
                <div class="card">
                    <div class="card-header">
                        <div class="row">
                            <div class="col-sm-4">
                                <h4>Datos de la Aerolínea</h4>
                            </div>
                            <div class="col-sm-4 text-center">
                                <a href="../Aerolinea" class="btn btn-primary"><strong><i class="fa fa-bars"></i> Lista de Aerolíneas</strong></a>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-4">
                                <label for="" class="col-form-label">ID:</label>
                                <dd><%= aerolinea.getId()%></dd>
                            </div>
                            <div class="col-sm-4">
                                <label for="" class="col-form-label">Nombre:</label>
                                <dd><%= aerolinea.getNombre()%></dd>
                            </div>
                        </div>
                    </div>
                    <% if(!aeronaves.isEmpty()) { %>
                    <div class="card-header">
                        <div class="row">
                            <div class="col-sm-4">
                                <h4>Aerolíneas Ligadas</h4>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-12 text-center">
                                <table class="table table-sm table-stripped table-bordered table-hover" style="margin-bottom: 0;">
                                    <thead class="thead-light">
                                        <tr class="info">
                                            <th style="width: 50%;">Aeronave</th>
                                            <th>Cantidad de Aviones</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tbody">
                                        <% for(Aeronave aeronave : aeronaves) { %>
                                        <tr>
                                            <td><%= aeronave.getModelo()%></td>
                                            <td><%= aeronave.count(aerolinea.getId()) %></td>
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
                            <form action="../Aerolinea/Edit" method="POST">
                                <input type="hidden" name="id" value="<%= aerolinea.getId()%>">
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