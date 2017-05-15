<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

	<header>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
			    <div class="navbar-header">
			    	<a class="navbar-brand" href="/casadocodigo/"><fmt:message key="menu.titulo"/></a>
			    </div>
			    <ul class="nav navbar-nav">
				    <security:authorize access="isAuthenticated()">
					    <li><a href="/casadocodigo/produtos/lista"><fmt:message key="menu.produtos.lista"/></a></li>
					    <li><a href="/casadocodigo/produtos/form"><fmt:message key="menu.produtos.cadastro"/></a></li>
				    </security:authorize>
			    </ul>
			    <ul class="nav navbar-nav navbar-right">
			    	<security:authorize access="isAuthenticated()">
					    <li><a href="#"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<security:authentication property="principal.username"></security:authentication></a></li>
				    </security:authorize>
				    <li><a href="/casadocodigo/carrinho/lista"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;&nbsp;<fmt:message key="menu.carrinho"><fmt:param value="${carrinho.quantidade}"/></fmt:message></a></li>
				    <security:authorize access="isAnonymous()">
				    	<li><a href="/casadocodigo/login"><span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;<fmt:message key="menu.entrar"/></a></li>
				    </security:authorize>
				    <security:authorize access="isAuthenticated()">
				    	<li><a href="/casadocodigo/logout"><span class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;<fmt:message key="menu.sair"/></a></li>
				    </security:authorize>
				    <li><a href="?locale=pt_BR"><fmt:message key="menu.pt"/></a></li>
				    <li><a href="?locale=en_US"><fmt:message key="menu.en"/></a></li>
			    </ul>
			</div>
		</nav>
	</header>