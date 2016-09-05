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

<security:authorize access="hasRole('STUDENT')">
<p><spring:message code="assigment.intro"></spring:message></p>
<display:table name="assigments" pagesize="5" class="displaytag" requestURI="${requestUri}" id="assigment">
	<acme:column code="assigment.title" property="title" />
	<acme:column code="assigment.description" property="description" />
	<acme:column code="assigment.mark" property="mark" />
	<acme:column code="assigment.openingTime" property="openingTime" />
	<acme:column code="assigment.deadline" property="deadline" />
	
	<display:column title="${rubrics}">
			<spring:message code="assigment.rubrics" var="rubrics"></spring:message>
			<a href="rubric/list.do?assigmentId=${assigment.id}"> ${rubrics}</a>
	</display:column>
	
	<display:column>
		<input type="button" name="updload" value="<spring:message code="assigment.list.upload"/>" 
			onclick="javascript: window.location.replace('deliverable/upload.do?assigmentId=${assigment.id}')"/>
	</display:column>
</display:table>
</security:authorize>