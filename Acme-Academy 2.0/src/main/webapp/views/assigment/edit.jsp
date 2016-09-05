<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="hasRole('LECTURER')">

<form:form action="assigment/lecturer/edit.do"  modelAttribute="assigment">
	<div>
		<form:hidden path="id" />
		<form:hidden path="version" />
		
		<acme:textbox code="assigment.title" path="title" />
		<acme:textbox code="assigment.description" path="description" />
		
		<acme:textbox code="assigment.mark" path="mark" />
		
		
		<acme:textbox code="assigment.deadline" path="deadline" placeholder="dd/MM/yyyy HH:mm" />
		
		
		<acme:select code="assigment.group" path="group" items="${groups}" itemLabel="name" id="groups" />
		
		<form:hidden path="openingTime" />
		<form:hidden path="lecturer" />
		<form:hidden path="subject" />
		<form:hidden path="deliverables" />
		<form:hidden path="rubrics" />
		
		<acme:submit name="save" code="assigment.edit.save"/>
		
		<input type="button" name="cancel" value="<spring:message code="return"/>" onclick="javascript: window.location.replace('/Acme-Academy')"/>
	</div>
</form:form>
</security:authorize>
