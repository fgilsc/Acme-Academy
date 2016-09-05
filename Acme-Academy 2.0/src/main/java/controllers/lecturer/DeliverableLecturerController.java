package controllers.lecturer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.DeliverableService;
import services.StudentService;

import controllers.AbstractController;
import domain.Deliverable;
import domain.Student;

@Controller
@RequestMapping("/deliverable/lecturer")
public class DeliverableLecturerController extends AbstractController {
	
	public DeliverableLecturerController() {
		super();
	}
	
	//Managed Services
	@Autowired
	private DeliverableService deliService;
	@Autowired
	private StudentService studentService;

	//Listing 
	@RequestMapping(value ="/list", method= RequestMethod.GET)
	public ModelAndView list(){
		Collection<Deliverable> dels = deliService.getDeliverablesFromLecturerAssgigments();
		Map<Integer,String>mapeadorNombre = new HashMap<Integer, String>();
		
		for(Deliverable del : dels){
			int id = del.getUploaderStudentID();
			Student s = studentService.findOne(id);
			String name = s.getName();
			String surname = s.getSurname();
			String nameComp = name + " " + surname;
			mapeadorNombre.put(id, nameComp);
		}
		ModelAndView res = new ModelAndView("deliverable/list");
		res.addObject("deliverables", dels);
		res.addObject("requestURI", "deliverable/lecturer/list.do");
		res.addObject("mapeadorNombre",mapeadorNombre);
		return res;
	}
}
