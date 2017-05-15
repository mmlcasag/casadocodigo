<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:template>

	<div class="container">
	
	<h3>Casa do Código</h3>
	<hr />
	
	<div><b>${mensagem}</b></div>
	<br />
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Título</th>
				<th>Descrição</th>
				<th>Páginas</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td><a href="/casadocodigo/produtos/detalhe/${produto.id}">${produto.titulo}</a></td>
					<td><a href="/casadocodigo/produtos/detalhe/${produto.id}">${produto.descricao}</a></td>
					<td><a href="/casadocodigo/produtos/detalhe/${produto.id}">${produto.paginas}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		
	</div>

</tags:template>