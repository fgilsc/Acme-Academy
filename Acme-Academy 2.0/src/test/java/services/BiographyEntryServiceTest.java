package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.BiographyEntry;
import domain.Syllabus;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BiographyEntryServiceTest extends AbstractTest {
	
	//Service based tes--------
		@Autowired
		private BiographyEntryService bioService;
		@Autowired
		private SyllabusService syllaService;
		
		/*
		 List the bibliography, including a counter that must indicate 
		 the number of subjects in which each bibliography entry is recommended. 
		 */
		@Test
		public void contadorBioperSubject(){
			authenticate("lecturer1"); // Esto es para poder modificar un syllabus añadiendo + bio
			Syllabus s = syllaService.findOne(41);
			s.getBiographyEntries().add(bioService.findOne(39));
			syllaService.update(s);
			Integer test = bioService.contadorBioPorAsignatura(39);
			System.out.println(test);
		}
		/*
		 List the lecturers, including a counter that must indicate the number of bibliography entries 
		 that he or she recommended in his or her subjects.
		 */
		@Test
		public void contadorBioperLecturer(){
			authenticate("lecturer1"); //Esto es para poder modificar un syllabus añadiendo + bio
			Syllabus s = syllaService.findOne(41);
			s.getBiographyEntries().add(bioService.findOne(40));
			syllaService.update(s);
			Integer test = bioService.counterBioByLecturer(15);
			System.out.println(test);
		}
		
		/*
		 Display a dashboard with the following information:
 			The average number of bibliography entries per syllabus.
		 */
		@Test
		public void AverageNumberBiographyEntriesPerSyllabus(){
			System.out.println(bioService.getAverageNumberBiographyEntriesPerSyllabus());
		}
		
		/*
		 The implementation of syllabi is not good enough. 
		 They have requested that the system must store the following information regarding them:
		 	the bibliography that is recommended
		 */
		@Test
		public void bioPerSyllabus(){
			Collection<BiographyEntry>bio = bioService.getBySyllabus(42);
			for(BiographyEntry b : bio){
				System.out.println(b.getTitle());
			}
		}

}
