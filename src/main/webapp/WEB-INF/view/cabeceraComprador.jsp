<%--
  Created by IntelliJ IDEA.
  User: Enrique Cañadas Cobo
--%>

<%@ page import="es.taw.aliebay.dto.UsuarioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    /*UsuarioDTO user = (UsuarioDTO) session.getAttribute("usuario");
    if (user == null){
        response.sendRedirect(request.getContextPath());
    }
     */
%>

<%
    String strError = (String)request.getAttribute("error");
    if (strError == null) strError = "";
    UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
%>

<table width="80%">
    <tr width="80%">
        <td>Bievenido, </td>
        <td>Session ID,  <%=user.getIdUsuario() %></td>
        <td><a href="/comprador/favoritos/">Productos favoritos</a></td>
        <td><a href="/comprador/productos/">Mis productos</a></td>
        <td><a href="/logout/">Salir</a></td>
    </tr>
</table>
<html>
<body>
<h2 style="color:red"><%= strError%><br/></h2>
</body>
</html>