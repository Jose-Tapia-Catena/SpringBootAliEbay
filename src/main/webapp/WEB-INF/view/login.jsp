<%--
  Created by IntelliJ IDEA.
  User: felip
  Date: 01/06/2022
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bienvenidos al sistema</title>
</head>
<body>
<h1>Login</h1>
<%
    String strError = (String)request.getAttribute("error");
    if (strError != null){
%>
<h3 style="color:red"><%= strError%><br/></h3>
<%
    }
%>
<form id = "form" method="POST" action="LoginServlet">
    <table>
        <tr>
            <td>Usuario:</td>
            <td><input type="text" name="usuario" value="" /></td>
        </tr>
        <tr>
            <td>Contraseña:</td>
            <td><input type="password" name="clave" value=""/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Enviar"/></td>
        </tr>
    </table>
    <br/>
    ¿No tienes una cuenta? <a href="UsuarioNuevoEditarServlet">No le des, no está hecho</a>
</form>
</body>
</html>

