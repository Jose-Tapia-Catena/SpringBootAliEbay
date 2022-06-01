<%--
  Created by IntelliJ IDEA.
  User: felip
  Date: 01/06/2022
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="aliebay.dto.MarketingDTO"%>
<%@page import="aliebay.dto.VendedorDTO"%>
<%@page import="aliebay.dto.UsuarioDTO"%>
<%@page import="aliebay.dto.CompradorDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listado de usuarios</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/cabecera.jsp" />
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
        List<UsuarioDTO> compradores = (List)request.getAttribute("compradores");
        for (UsuarioDTO u: compradores) {

    %>
    <tr>
        <td><%= u.getIdUsuario()%></td>
        <td><%= u.getNombre() %></td>
        <td><%= u.getApellidos() %></td>
        <td><%= u.getDomicilio() %></td>
        <td><%= u.getCiudadResidencia()%></td>
        <td><%= u.getEdad()%></td>
        <td><%= u.getSexo()%></td>
        <td><a href="UsuarioBorrarServlet?id=<%= u.getIdUsuario()%>">Borrar</a></td>
        <td><a href="UsuarioNuevoEditarServlet?id=<%= u.getIdUsuario()%>">Editar</a></td>
        <td><a href="ProductosCompradorServlet?id=<%=u.getIdUsuario()%>">Productos</a></td>
    </tr>

    <%
        }
    %>
</table>
<a href="UsuarioNuevoEditarServlet?tipoUsuario=comprador">Crear Nuevo Comprador</a><br/>

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
        List<UsuarioDTO> vendedores = (List)request.getAttribute("vendedores");
        for (UsuarioDTO u: vendedores) {
    %>
    <tr>
        <td><%= u.getIdUsuario()%></td>
        <td><%= u.getNombre() %></td>
        <td><%= u.getApellidos() %></td>
        <td><%= u.getDomicilio() %></td>
        <td><%= u.getCiudadResidencia()%></td>
        <td><%= u.getEdad()%></td>
        <td><%= u.getSexo()%></td>
        <td><a href="UsuarioBorrarServlet?id=<%= u.getIdUsuario()%>">Borrar</a></td>
        <td><a href="UsuarioNuevoEditarServlet?id=<%= u.getIdUsuario()%>">Editar</a></td>
        <td><a href="MostrarProductosServlet?id=<%=u.getIdUsuario()%>">Productos</a></td>
    </tr>

    <%
        }
    %>
</table>
<a href="UsuarioNuevoEditarServlet?tipoUsuario=vendedor">Crear Nuevo Vendedor</a><br/>
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
        List<UsuarioDTO> marketings = (List)request.getAttribute("marketings");
        for (UsuarioDTO u: marketings) {
    %>
    <tr>
        <td><%= u.getIdUsuario()%></td>
        <td><%= u.getNombre() %></td>
        <td><%= u.getApellidos() %></td>
        <td><%= u.getDomicilio() %></td>
        <td><%= u.getCiudadResidencia()%></td>
        <td><%= u.getEdad()%></td>
        <td><%= u.getSexo()%></td>
        <td><a href="UsuarioBorrarServlet?id=<%= u.getIdUsuario()%>">Borrar</a></td>
        <td><a href="UsuarioNuevoEditarServlet?id=<%= u.getIdUsuario()%>">Editar</a></td>
    </tr>

    <%
        }
    %>
</table>
<a href="UsuarioNuevoEditarServlet?tipoUsuario=marketing">Crear Nuevo Marketing</a>
<br/>
<br/>

<a href="GestionarCategoriasServlet">Categorias de productos</a>
</body>
</html>

