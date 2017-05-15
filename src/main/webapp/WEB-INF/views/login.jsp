<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Casa do Código</title>
	
	<c:url value="/resources/css" var="css" />
	<link rel="stylesheet" href="${css}/bootstrap.min.css">
	<link rel="stylesheet" href="${css}/bootstrap-theme.min.css">
</head>

<body>
	
	<div class="container">
	
	<h3>Acesse o sistema</h3>
	<hr />
	
	<form:form servletRelativeAction="/login" method="post">
		
		<div class="form-group">
			<label>E-mail</label>
			<input type="text" name="username" class="form-control"/>
		</div>
		
		<div class="form-group">
			<label>Senha</label>
			<input type="password" name="password" class="form-control"/>
		</div>
		
		<button type="submit" class="btn btn-primary">Entrar</button>
		
	</form:form>
	
	<br />
	
	</div>
	
</body>

</html>