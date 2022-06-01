<%--
  Created by IntelliJ IDEA.
  User: Cate
  Date: 23/05/2022
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="es.taw.aliebay.dto.UsuarioDTO" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listado de clientes</title>
</head>
<body>

<h1>Listado de clientes</h1>


<%
    List<UsuarioDTO> usuarios = (List)request.getAttribute("usuarios");
    if (usuarios == null || usuarios.isEmpty() ) {
%>
<h2>No hay usuarios</h2>
<%
} else {
%>
<table border="1">
    <tr>
        <th>Nombre</th>
<%--        <th>ADDRESS</th>
        <th>EMAIL</th>
        <th>CITY</th>
        <th>MICROMARKET</th>
        <th>DISCOUNTCODE</th>--%>
    </tr>
    <%
        for (UsuarioDTO usuario: usuarios) {
    %>
    <tr>
        <td><%= usuario.getNombre() + " " + usuario.getApellidos()  %></td>
<%--        <td><%= %></td>
        <td><%= cliente.getEmail() %></td>
        <td><%= cliente.getCity() %></td>
        <td><%= cliente.getMicroMarketByZip().getZipCode() %></td>
        <td><%= cliente.getDiscountCodeByDiscountCode().getDiscountCode() %></td>--%>
    </tr>

    <%
        }
    %>
</table>
<%
    }
%>
</body>
</html>
