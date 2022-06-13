<%--
  Created by IntelliJ IDEA.
  User: Cate
  Date: 12/06/2022
  Time: 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.aliebay.dto.ListacompradorDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Marketing</title>
</head>
<body>
<h1>Lista de compradores</h1>
<table border="1" width="80%">
    <tr>
        <th>Nombre</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    <a href="/marketing/listaCompradorCrear/">Crear nueva lista comprador....</a>
    <%
        List<ListacompradorDTO> listaCompradores = (List)request.getAttribute("listaCompradores");
        for (ListacompradorDTO lc: listaCompradores) {
    %>
    <tr>
        <td><%= lc.getNombre()%></td>

        <td><a href="/marketing/listaCompradorMensajes/<%= lc.getIdLista()%>/">Ver Mensajes</td>
        <td><a href="/marketing/listaCompradorEditar/<%= lc.getIdLista()%>/">Editar</a></td>
        <td><a href="/marketing/listaCompradorBorrar/<%= lc.getIdLista()%>/">Borrar</a></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
