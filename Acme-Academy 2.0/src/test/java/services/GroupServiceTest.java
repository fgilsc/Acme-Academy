package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Group;

import forms.GroupForm;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class GroupServiceTest extends AbstractTest {
	
	//BAsed services test---
	@Autowired
	private GroupService groupService;
	@Autowired
	private SubjectService subjectService;
	
	/*
	 An actor who is authenticated as a lecturer must be able to:
		2. Create a group of students and associate it with a subject.
	 */
	@Test
	public void create(){
		authenticate("lecturer3");
		GroupForm gf = new GroupForm();
		gf.setAcademicYear(2017);
		gf.setDescription("Test");
		gf.setName("test");
		gf.setSubject(subjectService.findOne(21));
		Group g = groupService.recontruct(gf);
		
		groupService.save(g);
		
		for(Group gr : groupService.findAll()){
			System.out.println(gr.getName());
		}
		
	}
	/*
	 An actor who is authenticated as an administrator must be able to:
		2. Display a dashboard with the following information:
			 The average number of students per group.
	 */
	@Test
	public void getAverageStudentsPerGroup(){
		double d = groupService.getAverageStudentsPerGroup();
		System.out.println(d);
	}
	
	/*
	 An actor who is authenticated as an administrator must be able to:
		2. Display a dashboard with the following information:
			The groups to which more than 20% or less than 20% the average number of students have enrolled.
	 */
	@Test
	public void findGroups(){
		Collection<Group>g1 = groupService.findGroupsWithLessThanTwentyPercentOfAvgStudentsPerGroup();
		Collection<Group>g2 = groupService.findGroupsWithMoreThanTwentyPercentOfAvgStudentsPerGroup();
		
		for(Group g : g1){
			System.out.println("Less: " +g.getName());
		}
		
		for(Group g : g2){
			System.out.println("More: " +g.getName());
		}
	}
	
	//Lecturer can listing the groups that he/she has created
	@Test
	public void findByLecturerLogin(){
		authenticate("lecturer2");
		
		for(Group g  : groupService.findByLecturerLogin()){
			System.out.println(g.getName());;
		}
	}
	//Student can listing the groups that he/she has created
	@Test
	public void findByStudentLogin(){
		authenticate("student3");
		
		for(Group g  : groupService.findByStudentLogin()){
			System.out.println(g.getName());;
		}
	}
	/*
	 An actor who is authenticated as a student must be able to:
		1. Enrol a group of students so that he or she becomes a member of that group.
	 */
	@Test
	public void enrol(){
		authenticate("student2");
		groupService.enrol(groupService.findOne(24));
		
		
		
		for(Group g  : groupService.findByStudentLogin()){
			System.out.println(g.getName());;
		}
	}
	

}
