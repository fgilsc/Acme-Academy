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


<display:table name="deliverables" pagesize="5" class="displaytag" requestURI="${requestUri}" id="deliverable">
	<display:column titleKey="deliverable.student">
			<jstl:set var="nombre" value="${mapeadorNombre.get(deliverable.uploaderStudentID)}"/>
			<jstl:out value="${nombre}" />
		</display:column>
	<acme:column code="deliverable.moment" property="moment" />
	<acme:column code="deliverable.content" property="content" />
	<security:authorize access="hasRole('LECTURER')">
		<display:column title="${assesment}">
		<spring:message code="deliverable.assesments" var="assesment"></spring:message>
		<a href="assesment/lecturer/list.do?deliverableId=${deliverable.id}"> ${assesment}</a>
	</display:column>
	</security:authorize>
	<security:authorize access="hasRole('STUDENT')">
		<display:column title="${assesment}">
		<spring:message code="deliverable.assesments" var="assesment"></spring:message>
		<a href="assesment/list.do?deliverableId=${deliverable.id}"> ${assesment}</a>
	</display:column>
	</security:authorize>
</display:table>