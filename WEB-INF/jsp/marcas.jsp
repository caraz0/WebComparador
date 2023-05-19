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
                <h3>Añadir marcas</h3>
                <form action="marcas" method="POST">
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Marca:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" id="marca" name="marca" class="form-control"
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
                <h3>Modificar marca</h3>
                <% List<String> marcas = (List<String>) request.getAttribute("marcas"); %>
                <form action="marcas" method="POST">
                    <p>
                        Marca a actualizar: 
                        <select id="id" name="id" >
                            <% for (String marca: marcas) { %>
                                <option value="<%= marca%>"><%= marca%></option>
                            <% } %>
                        </select>
                    </p>
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Marca nueva:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" id="marca" name="marca" class="form-control"
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
                <h3>Eliminar marcas</h3>
                <% if(request.getAttribute("notDel")!=null) { %>
                    <div class="alert alert-danger" role="alert">
                        No se ha podido eliminar la(s) marca(s) con id(s) = <%= request.getAttribute("notDel")%>ya que hay ordenadores con dichas características.
                    </div>
                <% } %>

                
                <% if(marcas != null){ %>
                    <form action="marcas" method="POST">
                        <table class="table styled-table-detalle">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Marca</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (String marca: marcas) { %>
                                    <tr>
                                        <td><input type="checkbox" name="marcasDel" value="<%= marca%>"
                                            class="form-control"></td>
                                        <td><%= marca%></td>
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