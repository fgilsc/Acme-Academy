package controllers;


import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Group;

import domain.Subject;
import forms.GroupForm;

import services.GroupService;
import services.SubjectService;

@Controller
@RequestMapping("/group")
public class GroupController extends AbstractController {
	
	public GroupController() {
		super();
	
	}

		//Managed Services
		@Autowired
		private GroupService groupService;
		@Autowired
		private SubjectService subjectService;
		
		// Creation ---------------------------------------------------------------
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			//Group g = groupService.create();
			GroupForm gf = new GroupForm();
			ModelAndView res = createEditModelAndView(gf);
			return res;
		}
		/*@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam Integer groupId){
			Group g = groupService.findOne(groupId);
			Assert.notNull(g);
			return createEditModelAndView(g);
		}*/
		
		@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid GroupForm gf, BindingResult binding) {
			ModelAndView res;
			Group g;
			/*if(g.getLearningMaterials()==null){
				g.setLearningMaterials(new ArrayList<LearningMaterial>());
			}*/
			if (binding.hasErrors()) {
				res = createEditModelAndView(gf,"group.edit.commit.error");
			} else {
				try {
					g = groupService.recontruct(gf);
					groupService.save(g);
					res = new ModelAndView("redirect:/group/lecturer/list.do");
				} catch (Throwable oops) {
					res = createEditModelAndView(gf,"group.edit.commit.error");
				}
			}
			return res;
		}
		//Listing
		@RequestMapping("/list")
		public ModelAndView list(){
			Collection<Group>	 g = groupService.findAll();
			Collection<Group> loginHas = groupService.findByStudentLogin();
			for(Group gr : loginHas){
				if(g.contains(gr)){
					g.remove(gr);
				}
			}
			ModelAndView res = new ModelAndView("group/list");
			res.addObject("groups", g);
			res.addObject("requestUri", "group/list.do");
			return res;
			 
		}
		
		@RequestMapping("/lecturer/list")
		public ModelAndView listByLecturer(){
			Collection<Group> loginHas = groupService.findByLecturerLogin();
			ModelAndView res = new ModelAndView("group/list");
			res.addObject("groups", loginHas);
			res.addObject("requestUri", "group/lecturer/list.do");
			return res;
			 
		}
		
		@RequestMapping("/enrol")
		public ModelAndView enrol(@RequestParam Integer groupId){
			ModelAndView res;
			try{
				Group g = groupService.findOne(groupId);
				groupService.enrol(g);
				groupService.update(g);
				res = new ModelAndView("redirect:/group/myGroups.do");
				
			}catch(Throwable oops){
				res = new ModelAndView("redirect:/group/myGroups.do");
				
			}
			return res;
		}
		
		@RequestMapping("/myGroups")
		public ModelAndView myGroups(){
			Collection<Group> loginHas = groupService.findByStudentLogin();
			ModelAndView res = new ModelAndView("group/myGroups");
			res.addObject("myGroups", loginHas);
			res.addObject("requestUri", "group/myGroups.do");
			return res;
			 
		}
		
		// Ancillary methods --------------------------------------------
		protected ModelAndView createEditModelAndView(GroupForm gf) {
			return createEditModelAndView(gf, null);
		}

		protected ModelAndView createEditModelAndView(GroupForm gf, String message) {
			ModelAndView  res = new ModelAndView("group/create");
			Collection<Subject>s = subjectService.findByLoginNow();
			res.addObject("subjects", s);
			res.addObject("message", message);
			res.addObject("groupForm", gf);
			
			return res;
		}
}
