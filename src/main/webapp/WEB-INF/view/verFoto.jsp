<%@ page import="es.taw.aliebay.dto.UsuarioDTO" %><%--
  Created by IntelliJ IDEA.
  User: felip
  Date: 08/06/2022
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
        String cabecera  = "";
        if (user.getTipoUsuario().equals("comprador")){
            cabecera = "Favorito";
        }
    %>
    <jsp:include page="cabecera.jsp"/>

    <title>Foto</title>
</head>
<body>
<%
    String url = (String)request.getAttribute("url");
%>

<img src="<%=url%>">
</body>
</html>