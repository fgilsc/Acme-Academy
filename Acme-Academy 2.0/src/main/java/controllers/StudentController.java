package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Student;
import forms.StudentForm;

import security.UserAccountRepository;
import services.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController extends AbstractController {

	
	
	// Constructors -----------------------------------------------------------
	public StudentController() {
		super();
	}
	
	//Managed Services
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	//Register
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView create(){
		StudentForm sf = new StudentForm();
		ModelAndView res = createEditModelAndView(sf);
		return res;
	}
	@RequestMapping(value="/register", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid StudentForm sf, BindingResult binding){
		ModelAndView res;
		Student s;
		if(binding.hasErrors()){
			res = createEditModelAndView(sf);
		}else{
			try{
				s = studentService.reconstruct(sf);
				studentService.save(s);
				res = new ModelAndView("redirect:../");
			}catch(Throwable oops){
				if(userAccountRepository.findByUsername(sf.getUsername())!=null){
					res = createEditModelAndView(sf,"student.duplicated");
				}else if(!sf.getValid()){
					res = createEditModelAndView(sf,"student.conditionsNotAcepted");
				}else{
					res = createEditModelAndView(sf,"student.invalid.password");
				}
			}
		}
		return res;
	}
	
	//Ancillary methods
	protected ModelAndView createEditModelAndView(StudentForm sf){
		ModelAndView res = createEditModelAndView(sf,null);
		return res;
	}
	
	protected ModelAndView createEditModelAndView(StudentForm sf,String message){
		String requestURI = "student/register.do";
		ModelAndView res = new ModelAndView("student/register");
		res.addObject("studentForm", sf);
		res.addObject("message", message);
		res.addObject("requestURI", requestURI);
		return res;
	}
	
}
