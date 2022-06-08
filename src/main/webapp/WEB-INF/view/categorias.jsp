<%--
  Created by IntelliJ IDEA.
  User: felip
  Date: 08/06/2022
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@ page import="es.taw.aliebay.dto.CategoriaDTO" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestionar categorias</title>
</head>
<body>
<%--
<jsp:include page="/WEB-INF/jsp/cabecera.jsp" />
--%>
<h1>Listado de categorias</h1>
<table border="1" width="80%">
    <tr>
        <th>Nombre</th>
        <th></th><!-- Borrar -->
        <th></th><!-- Mostrar Productos -->
    </tr>
    <%
        List<CategoriaDTO> categorias = (List)request.getAttribute("categorias");
        for (CategoriaDTO c: categorias) {
    %>
    <tr>
        <td><%= c.getIdCategoria() %></td>
        <td><a href="/administrador/categorias/borrar/<%=c.getIdCategoria()%>/">Borrar</a></td>
        <td><a href="">Mostrar Productos</a></td>
    </tr>

    <%
        }
    %>
</table>
<a href="/administrador/categorias/nuevo/">Nueva categoria</a>
</body>
</html>
