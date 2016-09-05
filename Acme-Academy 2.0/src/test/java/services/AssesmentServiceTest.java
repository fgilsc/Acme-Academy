package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Assesment;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AssesmentServiceTest extends AbstractTest {
	
	//Service under Test------------------
	@Autowired
	private AssesmentService assesmentService;
	
	/*
	 * Lecturers may associate assessments with the deliverables...
	 */
	@Test
	public void findByDeliverable(){
		Collection<Assesment>ass = assesmentService.findByDeliverable(31);
		for(Assesment a : ass)
			System.out.println(a.getExplanation());
		
	}

}
