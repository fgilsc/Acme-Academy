package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Subject;

import services.SubjectService;

@Controller
@RequestMapping("/subject")
public class SubjectController extends AbstractController {
	
	//Managed Services
		@Autowired
		public SubjectService subjectService;
	//listing
		@RequestMapping("/list")
		public ModelAndView list(){
			Collection<Subject>	 c = subjectService.findAll();
			ModelAndView res = new ModelAndView("subject/list");
			res.addObject("subjects", c);
			return res;
		}
		
		@RequestMapping(value="/listByLecturer",method=RequestMethod.GET)
		public ModelAndView listByLecturer(@RequestParam int lecturerId){
			Collection<Subject> s = subjectService.findByLecturer(lecturerId);
			ModelAndView res = new ModelAndView("subject/listByLecturer");
			res.addObject("requestURI", "subject/listByLecturer.do");
			res.addObject("subjectsByLecturer", s);
			return res;
		}
		
		@RequestMapping(value="/listByLogin",method=RequestMethod.GET)
		public ModelAndView listByLogin(){
			Collection<Subject> s = subjectService.findByLoginNow();
			ModelAndView res = new ModelAndView("subject/listByLogin");
			res.addObject("requestURI","subject/listByLogin.do");
			res.addObject("subjectsByLecturer", s);
			return res;
		}
		
		

}
