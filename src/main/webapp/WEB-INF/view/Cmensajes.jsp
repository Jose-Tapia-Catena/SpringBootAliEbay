<%--
  Created by IntelliJ IDEA.
  User: Cate
  Date: 13/06/2022
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%--
    Document   : mensajesComprador
    Created on : 15 may. 2022, 13:21:06
    Author     : Cate
--%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="es.taw.aliebay.dto.MensajeDTO" %>
<%@ page import="es.taw.aliebay.entity.Mensaje" %>
<%@ page import="es.taw.aliebay.dto.ProductoDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de mensajes</title>
</head>
<body>
<jsp:include page="cabeceraComprador.jsp"/>
<h1>Lista de mensajes</h1>
<%
    List<MensajeDTO> mensajes = (List<MensajeDTO>) request.getAttribute("mensajes");
    if (mensajes.isEmpty()){
%>
<h2>No tienes mensajes</h2>
<%
}else{
%>
<table border="1"  width="70%">
    <tr>
        <th>Nombre de listaComprador</th>
        <th>Asunto</th>
        <th>Id de los productos</th>
        <th>Descripcion</th>
        <th>Fecha</th>
    </tr>
    <%
        for (MensajeDTO m : mensajes){
    %>
    <tr>
        <td><%= m.getListaComprador().getNombre()%></td>
        <td><%= m.getAsunto() %></td>

        <%
            if (m.getProductoList() == null || m.getProductoList().isEmpty()){
        %>
        <td>No hay productos asignados a este mensaje</td>
        <%
        } else {
        %>
        <td>
            <%
                for (Integer productoId : m.getProductoList()){

            %>
            <%= productoId %>
            <% } %>
        </td>
        <%
            }
        %>
        <td><%= m.getDescripcion() %></td>
        <td> <%= m.getDate() + " " + m.getTime() %></td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>
</br>
</body>
</html>
