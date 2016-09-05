<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<security:authorize access="hasRole('STUDENT')">
<p><spring:message code="group.intro"></spring:message></p>
</security:authorize>
<display:table name="groups" pagesize="5" class="displaytag" requestURI="${requestUri}" id="group">
	<acme:column code="group.name" property="name" />
	<acme:column code="group.description" property="description" />
	<acme:column code="group.academicYear" property="academicYear" />
	<%--JOINS --%>
	<security:authorize access="hasRole('STUDENT')">
	<display:column>
		<input type="button" name="enrol" value="<spring:message code="group.list.enrol"/>" 
			onclick="javascript: window.location.replace('group/enrol.do?groupId=${group.id}')"/>
	</display:column>
	</security:authorize>
	<security:authorize access="hasRole('LECTURER')">
		<display:column title="${learningMaterial}">
		<spring:message code="group.learningMaterials" var="learningMaterial"></spring:message>
		<a href="learningMaterial/lecturer/listByGroup.do?groupId=${group.id}"> ${learningMaterial}</a>
	</display:column>
	</security:authorize>
</display:table>
