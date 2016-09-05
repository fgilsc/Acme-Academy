package controllers;

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

import domain.SocialIdentity;
import forms.SocialIdentityForm;

import services.SocialIdentityService;

@Controller
@RequestMapping("/socialIdentity")
public class SocialIdentityController extends AbstractController {

	public SocialIdentityController() {
		super();
	}
	
	//Managed Services
	@Autowired
	public SocialIdentityService sIService;
	
	//Listing
	@RequestMapping("/list")
	public ModelAndView list(){
		Collection<SocialIdentity>si = sIService.findByActor();
		ModelAndView res= new ModelAndView("socialIdentity/list");
		res.addObject("socialIdentities", si);
		res.addObject("requestURI", "socialIdentity/list.do");
		return res;
		
	}
	
	//Creation
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		SocialIdentityForm sif = new SocialIdentityForm();
		ModelAndView res = createEditModelAndView(sif);
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Integer socialIdentityId){
		ModelAndView res;
		SocialIdentity si = sIService.findOne(socialIdentityId);
		SocialIdentityForm sif = null;
		
		try{
			sif = sIService.constructForm(si);
			res = createEditModelAndView(sif);
		}catch(Throwable oops){
			res = createEditModelAndView(sif,"socialIdentity.error");
		}
		return res;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid SocialIdentityForm sif, BindingResult binding){
		ModelAndView res;
		SocialIdentity si;
		if (binding.hasErrors()) {
			res = createEditModelAndView(sif);
		}else {
			try{
				si = sIService.recontruct(sif);
				sIService.save(si);
				res = new ModelAndView("redirect:/socialIdentity/list.do");
			}catch(Throwable oops){
				if((sif.getSocialPlatformName().isEmpty()) || (sif.getNick().isEmpty()) || (sif.getHomepage().isEmpty())){
					res = createEditModelAndView(sif,"socialIdentity.empty");
				}else{
					res = createEditModelAndView(sif,"socialIdentity.format");
				}
			}
		}
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView update(@RequestParam Integer socialIdentityId, @Valid SocialIdentityForm sif, BindingResult binding){
		ModelAndView res;
		
		if (binding.hasErrors()) {
			res = new ModelAndView();
			res.addObject("socialIdentityForm",sif);
			/*BindingResult br = binding;
			System.out.println(br);                   */
		}else {
			try{
				SocialIdentity si = sIService.recontructEdit(sif, socialIdentityId);
				sIService.update(si);
				res = new ModelAndView("redirect:/socialIdentity/list.do");
			}catch(Throwable oops){
				if((sif.getSocialPlatformName().isEmpty()) || (sif.getNick().isEmpty()) || (sif.getHomepage().isEmpty())){
					res = createEditModelAndView(sif,"socialIdentity.empty");
				}else{
					res = createEditModelAndView(sif,"socialIdentity.format");
				}
			}
		}
		return res;
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam int socialIdentityId){
		ModelAndView res = new ModelAndView();
		SocialIdentity si = sIService.findOne(socialIdentityId);
		try{
			Assert.notNull(si);
			sIService.delete(si);
			res = new ModelAndView("redirect:/socialIdentity/list.do");
		}catch(Throwable oops){
			
		}
		return res;
	}
	
	
	// Ancillary methods --------------------------------------------
		protected ModelAndView createEditModelAndView(SocialIdentityForm sif){
			ModelAndView res = createEditModelAndView(sif,null);
			return res;
		}
		
		protected ModelAndView createEditModelAndView(SocialIdentityForm sif,String message){
			ModelAndView res = new ModelAndView("socialIdentity/edit");
			res.addObject("socialIdentityForm", sif);
			res.addObject("message", message);
			return res;
		}
		
		
	
	
	
	

}
