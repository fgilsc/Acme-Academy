<%--
 * footer.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="date" class="java.util.Date" />

<hr />

<b>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> Acme-Academy Co., Inc.</b>

<br>

<b><a href="legalTerms/legalTerms.do"><spring:message
			code="master.page.legalTerms" /></a></b>
<b><a href="legalTerms/cookies.do"><spring:message
			code="master.page.cookies" /></a></b>
<b><a href="legalTerms/legalInformation.do"><spring:message
			code="master.page.legalInformation" /></a></b>
<b><a href="legalTerms/personalData.do"><spring:message
			code="master.page.personalData" /></a></b>