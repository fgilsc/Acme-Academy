package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Rubric;

import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RubricServiceTest extends AbstractTest {
	
	//Service under Test------------------
	@Autowired
	private RubricService rubricService;
	
	//Listing RubricsPerAssignment
	@Test
	public void rubricsPerAssignment(){
		Collection<Rubric> rr =  rubricService.findByAssigment(30);
		for(Rubric r : rr)
			System.out.println(r.getExplanation());
	}
	
	//Lecturers can list the rubrics he has created
		@Test
		public void rubricsPerLecturer(){
			authenticate("lecturer1");
			Collection<Rubric> rr =  rubricService.findByLecturer();
			for(Rubric r : rr)
				System.out.println(r.getExplanation());
		}
	
	
	

}
