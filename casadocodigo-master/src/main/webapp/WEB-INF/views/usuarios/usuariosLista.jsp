<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Lista de Usuários">

	<div class="container">
		<h1>Lista de Usuários</h1>
		<h1>${sucesso }</h1>
		<h1>${role }</h1>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Nome</th>
				<th>Email</th>
				<th>Roles</th>
				<th> </th>
			</tr>
			<c:forEach items="${usuarios }" var="usuario">
				<tr>
					<td>${usuario.nome }</td>
					<td>${usuario.email }</td>
					<td>${usuario.roles }</td>
					<td><a href="${s:mvcUrl('UC#formRole').arg(0, usuario.email).build()}">Alterar Roles</a></td>
				</tr>
			</c:forEach>
		</table>
		<a href="${s:mvcUrl('UC#form').build()}">Novo Usuário</a>
	</div>

</tags:pageTemplate>