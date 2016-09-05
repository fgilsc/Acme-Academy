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
<p><spring:message code="group.myIntro"></spring:message></p>
<display:table name="myGroups" pagesize="5" class="displaytag" requestURI="${requestUri}" id="group">
	<acme:column code="group.name" property="name" />
	<acme:column code="group.description" property="description" />
	<acme:column code="group.academicYear" property="academicYear" />
	
	<display:column title="${learningMaterial}">
		<spring:message code="group.learningMaterials" var="learningMaterial"></spring:message>
		<a href="learningMaterial/student/list.do?groupId=${group.id}"> ${learningMaterial}</a>
	</display:column>
	
</display:table>
</security:authorize>