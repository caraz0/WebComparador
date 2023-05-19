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
                <h3>Añadir tipos de memoria</h3>
                <form action="tiposMemoria" method="POST">
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Memoria:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" id="memoria" name="memoria" class="form-control"
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
                <h3>Modificar tipos de memoria</h3>
                <% List<String> tiposMem = (List<String>) request.getAttribute("tiposMem"); %>
                <form action="tiposMemoria" method="POST">
                    <p>
                        Memoria a actualizar: 
                        <select id="id" name="id" >
                            <% for (String t: tiposMem) { %>
                                <option value="<%= t%>"><%= t%></option>
                            <% } %>
                        </select>
                    </p>
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Memoria nueva:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" id="memoria" name="memoria" class="form-control"
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
                <h3>Eliminar tipos de Memoria</h3>
                <% if(request.getAttribute("notDel")!=null) { %>
                    <div class="alert alert-danger" role="alert">
                        No se ha podido eliminar el/los tipo(s) de memoria con id(s) = <%= request.getAttribute("notDel")%>ya que hay ordenadores con dichas características.
                    </div>
                <% } %>

                
                <% if(tiposMem != null){ %>
                    <form action="tiposMemoria" method="POST">
                        <table class="table styled-table-detalle">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Memoria</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (String t: tiposMem) { %>
                                    <tr>
                                        <td><input type="checkbox" name="tiposMemDel" value="<%= t%>"
                                            class="form-control"></td>
                                        <td><%= t%></td>
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