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

<security:authorize access="hasRole('ADMIN')">

<form:form requestURI="${requestURI}" modelAttribute="subjectForm">
	<div>
	
	<acme:textbox code="subject.code" path="code"/>
	<acme:textbox code="subject.title" path="title" />
	
	
	<acme:multiCheckbox path="lecturers" items="${lecturers}" itemLabel="name"/>
	
	</div>
	
	<input type="submit" name="save" value="<spring:message code="subject.save"/>" />
&nbsp;
<input type="button" name="cancel" value="<spring:message code="return"/>" onclick="javascript: window.location.replace('subject/administrator/list.do')"/>
</form:form>



</security:authorize>