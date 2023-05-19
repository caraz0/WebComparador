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
                <h3>Añadir portátiles</h3>
                <form action="portatiles" method="POST">
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Marca:</th>
                                <th>Modelo:</th>
                                <th>Memoria:</th>
                                <th>Capacidad:</th>
                                <th>Pantalla:</th>
                                <th>Tipo Memoria:</th>
                                <th>Tipo Disco:</th>
                                <th>Procesador:</th>
                            </tr>
                        </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <% List<String> marcas = (List<String>) request.getAttribute("marcas"); %>
                                    <select id="marca" class="form-control" name="marca">
                                        <% for (String marca: marcas) { %>
                                            <option value="<%= marca%>"><%= marca%></option>
                                        <% } %>
                                    </select>
                                </td>
                                <td>
                                    <input type="text" id="modelo" name="modelo" class="form-control"
                                        required minlength="2" maxlength="20" size="10">
                                </td>
                                <td>
                                    <input type="number" id="memoria" name="memoria" class="form-control"
                                        required min="1" max="9999999">
                                </td>
                                <td>
                                    <input type="number" id="capacidad" name="capacidad" class="form-control"
                                        required min="1" max="9999999">
                                </td>
                                <td>
                                    <input type="number" step="0.01" id="pantalla" name="pantalla" class="form-control"
                                        required min="1" max="99">
                                </td>
                                <td>
                                <% List<String> tipo_mem = (List<String>) request.getAttribute("tipo_mem"); %>
                                    <select id="tipo_mem" name="tipo_mem" class="form-control">
                                        <% for (String t_mem: tipo_mem) { %>
                                            <option value="<%= t_mem%>"><%= t_mem%></option>
                                        <% } %>
                                    </select>
                                </td>
                                <td>
                                <% List<String> discos = (List<String>) request.getAttribute("disco"); %>
                                    <select id="disco" name="disco" class="form-control">
                                        <% for (String dis: discos) { %>
                                            <option value="<%= dis%>"><%= dis%></option>
                                        <% } %>
                                    </select>
                                </td>
                                <td>
                                <% List<String> proc = (List<String>) request.getAttribute("procesador"); %>
                                    <select id="procesador" name="procesador" class="form-control">
                                        <% for (String pr: proc) { %>
                                            <option value="<%= pr%>"><%= pr%></option>
                                        <% } %>
                                    </select>
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
                <h3>Modificar portátiles</h3>
                <form action="portatiles" method="POST">
                    <p>Identificador: <input type="number" id="id" name="id" required min="1"></p>
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th>Marca:</th>
                                <th>Modelo:</th>
                                <th>Memoria:</th>
                                <th>Capacidad:</th>
                                <th>Pantalla:</th>
                                <th>Tipo Memoria:</th>
                                <th>Tipo Disco:</th>
                                <th>Procesador:</th>
                            </tr>
                        </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <select id="marca" name="marca" class="form-control">
                                        <% for (String marca: marcas) { %>
                                            <option value="<%= marca%>"><%= marca%></option>
                                        <% } %>
                                    </select>
                                </td>
                                <td>
                                    <input type="text" id="modelo" name="modelo" class="form-control"
                                        required minlength="2" maxlength="20" size="10">
                                </td>
                                <td>
                                    <input type="number" id="memoria" name="memoria" class="form-control"
                                        required min="1" max="9999999">
                                </td>
                                <td>
                                    <input type="number" id="capacidad" name="capacidad" class="form-control"
                                        required min="1" max="9999999">
                                </td>
                                <td>
                                    <input type="number" step="0.01" id="pantalla" name="pantalla" class="form-control"
                                        required min="1" max="99999">
                                </td>
                                <td>
                                    <select id="tipo_mem" name="tipo_mem" class="form-control">
                                        <% for (String t_mem: tipo_mem) { %>
                                            <option value="<%= t_mem%>"><%= t_mem%></option>
                                        <% } %>
                                    </select>
                                </td>
                                <td>
                                    <select id="disco" name="disco" class="form-control">
                                        <% for (String dis: discos) { %>
                                            <option value="<%= dis%>"><%= dis%></option>
                                        <% } %>
                                    </select>
                                </td>
                                <td>
                                    <select id="procesador" name="procesador" class="form-control">
                                        <% for (String pr: proc) { %>
                                            <option value="<%= pr%>"><%= pr%></option>
                                        <% } %>
                                    </select>
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
                <h3>Eliminar portátiles</h3>
                <form action="portatiles" method="post" style="margin-bottom: 10px;">
                    <input type="number" name="searchId" id="idPcAdmin" required min="1" max="9999999" >
                    <input type="submit" value=" Buscar ordenador " class="btn btn-info">                    
                </form>
    
                <% List<Ordenador> pcs = (List<Ordenador>) request.getAttribute("pcs"); %>
                <% if(pcs != null){ %>

                    <form action="portatiles" method="POST">
                        <table class="table styled-table-detalle">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Id</th>
                                    <th scope="col">Marca</th>
                                    <th scope="col">Modelo</th>
                                    <th scope="col">Memoria</th>
                                    <th scope="col">Capacidad</th>
                                    <th scope="col">Pantalla</th>
                                    <th scope="col">T. Memoria</th>
                                    <th scope="col">Disco</th>
                                    <th scope="col">Procesador</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Ordenador pc: pcs) { %>
                                    <tr>
                                        <td><input type="checkbox" name="pc" value="<%= pc.getId_producto() %>"></td>
                                        <td><%= pc.getId_producto()%></td>
                                        <td><%= pc.getMarca() %> </td>
                                        <td><%= pc.getModelo() %></td>
                                        <td><%= pc.getMemoria() %></td>
                                        <td><%= pc.getCapacidad() %></td>
                                        <td><%= pc.getPantalla() %></td>
                                        <td><%= pc.getTipoMemoria() %></td>
                                        <td><%= pc.getDisco() %></td>
                                        <td><%= pc.getProcesador() %></td>
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