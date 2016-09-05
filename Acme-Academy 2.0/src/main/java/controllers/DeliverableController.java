package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Assigment;
import domain.Deliverable;
import domain.Student;

import forms.DeliverableForm;

import services.AssigmentService;
import services.DeliverableService;
import services.StudentService;

@Controller
@RequestMapping("/deliverable")
public class DeliverableController extends AbstractController {
	
	private Integer assID = null;
	
	public DeliverableController() {
		super();
	}
	
	//Managed Services
		@Autowired
		private DeliverableService deliService;
		@Autowired
		private AssigmentService assServ;
		@Autowired
		private StudentService studentService;
		
		//Listing
		@RequestMapping(value ="/list", method= RequestMethod.GET)
		public ModelAndView list(){
			Collection<Deliverable> dels = deliService.deliByStudent();
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
		
		
	//Create
		@RequestMapping(value="/upload", method=RequestMethod.GET)
		public ModelAndView create(@RequestParam Integer assigmentId){
			assID = assigmentId;
			DeliverableForm df = new DeliverableForm();
			ModelAndView res = createEditModelAndView(df);
			return res;
		}
		
		@RequestMapping(value="/upload", method=RequestMethod.POST, params="save")
		public ModelAndView upload(@Valid DeliverableForm df, BindingResult binding){
			ModelAndView res;
			Deliverable d;
			if(binding.hasErrors()){
				res = createEditModelAndView(df);
			}else{
				try{
					d = deliService.recontruct(df);
					Assigment a = assServ.findOne(assID);
					d.setAssigment(a);
					d.setGroup(a.getGroup());
					deliService.save(d);
					res = new ModelAndView("redirect:/assigment/student/list.do");
				}catch(Throwable oops){
					res = createEditModelAndView(df,"deliverable.edit.commit.error");
				}
			}
			return res;
		}
		
		//Ancillary methods
		protected ModelAndView createEditModelAndView(DeliverableForm df){
			return createEditModelAndView(df,null);
		}
		
		protected ModelAndView createEditModelAndView(DeliverableForm df,String message){
			String requestURI = "deliverable/upload.do";
			ModelAndView res = new ModelAndView("deliverable/upload");
			res.addObject("deliverableForm", df);
			res.addObject("message", message);
			res.addObject("requestURI", requestURI);
			return res;
		}
		
	

}
