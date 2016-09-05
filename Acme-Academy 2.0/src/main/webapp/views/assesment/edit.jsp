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

<form:form action="assesment/lecturer/edit.do"  modelAttribute="assesment">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="deliverable" />
	
	<acme:textbox code="assesment.explanation" path="explanation" />
	<acme:textbox code="assesment.points" path="points" />
	
	<acme:select code="assesment.rubric" path="rubric" items="${rubrics}" itemLabel="explanation" id="rubric" />
	<input type="submit" name="save" value="<spring:message code="assesment.save"/>" />
	&nbsp;
	<input type="button" name="cancel" value="<spring:message code="assesment.return" />"
														onclick="javascript: window.location.replace('deliverable/lecturer/list.do')" />
</form:form>
</security:authorize>