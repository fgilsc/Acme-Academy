package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import domain.Assesment;
import domain.Assigment;
import domain.Deliverable;
import domain.Group;
import domain.Lecturer;
import domain.Student;
import forms.DeliverableForm;

import repositories.DeliverableRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class DeliverableService {
	
	//Managed repository -----------------------------------------
		@Autowired
		private DeliverableRepository deliRepo;
		
	//Supporting Service-----------------------------------------
		@Autowired
		private StudentService studentService;
		@Autowired
		private LecturerService lecturerService;
		@Autowired
		private GroupService groupService;
	
		//Simple CRUD methods -------------------------------------------------
		public Deliverable create(){
			UserAccount loginNow = LoginService.getPrincipal();
			studentService.isStudent(loginNow);
			Student st = studentService.findByUserAccount(loginNow);
			Deliverable res = new Deliverable();
			res.setUploaderStudentID(st.getId());
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date d1 = new Date();
			String s = formatter.format(d1);
			Date d = null;
			try {
				d = formatter.parse(s);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			res.setMoment(d);
			Collection<Assesment>ass = new ArrayList<Assesment>();
			res.setAssesments(ass);
			return res;
		}
		
		public Collection<Deliverable> findAll(){
			Collection<Deliverable> res = deliRepo.findAll();
			Assert.notNull(res);
			return res;
		}
		
		public Deliverable findOne(int id){
			Deliverable res = deliRepo.findOne(id);
			Assert.notNull(res);
			return res;
		}
		
		public void save(Deliverable del){
			UserAccount loginNow = LoginService.getPrincipal();
			studentService.isStudent(loginNow);
			Assert.notNull(del);
			deliRepo.saveAndFlush(del);
		}
		
		public void update(Deliverable del){
			Assert.notNull(del);
			deliRepo.save(del);
		}
		// Other Bussines Methods
		
		public Deliverable recontruct (DeliverableForm df){
			Deliverable res = create();
			res.setContent(df.getContent());
			return res;
		}
		
		public Collection<Deliverable> deliByStudent(){
			UserAccount loginNow = LoginService.getPrincipal();
			studentService.isStudent(loginNow);
			Collection<Group> studentGroups = groupService.findByStudentLogin();
			Collection<Deliverable> res = new ArrayList<Deliverable>();
			for(Group g : studentGroups ){
				res.addAll(g.getDeliverables());
				
			}
			return res;
		}
		
		public Collection<Deliverable> getDeliverablesFromLecturerAssgigments(){
			UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);
			Lecturer l = lecturerService.findByUserAccount(loginNow);
			Collection<Assigment> ass = l.getAssigments();
			Collection<Deliverable> res = new ArrayList<Deliverable>();
			for(Assigment a : ass){
				res.addAll(a.getDeliverables());
			}
			return res;
		}
}
