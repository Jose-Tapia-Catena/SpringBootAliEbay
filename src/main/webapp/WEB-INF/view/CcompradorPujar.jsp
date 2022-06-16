<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.aliebay.dto.ProductoDTO" %><%--
  Created by IntelliJ IDEA.
  User: Enrique Cañadas Cobo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bienvenidos al sistema</title>
</head>
<body>
<jsp:include page="cabeceraFavoritos.jsp" />
<h1>Pujar</h1>

<%
    ProductoDTO p = (ProductoDTO) request.getAttribute("productoDTO");
    String puja = "No pujado";
    if(p.getPuja() != null) {
        puja = p.getPuja().getPuja() + " €";
    }
%>

<%--@elvariable id="usuario" type="es.taw.aliebay.dto.UsuarioDTO"--%>
    <table>
        <tr>
            <td><form method="post" action="/verFotoComprador/">
                <input type="hidden" name="url" value="<%=p.getuRLFoto()%>">
                Foto: <input type="submit" value="Ver foto"/>
            </form></td>
        </tr>
        <tr>
            <td>Título: <%= p.getTitulo()%></td>
        </tr>
        <tr>
            <td>Descripción: <%= p.getDescripcion()%></td>
        </tr>
        <tr>
            <td>ID Vendedor: <%= p.getVendedor()%></td>
        </tr>
        <tr>
            <td>Categoría: <%= p.getCategoria()%></td>
        </tr>
        <tr>
            <td>Precio salida: <%= p.getPrecioSalida()%></td>
        </tr>

        <tr>
            <td>Puja actual: <%= puja%></td>
        </tr>

        <%--@elvariable id="puja" type="es.taw.aliebay.dto.PujaDTO"--%>
        <form:form method="post" action="/guardarPuja/" modelAttribute="puja">
            <form:hidden path="producto" value="<%= p.getIdProducto()%>"/>
        <tr>
            <td>Puja: <form:input path="puja"/></td>
        </tr>
            <%
                String strError = (String)request.getAttribute("error");
                if (strError != null){
            %>
            <td>
                <h3 style="color:red"><%= strError%><br/></h3>
            </td>
            <%
                }
            %>
        <tr>
            <td><form:button>Pujar</form:button></td>
        </tr>
        </form:form>
    </table>
</body>
</html>