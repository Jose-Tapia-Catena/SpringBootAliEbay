<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.aliebay.dto.ProductoDTO" %>
<%@ page import="es.taw.aliebay.dto.UsuarioDTO" %>
<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Crear nuevo producto</title>
</head>
<body>
<%
    UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
%>

<h1>Datos del producto</h1>
<%--@elvariable id="producto" type="es.taw.aliebay.dto.ProductoDTO"--%>
<form:form method="post" action="vendedor/productos/guardar/" modelAttribute="producto">
    <form:hidden path="idProducto"/>
    <table>
        <tr>
            <td>Titulo:</td>
            <td><form:input path="titulo" type="text"/></td>
        </tr>
        <tr>
            <td>Descripción:</td>
            <td><form:input path="descripcion" type="text"/></td>
        </tr>
        <tr>
            <td>Precio de salida:</td>
            <td><form:input path="precioSalida" type="number" /></td>
        </tr>
        <tr>
            <td>URL de la foto:</td>
            <td><form:input path="uRLFoto" type="text"/></td>
        </tr>
        <tr>
            <td>Fecha de salida:</td>
            <td><form:input path="fechaSalida" type="text" /></td>
        </tr>
        <tr>
            <td>Fecha de fin:</td>
            <td><form:input path="fechaFin" type="text" /></td>
        </tr>
        <tr>
            <td>Categoria:</td>
            <td><form:input path="categoria" type="text" /></td>
        </tr>
        <tr>
            <td><form:button>Crear producto</form:button></td>
        </tr>
    </table>
</form:form>
</body>
</html>
