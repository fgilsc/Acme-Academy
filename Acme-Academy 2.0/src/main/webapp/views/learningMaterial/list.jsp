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

<display:table name="learningMaterials" pagesize="5" class="displaytag" requestURI="${requestURI}" id="learningMaterial">
	<acme:column code="learningMaterial.title" property="title" />
	<acme:column code="learningMaterial.notes" property="notes" />
	<acme:column code="learningMaterial.keywords" property="keywords" />
	<acme:column code="learningMaterial.content" property="content" />
	
	
	<security:authorize access="hasRole('LECTURER')">
	<display:column>
		<input type="button" name="associate" value="<spring:message code="learningMaterial.associate"/>" 
			onclick="javascript: window.location.replace('learningMaterial/lecturer/associate.do?learningMaterialId=${learningMaterial.id}')"/>
	</display:column>
	</security:authorize>
</display:table>
	
<security:authorize access="hasRole('LECTURER')">
<input type="button" name="updload" value="<spring:message code="learningMaterial.create"/>" 
	onclick="javascript: window.location.replace('learningMaterial/lecturer/create.do')"/>
</security:authorize>

