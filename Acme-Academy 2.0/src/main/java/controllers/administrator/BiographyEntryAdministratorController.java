package controllers.administrator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import services.BiographyEntryService;
import controllers.AbstractController;
import domain.BiographyEntry;

@Controller
@RequestMapping("/biographyEntry/administrator")
public class BiographyEntryAdministratorController extends AbstractController {

		public BiographyEntryAdministratorController() {
			super();
		}
		
		//MAnaged Services
		@Autowired
		private BiographyEntryService bioService;

		//Listing 
		@RequestMapping(value ="/list", method= RequestMethod.GET)
		public ModelAndView list(String message){
			Collection<BiographyEntry> all = bioService.findAll();
			Map<Integer,Integer>contadorBio = new HashMap<Integer, Integer>();
			for(BiographyEntry bio : all){
				Integer id = bio.getId();
				Integer veces = bioService.contadorBioPorAsignatura(id);
				contadorBio.put(id, veces);
			}
			ModelAndView res = new ModelAndView("biographyEntry/list");
			res.addObject("biographyEntries", all);
			res.addObject("message",message);
			res.addObject("requestUri","biographyEntry/administrator/list.do");
			res.addObject("contadorBio",contadorBio);
			return res;
		}

}
	