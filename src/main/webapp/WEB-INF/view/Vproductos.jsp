<%--
  Created by IntelliJ IDEA.
  User: felip
  Date: 14/06/2022
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@ page import="es.taw.aliebay.dto.ProductoDTO" %>
<%@ page import="es.taw.aliebay.dto.UsuarioDTO" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Productos</title>
</head>
<body>
<%--
<jsp:include page="/WEB-INF/view/cabeceraVendedor.jsp" />
--%>

<%
    List<ProductoDTO> productosVendidos = (List)request.getAttribute("productosVendidos");
    List<ProductoDTO> productosNoVendidos = (List)request.getAttribute("productosNoVendidos");
    List<ProductoDTO> productosNoVendidosTerminados = (List)request.getAttribute("productosNoVendidosTerminados");


    if(productosVendidos.isEmpty() && productosNoVendidos.isEmpty() && productosNoVendidosTerminados.isEmpty()){
%>
<h2> Usted no dispone de ningún producto</h2>
<%
}else{
%>
<a href="/vendedor/productos/crear/">Crear Nuevo Producto</a><br/>

<h2> Sus productos: </h2>
<%
    if(!productosNoVendidos.isEmpty()){
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
        <form method="post" action="/verFoto/">
            <input type="hidden" name="url" value="<%=p.getuRLFoto()%>">
            <td><input type="submit" value="Ver foto"/></td>
        </form>
        <td><%= p.getFechaSalida() %></td>
        <td><%= p.getFechaFin() %></td>
        <td><a href="/vendedor/productos/<%=p.getIdProducto() %>/borrar/">Borrar</a></td>
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
        <td><a href="/vendedor/productos/<%=p.getIdProducto() %>/borrar/">Borrar</a></td>
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
        <form method="post" action="/verFoto/">
            <input type="hidden" name="url" value="<%=p.getuRLFoto()%>">
            <td><input type="submit" value="Ver foto"/></td>
        </form>
        <td><%= p.getFechaSalida() %></td>
        <td><%= p.getFechaFin() %></td>
        <td><a href="/vendedor/productos/<%=p.getIdProducto() %>/borrar/">Borrar</a></td>
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

