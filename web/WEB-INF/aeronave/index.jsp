<%-- 
    Document   : index
    Created on : 27/04/2019, 08:28:49 PM
    Author     : Armando
--%>

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
        <script type="text/javascript">

            function buscar() {
                var val = $('#data').val();
                $.ajax({
                    url: "/Bases/Aeronave/Search",
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
                                <h4>Aeronaves</h4>
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
                                            <th>Modelo</th>
                                            <th>Capacidad</th>
                                            <th>Fabricante</th>
                                            <th style="width: 30%;">Acción</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tbody">
                                        <% for(Aeronave aeronave : (ArrayList<Aeronave>)request.getAttribute("aeronaves")) { %>
                                        <tr>
                                            <td><%= aeronave.getModelo() %></td>
                                            <td><%= aeronave.getCapacidad() %></td>
                                            <td><%= aeronave.fabricante().getNombre() %></td>
                                            <td>
                                                <form action="Aeronave/View" method="POST" style="display: inline;">
                                                    <input type="hidden" name="id" value="<%= aeronave.getId()%>">
                                                    <button type="submit" class="btn btn-sm btn-primary">
                                                        <strong><i class="fa fa-eye"></i> Ver</strong>
                                                    </button>
                                                </form>
                                                <form action="Aeronave/Edit" method="POST" style="display: inline;">
                                                    <input type="hidden" name="id" value="<%= aeronave.getId() %>">
                                                    <button type="submit" class="btn btn-sm btn-warning">
                                                        <strong><i class="fa fa-pencil"></i> Editar</strong>
                                                    </button>
                                                </form>
                                                <form action="Aeronave/Bind" method="POST" style="display: inline;">
                                                    <input type="hidden" name="id" value="<%= aeronave.getId()%>">
                                                    <button type="submit" class="btn btn-sm btn-success">
                                                        <strong><i class="fa fa-link"></i> Ligar</strong>
                                                    </button>
                                                </form>
                                                <form action="Aeronave/Destroy" method="POST" style="display: inline;">
                                                    <input type="hidden" name="id" value="<%= aeronave.getId() %>">
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