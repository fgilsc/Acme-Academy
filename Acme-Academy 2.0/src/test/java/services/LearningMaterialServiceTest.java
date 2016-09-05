package services;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.LearningMaterial;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LearningMaterialServiceTest extends AbstractTest {
	
	//Based service test------
	@Autowired
	private LearningMaterialService lmService;
	
	
	/*
	 An actor who is authenticated as a lecturer must be able to:
		Upload learning materials and associate them with a group that he or she has created.
	 */
	@Test
	public void create(){
		authenticate("lecturer1");
		LearningMaterial lm = lmService.create();
		lm.setContent("https://www.drobox.com/gtvyb");
		lm.setKeywords(new ArrayList<String>());
		lm.setNotes(new ArrayList<String>());
		lm.setTitle("test");
		
		lmService.save(lm);
		
		for(LearningMaterial l : lmService.findByLecturer()){
			System.out.println(l.getTitle());
		}
		
	}
	/*
	 An actor who is authenticated as a student must be able to:
		1. Download the learning materials that are associated with the groups that he or she has enrolled.
	 */
	@Test
	public void findByStudent(){
		authenticate("student3");
		
		for(LearningMaterial l : lmService.findByStudent()){
			System.out.println(l.getTitle());
		}
		
	}
	/*
	 An actor who is authenticated as a lecturer must be able to:
		2. Download the learning materials in the groups that he or she has created.

	 */
	@Test
	public void findByGroup(){
		
		for(LearningMaterial l : lmService.findByGroup(24)){
			System.out.println(l.getTitle());
		}
	}
	
	/*
	 An actor who is authenticated as an administrator must be able to:
	1. Display a dashboard with the following information:
		The average number of learning materials per group.
	 */
	@Test
	public void  getAverageLearningMaterialsPerGroup(){
		System.out.println(lmService.getAverageLearningMaterialsPerGroup());
	}
	
	


}
