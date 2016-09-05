package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AssesmentService;
import services.DeliverableService;
import domain.Assesment;

@Controller
@RequestMapping("/assesment")
public class AssesmentController extends AbstractController {

	public AssesmentController() {
		super();
		
	}
	
	//Managed Services
			@Autowired
			private AssesmentService assesmentService;
			@Autowired
			private DeliverableService deliService;
		
	
		//Listing 
			@RequestMapping(value ="/list", method= RequestMethod.GET)
			public ModelAndView list(@RequestParam Integer deliverableId){
				ModelAndView res;
				int rubricas = deliService.findOne(deliverableId).getAssigment().getRubrics().size();
				Collection<Assesment> ass = assesmentService.findByDeliverable(deliverableId);
				int correcciones = ass.size();
				if(correcciones != rubricas || rubricas ==0){
					res = new ModelAndView("assesment/underReview");
				}else{
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
					res = new ModelAndView("assesment/list");
					res.addObject("assesments", ass);
					res.addObject("requestURI", "assesment/lecturer/list.do");
					res.addObject("finalMark",finalMarkStr);
					res.addObject("totalPercentage",points);
				}
				return res;
			}

}
