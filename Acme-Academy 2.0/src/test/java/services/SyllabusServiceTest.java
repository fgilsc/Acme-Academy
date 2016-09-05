package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Syllabus;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SyllabusServiceTest extends AbstractTest {
	
	//Service based tes--------
	@Autowired
	private SyllabusService syllabusService;
	
	//Lecturer logged can list his syllabi
	@Test
	public void findByLecturerLogin(){
		authenticate("lecturer2");
		Collection<Syllabus>s = syllabusService.findByLecturerLogin();
		for(Syllabus sy :s)
			System.out.println(sy.getSummary());
	}
	
	//Lecturer logged can list his  unassociated syllabi to associate later.
		@Test
		public void findUnassociated(){
			authenticate("lecturer2");
			Collection<Syllabus>s = syllabusService.findUnAssociated();
			for(Syllabus sy :s)
				System.out.println(sy.getSummary());
		}
		
		/*
		 Wherever a subject is displayed, the system must allow to display its syllabi; by default, 
		 the syllabus that corresponds to the current academic year must be shown, 
		 but the user must be able to navigate to the others.
		 */
		@Test
		public void findBySubject(){
			Collection<Syllabus>s = syllabusService.getBySubject(20);
			for(Syllabus sy :s)
				System.out.println(sy.getSummary());
		}
		@Test
		public void findBySubjectcurrentYear(){
			Collection<Syllabus>cs = syllabusService.getBySubject(20);
			Syllabus s = syllabusService.getBySubjectCurrentYear(cs);
			System.out.println(s.getSummary());
		}
		
		//Listing the syllabus that includes one biographyEntry
		@Test
		public void getSyllabyPerBio(){
			Collection<Syllabus>cs = syllabusService.getSyllabyPerBio(39);
			for(Syllabus sy :cs)
				System.out.println(sy.getSummary());
			
		}
		
		/*
		 [..] Display a dashboard with the following information:
				The average number of syllabi per subject.
		 */
		@Test
		public void getAverageNumberSyllabiPerSubject(){
			System.out.println(syllabusService.getAverageNumberSyllabiPerSubject());
		}
		

}
