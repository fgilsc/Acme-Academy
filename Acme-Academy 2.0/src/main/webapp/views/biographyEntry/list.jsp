<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<display:table name="biographyEntries" pagesize="5" class="displaytag" requestURI="${requestUri}" id="biographyEntry">
	<acme:column code="biographyEntry.title" property="title" />
	<acme:column code="biographyEntry.author" property="author" />
	<acme:column code="biographyEntry.locator" property="locator" />
	<acme:column code="biographyEntry.url" property="url" />
	
	<security:authorize access="hasRole('LECTURER')">
	<display:column title="${edit}">
			<spring:message code="biographyEntry.edit" var="edit"></spring:message>
			<a href="biographyEntry/lecturer/edit.do?biographyEntryId=${biographyEntry.id}"> ${edit}</a>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="biographyEntry.subjectCounter" sortable="true">
			<jstl:set var="veces" value="${contadorBio.get(biographyEntry.id)}"/>
			<jstl:out value="${veces}" />
		</display:column>	
	</security:authorize>
</display:table>