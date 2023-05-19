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
                <h3>Añadir procesadores</h3>
                <form action="procesadores" method="POST">
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Procesador:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" id="proc" name="proc" class="form-control"
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
                <h3>Modificar procesadores</h3>
                <% List<String> procesadores = (List<String>) request.getAttribute("procesadores"); %>
                <form action="procesadores" method="POST">
                    <p>
                        Procesador a actualizar: 
                        <select id="id" name="id">
                            <% for (String proc: procesadores) { %>
                                <option value="<%= proc%>"><%= proc%></option>
                            <% } %>
                        </select>
                    </p>
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Procesador nuevo:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" id="proc" name="proc" class="form-control"
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
                <h3>Eliminar procesadores</h3>
                <% if(request.getAttribute("notDel")!=null) { %>
                    <div class="alert alert-danger" role="alert">
                        No se ha podido eliminar el/los procesador(es) con id(s) = <%= request.getAttribute("notDel")%>ya que hay ordenadores con dicha características.
                    </div>
                <% } %>

                <% if(procesadores != null){ %>
                    <form action="procesadores" method="POST">
                        <table class="table styled-table-detalle">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Procesador</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (String proc: procesadores) { %>
                                    <tr>
                                        <td><input type="checkbox" name="procesadoresDel" value="<%= proc%>"
                                            class="form-control"></td>
                                        <td><%= proc%></td>
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