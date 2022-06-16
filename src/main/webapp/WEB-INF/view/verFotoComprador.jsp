<%@ page import="es.taw.aliebay.dto.UsuarioDTO" %><%--
  Created by IntelliJ IDEA.
  User: Enrique Cañadas Cobo
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
    <jsp:include page="cabeceraFoto.jsp"/>

    <title>Foto</title>
</head>
<body>
<%
    String url = (String)request.getAttribute("url");
%>

<img src="<%=url%>">
</body>
</html>