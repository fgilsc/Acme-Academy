package services;



import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Deliverable;

import forms.DeliverableForm;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class DeliverableServiceTest extends AbstractTest {
	
	//Sevices based Test
	@Autowired
	private DeliverableService deliService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private AssigmentService assigmentService;
	
	
	/*
	 An actor who is authenticated as a student must be able to:
		2. Create and upload a deliverable or a new version of a deliverable that was uploaded previously.
	 */
	@Test
	public void create(){
		authenticate("student3");
		DeliverableForm df = new DeliverableForm();
		df.setContent("https://www.dropbox.com/gthbtfh");
		Deliverable d = deliService.recontruct(df);
		d.setGroup(groupService.findOne(26));
		d.setAssigment(assigmentService.findOne(28));
		
		deliService.save(d);
		
		for(Deliverable del : deliService.findAll()){
			System.out.println(del.getContent()+ ", " + del.getMoment());
		}
		
		
	}
	
	//Student can list the deliverables their groups has uploaded
	@Test
	public void deliByStudent(){
		authenticate("student3");
		Collection<Deliverable> dels = deliService.deliByStudent();
		for(Deliverable del : dels)
			System.out.println(del.getContent()+ ", " + del.getMoment());
	}
	// Lecturers can list the deliverables from his asignments
	@Test
	public void getDeliverablesFromLecturerAssgigments(){
		authenticate("lecturer1");
		Collection<Deliverable> dels = deliService.getDeliverablesFromLecturerAssgigments();
		for(Deliverable del : dels)
			System.out.println(del.getContent()+ ", " + del.getMoment());
		
	}

}
