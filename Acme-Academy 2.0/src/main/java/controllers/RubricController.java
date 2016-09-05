package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Rubric;

import services.RubricService;

@Controller
@RequestMapping("/rubric")
public class RubricController extends AbstractController {
	
	public RubricController(){
		
	}
	
	@Autowired
	private RubricService rubricService;
	
	//Listing 
		@RequestMapping(value ="/list", method= RequestMethod.GET)
		public ModelAndView list(@RequestParam Integer assigmentId){
			Collection<Rubric>rubs = rubricService.findByAssigment(assigmentId);
			int sumaPercentRubs = 0;
			for(Rubric r: rubs){
				sumaPercentRubs += r.getPercentage();
			}
			if(sumaPercentRubs<100){
				Rubric rubDefault = rubricService.create();
				rubDefault.setExplanation("Default Rubric");
				int percentDefault = 100 - sumaPercentRubs;
				rubDefault.setPercentage(percentDefault);
				rubs.add(rubDefault);
			}
			ModelAndView res = new ModelAndView("rubric/list");
			res.addObject("rubrics", rubs);
			res.addObject("requestURI", "rubric/list.do");
			return res;
		}

}
