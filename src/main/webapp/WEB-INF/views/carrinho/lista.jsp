<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:template>
	
	<div class="container">
	
	<h3>Meu Carrinho</h3>
	<hr />
	
	<table class="table table-hover">
		<thead>
			<tr>
				<td align="left"  width="60%"><b>Produto</b></td>
				<td align="right" width="10%"><b>Unitário</b></td>
				<td align="right" width="10%"><b>Qtdade</b></td>
				<td align="right" width="10%"><b>Total</b></td>
				<td align="right" width="10%"><b>Ações</b></td>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${carrinho.itens}" var="item">
				<tr>
					<td>${item.produto.titulo}</td>
					<td align="right">${item.preco}</td>
					<td align="right">${carrinho.getQuantidade(item)}</td>
					<td align="right">${carrinho.getTotal(item)}</td>
					<td align="right">
						<form:form servletRelativeAction="/carrinho/remove" method="post">
							<input type="hidden" name="produtoId" value="${item.produto.id}"/>
							<input type="hidden" name="tipoPreco" value="${item.tipoPreco}"/>
							<input type="submit" value="Remover" class="btn-xs btn-danger"/>
						</form:form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		
		<tfoot>
			<tr>
				<td align="left" colspan="3"><b>Total: ${carrinho.quantidade} produto(s)</b></td>
				<td align="right" ><b>${carrinho.total}</b></td>
				<td align="right" ><br /></td>
			</tr>
		</tfoot>
	</table>
	
	<br />
	
	<form:form servletRelativeAction="/pagamento/finaliza" method="post">
		<input type="submit" value="Finalizar compra" class="btn btn-primary"/>
		<input type="button" value="Continuar compra" onclick="location.href='/casadocodigo/'" class="btn btn-default"/>
	</form:form>
	
	</div>

</tags:template>