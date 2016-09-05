package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Student;

import forms.StudentForm;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class StudentServiceTest extends AbstractTest {
	
	//Services based Test
	@Autowired
	private StudentService studentService;
	
	/*
	 An actor who is not authenticated must be able to:
		1. Register to the system as a student.
	 */
	@Test
	public void register(){
		StudentForm sf = new StudentForm();
		sf.setEmail("test@test.com");
		sf.setName("Test");
		sf.setPassword("test");
		sf.setRepeatPassword("test");
		sf.setPhone("+95(422)7057");
		sf.setSurname("test");
		sf.setUsername("test1");
		sf.setValid(true);
		Student s = studentService.reconstruct(sf);
		studentService.save(s);
		for(Student stu : studentService.findAll()){
			System.out.println(stu.getName() +" " + stu.getSurname());
		}	
	}
	
		@Test(expected = Exception.class)
		public void registerWrongPassword(){
			StudentForm sf = new StudentForm();
			sf.setEmail("test@test.com");
			sf.setName("Test");
			sf.setPassword("test");
			sf.setRepeatPassword("tesT");
			sf.setPhone("+95(422)7057");
			sf.setSurname("test");
			sf.setUsername("test1");
			sf.setValid(true);
			Student s = studentService.reconstruct(sf);
			studentService.save(s);
			for(Student stu : studentService.findAll()){
				System.out.println(stu.getName() +" " + stu.getSurname());
			}	
		}
	
		@Test(expected = Exception.class)
		public void registerNotUnique(){
			StudentForm sf = new StudentForm();
			sf.setEmail("test@test.com");
			sf.setName("Test");
			sf.setPassword("test");
			sf.setRepeatPassword("test");
			sf.setPhone("+95(422)7057");
			sf.setSurname("test");
			sf.setUsername("student1");
			sf.setValid(true);
			Student s = studentService.reconstruct(sf);
			studentService.save(s);
			for(Student stu : studentService.findAll()){
				System.out.println(stu.getName() +" " + stu.getSurname());
			}	
		}
		
		/*
		 An actor who is authenticated as an administrator must be able to:
			2. Display a dashboard with the following information:
 				The students who have enrolled more than 20% or less than 20% the aver-age number of subjects.
		 */
		@Test
		public void moreLEss(){
			Collection<Student>more = studentService.getStudentWithEnrolledMore20percentAvgSubject();
			Collection<Student>less = studentService.getStudentWithEnrolledLess20percentAvgSubject();
			
			for(Student s :more){
				System.out.println("More: " + s.getName() +" " + s.getSurname());
			}
			for(Student s :less){
				System.out.println("Less: " +s.getName() +" " + s.getSurname());
			}
		}
		
		/*
		 An actor who is authenticated as an administrator must be able to:
			2. Display a dashboard with the following information:
				The average number of students per subject.
		 */
		@Test
		public void avg(){
			authenticate("admin1");
			System.out.println(studentService.getStudentsAvgPerSubject());
		}
	

}
