<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:template>
	
	<div class="container">
	
	<h3>Listagem de Produtos</h3>
	<hr />
	
	<div><b>${mensagem}</b></div>
	<br />
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th><b>Título</b></th>
				<th><b>Descrição</b></th>
				<th><b>Lançamento</b></th>
				<th><b>Páginas</b></th>
				<th><b>Preços</b></th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td>${produto.titulo}</td>
					<td>${produto.descricao}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${produto.lancamento.time}"/></td>
					<td>${produto.paginas}</td>
					<td>${produto.precos}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	</div>
	
</tags:template>