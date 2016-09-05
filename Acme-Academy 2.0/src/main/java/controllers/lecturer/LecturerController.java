package controllers.lecturer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BiographyEntryService;
import services.LecturerService;

import controllers.AbstractController;
import domain.Lecturer;

@Controller
@RequestMapping("/lecturer")
public class LecturerController extends AbstractController {
	
	//Managed Services
	@Autowired
	private LecturerService lecturerService;
	@Autowired
	private BiographyEntryService biographyEntryService;
	
	//listing
	@RequestMapping(value ="/list", method= RequestMethod.GET)
	public ModelAndView list(){		
		Collection<Lecturer> cl = lecturerService.findAll();
		Map<Integer,Integer>contadorBio = new HashMap<Integer, Integer>();
		for(Lecturer l : cl){
			int id = l.getId();
			int veces = biographyEntryService.counterBioByLecturer(id);
			contadorBio.put(id, veces);
		}
		ModelAndView res = new ModelAndView("lecturer/list");
		res.addObject("lecturers", cl);
		res.addObject("contadorBio", contadorBio);
		res.addObject("actor", "");
		res.addObject("lecturerService", lecturerService);
		res.addObject("requestURI", "lecturer/list.do");
		return res;
	}
	
	@RequestMapping(value="/listBySubject",method=RequestMethod.GET)
	public ModelAndView listBySubject(@RequestParam int subjectId){
		Collection<Lecturer> cl = lecturerService.findBySubject(subjectId);
		ModelAndView res = new ModelAndView("lecturer/listBySubject");
		res.addObject("requestURI", "lecturer/listBySubject.do");
		res.addObject("lecturersBySubject", cl);
		return res;
		
	}

}
