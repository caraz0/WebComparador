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
                <h3>Añadir usuarios 
                    <% if(request.getAttribute("userAdmin")!=null) { %>
                        administradores
                    <% } %>
                </h3>
                <form action="usuarios" method="POST">
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Nombre:</th>
                                <th>Email:</th>
                                <th>Contraseña:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" id="nombre" name="nombre" class="form-control"
                                        required minlength="1" maxlength="20" size="20">
                                </td>
                                <td>
                                    <input type="text" id="email" name="email" class="form-control"
                                        required minlength="1" maxlength="30" size="30">
                                </td>
                                <td>
                                    <input type="text" id="contrasena" name="contrasena" class="form-control"
                                        required minlength="8" maxlength="30" size="30">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <% if(request.getAttribute("userAdmin")!=null) { %>
                        <input type="hidden" name="userAdmin" value="1">
                    <% } %>
                    <input type="hidden" name="add" value=1>
                    <input type="submit" class="btn btn-primary" value="Añadir">
                </form>
                <% if (request.getAttribute("InvalidAdd") != null){ %>
                    <div class="alert alert-danger" role="alert">
                    Debes introducir todos los parámetros.
                    </div>
                <% }%>

                <p>&nbsp;</p>
                <h3>Modificar usuarios
                    <% if(request.getAttribute("userAdmin")!=null) { %>
                        administradores
                    <% } %>
                </h3>
                <form action="usuarios" method="POST">
                    <p>Identificador: <input type="number" id="id" name="id" required min="1"></p>
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Nombre:</th>
                                <th>Email:</th>
                                <th>Contraseña:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" id="nombre" name="nombre" class="form-control"
                                        required minlength="1" maxlength="20" size="20">
                                </td>
                                <td>
                                    <input type="text" id="email" name="email" class="form-control"
                                        required minlength="1" maxlength="30" size="30">
                                </td>
                                <td>
                                    <input type="text" id="contrasena" name="contrasena" class="form-control"
                                        required minlength="8" maxlength="30" size="30">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <% if(request.getAttribute("userAdmin")!=null) { %>
                        <input type="hidden" name="userAdmin" value="1">
                    <% } %>
                    <input type="hidden" name="modify" value=1>
                    <input type="submit" class="btn btn-primary" value="Modificar">
                </form>
                <% if (request.getAttribute("InvalidModify") != null){ %>
                    <div class="alert alert-danger" role="alert">
                    Debes introducir todos los parámetros.
                    </div>
                <% }%>


                <p>&nbsp;</p>
                <h3>Eliminar usuarios
                    <% if(request.getAttribute("userAdmin")!=null) { %>
                        administradores
                    <% } %>
                </h3>

                <% List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios"); %>
                <% if(usuarios != null){ %>

                    <form action="usuarios" method="POST">
                        <table class="table styled-table-detalle">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Id</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Contraseña</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Usuario u: usuarios) { %>
                                    <% if(request.getAttribute("userAdmin")!=null) { %>
                                        <% if(u.getIsAdmin()) { %>
                                            <tr>
                                                <td><input type="checkbox" name="usuariosDel" value="<%= u.getId() %>"></td>
                                                <td><%= u.getId()%></td>
                                                <td><%= u.getName() %> </td>
                                                <td><%= u.getEmail() %></td>
                                                <td><%= u.getContrasena() %></td>
                                            </tr>
                                        <% } %>
                                    <% } else { %>
                                        <% if(!u.getIsAdmin()) { %>
                                            <tr>
                                                <td><input type="checkbox" name="usuariosDel" value="<%= u.getId() %>"></td>
                                                <td><%= u.getId()%></td>
                                                <td><%= u.getName() %> </td>
                                                <td><%= u.getEmail() %></td>
                                                <td><%= u.getContrasena() %></td>
                                            </tr>
                                        <% } %>  
                                    <% } %>
                                <% } %>
                            </tbody>
                        </table>
                        <% if(request.getAttribute("userAdmin")!=null) { %>
                            <input type="hidden" name="userAdmin" value="1">
                        <% } %>
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