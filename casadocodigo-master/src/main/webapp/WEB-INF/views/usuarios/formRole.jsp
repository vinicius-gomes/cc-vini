<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tags:pageTemplate titulo="Alterar Permissões">
	<h1>Cadastro de Permissões para ${usuario.nome }</h1>
	
	<form:form action="${s:mvcUrl('UC#update').build() }" method="post" commandName="usuario">
		
		<form:hidden path="nome" />
		<form:hidden path="email"/>
		<form:hidden path="senha"/>
		<form:hidden path="senhaConfirma"/>
		<c:forEach items="${roles }" var="roles" varStatus="status">
			
			<form:checkbox path="roles" value="${roles.nome}" />${roles.nome }

		</c:forEach>
		
		<button type="submit" class="btn btn-primary">Cadastrar Roles</button>
	</form:form>
	
</tags:pageTemplate>