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

<form:form action="syllabus/lecturer/edit.do"  modelAttribute="syllabus">
	<div>
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="lecturer" />
		<form:hidden path="subject" />
		
		<acme:textbox code="syllabus.academicYear" path="academicYear" />
		<acme:textbox code="syllabus.summary" path="summary" />
	</div>
	<div> 	
		<acme:textarea code="syllabus.goal" path="goal" 
						placeholder="" /> <spring:message code="biographyEntry.semicolon"></spring:message>
		
		<acme:textarea code="syllabus.prerrequisite" path="prerrequisite" 
						/> <spring:message code="biographyEntry.semicolon"></spring:message>
	</div>
	<div>	
		<acme:textbox code="syllabus.evaluationPolicy" path="evaluationPolicy" />				
		
		<acme:multiCheckbox path="biographyEntries" items="${biographyEntries}" itemLabel="title"/>
		<a href="biographyEntry/lecturer/create.do"><spring:message code="syllabus.create.biographyEntry" /></a>
		<a href="biographyEntry/lecturer/list.do"><spring:message code="syllabus.edit.biographyEntry" /></a>									
	</div>
	
	<input type="submit" name="save" value="<spring:message code="syllabus.save"/>" />
	&nbsp;
	<input type="button" name="cancel" value="<spring:message code="return"/>" onclick="javascript: window.location.replace('syllabus/lecturer/list.do')"/>

	
</form:form>
</security:authorize>