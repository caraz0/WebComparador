<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='model.*' %>
<%@ page import='java.util.List' %>

<!DOCTYPE html>
<html>
    <head>
        <title>Comparador de Portátiles</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="styles/table_styles.css">
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <body style="text-align: -webkit-center;">

        <%@include file = "/WEB-INF/jsp/header.jsp"%>

        <% List<Ordenador> pcs = (List<Ordenador>) session.getAttribute("seleccionados"); %>

        <% if(pcs != null){ %>
        <div class="contenido">
            <p>&nbsp;</p>
            <table class="table styled-table">
                <thead>
                    <tr>
                        <th scope="col"></th>
                        <% for(Ordenador pc: pcs){ %>
                            <th scope="col">
                                <p class="compTitle"><%= pc.getMarca() %></p>
                                <p class="compTitle"><%= pc.getModelo() %></p>
                            </th>
                        <% } %>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td class="tdComp">Memoria</td>
                        <% for(Ordenador pc: pcs){ %>
                            <td><%= pc.getMemoria() %></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td class="tdComp">Capacidad</td>
                        <% for(Ordenador pc: pcs){ %>
                            <td><%= pc.getCapacidad() %></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td class="tdComp">Pantalla</td>
                        <% for(Ordenador pc: pcs){ %>
                            <td><%= pc.getPantalla() %></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td class="tdComp">Tipo de Memoria</td>
                        <% for(Ordenador pc: pcs){ %>
                            <td><%= pc.getTipoMemoria() %></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td class="tdComp">Tipo de Disco</td>
                        <% for(Ordenador pc: pcs){ %>
                            <td><%= pc.getDisco() %></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td class="tdComp">Procesador</td>
                        <% for(Ordenador pc: pcs){ %>
                            <td><%= pc.getProcesador() %></td>
                        <% } %>
                    </tr>
                    <% if( user != null ){ %>
                        <tr>
                            <td class="tdComp">Valoración</td>
                            <% List<String> opiniones = (List<String>) request.getAttribute("valoraciones");%>
                                <% if(opiniones != null){ %>
                                    <% for(String val: opiniones){ %>
                                        <td><%= val %></td>
                                    <% } %>
                            <% } %> 
                        </tr>
                        <tr>
                            <td class="tdComp">Tiendas</td>
                            <% for(Ordenador pc: pcs){ %>
                                <% List<String> tiendas = pc.getTiendas(); %>
                                <% List<Double> precios = pc.getPrecios(); %>
                                <% List<String> urls = pc.getUrls(); %>
                                <td>
                                    <table>
                                        <% for(int i=0;i<tiendas.size();i++){ %>
                                            <tr><td><%= tiendas.get(i) %>: <%= precios.get(i)%> €</td></tr>
                                            <tr><td><a href="<%= urls.get(i)%>">Click aquí.</a></td></tr>
                                        <% } %>
                                    </table>
                                </td>
                            <% } %>
                        </tr>
                    <% } %> 
                </tbody>
            </table>
            <% } %>
            <% if( user != null ){ %>
                <form action="mail" style="margin-top: 10px;" method="post">
                    <input type="hidden" name="count" value="0">
                    <input type="submit" class="btn btn-secondary" value="Mandar comparación por Mail">
                </form>
            <% } %>
            <% if (request.getAttribute("correo") != null){ %>
                <div class="alert alert-primary" style="margin-top: 10px;" role="alert">
                      Correo enviado al Mail asociado a su cuenta.
                  </div>
            <% }%>

            <form action="inicio" style="margin-top: 10px;" method="post">
                <input type="hidden" name="count" value="0">
                <input type="submit" class="btn btn-secondary" value="Volver a la página principal">
            </form>

            <p>&nbsp;</p>
        </div>
    </body>
</html>