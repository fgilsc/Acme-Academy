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

<form:form action="biographyEntry/lecturer/edit.do"  modelAttribute="biographyEntry">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="syllabi" />
	<div>
		<acme:textbox code="biographyEntry.title" path="title" />
	</div>	
	<div>
		<acme:textbox code="biographyEntry.author" path="author" />&nbsp;<spring:message code="biographyEntry.semicolon"></spring:message>
	</div>
	<div>
		<acme:textbox code="biographyEntry.locator" path="locator" />
		<acme:textbox code="biographyEntry.url" path="url" />
	</div>
	
	<input type="submit" name="save" value="<spring:message code="biographyEntry.save"/>" />
	&nbsp;
	<input type="button" name="cancel" value="<spring:message code="biographyEntry.edit.cancel" />"
														onclick="javascript: window.location.replace('syllabus/lecturer/create.do')" />
</form:form>
</security:authorize>