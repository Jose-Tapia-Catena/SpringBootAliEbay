<%--
  Created by IntelliJ IDEA.
  User: felip
  Date: 01/06/2022
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@ page import="es.taw.aliebay.dto.UsuarioDTO" %>
<%@ page import="es.taw.aliebay.dto.CompradorDTO" %>
<%@ page import="es.taw.aliebay.dto.VendedorDTO" %>
<%@ page import="es.taw.aliebay.dto.MarketingDTO" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listado de usuarios</title>
</head>
<body>
<%--
<jsp:include page="/WEB-INF/jsp/cabecera.jsp" />
--%>
<h1>Listado de usuarios</h1>
<h2>Compradores: </h2>
<table border="1" width="80%">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Domicilio</th>
        <th>Ciudad</th>
        <th>Edad</th>
        <th>Sexo</th>
        <th></th><!-- Borrar -->
        <th></th><!-- Editar -->
        <th></th><!-- Productos -->
    </tr>
    <%
        List<CompradorDTO> compradores = (List)request.getAttribute("compradores");
        for (CompradorDTO c: compradores) {
            UsuarioDTO u = c.getUsuario();
    %>
    <tr>
        <td><%= u.getIdUsuario()%></td>
        <td><%= u.getNombre() %></td>
        <td><%= u.getApellidos() %></td>
        <td><%= u.getDomicilio() %></td>
        <td><%= u.getCiudadResidencia()%></td>
        <td><%= u.getEdad()%></td>
        <td><%= u.getSexo()%></td>
        <td><a href="">Borrar</a></td>
        <td><a href="">Editar</a></td>
        <td><a href="">Productos</a></td>
    </tr>

    <%
        }
    %>
</table>
<a href="/administrador/usuario/comprador/crear/">Crear Nuevo Comprador</a><br/>

<h2>Vendedores: </h2>
<table border="1" width="80%">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Domicilio</th>
        <th>Ciudad</th>
        <th>Edad</th>
        <th>Sexo</th>
        <th></th><!-- Borrar -->
        <th></th><!-- Editar -->
        <th></th><!-- Productos -->
    </tr>
    <%
        List<VendedorDTO> vendedores = (List)request.getAttribute("vendedores");
        for (VendedorDTO v: vendedores) {
            UsuarioDTO u = v.getUsuario();
    %>
    <tr>
        <td><%= u.getIdUsuario()%></td>
        <td><%= u.getNombre() %></td>
        <td><%= u.getApellidos() %></td>
        <td><%= u.getDomicilio() %></td>
        <td><%= u.getCiudadResidencia()%></td>
        <td><%= u.getEdad()%></td>
        <td><%= u.getSexo()%></td>
        <td><a href="">Borrar</a></td>
        <td><a href="">Editar</a></td>
        <td><a href="/administrador/vendedor/<%=u.getIdUsuario()%>/productos/">Productos</a></td>
    </tr>

    <%
        }
    %>
</table>
<a href="/administrador/usuario/vendedor/crear/">Crear Nuevo Vendedor</a><br/>
<h2>Marketing: </h2>
<table border="1" width="80%">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Domicilio</th>
        <th>Ciudad</th>
        <th>Edad</th>
        <th>Sexo</th>
        <th></th><!-- Borrar -->
        <th></th><!-- Editar -->
    </tr>
    <%
        List<MarketingDTO> marketings = (List)request.getAttribute("marketings");
        for (MarketingDTO m: marketings) {
            UsuarioDTO u = m.getUsuario();
    %>
    <tr>
        <td><%= u.getIdUsuario()%></td>
        <td><%= u.getNombre() %></td>
        <td><%= u.getApellidos() %></td>
        <td><%= u.getDomicilio() %></td>
        <td><%= u.getCiudadResidencia()%></td>
        <td><%= u.getEdad()%></td>
        <td><%= u.getSexo()%></td>
        <td><a href="">Borrar</a></td>
        <td><a href="">Editar</a></td>
    </tr>

    <%
        }
    %>
</table>
<a href="/administrador/usuario/marketing/crear/">Crear Nuevo Marketing</a>
<br/>
<br/>

<a href="/administrador/categorias/">Categorias de productos</a>
</body>
</html>

