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

<form:form action="learningMaterial/lecturer/edit.do"  modelAttribute="learningMaterial">
	<div>
		<form:hidden path="id" />
		<form:hidden path="version" />
		
		<form:hidden path="title" />
		<form:hidden path="notes"  />
		<form:hidden path="keywords"   />
		<form:hidden path="content" />
		
		<acme:select code="learningMaterial.group" path="group" items="${groups}" itemLabel="name" id="groups" />
		
		<form:hidden path="lecturer" />
	</div>
		
	<div>
		<acme:submit name="save" code="learningMaterial.edit.associate"/>
		
		<input type="button" name="cancel" value="<spring:message code="return"/>" onclick="javascript: window.location.replace('learningMaterial/lecturer/list.do')"/>
	</div>
</form:form>
</security:authorize>