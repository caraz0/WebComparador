<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='model.*' %>
<%@ page import='java.util.List' %>

<!DOCTYPE html>
<html>
    <head>
        <title>Administración</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="styles/styles.css">
        <link rel="stylesheet" href="styles/table_styles.css">
    </head>
    <body>
      
        <%@include file = "/WEB-INF/jsp/headerAdmin.jsp"%>

        <% boolean isAdmin = false; %>
        <% if(user != null){ isAdmin = user.getIsAdmin(); } %>
        <% if(isAdmin == true){ %>

            <div class="contenido divAdmin">
                <p>&nbsp;</p>
                <h3>Añadir opiniones</h3>
                <form action="opiniones" method="POST">
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Id Pc:</th>
                                <th>Valor:</th>
                                <th>Mensaje:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="number" id="idPc" name="idPc" class="form-control"
                                        required min="1" max="9999999">
                                </td>
                                <td>
                                    <input type="number" id="valor" name="valor" class="form-control"
                                        required min="1" max="5">
                                </td>
                                <td>
                                    <input type="text" id="mensaje" name="mensaje" class="form-control"
                                        required minlength="2" maxlength="250" size="20">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <input type="hidden" name="add" value=1>
                    <input type="submit" class="btn btn-primary" value="Añadir">
                </form>
                <% if (request.getAttribute("InvalidAdd") != null){ %>
                    <div class="alert alert-danger" role="alert">
                    Debes introducir todos los parámetros.
                    </div>
                <% }%>


                <p>&nbsp;</p>
                <h3>Eliminar opiniones</h3>

                <% List<Opinion> opiniones = (List<Opinion>) request.getAttribute("opiniones"); %>
                <% if(opiniones != null){ %>

                    <form action="opiniones" method="POST">
                        <table class="table styled-table-detalle">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Id</th>
                                    <th scope="col">Id Pc</th>
                                    <th scope="col">Marca</th>
                                    <th scope="col">Modelo</th>
                                    <th scope="col">Valor</th>
                                    <th scope="col">Mensaje</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Opinion op: opiniones) { %>
                                    <tr>
                                        <td><input type="checkbox" name="opinionesDel" value="<%= op.getId() %>"
                                            class="form-control"></td>
                                        <td><%= op.getId()%></td>
                                        <td><%= op.getIdPc() %> </td>
                                        <td><%= op.getMarcaPc() %></td>
                                        <td><%= op.getModeloPc() %></td>
                                        <td><%= op.getValor() %>★</td>
                                        <td><%= op.getMensaje() %></td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                        <input type="submit" class="btn btn-danger" value="Eliminar">
                    </form>
                <% } %>

                <p>&nbsp;</p>
                <form action="admin" method="POST">
                    <input type="submit" class="btn btn-secondary" value="Volver a la página de administrador">
                </form>
                
            <% } else { %>
                <div class="alert alert-danger" role="alert">
                    No tienes permiso para acceder a esta página.
                </div>
            <% } %>
            <p>&nbsp;</p>
        </div>
        
    </body>
</html>