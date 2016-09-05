package controllers.lecturer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import services.AssigmentService;
import services.RubricService;

import controllers.AbstractController;

import domain.Assigment;
import domain.Rubric;

@Controller
@RequestMapping("/rubric/lecturer")
public class RubricLecturerController extends AbstractController {

	public RubricLecturerController() {
		super();
	}
	
	@Autowired
	private RubricService rubricService;
	@Autowired
	private AssigmentService assigmentService;
	
	//Listing 
	@RequestMapping(value ="/list", method= RequestMethod.GET)
	public ModelAndView list(){
		Collection<Rubric>rubs = rubricService.findByLecturer();
		ModelAndView res = new ModelAndView("rubric/list");
		res.addObject("rubrics", rubs);
		res.addObject("requestURI", "rubric/lecturer/list.do");
		return res;
	}
	
	
	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		Rubric r = rubricService.create();
		ModelAndView res = createEditModelAndView(r);
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Rubric r, BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = createEditModelAndView(r,"rubric.edit.commit.error");
		}else{
			Collection<Rubric>rubricasActualesAss = r.getAssigment().getRubrics();
			int sumActual = 0;
			for(Rubric rub : rubricasActualesAss){
				sumActual += rub.getPercentage();
			}
			int sumTotal = sumActual + r.getPercentage();
			if(sumTotal>100) {
				res = createEditModelAndView(r,"rubric.edit.error.bgt100");
			}else{
				try {
					rubricService.save(r);
					res = new ModelAndView("redirect:/rubric/lecturer/list.do");
				} catch (Throwable oops) {
					res = createEditModelAndView(r,"rubric.edit.commit.error");
				}
			}
		}
		return res;
	}
	
	// Ancillary methods --------------------------------------------
	protected ModelAndView createEditModelAndView(Rubric r) {
		return createEditModelAndView(r, null);
	}
	
	protected ModelAndView createEditModelAndView(Rubric r, String message) {
		ModelAndView res;
		Collection<Assigment> ass = assigmentService.findByLecturer();
		if(ass.isEmpty()){
			res = listModelAndView("rubric.edit.notAssignments");
		}else{			
		res = new ModelAndView("rubric/edit");
		res.addObject("assigments", ass);
		res.addObject("message", message);
		res.addObject("rubric", r);		
		}	
		return res;
	}
	
	protected ModelAndView listModelAndView(String message){
		Collection<Rubric>rubs = rubricService.findByLecturer();
		ModelAndView res = new ModelAndView("rubric/list");
		res.addObject("message", message);
		res.addObject("rubrics", rubs);
		res.addObject("requestURI", "rubric/lecturer/list.do");
		return res;
	}
		
	
}
