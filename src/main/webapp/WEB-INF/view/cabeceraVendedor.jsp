<%@ page import="es.taw.aliebay.dto.UsuarioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    String strError = (String)request.getAttribute("error");
    if (strError == null) strError = "";
    UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
%>

<table width="80%">
    <tr width="80%">
        <td>Bievenido, </td>
        <td>Session ID,  <%=user.getIdUsuario() %></td>
        <td><a href="/vendedor/">Volver</a></td>
        <td><a href="/logout/">Salir</a></td>
    </tr>
</table>
<html>
<body>
<h2 style="color:red"><%= strError%><br/></h2>
</body>
</html>