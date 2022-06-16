<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Cate
  Date: 13/06/2022
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mensaje</title>
</head>
<body>
<jsp:include page="cabeceraMarketing.jsp"/>
<h1>Editar informacion del mensaje</h1>
<%--@elvariable id="mensaje" type="es.taw.aliebay.dto.MensajeDTO"--%>
    <form:form modelAttribute="mensaje" action="/marketing/MensajeGuardar">
        <form:hidden path="id"/>
        <form:hidden path="marketing.usuario.idUsuario"/>
        <form:hidden path="listaComprador.idLista"/>

        Asunto: <form:input path="asunto"/></br></br>
        Descripcion: <form:input path="descripcion"/></br></br>
        <div style="font-size:15px; font-palette: light; width: 10%">Formato de la fecha: dd/MM/yyyy HH:mm:ss</div>
        </br>
        Fecha: <form:input path="fecha"/></br></br>
        Productos:</br>
        <form:checkboxes path="productoList" items="${productos}" itemLabel="titulo" itemValue="idProducto"/></br></br>
        <form:button>Enviar</form:button>
    </form:form>
</body>
</html>
