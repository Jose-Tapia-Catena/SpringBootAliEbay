<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<%--@elvariable id="usuario" type="es.taw.aliebay.dto.UsuarioDTO"--%>
<form:form method="post" action="/autentica/" modelAttribute="usuario">
    Usuario: <form:input path="userName" type="text"/><br>
    Contraseña: <form:password path="password" /> <br>
    <form:button>Enviar</form:button>
</form:form>
    ¿No tienes una cuenta? <a href="">Regístrate</a>
</form>
</body>
</html>

