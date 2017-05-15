<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:template>
	
	<div class="container">
	
	<h3>Cadastro de Produtos</h3>
	<hr />
	
	<form:form servletRelativeAction="/produtos/grava" method="post" commandName="produto" enctype="multipart/form-data">
		
		<div class="form-group">
			<label>Título</label>
			<form:input path="titulo" class="form-control"/>
			<form:errors path="titulo" class="help-block"/>
		</div>
		
		<div class="form-group">
			<label>Descrição</label>
			<form:textarea path="descricao" rows="4" class="form-control"/>
			<form:errors path="descricao" class="help-block"/>
		</div>
		
		<div class="form-group">
			<label>Páginas</label>
			<form:input path="paginas" class="form-control"/>
			<form:errors path="paginas" class="help-block"/>
		</div>
		
		<div class="form-group">
			<label>Lançamento</label>
			<form:input path="lancamento" class="form-control"/>
			<form:errors path="lancamento" class="help-block"/>
		</div>
		
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div class="form-group">
				<label>${tipoPreco}</label>
				<form:input path="precos[${status.index}].valor" class="form-control"/>
				<form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}"/>
			</div>
		</c:forEach>
		
		<div class="form-group">
			<label>Sumário</label>
			<input type="file" name="sumario" class="form-control"/>
		</div>
		
		<button type="submit" class="btn btn-primary">Cadastrar</button>
		
	</form:form>
	
	</div>

</tags:template>