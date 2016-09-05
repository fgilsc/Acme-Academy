package services;


import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Assigment;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AssigmentServiceTest extends AbstractTest {
	
	//Service under Test------------------
	@Autowired
	private AssigmentService assigmentService;
	@Autowired 
	private SubjectService subjectService;
	@Autowired 
	private GroupService groupService;
	
	/*An actor who is authenticated as a lecturer must be able to: 
	 * Create an assignment regarding a subject and assign it to a group of students.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void create(){
		authenticate("lecturer1");
		Assigment a = assigmentService.create();
		Date deadline = new Date(2016,10,23,20,59);
		a.setDeadline(deadline);
		a.setTitle("test");
		a.setMark(20);
		a.setDescription("test");
		a.setSubject(subjectService.findOne(20));
		a.setGroup(groupService.findOne(26));
		
		assigmentService.save(a);
		for(Assigment ass : assigmentService.findAll()){
			System.out.println(ass.getTitle());
		}
		System.out.println(a.getDeadline());
		
	}
	// Students can list their assigments for upload a deliverable.
	@Test
	public void assigmentsByUserLogged(){
		authenticate("student2");
		Collection<Assigment> assBYL = assigmentService.assigmentsByUserLogged();
		
		for(Assigment ass : assBYL){
			System.out.println(ass.getTitle());
		}
	}
	
	//Lecturers can list assignments he has been created
	@Test
	public void assigmentsLecturer(){
		authenticate("lecturer1");
		Collection<Assigment> assBYL = assigmentService.findByLecturer();
		for(Assigment ass : assBYL){
			System.out.println(ass.getTitle());
		}
	}
	
	/*
	 An actor who is authenticated as an administrator must be able to:
		1. Display a dashboard with the following information:
 			The assignments that have 20% more or 20% less than the average number of rubrics per assignment.
	 */
	@Test
	public void dashboard(){
		Collection<Assigment> more = assigmentService.getAssigmentWithTwentyPercetMoreThanAverageNumberRubricPerAssigment();
		Collection<Assigment> less = assigmentService.getAssigmentWithTwentyPercetLessThanAverageNumberRubricPerAssigment();
		for(Assigment a : more){
			System.out.println("More20%: " + a.getTitle());
		}
		for(Assigment a : less){
			System.out.println("Less20%: " + a.getTitle());
		}
	}

}
