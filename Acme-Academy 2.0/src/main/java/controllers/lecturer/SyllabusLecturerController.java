package controllers.lecturer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import services.BiographyEntryService;
import services.SubjectService;
import services.SyllabusService;

import controllers.AbstractController;



import domain.Subject;
import domain.Syllabus;

@Controller
@RequestMapping("/syllabus/lecturer")
public class SyllabusLecturerController extends AbstractController {

	public SyllabusLecturerController() {
		super();
		
	}
	
	//MAnaged Services
	@Autowired
	private SyllabusService syllaServ;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private BiographyEntryService bioService;
	
	//Creating & Editing
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		Syllabus s = syllaServ.create();
		ModelAndView res = createEditModelAndView(s);
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Integer syllabusId){
		Syllabus s = syllaServ.findOne(syllabusId);
		Assert.notNull(s);
		return createEditModelAndView(s);
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Syllabus s, BindingResult binding) {
		ModelAndView res;
		Collection<String> goal = s.getGoal();
		for(String g : goal){
			if(g.contains(";")){
				String[] partes = g.split(";");
				for(int i=0;i<partes.length;i++){
					goal.add(partes[i]);
				}
				goal.remove(g);
			}
		}
		Collection<String>prerrequisite = s.getPrerrequisite();
		for(String p : prerrequisite){
			if(p.contains(";")){
				String[] partes = p.split(";");
				for(int i=0;i<partes.length;i++){
					prerrequisite.add(partes[i]);
				}
				prerrequisite.remove(p);
			}
		}
		if (binding.hasErrors()) {
			res = createEditModelAndView(s,"syllabus.edit.commit.error");
		}else {
			try {
				/*if(s.getSubject()!=null){
					s.setSubject(s.getSubject());
				}*/
				syllaServ.save(s);
				res = new ModelAndView("redirect:/syllabus/lecturer/list.do");
			}catch(Throwable oops){
				res = createEditModelAndView(s,"syllabus.edit.commit.error");
			}
		}
		return res;
	}
	
	
	//Listing 
	@RequestMapping(value ="/list", method= RequestMethod.GET)
	public ModelAndView list(String message){
		Collection<Syllabus> s = syllaServ.findByLecturerLogin();
		ModelAndView res = new ModelAndView("syllabus/list");
		res.addObject("syllabi", s);
		res.addObject("message",message);
		res.addObject("requestUri", "syllabus/lecturer/list.do");
		return res;
	}
	
	@RequestMapping(value ="/listUnassociated", method= RequestMethod.GET)
	public ModelAndView listUnassociated(){
		Collection<Syllabus> s = syllaServ.findUnAssociated();
		ModelAndView res = new ModelAndView("syllabus/listUnassociated");
		res.addObject("requestUri", "syllabus/lecturer/listUnnassociated.do");
		 res.addObject("syllabi", s);
		 return res;
	}
	
	//Associate
	@RequestMapping(value="/associate", method = RequestMethod.GET)
	public ModelAndView associate(@RequestParam Integer syllabusId){
		Syllabus s = syllaServ.findOne(syllabusId);
		return associateModelAndView(s,null);
	}
	
	@RequestMapping(value = "/associate", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAssociation(@Valid Syllabus s, BindingResult binding) {
		ModelAndView res = null;
		Collection<Syllabus>sySubjt = syllaServ.getBySubject(s.getSubject().getId());
		if (binding.hasErrors()) {
			res = createEditModelAndView(s,"syllabus.edit.commit.error");	
		}else if(sySubjt.isEmpty()){
			for(Syllabus sy : sySubjt ){
				if(sy.getSubject().equals(s.getSubject()) && sy.getAcademicYear() == s.getAcademicYear()){
					res = createEditModelAndView(s,"syllabus.edit.commit.error2");	
				}else {
					try {
						syllaServ.update(s);
						res = new ModelAndView("redirect:/syllabus/lecturer/listUnassociated.do"); 
					}catch (Throwable oops) {
						
						res = createEditModelAndView(s,"syllabus.edit.commit.error");
					}	
				} 
			}
		}else{
			try {
				syllaServ.update(s);
				res = new ModelAndView("redirect:/syllabus/lecturer/listUnassociated.do"); 
			}catch (Throwable oops) {
				
				res = createEditModelAndView(s,"syllabus.edit.commit.error");
			}
		}
		return res;
	}
	//Deleting
	@RequestMapping("/delete")		
	public ModelAndView delete(@RequestParam int syllabusId){
		ModelAndView res = new ModelAndView();
		Syllabus s = syllaServ.findOne(syllabusId);
		try{
			Assert.notNull(s);
			syllaServ.delete(s);
			res = new ModelAndView("redirect:/syllabus/lecturer/list.do");
		}catch(Throwable oops){
			String message = "syllabus.edit.commit.error";
			res = list(message);
		}
		return res;
	}
	 
	
	
	//Ancillary Methods
	protected ModelAndView createEditModelAndView(Syllabus s) {
		return createEditModelAndView(s, null);
	}
	
	protected ModelAndView createEditModelAndView(Syllabus s, String message){
		ModelAndView  res = new ModelAndView("syllabus/edit");
		res.addObject("biographyEntries", bioService.findAll());
		res.addObject("message", message);
		res.addObject("syllabus", s);		
		return res;
	}
	
	protected ModelAndView associateModelAndView(Syllabus s, String message){
		 ModelAndView  res = new ModelAndView("syllabus/associate"); 
		 Collection<Subject>subj = subjectService.findByLoginNow();
		 res.addObject("subjects", subj);
		 res.addObject("message", message);
		 res.addObject("syllabus", s);
		 return res;
	 }
	
}
