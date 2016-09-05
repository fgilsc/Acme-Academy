
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

<display:table name="subjectsByLecturer" pagesize="5" class="displaytag" requestURI="subject/${actor}list.do${requestUri}" id="subject">
	<acme:column code="subject.code" property="code" />
	<acme:column code="subject.title" property="title" />


	<display:column titleKey="subject.lecturers">
		<a href="lecturer/listBySubject.do?subjectId=${subject.id}">
			<spring:message code="subject.lecturers"></spring:message></a>
	</display:column>
</display:table>	