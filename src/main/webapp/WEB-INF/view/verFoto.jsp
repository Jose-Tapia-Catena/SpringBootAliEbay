<%--
  Created by IntelliJ IDEA.
  User: felip
  Date: 08/06/2022
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
    Document   : verFoto
    Created on : 14 may 2022, 16:43:14
    Author     : felip
--%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--
    <jsp:include page="/WEB-INF/jsp/cabeceraFavoritos.jsp" />
    --%>
    <title>Foto</title>
</head>
<body>
<%
    String url = (String)request.getAttribute("url");
%>

<img src="<%=url%>">
</body>
</html>

