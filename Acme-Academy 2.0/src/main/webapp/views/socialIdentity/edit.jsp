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

<form:form requestURI="${requestURI}" modelAttribute="socialIdentityForm">
	<div>
		
		<acme:textbox code="socialIdentity.socialPlatformName" path="socialPlatformName"/>
		<acme:textbox code="socialIdentity.nick" path="nick"/>
		<acme:textbox code="socialIdentity.homepage" path="homepage"/>
		<acme:textbox code="socialIdentity.email" path="email"/>
	</div>
	
	<input type="submit" name="save" value="<spring:message code="socialIdentity.save"/>" />
	&nbsp;
	<input type="button" name="cancel" value="<spring:message code="return"/>" onclick="javascript: window.location.replace('socialIdentity/list.do')"/>
</form:form>	