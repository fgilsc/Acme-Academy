package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.BiographyEntry;
import domain.Syllabus;

import services.BiographyEntryService;
import services.SyllabusService;

@Controller
@RequestMapping("/syllabus")
public class SyllabusController extends AbstractController {

	public SyllabusController() {
		super();
		
	}
	private Integer subjId;
	//MAnaged Services
	@Autowired
	private SyllabusService syllaServ;
	@Autowired
	private BiographyEntryService bioServ;
	//Detail
	//Listing
		@RequestMapping("/detail")
		public ModelAndView detail(@RequestParam Integer subjectId){
			subjId = subjectId;
			return createDetail(subjectId);
		}
		
		public ModelAndView createDetail(Integer subjectId){
			ModelAndView res = new ModelAndView();
			Collection<Syllabus>cs = syllaServ.getBySubject(subjectId);
			Syllabus s = syllaServ.getBySubjectCurrentYear(cs);
			if(s == null){
				res = new ModelAndView("syllabus/error");
			}else{
				//String message = "syllabus.detail.error";
				res = new ModelAndView("syllabus/detail");
				res.addObject("academicYear", s.getAcademicYear());
				res.addObject("summary", s.getSummary());
				res.addObject("goal", s.getGoal());
				res.addObject("prerrequisite", s.getPrerrequisite());
				res.addObject("evaluationPolicy", s.getEvaluationPolicy());
				Collection<BiographyEntry> bio = bioServ.getBySyllabus(s.getId());
				res.addObject("bio", bio);
			}
			return res;
		}
		
		@RequestMapping("/listBySubject")
		public ModelAndView listBySubject(){
			Collection<Syllabus>cs = syllaServ.getBySubject(subjId);
			ModelAndView res = new ModelAndView("syllabus/listBySubject");
			res.addObject("requestUri", "syllabus/listBySubject.do");
			res.addObject("syllabi", cs);
			return res;
		}

}
