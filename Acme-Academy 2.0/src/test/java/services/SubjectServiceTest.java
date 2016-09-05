package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Subject;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SubjectServiceTest extends AbstractTest {
	
	//Service based tes--------
	@Autowired
	private SubjectService subService;
	
	/*
	 An actor who is authenticated as a lecturer must be able to:
		1. List the subjects that he or she teaches.
	 */
	//@Test
	public void listLEcturer(){
		authenticate("lecturer3");
		Collection<Subject> s = subService.findByLoginNow();
		for(Subject sub : s){
			System.out.println(sub.getTitle());
		}
	}
	/*
	 An actor who is authenticated as an administrator must be able to:
		1. Display a dashboard with the following information:
			The subject that has more learning materials available.
	 */
	//@Test
	public void subjWithMoreLM(){
		System.out.println(subService.getSubjectWithMoreLearningMaterials().getTitle());
	}
	
	@Test 
	public void subjWithMoreLB(){
		System.out.println(subService.SubjectWithTheLargestBibliography().getTitle());
	}
	
	

}
