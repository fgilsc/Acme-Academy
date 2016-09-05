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

import services.GroupService;
import services.LearningMaterialService;

import controllers.AbstractController;


import domain.Group;
import domain.LearningMaterial;

@Controller
@RequestMapping("/learningMaterial/lecturer")
public class LearningMaterialLecturerController extends AbstractController {

	public LearningMaterialLecturerController() {
		super();
	}
	
	//MAnaged Services
	@Autowired
	private LearningMaterialService lmServ;
	@Autowired
	private GroupService groupService;
	
	//Creating
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		LearningMaterial lm = lmServ.create();
		ModelAndView res = createEditModelAndView(lm);
		return res;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid LearningMaterial lm, BindingResult binding) {
		ModelAndView res;
		Collection<String> notes = lm.getNotes();
		for(String note: notes){
			if(note.contains(";")){
				String[] partes = note.split(";");
				for(int i=0;i<partes.length;i++){
					notes.add(partes[i]);
				}
				notes.remove(note);
			}
		}
		Collection<String> keys = lm.getKeywords();
		for(String key: keys){
			if(key.contains(";")){
				String[] partes = key.split(";");
				for(int i=0;i<partes.length;i++){
					keys.add(partes[i]);
				}
				keys.remove(key);
			}
		}
		
		if (binding.hasErrors()) {
			res = createEditModelAndView(lm,"learninMaterial.edit.commit.error");
		} else {
			try {
				lmServ.save(lm);
				res = new ModelAndView("redirect:/learningMaterial/lecturer/list.do"); 
			} catch (Throwable oops) {
				res = createEditModelAndView(lm,"learninMaterial.edit.commit.error");
			}
		}
		return res;
	}
	 //Listing 
	 @RequestMapping(value ="/list", method= RequestMethod.GET)
		public ModelAndView list(){
		 Collection<LearningMaterial> lms = lmServ.findByLecturer();
		 Collection<LearningMaterial>listaShow =  new ArrayList<LearningMaterial>();
		 for(LearningMaterial lm: lms){
			 if(lm.getGroup() == null){
				 listaShow.add(lm);
			 }
		 }
		 ModelAndView res = new ModelAndView("learningMaterial/list");
		 res.addObject("learningMaterials", listaShow);
		 res.addObject("requestURI", "learningMaterial/lecturer/list.do");
		 return res;
	 }
	 
	 @RequestMapping(value ="/listByGroup", method= RequestMethod.GET)
		public ModelAndView listByGroup(@RequestParam Integer groupId){
		 Collection<LearningMaterial> lms = lmServ.findByGroup(groupId);
		 ModelAndView res = new ModelAndView("learningMaterial/listByGroup");
		 res.addObject("requestURI", "learningMaterial/lecturer/listByGroup.do");
		 res.addObject("learningMaterials", lms);
		 return res;
	 }
	 //Associate-----------------------------------------------------------------------
	@RequestMapping(value="/associate", method = RequestMethod.GET)
	 public ModelAndView associate(@RequestParam Integer learningMaterialId){
		 LearningMaterial lm = lmServ.findOne(learningMaterialId);
		 return associateModelAndView(lm,null);
	 }
	
	@RequestMapping(value = "/associate", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAssociation(@Valid LearningMaterial lm, BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = createEditModelAndView(lm,"learninMaterial.edit.commit.error");
		} else {
			try {
				lmServ.upload(lm);
				res = new ModelAndView("redirect:/learningMaterial/lecturer/list.do"); 
			} catch (Throwable oops) {
				res = createEditModelAndView(lm,"learninMaterial.edit.commit.error");
			}
		}
		return res;
	}
	 
	 
	 
	 
	 // Ancillary methods --------------------------------------------
	protected ModelAndView createEditModelAndView(LearningMaterial lm) {
		return createEditModelAndView(lm, null);
	}

	protected ModelAndView createEditModelAndView(LearningMaterial lm, String message){
		ModelAndView  res = new ModelAndView("learningMaterial/edit");
		/*Collection<Group>s = groupService.findByLoginNow();
		res.addObject("groups", s);*/
		res.addObject("message", message);
		res.addObject("learningMaterial", lm);		
		return res;
		}
	
	 protected ModelAndView associateModelAndView(LearningMaterial lm, String message){
		 ModelAndView  res = new ModelAndView("learningMaterial/associate");
		 Collection<Group>s = groupService.findByLecturerLogin();
		 res.addObject("groups", s);
		 res.addObject("message", message);
		 res.addObject("learningMaterial", lm);
		 return res;
	 }
	 
	 
	 
	
	
	

}
