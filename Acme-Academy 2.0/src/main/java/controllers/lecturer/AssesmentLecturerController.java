package controllers.lecturer;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AssesmentService;
import services.DeliverableService;
import services.RubricService;

import controllers.AbstractController;
import domain.Assesment;

import domain.Rubric;


@Controller
@RequestMapping("/assesment/lecturer")
public class AssesmentLecturerController extends AbstractController {

	public AssesmentLecturerController() {
		super();
		
	}
	//Managed Services
		@Autowired
		private AssesmentService assesmentService;
		@Autowired
		private DeliverableService deliService;
		@Autowired
		private RubricService rubricService;
	
	
	private int deliId;	
	
		//Listing 
		@RequestMapping(value ="/list", method= RequestMethod.GET)
		public ModelAndView list(@RequestParam Integer deliverableId){
			deliId = deliverableId;
			Collection<Assesment> ass = assesmentService.findByDeliverable(deliverableId);
			double points = 0.0;
			double pointsAssRub = 0.0;
			for(Assesment a : ass){
				pointsAssRub = a.getPoints() * a.getRubric().getPercentage()/100.0;
				points = points + pointsAssRub;
			} 
			Integer totalPoints = deliService.findOne(deliverableId).getAssigment().getMark();
			double division = (double) (points/100.0);
			double finalMark = division * totalPoints;
			Double finalMarkD = finalMark;
			String finalMarkStr = finalMarkD.toString() + " / " + totalPoints +" points.";
			ModelAndView res = new ModelAndView("assesment/list");
			res.addObject("assesments", ass);
			res.addObject("requestURI", "assesment/lecturer/list.do");
			res.addObject("finalMark",finalMarkStr);
			res.addObject("totalPercentage",points);
			return res;
		}
		
		// Creation ---------------------------------------------------------------
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(){
			Assesment a = assesmentService.create();
			ModelAndView res;
			
			int rubricasNum = deliService.findOne(deliId).getAssigment().getRubrics().size();
			Collection<Assesment> ass = assesmentService.findByDeliverable(deliId);
			int correcciones = ass.size();
			
			Collection<Rubric>rubricas = deliService.findOne(deliId).getAssigment().getRubrics();
			int sumaPercentRubric = 0;
			for(Rubric r: rubricas){
				sumaPercentRubric += r.getPercentage();
			}
			if(rubricasNum == 0){
				String message = "assesment.error.noRubrics";
				res = listModelAndView(deliId,message);
			}
			else if(correcciones == rubricasNum){
					String message = "assesment.error.completed";
					res = listModelAndView(deliId,message);
			}else if(sumaPercentRubric != 100){
					String message = "assesment.error.notHundred";
					res = listModelAndView(deliId,message);
			}else{
					a.setDeliverable(deliService.findOne(deliId));
					res = createEditModelAndView(a);}
			return res;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Assesment a, BindingResult binding) {
			ModelAndView res;
			if (binding.hasErrors()) {
				res = createEditModelAndView(a,"assesment.edit.commit.error");
			} else {
				try {
					assesmentService.save(a);
					res = new ModelAndView("redirect:/deliverable/lecturer/list.do");
				} catch (Throwable oops) {
					res = createEditModelAndView(a,"assesgment.edit.commit.error");
				}
			}
			return res;
		}
		
		// Ancillary methods --------------------------------------------
		protected ModelAndView createEditModelAndView(Assesment a) {
			return createEditModelAndView(a, null);
		}

		protected ModelAndView createEditModelAndView(Assesment a, String message) {
			ModelAndView  res = new ModelAndView("assesment/edit");
			Collection<Rubric>s = rubricService.findByAssigment(a.getDeliverable().getAssigment().getId());
			Collection<Rubric>filtradas = new ArrayList<Rubric>();
			for(Rubric r : s){
				if(r.getAssesments().isEmpty()){
					filtradas.add(r);
				}else{
					for(Assesment ass : r.getAssesments()){ //Esto controla las rubricas que ya tienen assesment en el presente deliverable
						if(!(ass.getDeliverable().getId() == a.getDeliverable().getId())){
							filtradas.add(r);
						}
					}
				}
			}
			res.addObject("rubrics", filtradas);
			res.addObject("message", message);
			res.addObject("assesment", a);		
			return res;
		}
		
		protected ModelAndView listModelAndView(Integer deliverableId,String message){
			Collection<Assesment> ass = assesmentService.findByDeliverable(deliverableId);
			double points = 0.0;
			double pointsAssRub = 0.0;
			for(Assesment a : ass){
				pointsAssRub = a.getPoints() * a.getRubric().getPercentage()/100.0;
				points = points + pointsAssRub;
			} 
			Integer totalPoints = deliService.findOne(deliverableId).getAssigment().getMark();
			double division = (double) (points/100.0);
			double finalMark = division * totalPoints;
			Double finalMarkD = finalMark;
			String finalMarkStr = finalMarkD.toString() + " / " + totalPoints +" points.";
			ModelAndView res = new ModelAndView("assesment/list");
			res.addObject("assesments", ass);
			res.addObject("message", message);
			res.addObject("requestURI", "assesment/lecturer/list.do");
			res.addObject("finalMark",finalMarkStr);
			res.addObject("totalPercentage",points);
			return res;
		}
	
	

}
