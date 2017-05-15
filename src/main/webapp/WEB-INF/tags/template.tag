<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

<%@ include file="/WEB-INF/views/header.jsp" %>

<jsp:doBody />

<%@ include file="/WEB-INF/views/footer.jsp" %>

</body>

</html>