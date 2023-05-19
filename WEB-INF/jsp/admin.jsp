<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='model.*' %>
<%@ page import='java.util.List' %>

<!DOCTYPE html>
<html>
    <head>
        <title>Administración</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <body>
      
        <%@include file = "/WEB-INF/jsp/headerAdmin.jsp"%>
        <% boolean isAdmin = false; %>
        <% if(user != null){ isAdmin = user.getIsAdmin(); } %>
        <% if(isAdmin == true){ %>

            
            <div class="form-container" id="btnAdmin" style="text-align: center;">
                <div class="row">
                    <div class="col contenido">
                        <p>&nbsp;</p>
                        <h3>Portatiles</h3>
                        <div class="columnaAdmin">
                            <form action="portatiles" method="post" style="margin-bottom: 10px; min-width: 130px;">
                                <input type="submit" class="btn btn-info" value="Modificar portátiles">
                            </form>

                            <form action="marcas" method="post" style="margin-bottom: 10px;">
                                <input type="submit" class="btn btn-info" value="Modificar marcas">
                            </form>

                            <form action="procesadores" method="post" style="margin-bottom: 10px;">
                                <input type="hidden" name="userAdmin" value="1">
                                <input type="submit" class="btn btn-info" value="Modificar procesadores">
                            </form>

                            <form action="tipoDisco" method="post" style="margin-bottom: 10px;">
                                <input type="submit" class="btn btn-info" value="Modificar tipo de disco">
                            </form>
                        
                            <form action="tiposMemoria" method="post" style="margin-bottom: 10px;">
                                <input type="hidden" name="userAdmin" value="1">
                                <input type="submit" class="btn btn-info" value="Modificar tipo de memoria">
                            </form>

                            <form action="opiniones" method="post">
                                <input type="hidden" name="userAdmin" value="1">
                                <input type="submit" class="btn btn-info" value="Modificar opiniones">
                            </form>
                        </div>
                        <p>&nbsp;</p>
                    </div>

                    <div class="col contenido">
                        <p>&nbsp;</p>
                        <h3>Tiendas</h3>
                        <div class="columnaAdmin">
                            <form action="tiendas" method="post" style="margin-bottom: 10px;">
                                <input type="submit" class="btn btn-info" value="Modificar puntos de venta">
                            </form>
                        
                            <form action="asociar" method="post">
                                <input type="submit" class="btn btn-info" value="Asociar puntos de venta">
                            </form>
                        </div>
                        <p>&nbsp;</p>
                    </div>

                    <div class="col contenido"> 
                        <p>&nbsp;</p>   
                        <h3>Usuarios</h3>
                        <div class="columnaAdmin">
                            <form action="usuarios" method="post" style="margin-bottom: 10px;"s>
                                <input type="submit" class="btn btn-info" value="Modificar usuarios">
                            </form>
                        
                            <form action="usuarios" method="post">
                                <input type="hidden" name="userAdmin" value="1">
                                <input type="submit" class="btn btn-info" value="Modificar usuarios administradores">
                            </form>
                        </div>
                        <p>&nbsp;</p>
                    </div>
            
            
        <% } else { %>
            <div class="alert alert-danger" role="alert">
                No tienes permiso para acceder a esta página.
            </div>
        <% } %>

        
    </body>
</html>