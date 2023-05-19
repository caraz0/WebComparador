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
                <h3>Añadir tiendas</h3>
                <form action="tiendas" method="POST">
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Tienda:</th>
                                <th>URL:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" id="tienda" name="tienda" class="form-control"
                                        required minlength="2" maxlength="30" size="20">
                                </td>
                                <td>
                                    <input type="text" id="url" name="url" class="form-control"
                                        required minlength="2" maxlength="250" size="40">
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
                <h3>Modificar tiendas</h3>
                <form action="tiendas" method="POST">
                    <p>Identificador: <input type="number" id="id" name="id" required min="1"></p>
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Tienda:</th>
                                <th>URL:</th>
                            </tr>
                        </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <input type="text" id="tienda" name="tienda" class="form-control"
                                            required minlength="2" maxlength="30" size="20">
                                    </td>
                                    <td>
                                        <input type="text" id="url" name="url" class="form-control"
                                            required minlength="2" maxlength="40" size="40">
                                    </td>
                                </tr>
                        </tbody>
                    </table>
                    <input type="hidden" name="modify" value=1>
                    <input type="submit" class="btn btn-primary" value="Modificar">
                </form>
                <% if (request.getAttribute("InvalidModify") != null){ %>
                    <div class="alert alert-danger" role="alert">
                    Debes introducir todos los parámetros.
                    </div>
                <% }%>


                <p>&nbsp;</p>
                <h3>Eliminar tiendas</h3>
                <% if(request.getAttribute("notDel")!=null) { %>
                    <div class="alert alert-danger" role="alert">
                        No se ha podido eliminar la(s) tienda(s) con id(s) = <%= request.getAttribute("notDel")%>ya que hay ordenadores con dicho punto de venta asignado.
                    </div>
                <% } %>

                <% List<Tienda> tiendas = (List<Tienda>) request.getAttribute("tiendas"); %>
                <% if(tiendas != null){ %>

                    <form action="tiendas" method="POST">
                        <table class="table styled-table-detalle">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Id</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Url</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Tienda t: tiendas) { %>
                                    <tr>
                                        <td><input type="checkbox" name="tiendasDel" value="<%= t.getId() %>"></td>
                                        <td><%= t.getId()%></td>
                                        <td><%= t.getNombre() %> </td>
                                        <td><%= t.getUrl() %></td>
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