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

import services.AssigmentService;
import services.GroupService;

import controllers.AbstractController;
import controllers.WelcomeController;
import domain.Assigment;
import domain.Group;




@Controller
@RequestMapping("/assigment/lecturer")
public class AssigmentController extends AbstractController {

	public AssigmentController() {
		super();
		
	}
	
	//Managed Services
	@Autowired
	private AssigmentService assigmentService;
	@Autowired
	private GroupService groupService;
	
	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		Assigment a = assigmentService.create();
		ModelAndView res = createEditModelAndView(a);
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Integer assigmentId){
		Assigment a = assigmentService.findOne(assigmentId);
		Assert.notNull(a);
		return createEditModelAndView(a);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Assigment a, BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = createEditModelAndView(a,"assigment.edit.commit.error");
		} else {
			try {
				assigmentService.save(a);
				WelcomeController wc = new WelcomeController();
				res = wc.indexMess(null, "assigment.edit.commit.ok");
			} catch (Throwable oops) {
				res = createEditModelAndView(a,"assigment.edit.commit.error");
			}
		}
		return res;
	}
	
	// Ancillary methods --------------------------------------------
	protected ModelAndView createEditModelAndView(Assigment a) {
		return createEditModelAndView(a, null);
	}

	protected ModelAndView createEditModelAndView(Assigment a, String message) {
		ModelAndView  res = new ModelAndView("assigment/edit");
		Collection<Group>s = groupService.findByLecturerLogin();
		res.addObject("groups", s);
		res.addObject("message", message);
		res.addObject("assigment", a);		
		return res;
	}
	

}
