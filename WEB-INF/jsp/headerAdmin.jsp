<%@ page pageEncoding="utf-8" %>
<%@ page import="java.util.List" %>
<%@ page import='model.*' %>

<% Usuario user = (Usuario) session.getAttribute("user"); %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8" />
    <title>HeaderAdmin</title>
</head>

<header class="header">

    <div class="row page-header" style="justify-content: space-between; align-items: center;">
        <h1  style="margin:40px; font-family:Trebuchet MS, Arial, Helvetica, sans-serif; color:rgb(0, 0, 0)">Usuario con permisos de administrador</h1>
        
        <div class="col"></div>

        <% if (user == null) {%> 
            <form action="login" method="POST">
                <input type="submit" class="btn btn-success" value="Entrar">
            </form>
        <%}else {%>
            <form action="logout" method="POST">
                <input type="submit" class="btn btn-danger" value="Salir">
            </form>
        <%} %> 
            
        <form action="inicio" method="post" style="margin-left: 5px;">
            <input type="hidden" name="count" value="0">
            <input type="submit" class="btn btn-success" value="Volver a la página principal">
        </form>
    </div>

</header>