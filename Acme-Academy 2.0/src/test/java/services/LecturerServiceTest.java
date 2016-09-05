package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Lecturer;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LecturerServiceTest extends AbstractTest {
	
	//Based service test------
		@Autowired
		private LecturerService lecturerService;
		
		
		/*
		 An actor who is authenticated as an administrator must be able to:
			2. Display a dashboard with the following information:
				The average number of subjects that a lecturer teaches.
		 */
		@Test
		public void getAverageSubjectPerLecturer(){
			authenticate("admin1");
			System.out.println(lecturerService.getAverageSubjectPerLecturer());
		}
		
		/*
		 An actor who is authenticated as an administrator must be able to:
			2. Display a dashboard with the following information:
				The lecturers who teach more than 20% or less than 20% the average num-ber of subjects.
		 */
		@Test
		public void findWMoreLess20perc(){
			authenticate("admin1");
			Collection<Lecturer> l1 = lecturerService.findWithLessTwentyPercentOfSubjectsAvg();
			Collection<Lecturer> l2 = lecturerService.findWithMoreTwentyPercentOfSubjectsAvg();
			
			for(Lecturer l : l1){
				System.out.println("Less: " + l);
			}
			
			for(Lecturer l : l2){
				System.out.println("More: " + l);
			}
		}
		
		/*
		 An actor who is not authenticated must be able to:
			3. List the subjects and navigate to the lecturers that teach them.
		 */
		@Test
		public void findBySubject(){
			Collection<Lecturer> l1 = lecturerService.findBySubject(20);
			for(Lecturer l : l1){
				System.out.println(l.getName() + " " + l.getSurname());
			}
		}
		/*
		 An actor who is authenticated as an administrator must be able to:
			1. Display a dashboard with the following information:
				The lecturers who upload more learning materials.
		 */
		@Test
		public void lecturersWithMoreLearningMaterials(){
			Collection<Lecturer> l1 = lecturerService.lecturersWithMoreLearningMaterials();
			for(Lecturer l : l1){
				System.out.println(l.getName() + " " + l.getSurname());
			}
		}
		/*
		 An actor who is authenticated as an administrator must be able to:
		1. Display a dashboard with the following information:
			The lecturer who s created more rubrics per assignment.
		 */
		@Test
		public void lecturerMoreRubricPerAssignment(){
			Collection<Lecturer> l1 = lecturerService.findByMoreRubricsPerAssigments();
			for(Lecturer l : l1){
				System.out.println(l.getName() + " " + l.getSurname());
			}
		}
		
		/*
		 An actor who is authenticated as an administrator must be able to:
		1. Display a dashboard with the following information:
			The lecturer who s created more rubrics per assignment.
		 */
		@Test
		public void lecturerLessRubricPerAssignment(){
			Collection<Lecturer> l1 = lecturerService.findByLessRubricsPerAssigments();
			for(Lecturer l : l1){
				System.out.println(l.getName() + " " + l.getSurname());
			}
		}
		
}
