<%-- 
    Document   : view
    Created on : 4/05/2019, 01:59:47 PM
    Author     : Armando
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Empleado"%>
<%@page import="MODEL.Usuario"%>
<%@page import="MODEL.Persona"%>
<%@page import="MODEL.Vuelo"%>
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
            Vuelo vuelo = (Vuelo)request.getAttribute("vuelo");
        %>
        <%@include file="../header.jsp"%>
        <div class="container-fluid" style="position: absolute; top: 106px; padding-top: 20px; padding-bottom: 20px;">
            <div class="container">
                <div class="card">
                    <div class="card-header">
                        <div class="row">
                            <div class="col-sm-4">
                                <h4>Datos del Vuelo</h4>
                            </div>
                            <div class="col-sm-4 text-center">
                                <a href="../Vuelo" class="btn btn-primary"><strong><i class="fa fa-bars"></i> Lista de Aerolíneas</strong></a>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-4">
                                <label for="" class="col-form-label">ID:</label>
                                <dd><%= vuelo.getId() %></dd>
                            </div>
                            <div class="col-sm-4">
                                <label for="" class="col-form-label">Fecha:</label>
                                <dd><%= vuelo.getFecha() %></dd>
                            </div>
                            <div class="col-sm-4">
                                <label for="" class="col-form-label">Hora:</label>
                                <dd><%= vuelo.getHora() %></dd>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4">
                                <label for="" class="col-form-label">Aerolínea:</label>
                                <dd><%= vuelo.avion().aerolinea().getNombre() %></dd>
                            </div>
                            <div class="col-sm-4">
                                <label for="" class="col-form-label">Aeronave:</label>
                                <dd><%= vuelo.avion().aeronave().getModelo() %></dd>
                            </div>
                            <div class="col-sm-4">
                                <label for="" class="col-form-label">Precio:</label>
                                <dd>$<%= vuelo.getPrecio() %></dd>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4">
                                <label for="" class="col-form-label">Origen:</label>
                                <dd><%= vuelo.origen().getNombre() %></dd>
                            </div>
                            <div class="col-sm-4">
                                <label for="" class="col-form-label">Destino:</label>
                                <dd><%= vuelo.destino().getNombre() %></dd>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="col-sm-4 offset-sm-4 text-center">
                            <form action="../Vuelo/Edit" method="POST">
                                <input type="hidden" name="id" value="<%= vuelo.getId()%>">
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