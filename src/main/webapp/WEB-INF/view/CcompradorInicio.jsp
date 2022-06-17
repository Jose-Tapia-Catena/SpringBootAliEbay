<%--
  Created by IntelliJ IDEA.
  User: Enrique Cañadas Cobo
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@ page import="es.taw.aliebay.dto.ProductoDTO" %>
<%@ page import="es.taw.aliebay.dto.CompradorDTO" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Productos comprados</title>
</head>
<body>
  <jsp:include page="cabeceraComprador.jsp" />
<h1>Productos</h1>

<%
    CompradorDTO compradorDTO = (CompradorDTO) request.getAttribute("compradorDTO");
    List<ProductoDTO> productosPujados = (List)request.getAttribute("productosPujados");
    if(productosPujados == null || productosPujados.isEmpty()) {
%>
<h2>No hay ninguna puja activa</h2>
<%
    } else {
%>

<h3>Productos pujados por ti:</h3>
<table border="1" width="80%" style="text-align:center">
    <tr>
        <th>Foto</th>
        <th>Título</th>
        <th>Descripción</th>
        <th>ID Vendedor</th>
        <th>Categoría</th>
        <th>Precio salida</th>
        <th>Puja actual</th>
        <th></th><!--- Pujar --->
        <th></th><!--- Favorito --->
    </tr>
    <%
        for (ProductoDTO p : productosPujados) {
            String puja = "No pujado";
            if(p.getPuja() != null) {
                puja = p.getPuja().getPuja() + " €";
            }
    %>
    <tr>
        <form method="post" action="/verFoto/">
            <input type="hidden" name="previous" value="/comprador/">
            <input type="hidden" name="url" value="<%=p.getuRLFoto()%>">
            <td><input type="submit" value="Ver foto"/></td>
        </form>
        <td><%= p.getTitulo()%></td>
        <td><%= p.getDescripcion()%></td>
        <td><%= p.getVendedor()%></td>
        <td><%= p.getCategoria()%></td>
        <td><%= p.getPrecioSalida()%> €</td>
        <td><%= puja%></td>
        <td><a href="/comprador/productos/pujar/<%=p.getIdProducto() %>/"> Pujar </a></td>
        <%
            if(p.getCompradorFavorito() != null && p.getCompradorFavorito().contains(compradorDTO)) {
        %>
        <td>Ya es favorito</td>
        <%
            } else {
        %>
        <td><a href="/comprador/productos/anyadir/favorito/<%=p.getIdProducto() %>/"> Añadir favorito </a></td>
        <%
            }
        %>
    </tr>

    <%
        }
    %>
</table>
<%
    }
%>

<%
    List<ProductoDTO> productosDisponibles = (List)request.getAttribute("productoNoPujados");
    if(productosDisponibles == null || productosDisponibles.isEmpty()) {
%>
<h2>No hay productos disponibles</h2>
<%
    } else {
%>

<h3>Productos disponibles:</h3>
<table border="1" width="80%" style="text-align:center">
    <tr>
        <th>Foto</th>
        <th>Título</th>
        <th>Descripción</th>
        <th>ID Vendedor</th>
        <th>Categoría</th>
        <th>Precio salida</th>
        <th>Puja actual</th>
        <th></th><!--- Pujar --->
        <th></th><!--- Favorito --->
    </tr>
    <%
        for (ProductoDTO p : productosDisponibles) {
            String puja = "No pujado";
            if(p.getPuja() != null) {
                puja = p.getPuja().getPuja() + " €";
            }
    %>
    <tr>
        <form method="post" action="/verFoto/">
            <input type="hidden" name="url" value="<%=p.getuRLFoto()%>">
            <td><input type="submit" value="Ver foto"/></td>
        </form>
        <td><%= p.getTitulo()%></td>
        <td><%= p.getDescripcion()%></td>
        <td><%= p.getVendedor()%></td>
        <td><%= p.getCategoria()%></td>
        <td><%= p.getPrecioSalida()%> €</td>
        <td><%= puja%></td>
        <td><a href="/comprador/productos/pujar/<%=p.getIdProducto() %>/"> Pujar </a></td>
        <%
            if(p.getCompradorFavorito() != null && p.getCompradorFavorito().contains(compradorDTO)) {
        %>
        <td>Ya es favorito</td>
        <%
        } else {
        %>
        <td><a href="/comprador/productos/anyadir/favorito/<%=p.getIdProducto() %>/"> Añadir favorito </a></td>
        <%
            }
        %>
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