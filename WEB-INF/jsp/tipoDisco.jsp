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
                <h3>Añadir tipos de disco</h3>
                <form action="tipoDisco" method="POST">
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Disco:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" id="disco" name="disco" class="form-control"
                                        required minlength="2" maxlength="30" size="20">
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
                <h3>Modificar tipos de disco</h3>
                <% List<String> discos = (List<String>) request.getAttribute("discos"); %>
                <form action="tipoDisco" method="POST">
                    <p>
                        Tipo de disco a actualizar: 
                        <select id="id" name="id">
                            <% for (String disco: discos) { %>
                                <option value="<%= disco%>"><%= disco%></option>
                            <% } %>
                        </select>
                    </p>
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Tipo de disco nuevo:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" id="disco" name="disco" class="form-control"
                                        required minlength="2" maxlength="30" size="20">
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
                <h3>Eliminar tipos de disco</h3>
                <% if(request.getAttribute("notDel")!=null) { %>
                    <div class="alert alert-danger" role="alert">
                        No se ha podido eliminar el/los tipo(s) de disco con id(s) = <%= request.getAttribute("notDel")%>ya que hay ordenadores con dichas características.
                    </div>
                <% } %>

                
                <% if(discos != null){ %>
                    <form action="tipoDisco" method="POST">
                        <table class="table styled-table-detalle">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Tipo de disco</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (String disco: discos) { %>
                                    <tr>
                                        <td><input type="checkbox" name="discoDel" value="<%= disco%>"
                                            class="form-control"></td>
                                        <td><%= disco%></td>
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