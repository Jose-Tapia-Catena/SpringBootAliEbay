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
    UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
%>

<table width="80%">
    <tr width="80%">
        <td>Bievenido, </td>
        <td>Session ID,  <%=user.getIdUsuario() %></td>
        <td><a href="/comprador/">Pagina principal</a></td>
        <td><a href="/logout/">Salir</a></td>
    </tr>
</table>
<html>
<body>
</body>
</html>