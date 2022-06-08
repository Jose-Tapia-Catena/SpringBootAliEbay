<%--
  Created by IntelliJ IDEA.
  User: felip
  Date: 08/06/2022
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@ page import="es.taw.aliebay.dto.ProductoDTO" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Productos</title>
</head>
<body>
<%--
<jsp:include page="/WEB-INF/jsp/cabecera.jsp" />
--%>

<%
    List<ProductoDTO> productosVendidos = (List)request.getAttribute("productosVendidos");
    List<ProductoDTO> productosNoVendidos = (List)request.getAttribute("productosNoVendidos");
    List<ProductoDTO> productosNoVendidosTerminados = (List)request.getAttribute("productosNoVendidosTerminados");

    String categoria = (String)request.getAttribute("categoria");
    Integer vendedor = (Integer) request.getAttribute("vendedor");
    String llamada = "este usuario";
    if(categoria != null){
        llamada = "categoria " + categoria;
    }else if(vendedor != null){
        llamada = "el vendedor con id " + vendedor;
    }

    if(productosVendidos.isEmpty() && productosNoVendidos.isEmpty() && productosNoVendidosTerminados.isEmpty()){
%>
<h2> No existen productos para <%=llamada%></h2>
<%
}else{
    if(categoria != null || vendedor != null){
%>
<h2> Productos para <%=llamada %>: </h2>
<%
    } if(!productosNoVendidos.isEmpty()){
%>

<h3>Productos en venta:</h3>
<table border="1" width="80%" style="text-align:center">
    <tr>
        <th>Titulo</th>
        <th>Descripcion</th>
        <th>Categoria</th>
        <th>Precio de salida</th>
        <th>Foto del producto</th>
        <th>Fecha de salida</th>
        <th>Fecha de fin:</th>
        <th></th><!-- Borrar -->
    </tr>
    <%
        for (ProductoDTO p: productosNoVendidos) {
    %>
    <tr>
        <td><%= p.getTitulo()%></td>
        <td><%= p.getDescripcion() %></td>
        <td><%= p.getCategoria() %></td>
        <td><%= p.getPrecioSalida() %></td>
        <td><a href="/verFoto/<%=p.getuRLFoto()%>>">Ver Foto</a></td>
        <td><%= p.getFechaSalida() %></td>
        <td><%= p.getFechaFin() %></td>
        <td><a href="">Borrar</a></td>
    </tr>

    <%
        }
    %>
</table>
<%
    }
%>

<%
    if(!productosVendidos.isEmpty()){
%>

<h3>Productos vendidos:</h3>
<table border="1" width="80%" style="text-align:center">
    <tr>
        <th>Titulo</th>
        <th>Descripcion</th>
        <th>Categoria</th>
        <th>Precio compra</th>
        <th>Comprador</th>
        <th>Fecha compra</th>
        <th></th><!-- Borrar -->
    </tr>
    <%
        for (ProductoDTO p: productosVendidos) {
    %>
    <tr>
        <td><%= p.getTitulo()%></td>
        <td><%= p.getDescripcion() %></td>
        <td><%= p.getCategoria() %></td>
        <td><%= p.getVenta().getPrecioVenta() %></td>
        <td><%= p.getVenta().getComprador() %></td>
        <td><%= p.getVenta().getFecha() %></td>
        <td><a href="">Borrar</a></td>
    </tr>

    <%
        }
    %>
</table>
<%
    }
%>

<%
    if(!productosNoVendidosTerminados.isEmpty()){
%>

<h3>Pujas terminadas sin comprador:</h3>
<table border="1" width="80%" style="text-align:center">
    <tr>
        <th>Titulo</th>
        <th>Descripcion</th>
        <th>Categoria</th>
        <th>Precio de salida</th>
        <th>Foto del producto</th>
        <th>Fecha de salida</th>
        <th>Fecha de fin:</th>
        <th></th><!-- Borrar -->
    </tr>
    <%
        for (ProductoDTO p: productosNoVendidosTerminados) {
    %>
    <tr>
        <td><%= p.getTitulo()%></td>
        <td><%= p.getDescripcion() %></td>
        <td><%= p.getCategoria() %></td>
        <td><%= p.getPrecioSalida() %></td>
        <td><a href="/verFoto/<%=p.getuRLFoto()%>>">Ver Foto</a></td>
        <td><%= p.getFechaSalida() %></td>
        <td><%= p.getFechaFin() %></td>
        <td><a href="">Borrar</a></td>
    </tr>

    <%
        }
    %>
</table>
<%
        }
    }
%>


</body>
</html>

