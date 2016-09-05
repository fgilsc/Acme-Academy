package controllers;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import domain.LearningMaterial;


import services.LearningMaterialService;

@Controller
@RequestMapping("/learningMaterial/student")
public class LearningMaterialController extends AbstractController {

	public LearningMaterialController() {
		super();
	}
	
	//MAnaged Services
	@Autowired
	private LearningMaterialService lmServ;
	
	
	
	 //Listing 
	@RequestMapping(value ="/list", method= RequestMethod.GET)
	public ModelAndView list(@RequestParam Integer groupId){ 
		Collection<LearningMaterial>lmG = lmServ.findByGroup(groupId);
		ModelAndView res = new ModelAndView("learningMaterial/list");
		res.addObject("learningMaterials", lmG);
		res.addObject("requestURI", "learningMaterial/student/list.do");
		return res;
	}
}
