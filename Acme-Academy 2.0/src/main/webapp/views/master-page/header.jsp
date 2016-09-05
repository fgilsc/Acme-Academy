<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a title="Acme-Academy Co., Inc." href="">
		<img src="images/logo.png" alt="Acme-Academy Co., Inc." />
	</a>	
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		
		
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
						<li> <a href="administrator/dashboard.do"><spring:message code="master.page.dashboard" /></a></li>		
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.subject" /></a>
				<ul>
					<li class="arrow"></li>
					<li> <a href="subject/administrator/list.do"><spring:message code="master.page.subject.list" /></a></li>
					<li> <a href="subject/administrator/create.do"><spring:message code="master.page.subject.create" /></a></li>	
				</ul>
			</li>
			<li> <a href="biographyEntry/administrator/list.do"><spring:message code="master.page.biogrpahyEntry.list" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv"><spring:message code="master.page.account" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="security/login.do"><spring:message code="master.page.login" /></a></li>
					<li><a href="student/register.do"><spring:message code="master.page.student.register" /></a></li>	
				</ul>
			</li>
				
		</security:authorize>
		
		<security:authorize access="hasRole('LECTURER')">
			<li><a class="fNiv"><spring:message	code="master.page.lecturer" /></a>
				<ul>
					<li class="arrow"></li>
						<%--Insert here  actions --%>
						
						<li> <a href="assigment/lecturer/create.do"><spring:message code="master.page.assigment.edit" /></a></li>
						<li> <a href="subject/listByLogin.do"><spring:message code="master.page.group.subjects" /></a></li>
								
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.learningMaterial" /></a>
				<ul>
					<li class="arrow"></li>
					<li> <a href="learningMaterial/lecturer/create.do"><spring:message code="master.page.learningMaterial.edit" /></a></li>
					<li> <a href="learningMaterial/lecturer/list.do"><spring:message code="master.page.learningMaterial.list" /></a></li>		
				</ul>
			</li>
		
		<li><a class="fNiv"><spring:message	code="master.page.group" /></a>
				<ul>
					<li class="arrow"></li>
					<li> <a href="group/create.do"><spring:message code="master.page.group.edit" /></a></li>
					<li> <a href="group/lecturer/list.do"><spring:message code="master.page.group.mylist" /></a></li>		
				</ul>
		</li>
		
		<li><a class="fNiv"><spring:message	code="master.page.syllabus" /></a>
				<ul>
					<li class="arrow"></li>
					<li> <a href="syllabus/lecturer/create.do"><spring:message code="master.page.syllabus.edit" /></a></li>
					<li> <a href="syllabus/lecturer/list.do"><spring:message code="master.page.syllabus.list" /></a></li>
					<li> <a href="syllabus/lecturer/listUnassociated.do"><spring:message code="master.page.syllabus.listUnassociated" /></a></li>		
				</ul>
		</li>
		<li> <a href="deliverable/lecturer/list.do"><spring:message code="master.page.deliverable.list" /></a></li>
		<li><a class="fNiv"><spring:message	code="master.page.rubric" /></a>
				<ul>
					<li class="arrow"></li>
					<li> <a href="rubric/lecturer/create.do"><spring:message code="master.page.rubric.edit" /></a></li>
					<li> <a href="rubric/lecturer/list.do"><spring:message code="master.page.rubric.list" /></a></li>		
				</ul>
		</li>
		</security:authorize>
		
		
		<security:authorize access="hasRole('STUDENT')">
			<li><a class="fNiv"><spring:message	code="master.page.student" /></a>
				<ul>
					<li class="arrow"></li>
						<%--Insert here  actions --%>
						<li> <a href="assigment/student/list.do"><spring:message code="master.page.assigment.list" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.group" /></a>
				<ul>
					<li class="arrow"></li>
					<li> <a href="group/list.do"><spring:message code="master.page.group.list" /></a></li>
					<li> <a href="group/myGroups.do"><spring:message code="master.page.group.mylist" /></a></li>	
				</ul>
			</li>
			<li> <a href="deliverable/list.do"><spring:message code="master.page.deliverable.list" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>				
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
			<li> <a class="fNiv" href="socialIdentity/list.do"><spring:message code="master.page.socialIdentity.list" /></a></li>
		</security:authorize>
		<li> <a class="fNiv" href="lecturer/list.do"><spring:message code="master.page.lecturer.list" /></a></li>
		<li> <a class="fNiv" href="subject/list.do"><spring:message code="master.page.subject.list" /></a></li>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

