package controllers.administrator;

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

import services.LecturerService;
import services.SubjectService;
import controllers.AbstractController;
import domain.Lecturer;
import domain.Subject;
import forms.SubjectForm;

@Controller
@RequestMapping("/subject/administrator")
public class SubjectAdministratorController extends AbstractController {
		
	//Managed Services
	@Autowired
	public SubjectService subjectService;
	@Autowired
	public LecturerService lecturerService;
	
	//Listing
	@RequestMapping("/list")
	public ModelAndView list(){
		Collection<Subject>s = subjectService.findAll();
		ModelAndView res = new ModelAndView("subject/list");
		res.addObject("subjects", s);
		return res;
	}
	
	
	//Creation
	@RequestMapping(value="/create", method= RequestMethod.GET)
	public ModelAndView create(){
		SubjectForm sf = new SubjectForm();
		ModelAndView res = createEditModelAndView(sf);
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Integer subjectId){
		ModelAndView res;
		Subject s = subjectService.findOne(subjectId);
		SubjectForm sf = null;
		try{
			sf = subjectService.constructForm(s);
			res = createEditModelAndView(sf);
		}catch(Throwable oops){
			res = createEditModelAndView(sf,"subject.commit.error");
		}
		return res;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid SubjectForm sf, BindingResult binding){
		ModelAndView res = null;
		Subject s;
		if (binding.hasErrors()) {
			res = createEditModelAndView(sf);
		}else{
			try{
				s = subjectService.recontruct(sf);
				subjectService.save(s);
				res = new ModelAndView("redirect:/subject/administrator/list.do");
			}catch(Throwable oops){
				if((sf.getCode().isEmpty()) || (sf.getTitle().isEmpty()) ){
					res = createEditModelAndView(sf,"subject.empty");
				}else{
					res = createEditModelAndView(sf,"subject.code.duplicated");
				}
			}
		}
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView update(@RequestParam Integer subjectId, @Valid SubjectForm sf, BindingResult binding){
		ModelAndView res = null;
		if (binding.hasErrors()) {
			res = createEditModelAndView(sf,"subject.commit.error");
		}else {
			try{
				Subject s = subjectService.recontructEdit(sf, subjectId);
				subjectService.update(s);
				res = new ModelAndView("redirect:/subject/administrator/list.do");
			}catch(Throwable oops){
				if((sf.getCode().isEmpty()) || (sf.getTitle().isEmpty()) ){
					res = createEditModelAndView(sf,"subject.empty");
				}
			}
		}
		return res;
	}
	
	@RequestMapping("/delete")		
	public ModelAndView delete(@RequestParam int subjectId){
		ModelAndView res = new ModelAndView();
		Subject s = subjectService.findOne(subjectId);
		try{
			Assert.notNull(s);
			subjectService.delete(s);
			res = new ModelAndView("redirect:/subject/administrator/list.do");
		}catch(Throwable oops){
			res = new ModelAndView("redirect:error.do");
		}
		return res;
	}
	//Error----------------------------------------------------------
	@RequestMapping("/error")
	public ModelAndView error(){
		return new ModelAndView("subject/error");
	}
	
	// Ancillary methods --------------------------------------------
	protected ModelAndView createEditModelAndView(SubjectForm sf){
		ModelAndView res = createEditModelAndView(sf,null);
		return res;
	}
	
	protected ModelAndView createEditModelAndView(SubjectForm sf, String message){
		ModelAndView res = new ModelAndView("subject/edit");
		Collection<Lecturer>l = lecturerService.findAll();
		res.addObject("subjectForm", sf);
		res.addObject("lecturers", l);
		res.addObject("message", message);
		return res;
	}
	
		
	
	
	

}
