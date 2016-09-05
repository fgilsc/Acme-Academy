<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<b><spring:message code="syllabus.academicYear"/>:</b>
<jstl:out value="${academicYear}"></jstl:out>
<br>
<br>
<b><spring:message code="syllabus.summary"/>:</b>
<jstl:out value="${summary}"></jstl:out>
<br>
<br>
<b><spring:message code="syllabus.goal"/>:</b>
<jstl:out value="${goal}"></jstl:out>
<br>
<br>
<b><spring:message code="syllabus.prerrequisite"/>:</b>
<jstl:out value="${prerrequisite}"></jstl:out>
<br>
<br>
<b><spring:message code="syllabus.evaluationPolicy"/>:</b>
<jstl:out value="${evaluationPolicy}"></jstl:out>
<br>
<br>
<b><spring:message code="syllabus.biographyEntries"/>:</b>
<display:table name="bio" id="row" requestURI="requestUri" pagesize="5" class="displaytag" keepStatus="true">
	<acme:column code="syllabus.biographyEntries.title" property="title" />
	<acme:column code="syllabus.biographyEntries.author" property="author" />
	<acme:column code="syllabus.biographyEntries.locator" property="locator" />
	<acme:column code="syllabus.biographyEntries.url" property="url" />
</display:table>
<br>
<br>
<a href="syllabus/listBySubject.do"><spring:message code="syllabus.AllSyllabi" /></a>