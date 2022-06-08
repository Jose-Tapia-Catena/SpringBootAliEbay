<%@ page import="es.taw.aliebay.dto.UsuarioDTO" %><%--
  Created by IntelliJ IDEA.
  User: felip
  Date: 08/06/2022
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
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
%>

<table width="80%">
    <tr width="80%">
        <td>Bievenido, </td>
        <td>Session ID,  %></td>
        <td><a href="/administrador/">Listado de clientes</a></td>
        <td><a href="/logout/">Salir</a></td>
    </tr>
</table>
<html>
<body>
<h2 style="color:red"><%= strError%><br/></h2>
</body>
</html>

