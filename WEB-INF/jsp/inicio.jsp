<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='model.*' %>
<%@ page import='java.util.List' %>

<!DOCTYPE html>
<html>
    <head>
        <title>Comparador de Portátiles</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="styles/styles.css">
        <link rel="stylesheet" href="styles/table_styles.css">
    </head>
    <body>
        <%@include file = "/WEB-INF/jsp/header.jsp"%>
        <div class="row">
        <div class="col-9">

        <div class="contenido">
            <% List<Marca> marcas = (List<Marca>) request.getAttribute("marcas"); %>

            <div class="row col-12" style="justify-content: space-evenly;  gap: 10px; margin:0;">
            <% for (Marca marca: marcas) { %>
                <div class="card" style="width: 12rem; margin-top: 20px;">
                    <% String s = "img/" + marca.getIdMarca() + "_" + marca.getNombre() + ".png"; %>
                    
                    <img src="<%= s %>" class="card-img-top" alt="...">
                    <div class="card-body" style="text-align: center;">
                        <h5 class="card-title"><%= marca.getNombre() %></h5>
                        <button type="button" class="btn btn-primary">
                            Portátiles <span class="badge badge-light"><%= marca.getCantidad() %></span>
                        </button>    
                    </div>
                </div>
            <% } %>     
            </div>
            <p>&nbsp;</p>
            
            <hr style="margin-left: 40px; margin-right: 40px; color:rgb(74, 73, 73)"/>
          
            <p>&nbsp;</p>
            <h3 style="margin-left: 30px; margin-bottom: 20px; color:#013568"><b>Filtros</b></h3>
            <form action="buscar" method="POST">
                <div class="form-container">
                    <div class="row filter">
                        <div class="col">
                            <div class="row select-container">
                                <p>Marca: </p> 
                                <select id="marca" class="form-control" name="marca">
                                    <option value="null" selected> - - - </option>
                                    <% for (Marca marca: marcas) { %>
                                        <option value="<%= marca.getNombre()%>"><%= marca.getNombre()%></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                        <% List<String> memoria = (List<String>) request.getAttribute("memoria"); %>
                        <div class="col">
                            <div class="row select-container">    
                                <p>Memoria (GB):</p>
                                <select id="memoria" class="form-control" name="memoria">
                                    <option value="null" selected> - - - </option>
                                    <% for (String mem: memoria) { %>
                                        <option value="<%= mem%>"><%= mem%></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                    <% List<String> capacidades = (List<String>) request.getAttribute("capacidad"); %>
                    <div class="row filter">
                        <div class="col">
                            <div class="row select-container">
                                <p>Capacidad (GB): </p>
                                <select id="capacidad" class="form-control" name="capacidad">
                                    <option value="null" selected> - - - </option>
                                    <% for (String cap: capacidades) { %>
                                    <option value="<%= cap%>"><%= cap%></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>  
                    <% List<String> pantallas = (List<String>) request.getAttribute("pantalla"); %>
                        <div class="col">
                            <div class="row select-container">
                                <p>Pantalla (inches):</p>
                                <select id="pantalla" class="form-control" name="pantalla"> 
                                    <option value="null" selected> - - - </option>
                                    <% for (String pant: pantallas) { %>
                                    <option value="<%= pant%>"><%= pant%></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row filter">
                        <div class="col">
                            <% List<String> tipo_mem = (List<String>) request.getAttribute("tipo_mem"); %>
                            <div class="row select-container">
                                <p>Tipo Memoria:</p>
                                <select id="tipo_mem" class="form-control" name="tipo_mem">
                                    <option value="null" selected> - - - </option>
                                    <% for (String t_mem: tipo_mem) { %>
                                        <option value="<%= t_mem%>"><%= t_mem%></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                        <div class="col">
                            <% List<String> discos = (List<String>) request.getAttribute("disco"); %>
                            <div class="row select-container">
                                <p>Tipo Disco:</p>
                                <select id="disco" class="form-control" name="disco">
                                    <option value="null" selected> - - - </option>
                                    <% for (String dis: discos) { %>
                                        <option value="<%= dis%>"><%= dis%></option>
                                <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row filter">
                        <div class="col">
                            <% List<String> proc = (List<String>) request.getAttribute("procesador"); %>
                            <div class="row select-container">
                                <p>Procesador:</p>
                                <select id="procesador" class="form-control" name="procesador">
                                    <option value="null" selected> - - - </option>
                                    <% for (String pr: proc) { %>
                                        <option value="<%= pr%>"><%= pr%></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                            <div class="col">
                        </div>
                    </div>
                    
                    <p>&nbsp;</p>    
                    <div class="row filter-button-container">
                        <input type="submit" class="btn btn-primary" value="Buscar">
                        <input type="hidden" name="count" value="-1">
                    </div>
                </div>
            </form>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            
            <% List<Ordenador> pcs = (List<Ordenador>) request.getAttribute("pcs"); %>
            <% int count = (int) request.getAttribute("count"); %>
            <% if((pcs != null) && (pcs.size() != 0)) { %>
                <table class="table styled-table" style="margin-top: 10px; margin-left: 45px;">
                    <thead>
                    <tr>
                        <th scope="col">Marca</th>
                        <th scope="col">Modelo</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                        <% int i = 0; %>
                        <% for (Ordenador pc: pcs) { %> 
                        <% if(i < (count*12)){ %>
                    <tr>
                        <td><%= pc.getMarca() %></td>
                        <td><%= pc.getModelo()%></td>
                        <td>
                            <form action="detalle" method="POST">
                                <input type="hidden" name="detallePc" value="<%= pc.getId_producto() %>">
                                <input type="submit" class="btn btn-secondary" value="Detalles">
                            </form>
                        </td>
                        <td>
                            <form action="inicio" method="POST">
                                <input type="hidden" name="pcSeleccionado" value="<%= pc.getId_producto() %>">
                                <input type="hidden" name="count" value="<%= count%>">
                                <input type="submit" class="btn btn-info" value="Seleccionar">
                            </form>
                        </td>
                    </tr>
                        <% i++; %>
                        <% } %> 
                    <% } %>
                    </tbody>
                </table>
                    <% if(count != 0 && (pcs.size() > (count*12))){ %>
                    <form action="inicio" style="text-align: center;" method="POST">
                        <input type="hidden" name="count" value="1">
                        <input type="submit" class="btn btn-primary" value="Mostrar más portátiles">
                    </form>
                <% } %>
            <% } else {  %>
                <div class="alert alert-danger" role="alert">
                    No hay ordenadores con esas características.
                </div>
            <% } %>
            <p>&nbsp;</p>
        </div>
    </div>

    <div class="col-3">
        <div id="menu">
            <p>&nbsp;</p>
            <h4 style="text-align: center; margin-bottom: 20px; color:#013568">Seleccionados</h4>
            <% List<Ordenador> seleccionados = (List<Ordenador>) session.getAttribute("seleccionados"); %>
                
            <% if(seleccionados != null){ %>
                <% int cuentaPcs = 0; %>
                <% for (Ordenador selec: seleccionados) { %>
                    <div class="row col-12" style="justify-content: space-evenly;  gap: 10px; margin: 0px;">
                        <div class="card" style="width: 12rem;">
                        <div class="card-body" style="text-align: center;">
                            <p class="btn btn-primary" style="width: 95%;" ><%= selec.getMarca() %></p>
                            <p><%= selec.getModelo() %></p>
                                <form action="inicio" method="POST">
                                    <input type="hidden" name="eliminarPc" value="<%= selec.getId_producto() %>">
                                    <input type="hidden" name="count" value="<%= count%>">
                                    <input type="submit" class="btn btn-danger" value="Eliminar">
                                </form>
                            </div>
                        </div>
                    </div>

                    <p>&nbsp;</p>
                    <% cuentaPcs++;%>
                <% } %>
             
                <% if(cuentaPcs != 0){ %>
                    <form action="comparador" style="text-align: center;" method="POST">
                        <input type="submit" class="btn btn-secondary" value="Comparar">
                    </form>
                    <form action="inicio" style="text-align: center; margin-top: 5px;" method="POST">
                        <input type="hidden" name="eliminarPc" value="todos">
                        <input type="hidden" name="count" value="0">
                        <input type="submit" class="btn btn-info" value="Vaciar lista">
                    </form>
                <% } %>
            <% } %>

            <p>&nbsp;</p>
            </div>
        </div>
    </div>
    </body>
</html>