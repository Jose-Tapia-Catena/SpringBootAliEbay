<%--
  Created by IntelliJ IDEA.
  User: felip
  Date: 14/06/2022
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@ page import="es.taw.aliebay.dto.ProductoDTO" %>
<%@ page import="es.taw.aliebay.dto.UsuarioDTO" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Productos comprados</title>
</head>
<body>
<jsp:include page="cabeceraFavoritos.jsp" />
<h1>Mis productos</h1>

<%
    List<ProductoDTO> productosConVentas = (List)request.getAttribute("productosConVentas");
    if(productosConVentas == null || productosConVentas.isEmpty()) {
%>
        <h2>No tiene ningún producto comprado</h2>
<%
    } else {
%>

<table border="1" width="90%" style="text-align:center">
    <tr>
        <th>Foto</th>
        <th>Título</th>
        <th>Descripción</th>
        <th>ID Vendedor</th>
        <th>Categoría</th>
        <th>Fecha</th>
        <th>Precio salida</th>
        <th>Precio compra</th>
        <th></th><!--- Borrar --->
    </tr>
        <%
                for (ProductoDTO pc : productosConVentas) {

            %>
    <tr>
        <form method="post" action="/verFoto/">
            <input type="hidden" name="previous" value="/comprador/productos/">
            <input type="hidden" name="url" value="<%=pc.getuRLFoto()%>">
            <td><input type="submit" value="Ver foto"/></td>
        </form>
        <td><%= pc.getTitulo()%></td>
        <td><%= pc.getDescripcion()%></td>
        <td><%= pc.getVendedor()%></td>
        <td><%= pc.getCategoria()%></td>
        <td><%= pc.getVenta().getFecha()%></td>
        <td><%= pc.getPrecioSalida()%> €</td>
        <td><%= pc.getVenta().getPrecioVenta()%> €</td>
        <td><a href="/comprador/productos/borrar/<%=pc.getIdProducto() %>/"> Borrar </a></td>
    </tr>
        <%
                    }
                }
            %>
</body>
</html>

