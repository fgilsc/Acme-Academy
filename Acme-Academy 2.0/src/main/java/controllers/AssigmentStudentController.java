package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.Assigment;


import services.AssigmentService;

@Controller
@RequestMapping("/assigment/student")
public class AssigmentStudentController extends AbstractController {

	public AssigmentStudentController() {
		super();
	}

	//Managed Services
	@Autowired
	public AssigmentService assigmentService;
	
	//Listing
	@RequestMapping("/list")
	public ModelAndView list(){
		Collection <Assigment> loginHas = assigmentService.assigmentsByUserLogged();
		ModelAndView res= new ModelAndView("assigment/list");
		res.addObject("assigments", loginHas);
		res.addObject("requestUri", "assigment/student/list.do");
		return res;
	}
}
