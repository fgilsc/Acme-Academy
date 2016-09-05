
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<display:table name="lecturers" pagesize="5" class="displaytag" requestURI="lecturer/${actor}list.do${requestUri}" id="lecturer">
	
	<acme:column code="lecturer.name" property="name" />
	<acme:column code="lecturer.surname" property="surname" />
	<acme:column code="lecturer.email" property="email" />
	
	<display:column titleKey="lecturer.subjects">
		<a href="subject/listByLecturer.do?lecturerId=${lecturer.id}">
			<spring:message code="lecturer.subjects"></spring:message></a>
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="lecturer.biographyEntryCounter">
			<jstl:set var="veces" value="${contadorBio.get(lecturer.id)}"/>
			<jstl:out value="${veces}" />
		</display:column>	
	</security:authorize>	
</display:table>