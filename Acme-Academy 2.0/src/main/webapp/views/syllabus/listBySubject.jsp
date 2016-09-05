<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<display:table name="syllabi" pagesize="5" class="displaytag" requestURI="${requestUri}" id="syllabus">
	<acme:column code="syllabus.subject" property="subject.title" />
	<acme:column code="syllabus.academicYear" property="academicYear" />
	<acme:column code="syllabus.summary" property="summary" />
	<acme:column code="syllabus.goal" property="goal" />
	<acme:column code="syllabus.prerrequisite" property="prerrequisite" />
	<acme:column code="syllabus.evaluationPolicy" property="evaluationPolicy" />
	
	<display:column titleKey="syllabus.biographyEntries">
		<a href="biographyEntry/listBySyllabus.do?syllabusId=${syllabus.id}">
			<spring:message code="syllabus.biographyEntries"></spring:message></a>
	</display:column>
	
	
</display:table>
