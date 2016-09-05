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

import domain.Assigment;
import domain.Deliverable;
import domain.Group;
import domain.Rubric;
import domain.Student;

import repositories.AssigmentRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AssigmentService {
	
	//Managed repository -----------------------------------------
	@Autowired
	private AssigmentRepository assigmentRepository;
	
//	Supporting Service-----------------------------------------
	@Autowired
	private LecturerService lecturerService;
	@Autowired
	private StudentService studentService;
	
	//Simple CRUD methods -------------------------------------------------
	
	public Assigment create() {
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		Assigment res = new Assigment();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date d1 = new Date();
		String s = formatter.format(d1);
		Date d = null;
		try {
			d = formatter.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		res.setOpeningTime(d);
		Collection<Deliverable> deli = new ArrayList<Deliverable>();
		res.setDeliverables(deli);
		res.setLecturer(lecturerService.findByUserAccount(loginNow));
		Collection<Rubric> r = new ArrayList<Rubric>();
		res.setRubrics(r);
		return res;
	}
	
	public Collection<Assigment> findAll(){
		Collection<Assigment> res = assigmentRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Assigment findOne(int id){
		Assigment res = assigmentRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public void save(Assigment a){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		a.setSubject(a.getGroup().getSubject());
		Assert.notNull(a);
		assigmentRepository.saveAndFlush(a);
	}
	
	public void update(Assigment a){
		Assert.notNull(a);
		assigmentRepository.save(a);
	}
	
	public void delete(Assigment a){
		Assert.notNull(a);
		assigmentRepository.delete(a);
	}
		
	//Other Bussines Methods
	
	public Collection<Assigment> assigmentsByUserLogged(){
		UserAccount loginNow = LoginService.getPrincipal();
		studentService.isStudent(loginNow);
		Student s = studentService.findByUserAccount(loginNow);
		Collection<Assigment> res = new ArrayList<Assigment>();
		for(Group g : s.getGroups()){
			Collection<Assigment>tareasAbiertas = new ArrayList<Assigment>();
			for(Assigment a : g.getAssigments()){
				if(new Date().before(a.getDeadline())){ // Comprueba que está abierta
					tareasAbiertas.add(a);
				}
			}
			res.addAll(tareasAbiertas);
		}
		return res;
	}
	
	public Collection<Assigment> findByLecturer(){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		Collection<Assigment>res = assigmentRepository.findByLecturer(lecturerService.findByUserAccount(loginNow).getId());
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Assigment> getAssigmentWithTwentyPercetMoreThanAverageNumberRubricPerAssigment(){
		Collection<Assigment>res = assigmentRepository.getAssigmentWithTwentyPercetMoreThanAverageNumberRubricPerAssigment();
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Assigment> getAssigmentWithTwentyPercetLessThanAverageNumberRubricPerAssigment(){
		Collection<Assigment>res = assigmentRepository.getAssigmentWithTwentyPercetLessThanAverageNumberRubricPerAssigment();
		Assert.notNull(res);
		return res;
	}
}
