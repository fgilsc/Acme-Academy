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

<b><spring:message code="dashboard.number.students.avg"/></b>
<jstl:out value="${avgDouble}"></jstl:out>
<br>
<br>

<b><spring:message code="dashboard.student.moreTwenty"/></b>
<display:table name="cmore" id="row" requestURI="administrator/dashboard.do" pagesize="5" class="displaytag" keepStatus="true">
	<acme:column code="actor.name" property="name" />
	<acme:column code="actor.surname" property="surname" />
	<acme:column code="actor.email" property="email" />
</display:table>
<br>
<br>

<b><spring:message code="dashboard.student.lessTwenty"/></b>
<display:table name="cless" id="row" requestURI="administrator/dashboard.do" pagesize="5" class="displaytag" keepStatus="true">
	<acme:column code="actor.name" property="name" />
	<acme:column code="actor.surname" property="surname" />
	<acme:column code="actor.email" property="email" />
</display:table>
<br>
<br>

<b><spring:message code="dashboard.number.subject.avg.lecturer"/></b>
<jstl:out value="${avgDoubleLecturer}"></jstl:out>
<br>
<br>

<b><spring:message code="dashboard.lecturer.moreTwenty"/></b>
<display:table name="lectMore" id="row" requestURI="administrator/dashboard.do" pagesize="5" class="displaytag" keepStatus="true">
	<acme:column code="actor.name" property="name" />
	<acme:column code="actor.surname" property="surname" />
	<acme:column code="actor.email" property="email" />
</display:table>
<br>
<br>

<b><spring:message code="dashboard.lecturer.lessTwenty"/></b>
<display:table name="lectLess" id="row" requestURI="administrator/dashboard.do" pagesize="5" class="displaytag" keepStatus="true">
	<acme:column code="actor.name" property="name" />
	<acme:column code="actor.surname" property="surname" />
	<acme:column code="actor.email" property="email" />
</display:table>
<br>
<br>

<b><spring:message code="dashboard.number.group.avg.student"/></b>
<jstl:out value="${avgDoubleStudentGroup}"></jstl:out>
<br>
<br>

<b><spring:message code="dashboard.group.moreTwenty"/></b>
<display:table name="grMore" id="row" requestURI="administrator/dashboard.do" pagesize="5" class="displaytag" keepStatus="true">
	<acme:column code="group.name" property="name" />
	<acme:column code="group.academicYear" property="academicYear" />
</display:table>
<br>
<br>

<b><spring:message code="dashboard.group.lessTwenty"/></b>
<display:table name="grLess" id="row" requestURI="administrator/dashboard.do" pagesize="5" class="displaytag" keepStatus="true">
	<acme:column code="group.name" property="name" />
	<acme:column code="group.academicYear" property="academicYear" />
</display:table>
<br>
<br>

<b><spring:message code="dashboard.lecturer.maxLearningMaterials"/></b>
<display:table name="lLMax" id="row" requestURI="administrator/dashboard.do" pagesize="5" class="displaytag" keepStatus="true">
	<acme:column code="actor.name" property="name" />
	<acme:column code="actor.surname" property="surname" />
	<acme:column code="actor.email" property="email" />
</display:table>
<br>
<br>

<b><spring:message code="dashboard.number.group.avg.learningMaterial"/></b>
<jstl:out value="${avgDoubleLearningMaterialGroup}"></jstl:out>
<br>
<br>

<b><spring:message code="dashboard.subjectWithMoreLearningMat"/></b>
<jstl:out value="${subjWithMoreLM}"></jstl:out>
<br>
<br>

<b><spring:message code="dashboard.number.actor.avg.socialIdentity"/></b>
<jstl:out value="${avgDoubleSocialIdActor}"></jstl:out>
<br>
<br>

<b><spring:message code="dashboard.number.syllabys.avg.subject"/></b>
<jstl:out value="${avgSyllabusSubject}"></jstl:out>
<br>
<br>

<b><spring:message code="dashboard.number.biographyEntry.avg.syllabus"/></b>
<jstl:out value="${avgBioEntrySyllabus}"></jstl:out>
<br>
<br>

<b><spring:message code="dashboard.subjectWithLongestBiography"/></b>
<jstl:out value="${subjectWithMoreBiographyEntry}"></jstl:out>
<br>
<br>

<b><spring:message code="dashboard.assigment.moreThantwentyPErcentAvgNumberRubric"/></b>
<display:table name="assmore" id="row" requestURI="administrator/dashboard.do" pagesize="5" class="displaytag" keepStatus="true">
	<acme:column code="assigment.title" property="title" />
	<acme:column code="assigment.description" property="description" />
	<acme:column code="assigment.mark" property="mark" />
	<acme:column code="assigment.openingTime" property="openingTime" />
	<acme:column code="assigment.deadline" property="deadline" />
</display:table>
<br>
<br>

<b><spring:message code="dashboard.assigment.lessThantwentyPErcentAvgNumberRubric"/></b>
<display:table name="assless" id="row" requestURI="administrator/dashboard.do" pagesize="5" class="displaytag" keepStatus="true">
	<acme:column code="assigment.title" property="title" />
	<acme:column code="assigment.description" property="description" />
	<acme:column code="assigment.mark" property="mark" />
	<acme:column code="assigment.openingTime" property="openingTime" />
	<acme:column code="assigment.deadline" property="deadline" />
</display:table>
<br>
<br>

<b><spring:message code="dashboard.lecturer.moreRubricsPerAssignment"/></b>
<jstl:out value="${lecturerWithMoreTubricsAss}"></jstl:out>
<br>
<br>

<b><spring:message code="dashboard.lecturer.lessRubricsPerAssignment"/></b>
<jstl:out value="${lecturerWithLessTubricsAss}"></jstl:out>
<br>
<br>