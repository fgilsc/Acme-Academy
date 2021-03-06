<%--
 * textbox.tag
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty"%>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%-- Attributes --%>


<%@ attribute name="code" required="true"%>
<%@ attribute name="property" required="true"%>




<%@ attribute name="sortable" required="false"%>

<jstl:if test="${sortable == null}">
	<jstl:set var="sortable" value="false" />
</jstl:if>

<%-- Definition --%>

<spring:message code="${code}" var="var" />
<display:column property="${property}" title="${var}"
	sortable="${sortable}" format="{0,date,dd/MM/yyyy HH:mm}"/>

