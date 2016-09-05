package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.BiographyEntry;

import services.BiographyEntryService;

@Controller
@RequestMapping("/biographyEntry")
public class BiographyEntryController extends AbstractController {

	public BiographyEntryController() {
		super();
	}
	
	//MAnaged Services
	@Autowired
	private BiographyEntryService bioService;
	
	@RequestMapping(value ="/listBySyllabus", method= RequestMethod.GET)
	public ModelAndView listBySyllabus(String message, @RequestParam Integer syllabusId){
		Collection<BiographyEntry> bio = bioService.getBySyllabus(syllabusId);
		ModelAndView res = new ModelAndView("biographyEntry/list");
		res.addObject("biographyEntries", bio);
		res.addObject("message",message);
		res.addObject("requestUri","biographyEntry/list.do");
		return res;
	}

}
