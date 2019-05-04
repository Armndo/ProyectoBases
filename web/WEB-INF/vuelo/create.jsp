<%-- 
    Document   : create
    Created on : 27/04/2019, 07:36:52 PM
    Author     : Armando
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="MODEL.Aeronave"%>
<%@page import="MODEL.Avion"%>
<%@page import="MODEL.Aeropuerto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Persona persona = (Persona)request.getSession().getAttribute("persona");
    ArrayList<Aeropuerto> aeropuertos = (ArrayList<Aeropuerto>)request.getAttribute("aeropuertos");
    ArrayList<Avion> aviones = (ArrayList<Avion>)request.getAttribute("aviones");
    String fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());
    String hora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../head.jsp"%>
        <script type="text/javascript">
            
            function changeHora(e) {
                if(e.value == '<%= fecha %>') {
                    $('#hora').prop('min', '<%= hora %>');
                    alert('a');
                } else {
                    $('#hora').prop('min', '');
                    alert('b');
                }
            }
            
        </script>
    </head>
    <body>
        <%@include file="../header.jsp"%>
        <div class="container-fluid" style="position: absolute; top: 106px; padding-top: 20px; padding-bottom: 20px;">
            <div class="container">
                <div class="card">
                    <form action="../Vuelo/Store" method="POST">
                        <div class="card-header">
                            <div class="row">
                                <div class="col-sm-4">
                                    <h4>Datos del Vuelo</h4>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-4">
                                    <label for="fecha" class="col-form-label required">Fecha:</label>
                                    <input type="date" min="<%= fecha %>" class="form-control" name="fecha" id="fecha" required="" value="<%= fecha %>" onchange="changeHora(this)">
                                </div>
                                <div class="col-sm-4">
                                    <label for="hora" class="col-form-label required">Hora:</label>
                                    <input type="time" min="<%= hora %>" class="form-control" name="hora" id="hora" required="" value="<%= hora %>">
                                </div>
                                <div class="col-sm-4">
                                    <label for="avion_id" class="col-form-label required">Avion:</label>
                                    <select name="avion_id" id="avion_id" class="form-control" required="">
                                        <option value="">Seleccionar</option>
                                        <% for(Avion avion : aviones) { %>
                                        <option value="<%= avion.getId() %>"><%= avion.getId() + " - " + avion.aeronave().getModelo() %></option>
                                        <% }%>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <label for="origen" class="col-form-label required">Origen:</label>
                                    <select name="origen" id="origen" class="form-control" required="">
                                        <option value="">Seleccionar</option>
                                        <% for(Aeropuerto aeropuerto : aeropuertos) { %>
                                        <option value="<%= aeropuerto.getIata() %>"><%= aeropuerto.getNombre() %></option>
                                        <% }%>
                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <label for="destino" class="col-form-label required">Destino:</label>
                                    <select name="destino" id="destino" class="form-control" required="">
                                        <option value="">Seleccionar</option>
                                        <% for(Aeropuerto aeropuerto : aeropuertos) { %>
                                        <option value="<%= aeropuerto.getIata() %>"><%= aeropuerto.getNombre() %></option>
                                        <% }%>
                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <label for="precio" class="col-form-label required">Precio:</label>
                                    <input type="number" class="form-control" name="precio" id="precio" required="">
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