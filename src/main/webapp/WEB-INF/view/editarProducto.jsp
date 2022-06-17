<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.aliebay.dto.ProductoDTO" %>
<%@ page import="es.taw.aliebay.dto.UsuarioDTO" %>
<%@ page import="es.taw.aliebay.dto.CategoriaDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.aliebay.dao.ProductoRepository" %>
<%@ page import="es.taw.aliebay.entity.Producto" %>
<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Editar producto</title>
</head>
<body>

<jsp:include page="/WEB-INF/view/cabeceraVendedor.jsp" />

<%
    List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");
    Integer vendedor = (Integer)request.getAttribute("vendedor");
    Integer prod = (Integer) request.getAttribute("idProducto");
    ProductoDTO producto = (ProductoDTO) request.getAttribute("producto");
%>
<h1>Datos del producto</h1>
<%--@elvariable id="producto" type="es.taw.aliebay.dto.ProductoDTO"--%>
<form:form method="post" action="/vendedor/productos/<%=prod%>/editar/guardar/" modelAttribute="producto">
    <form:hidden path="vendedor" value="<%=vendedor%>"/>
    <table>
        <tr>
            <td>Titulo:</td>
            <td><form:input path="titulo" type="text" value="<%=producto.getTitulo()%>"/></td>
        </tr>
        <tr>
            <td>Descripción:</td>
            <td><form:input path="descripcion" type="text" value="<%=producto.getDescripcion()%>"/></td>
        </tr>
        <tr>
            <td>Precio de salida:</td>
            <td><form:input path="precioSalida" type="number" value="<%=producto.getPrecioSalida()%>"/></td>
        </tr>
        <tr>
            <td>URL de la foto:</td>
            <td><form:input path="uRLFoto" type="text" value="<%=producto.getuRLFoto()%>"/></td>
        </tr>
        <tr>
            <td>Fecha de salida:</td>
            <td><form:input type="date" path="fechaSalidaDia" value="<%=producto.getFechaSalidaDia()%>"/></td>
            <td><form:input type="time" path="fechaSalidaHora" value="<%=producto.getFechaSalidaHora()%>"/></td>
        </tr>
        <tr>
            <td>Fecha de fin:</td>
            <td><form:input type="date" path="fechaFinDia" value="<%=producto.getFechaFinDia()%>" /></td>
            <td><form:input type="time" path="fechaFinHora" value="<%=producto.getFechaFinHora()%>"/></td>
        </tr>
        <tr>
            <td>Categoria:</td>
            <td>
                <form:select path="categoria">
                    <form:options items="${categorias}" itemLabel="idCategoria" itemValue="idCategoria"/>
                </form:select>
            </td>
        </tr>

        <tr>
            <td><form:button>Editar Producto</form:button></td>
        </tr>
    </table>
</form:form>
</body>
</html>
