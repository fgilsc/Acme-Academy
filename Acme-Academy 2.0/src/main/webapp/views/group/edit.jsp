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

<form:form requestURI="${requestURI}" modelAttribute="groupForm">
	<div>
		<acme:textbox code="group.name" path="name" />
		<acme:textbox code="group.description" path="description" />
		<acme:textbox code="group.academicYear" path="academicYear" />
		<acme:select code="group.subject" path="subject" items="${subjects}" itemLabel="title" id="subjects" />	
	</div>
	<acme:submit name="save" code="group.edit.save"/>
		
	<input type="button" name="cancel" value="<spring:message code="return"/>" onclick="javascript: window.location.replace('group/lecturer/list.do')"/>
</form:form>
</security:authorize>