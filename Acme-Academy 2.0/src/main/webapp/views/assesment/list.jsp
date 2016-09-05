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


<display:table name="assesments" pagesize="5" class="displaytag" requestURI="${requestUri}" id="assesment">
	<display:column titleKey="assesment.rubric">
			<p>${assesment.rubric.explanation} (${assesment.rubric.percentage} %)</p>
	</display:column>
	<acme:column code="assesment.explanation" property="explanation" />
	<acme:column code="assesment.points" property="points" />  
</display:table>

<security:authorize access="hasRole('LECTURER')">
<input type="button" name="create" value="<spring:message code="assesment.create"/>" 
	onclick="javascript: window.location.replace('assesment/lecturer/create.do')"/>
</security:authorize>

<p><spring:message code="assesment.totalPercentge" /><b> ${totalPercentage}%</b></p> 
<p><spring:message code="assesment.deliverableMark" /><b> ${finalMark}</b></p> 