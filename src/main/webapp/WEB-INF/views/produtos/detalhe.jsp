<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:template>
	
	<div class="container">
	
	<h3>Detalhe do Produto</h3>
	<hr />
	
	<form:form servletRelativeAction="/carrinho/adiciona" method="post">
		
		<input type="hidden" name="produtoId" value="${produto.id}"/>
		
		<table class="table table-hover">
			<thead>
				<tr>
					<th class="col-md-3">Campo</th>
					<th class="col-md-9">Valor</th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					<td>Título:</td>
					<td><b>${produto.titulo}</b></td>
				</tr>
				<tr>
					<td>Descrição:</td>
					<td><b>${produto.descricao}</b></td>
				</tr>
				<tr>
					<td>Páginas:</td>
					<td><b>${produto.paginas}</b></td>
				</tr>
				<tr>
					<td>Lançamento:</td>
					<td><b><fmt:formatDate pattern="dd/MM/yyyy" value="${produto.lancamento.time}"/></b></td>
				</tr>
				<c:forEach items="${produto.precos}" var="preco">
					<tr>
						<td><input type="radio" name="tipoPreco" checked="checked" value="${preco.tipo}"/>&nbsp;&nbsp;${preco.tipo}:</td>
						<td><b>${preco.valor}</b></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<button type="submit" class="btn btn-primary">Comprar</button>
		<input type="button" value="Retornar" onclick="location.href='/casadocodigo/'" class="btn btn-default"/>
		
	</form:form>
	
	</div>
	
</tags:template>