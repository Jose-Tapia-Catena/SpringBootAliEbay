<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: felip
  Date: 08/06/2022
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>NuevaCategoria</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/cabecera.jsp" />
<h1>Nueva Categoria:</h1>
<%--@elvariable id="categoria" type="es.taw.aliebay.dto.CategoriaDTO"--%>
<form:form method="post" action="/administrador/categorias/guardar/" modelAttribute="categoria">
    Nueva Categoría:
    <form:input path="idCategoria"/><br>
    <form:button>Enviar</form:button>
</form:form>
</body>
</html>

