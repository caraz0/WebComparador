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
                <h3>Asociar punto de venta</h3>
                <% List<Tienda> tiendas = (List<Tienda>) request.getAttribute("tiendas"); %>
                <% List<Ordenador> pcs = (List<Ordenador>) request.getAttribute("pcs"); %>

                <form action="asociar" method="POST">
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th scope="col">Ordenador:</th>
                                <th scope="col">Tienda:</th>
                                <th scope="col">Precio:</th>
                                <th scope="col">URL:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <select id="id_pc" class="form-control" name="id_pc">
                                        <% for (Ordenador pc: pcs) { %>
                                            <% System.out.println(pc.getMarca() + " " + pc.getModelo()); %>
                                            <option value="<%= pc.getId_producto()%>"><%= pc.getModelo()%></option>
                                        <% } %>
                                    </select>
                                </td>
                                <td>
                                        <select id="id_tienda" class="form-control" name="id_tienda">
                                            <% for (Tienda t: tiendas) { %>
                                                <option value="<%= t.getId()%>"><%= t.getNombre()%></option>
                                            <% } %>
                                        </select>
                                </td>
                                <td>
                                    <input type="number" step="0.01" id="precio" name="precio" class="form-control"
                                        required min="0" max="999999">
                                </td>
                                <td>
                                    <input type="text" id="url" name="url" class="form-control"
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
                <% if (request.getAttribute("isAlready") != null){ %>
                    <div class="alert alert-danger" role="alert">
                        Ya hay un ordenador asociado a ese punto de venta.
                    </div>
                <% }%>


                <p>&nbsp;</p>
                <h3>Eliminar puntos de venta</h3>
                <% List<Venta> ventas = (List<Venta>) request.getAttribute("ventas"); %>
                <% if(ventas != null){ %>

                    <form action="asociar" method="POST">
                        <table class="table styled-table-detalle">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Tienda</th>
                                    <th scope="col">Id tienda</th>
                                    <th scope="col">Ordenador</th>
                                    <th scope="col">Id ordenador</th>
                                    <th scope="col">Precio</th>
                                    <th scope="col">URL</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Venta v: ventas) { %>
                                    <tr>
                                        <td><input type="checkbox" name="tiendasDel" value="<%= v.getUrl() %>"></td>
                                        <% for(Tienda t: tiendas){ %>
                                            <% if(t.getId() == v.getIdTienda()){ %>
                                                <td><%= t.getNombre()%></td>
                                            <% } %>
                                        <% } %>
                                        <td><%= v.getIdTienda()%></td>
                                        <% for(Ordenador pc: pcs){ %>
                                            <% if(pc.getId_producto() == v.getIdPc()){ %>
                                                <td><%= pc.getMarca()%> <%= pc.getModelo()%></td>
                                            <% } %>
                                        <% } %>
                                        <td><%= v.getIdPc() %> </td>
                                        <td><%= v.getPrecio() %></td>
                                        <td><a href="<%= v.getUrl() %>">Click aquí.</a></td>
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
                <p>&nbsp;</p>
            </div>
            
        <% } else { %>
            <div class="alert alert-danger" role="alert">
                No tienes permiso para acceder a esta página.
            </div>
        <% } %>

        
    </body>
</html>