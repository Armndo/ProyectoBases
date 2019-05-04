<%-- 
    Document   : index
    Created on : 27/04/2019, 07:29:39 PM
    Author     : Armando
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Pasajero"%>
<%@page import="MODEL.Usuario"%>
<%@page import="MODEL.Persona"%>
<%@page import="MODEL.Pasajero"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../head.jsp"%>
        <script type="text/javascript">

            function buscar() {
                var val = $('#data').val();
                $.ajax({
                    url: "/Prueba/Pasajero/Search",
                    type: "GET",
                    dataType: "html",
                    data: {
                        data: val,
                    },
                }).done(function (res) {
                    $("#tbody").html(res);
                });
            } 

        </script>
    </head>
    <body>
        <%
            Persona persona = (Persona)request.getSession().getAttribute("persona");
        %>
        <%@include file="../header.jsp"%>
        <div class="container-fluid" style="position: absolute; top: 106px; padding-top: 20px; padding-bottom: 20px;">
            <div class="container">
                <div class="card">
                    <div class="card-header">
                        <div class="row">
                            <div class="col-sm-4">
                                <h4>Pasajeros</h4>
                            </div>
                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Buscar..." id="data">
                                    <div class="input-group-append">
                                        <button class="btn btn-secondary" type="button" style="z-index: unset;" onclick="buscar()">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-body" style="max-height: 540px; overflow-y: auto;">
                        <div class="row">
                            <div class="col-sm-12 text-center">
                                <table class="table table-sm table-stripped table-bordered table-hover" style="margin-bottom: 0;">
                                    <thead class="thead-light">
                                        <tr class="info">
                                            <th>Nombre</th>
                                            <th>Apellido Paterno</th>
                                            <th>Sexo</th>
                                            <th>Pasaporte</th>
                                            <th style="width: 30%;">Acción</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tbody">
                                        <% for(Pasajero pasajero : (ArrayList<Pasajero>)request.getAttribute("pasajeros")) { %>
                                        <% Persona per = pasajero.persona(); %>
                                        <tr>
                                            <td><%= per.getNombre()%></td>
                                            <td><%= per.getAppaterno()%></td>
                                            <td><%= per.getSexo() != null ? per.getSexo() : "N/A" %></td>
                                            <td><%= pasajero.getPasaporte()%></td>
                                            <td>
                                                <form action="Pasajero/View" method="POST" style="display: inline;">
                                                    <input type="hidden" name="id" value="<%= pasajero.getId()%>">
                                                    <button type="submit" class="btn btn-sm btn-primary">
                                                        <strong><i class="fa fa-eye"></i> Ver</strong>
                                                    </button>
                                                </form>
                                                <form action="Pasajero/Edit" method="POST" style="display: inline;">
                                                    <input type="hidden" name="id" value="<%= pasajero.getId() %>">
                                                    <button type="submit" class="btn btn-sm btn-warning">
                                                        <strong><i class="fa fa-pencil"></i> Editar</strong>
                                                    </button>
                                                </form>
                                                <form action="Pasajero/Destroy" method="POST" style="display: inline;">
                                                    <input type="hidden" name="id" value="<%= pasajero.getId() %>">
                                                    <button type="submit" class="btn btn-sm btn-danger">
                                                        <strong><i class="fa fa-times"></i> Eliminar</strong>
                                                    </button>
                                                </form>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>