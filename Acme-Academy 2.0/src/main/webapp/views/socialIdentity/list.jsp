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

<display:table name="socialIdentities" pagesize="5" class="displaytag" requestURI="${requestURI}" id="socialIdentity">
	<acme:column code="socialIdentity.socialPlatformName" property="socialPlatformName" />
	<acme:column code="socialIdentity.nick" property="nick" />
	<acme:column code="socialIdentity.homepage" property="homepage" />
	<acme:column code="socialIdentity.email" property="email" />
	
	<display:column title="${edit}">
		<spring:message code="socialIdentity.edit" var="edit"></spring:message>
		<a href="socialIdentity/edit.do?socialIdentityId=${socialIdentity.id}"> ${edit}</a>
	</display:column>
	<display:column title="${delete}">
		<spring:message code="socialIdentity.delete" var="delete"></spring:message>
		<a href="socialIdentity/delete.do?socialIdentityId=${socialIdentity.id}"> ${delete}</a>
	</display:column>	
	
</display:table>
<br>
<input type="button" name="create" value="<spring:message code="socialIdentity.create"/>" 
		onclick="javascript: window.location.replace('socialIdentity/create.do')"/>