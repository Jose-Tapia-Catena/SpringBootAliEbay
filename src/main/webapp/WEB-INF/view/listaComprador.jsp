<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Cate
  Date: 12/06/2022
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Editar-Crear Lista Comprador</title>
</head>
<body>

<h1>Listas de compradores</h1>
<%--@elvariable id="listaComprador" type="es.taw.aliebay.dto.ListacompradorDTO"--%>
<form:form modelAttribute="listaComprador" action="/marketing/listaCompradorGuardar/">
  <form:hidden path="idLista"/>
  <form:hidden path="mensajeList"/>
  Nombre: <form:input path="nombre" size=""/></br></br>
  Compradores:</br>
  <form:checkboxes path="compradorList" items="${compradores}"  itemLabel="usuario.nombre" itemValue="usuario.idUsuario"/>
  </br> </br>
  <form:button>Guardar</form:button>
</form:form>
</body>
</html>
