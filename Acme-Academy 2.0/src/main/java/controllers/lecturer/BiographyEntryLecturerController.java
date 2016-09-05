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

import services.BiographyEntryService;

import controllers.AbstractController;
import domain.BiographyEntry;


@Controller
@RequestMapping("/biographyEntry/lecturer")
public class BiographyEntryLecturerController extends AbstractController {

	public BiographyEntryLecturerController() {
		super();
	}
	
	//MAnaged Services
		@Autowired
		private BiographyEntryService bioService;
	
	//Creating & Editing
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		BiographyEntry b = bioService.create();
		ModelAndView res = createEditModelAndView(b);
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Integer biographyEntryId){
		BiographyEntry b = bioService.findOne(biographyEntryId);
		Assert.notNull(b);
		return  createEditModelAndView(b);
		
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid BiographyEntry be, BindingResult binding) {
		ModelAndView res;
		Collection<String> author  = be.getAuthor();
		for(String a : author){
			if(a.contains(";")){
				String[] partes = a.split(";");
				for(int i=0;i<partes.length;i++){
					author.add(partes[i]);
				}
				author.remove(a);
			}
		}
		if (binding.hasErrors()) {
			res = createEditModelAndView(be,"biographyEntry.edit.commit.error");
		}else {
			try {
				bioService.save(be);
				res = new ModelAndView("redirect:/syllabus/lecturer/create.do");
			}catch(Throwable oops){
				res = createEditModelAndView(be,"biographyEntry.edit.commit.error");
			}
		}
		return res;
	}	
	
	//Listing 
	@RequestMapping(value ="/list", method= RequestMethod.GET)
	public ModelAndView list(String message){
		Collection<BiographyEntry> bio = bioService.findAll();
		ModelAndView res = new ModelAndView("biographyEntry/list");
		res.addObject("biographyEntries", bio);
		res.addObject("message",message);
		res.addObject("requestUri","biographyEntry/lecturer/list.do");
		return res;
	}
	
	
	
	//Anciallary Methods
	protected ModelAndView createEditModelAndView(BiographyEntry be){
		return createEditModelAndView(be,null);
	}
	protected ModelAndView createEditModelAndView(BiographyEntry be, String message){
		ModelAndView  res = new ModelAndView("biographyEntry/edit");
		res.addObject("biographyEntry", be);
		res.addObject("message", message);
		return res;
	}
	
}
