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
        
        <div class="contenido">
            <% Ordenador pc = (Ordenador) request.getAttribute("detallePc"); %>
            <% if(pc != null){ %>
                <p>&nbsp;</p>
                
                <div>
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th scope="col">Marca</th>
                                <th scope="col">Modelo</th>
                                <th scope="col">Memoria (GB)</th>
                                <th scope="col">Capacidad(GB)</th>
                                <th scope="col">Pantalla</th>
                                <th scope="col">Tipo de Memoria</th>
                                <th scope="col">Tipo de Disco</th>
                                <th scope="col">Procesador</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><%= pc.getMarca() %></td>
                                <td><%= pc.getModelo()%></td>
                                <td><%= pc.getMemoria()%></td>
                                <td><%= pc.getCapacidad()%></td>
                                <td><%= pc.getPantalla()%></td>
                                <td><%= pc.getTipoMemoria()%></td>
                                <td><%= pc.getDisco()%></td>
                                <td><%= pc.getProcesador()%></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <p>&nbsp;</p>
                
                <% if(user != null){ %>
                    <p>&nbsp;</p>
                    <h4 class="h4-det">Puntos de venta</h4>
                    <table class="table table-striped styled-table-detalle">
                        <thead>
                            <tr>
                                <th scope="col">Tienda</th> 
                                <th scope="col">Precio</th>
                                <th scope="col">URL</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% List<String> tiendas = pc.getTiendas(); %>
                            <% List<Double> precios = pc.getPrecios(); %>
                            <% List<String> urls = pc.getUrls(); %>
                            
                            <% if(tiendas !=null){ %>
                                <tr>
                                    <% for(int i=0; i<tiendas.size(); i++){ %>
                                        <td><%= tiendas.get(i) %></td>
                                        <td><%= precios.get(i)%> €</td>
                                        <td><a href="<%= urls.get(i)%>">Click aquí.</a></td></tr>
                                    <% } %>
                                </tr>
                            <% } %> 

                        </tbody>
                    </table>
                

                    <p>&nbsp;</p>
                    <h4 class="h4-det">Opiniones</h4>
                    <table class="table styled-table-detalle">
                        <thead>
                            <tr>
                                <th scope="col">Valor</th>
                                <th scope="col">Mensaje</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% List<String> opiniones = (List<String>) request.getAttribute("opiniones"); %>
                            <% if(opiniones !=null){ %>
                                <% int countOp = 1;%>
                                    <% for(String op: opiniones){ %>
                                        <% if(countOp % 2 != 0) { %>
                                            <tr>
                                                <td><%= op %> ★</td>
                                        <% } else { %>
                                                <td><%= op%></td>
                                            </tr>
                                        <% } %>
                                        <% countOp++; %>
                                    <% } %>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } %>

                <p>&nbsp;</p>        
                <div id="opinion">   
                    <form action="detalle" method="post">
                        <div class="row" style="justify-content: center;  gap: 10px;">
                        <input id="star1" type="radio" name="valoracion" value="1" checked>
                        <label for="star1" >★</label>
                    
                        <input id="star2" type="radio" name="valoracion" value="2">
                        <label for="star2">★</label>

                        <input id="star3" type="radio" name="valoracion" value="3">
                        <label for="star3">★</label>

                        <input id="star4" type="radio" name="valoracion" value="4">
                        <label for="star4">★</label>

                        <input id="star5" type="radio" name="valoracion" value="5">
                        <label for="star5">★</label>
                        </div>

                        <div class="row" style="justify-content: space-evenly;  gap: 10px;">
                        <textarea name="mensaje" rows="5" cols="50" placeholder="Escribe tu opinión..."></textarea>
                        </div>

                        <div class="row" style="justify-content: space-evenly;  gap: 10px;">
                        <input type="submit" class="btn btn-primary" value="Añadir opinión" style="margin-top: 10px;">
                        </div>

                        <input type="hidden" name="count" value="0">
                        <input type="hidden" name="opinion" value=<%= pc.getId_producto()%>>
                    </form>  
                </div>        
                
            <% } %>

            <p>&nbsp;</p>
            <div class="row filter-button-container" style="justify-content: space-evenly;">
                <form action="inicio" method="post">
                    <input type="hidden" name="count" value="0">
                    <input type="submit" class="btn btn-secondary" value="Volver a la página principal">
                </form>
                <% if(pc!=null){ %>
                <form action="inicio" method="post">
                    <input type="hidden" name="pcSeleccionado" value="<%= pc.getId_producto() %>">
                    <input type="hidden" name="count" value="0">
                    <input type="submit" class="btn btn-info" value="Añadir portátil al comparador">
                </form>
                <% } %>
            </div>
            <p>&nbsp;</p>
        
        </div>
    </body>
</html>