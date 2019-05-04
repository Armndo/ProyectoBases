<%-- 
    Document   : edit
    Created on : 27/04/2019, 08:28:59 PM
    Author     : Armando
--%>

<%@page import="MODEL.Fabricante"%>
<%@page import="java.util.ArrayList"%>
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
                    <form action="../Aeronave/Update" method="POST">
                        <input type="hidden" name="id" value="<%= aeronave.getId()%>">
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
                                    <label for="modelo" class="col-form-label required">Modelo:</label>
                                    <input type="text" class="form-control" name="modelo" id="modelo" required="" value="<%= aeronave.getModelo() %>">
                                </div>
                                <div class="col-sm-4">
                                    <label for="capacidad" class="col-form-label required">Capacidad:</label>
                                    <input type="number" min="0" max="1000" class="form-control" name="capacidad" id="capacidad" required="" value="<%= aeronave.getCapacidad() %>">
                                </div>
                                <div class="col-sm-4">
                                    <label for="fabricante" class="col-form-label required">Fabricante:</label>
                                    <select name="fabricante_id" id="fabricante_id" required="" class="form-control">
                                        <option value="">Seleccionar</option>
                                        <% for(Fabricante fabricante : (ArrayList<Fabricante>)request.getAttribute("fabricantes")) { %>
                                        <option value="<%= fabricante.getId() %>"<%= aeronave.getFabricante_id() == fabricante.getId() ? " selected" : "" %>><%= fabricante.getNombre() %></option>
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