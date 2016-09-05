package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.AssigmentService;
import services.BiographyEntryService;
import services.GroupService;
import services.LearningMaterialService;
import services.LecturerService;
import services.SocialIdentityService;
import services.StudentService;
import services.SubjectService;
import services.SyllabusService;

import controllers.AbstractController;
import domain.Assigment;
import domain.Group;
import domain.Lecturer;
import domain.Student;
import domain.Subject;


@Controller
@RequestMapping("/administrator")
public class DashboardAdministratorController extends AbstractController {

	public DashboardAdministratorController() {
		super();
	}
	
	//Managed Services
	@Autowired
	private StudentService studentService;
	@Autowired
	private LecturerService lecturerService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private LearningMaterialService learningMaterialService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private SocialIdentityService socialIdentityService;
	@Autowired
	private SyllabusService syllabusService;
	@Autowired
	private BiographyEntryService biographyEntryService;
	@Autowired
	private AssigmentService assigmentService;
	
	//Listing
	@RequestMapping("/dashboard")
	public ModelAndView dashboard(){
		ModelAndView res = createDashboard();
		return res;
	}
	
	public ModelAndView createDashboard(){
		// Llamada a Servicos
		double avg;
		try{
			avg = studentService.getStudentsAvgPerSubject();
		}catch(Exception ex){
			avg = 0.0;
		}
		
		Collection<Student> cmore = studentService.getStudentWithEnrolledMore20percentAvgSubject();
		Collection<Student> cless = studentService.getStudentWithEnrolledLess20percentAvgSubject();
		double avgDoubleLecturer; 
		try{
			avgDoubleLecturer = lecturerService.getAverageSubjectPerLecturer();
		}catch(Exception ex){
			avgDoubleLecturer = 0.0;
		}
		
		Collection<Lecturer> lectMore = lecturerService.findWithMoreTwentyPercentOfSubjectsAvg();
		Collection<Lecturer> lectLess = lecturerService.findWithLessTwentyPercentOfSubjectsAvg();
		double avgDoubleStudentGroup; 
		try{
			avgDoubleStudentGroup= groupService.getAverageStudentsPerGroup();
		}catch(Exception ex){
			avgDoubleStudentGroup = 0.0;
		}
		Collection<Group>grMore = groupService.findGroupsWithMoreThanTwentyPercentOfAvgStudentsPerGroup();
		Collection<Group>grLess	= groupService.findGroupsWithLessThanTwentyPercentOfAvgStudentsPerGroup();
		Collection<Lecturer>lLMax = lecturerService.lecturersWithMoreLearningMaterials();
		double avgDoubleLearningMaterialGroup;
		try{
			avgDoubleLearningMaterialGroup = learningMaterialService.getAverageLearningMaterialsPerGroup();
		}catch(Exception ex){
			avgDoubleLearningMaterialGroup = 0.0;
		}
		Subject subjWithMoreLM = subjectService.getSubjectWithMoreLearningMaterials();
		String swmlm;
		if(subjWithMoreLM == null){
			swmlm = "-";
		}else{
			swmlm = subjWithMoreLM.getTitle();
		}	
		double avgDoubleSocialIdActor ;
		try{
			avgDoubleSocialIdActor = socialIdentityService.getAverageSocialIdentitiesPerActor();
		}catch(Exception ex){
			avgDoubleSocialIdActor = 0.0;
		}
		double avgSyllabusSubject;
		try{
			avgSyllabusSubject = syllabusService.getAverageNumberSyllabiPerSubject();
		}catch(Exception ex){
			avgSyllabusSubject = 0.0;
		}
		double avgBioEntrySyllabus;
		try{
			avgBioEntrySyllabus = biographyEntryService.getAverageNumberBiographyEntriesPerSyllabus();
		}catch(Exception ex){
			avgBioEntrySyllabus = 0.0;
		}
		Subject subjectWithMoreBiographyEntry = subjectService.SubjectWithTheLargestBibliography();
		String nill = "null";
		Collection<Assigment>assmore = assigmentService.getAssigmentWithTwentyPercetMoreThanAverageNumberRubricPerAssigment();
		Collection<Assigment>assless = assigmentService.getAssigmentWithTwentyPercetLessThanAverageNumberRubricPerAssigment();
		Collection<Lecturer> lecturerWithMoreTubricsAss = lecturerService.findByMoreRubricsPerAssigments();
		Collection<String>lecturerWithMoreTubricsAssStr = new ArrayList<String>();
		Collection<Lecturer> lecturerWithLessTubricsAss = lecturerService.findByLessRubricsPerAssigments();
		Collection<String>lecturerWithLessTubricsAssStr = new ArrayList<String>();
		for(Lecturer l : lecturerWithMoreTubricsAss){
			String ls = l.getName() + " " + l.getSurname();
			lecturerWithMoreTubricsAssStr.add(ls);
		}
		for(Lecturer l : lecturerWithLessTubricsAss){
			String ls = l.getName() + " " + l.getSurname();
			lecturerWithLessTubricsAssStr.add(ls);
		}
		//------
		ModelAndView res= new ModelAndView("administrator/dashboard");
		res.addObject("avgDouble",avg);
		res.addObject("cmore",cmore);
		res.addObject("cless",cless);
		res.addObject("avgDoubleLecturer",avgDoubleLecturer);
		res.addObject("lectMore", lectMore);
		res.addObject("lectLess", lectLess);
		res.addObject("avgDoubleStudentGroup", avgDoubleStudentGroup);
		res.addObject("grMore", grMore);
		res.addObject("grLess", grLess);
		res.addObject("lLMax",lLMax);
		res.addObject("avgDoubleLearningMaterialGroup", avgDoubleLearningMaterialGroup);
		res.addObject("subjWithMoreLM",swmlm);
		res.addObject("avgDoubleSocialIdActor", avgDoubleSocialIdActor);
		res.addObject("avgSyllabusSubject", avgSyllabusSubject);
		res.addObject("avgBioEntrySyllabus", avgBioEntrySyllabus);
		if(subjectWithMoreBiographyEntry == null){
			res.addObject("subjectWithMoreBiographyEntry",nill);
		}else{
			res.addObject("subjectWithMoreBiographyEntry", subjectWithMoreBiographyEntry.getTitle());}
		res.addObject("assmore",assmore);
		res.addObject("assless",assless);
		res.addObject("lecturerWithMoreTubricsAss",lecturerWithMoreTubricsAssStr);
		res.addObject("lecturerWithLessTubricsAss",lecturerWithLessTubricsAssStr);
		
		return res;
	}
	
	


}
