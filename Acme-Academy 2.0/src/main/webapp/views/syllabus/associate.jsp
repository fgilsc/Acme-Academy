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
		
		<form:hidden path="academicYear" />
		<form:hidden path="summary" />
		<form:hidden path="goal" />
		<form:hidden path="prerrequisite" />
		<form:hidden path="evaluationPolicy" />
		<form:hidden path="biographyEntries" />
		
		<acme:select code="syllabus.subject" path="subject" items="${subjects}" itemLabel="title" id="subjects" />
		
		<form:hidden path="lecturer" />
	</div>	
	<div>
		<acme:submit name="save" code="syllabus.edit.associate"/>
		&nbsp;
		<input type="button" name="cancel" value="<spring:message code="return"/>" onclick="javascript: window.location.replace('syllabus/lecturer/list.do')"/>
	</div>	
</form:form>
</security:authorize>