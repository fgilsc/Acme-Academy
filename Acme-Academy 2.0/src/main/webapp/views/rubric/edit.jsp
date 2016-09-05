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

<form:form action="rubric/lecturer/edit.do"  modelAttribute="rubric">
	<div>
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="assesments" />
		
		<acme:textbox code="rubric.explanation" path="explanation" />
		<acme:textbox code="rubric.percentage" path="percentage" />
		
		<acme:select code="rubric.assigment" path="assigment" items="${assigments}" itemLabel="title" id="assigments" />
		
		<input type="submit" name="save" value="<spring:message code="rubric.save"/>" />
	&nbsp;
	<input type="button" name="cancel" value="<spring:message code="rubric.edit.cancel" />"
														onclick="javascript: window.location.replace('rubric/lecturer/list.do')" />
	</div>
</form:form>
</security:authorize>	
		