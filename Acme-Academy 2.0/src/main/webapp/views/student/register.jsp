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

<script>
	function myFunction(str) {
     var patt = new RegExp("^([+][0-9]{1,2})?([0-9]{3})?(?:[0-9]{4,})");
     if(patt.test(str)==false){
    	 window.alert("<spring:message code="student.phone.confirm"/>");
     }	  
 	}
</script>


<form:form requestURI="${requestURI}" modelAttribute="studentForm">
	<fieldset>
		<legend align="left">
			<spring:message code="student.userAccount"/>
		</legend>
		<acme:textbox code="student.userAccount.username" path="username"/>
		<acme:password code="student.userAccount.password" path="password"/>
		<acme:password code="student.userAccount.repeatPassword" path="repeatPassword"/>
	</fieldset>
		
	<fieldset>
		<legend align="left">
			<spring:message code="student.personalInfo"/>
		</legend>
		<acme:textbox code="student.name" path="name"/>
		<acme:textbox code="student.surname" path="surname"/>
		<acme:textbox code="student.email" path="email"/>
	<%-- 	<acme:textbox code="student.phone" path="phone" /> --%>
		<spring:message code="student.phone" /><form:input path="phone" id="phoneId"/>
	</fieldset>
	
	<p><acme:checkbox code="student.acceptConditions" path="valid"/>
	<!-- <spring:message code="student.acceptConditions" /> -->
	<a href="legalTerms/legalTerms.do">
		<spring:message code="student.registration" />
	</a>
	
	<br>
	<br>
	
	
	
	
	<input type="submit" name="save" value="<spring:message code="save"/>" onclick="javascript: myFunction(this.form.phone.value) " />
	&nbsp;
	<input type="button" name="cancel" value="<spring:message code="return"/>" onclick="javascript: window.location.replace('')"/>
</form:form>

