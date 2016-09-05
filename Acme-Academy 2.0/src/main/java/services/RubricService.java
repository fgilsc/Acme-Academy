package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Assesment;
import domain.Rubric;

import repositories.RubricRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class RubricService {
	
		//Managed repository -----------------------------------------
		@Autowired
		private RubricRepository rubricRepository;
		
        //Supporting Service-----------------------------------------
		@Autowired
		private LecturerService lecturerService;
		
		//CRUD Methods
		public Rubric create(){
			/*UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);*/
			Rubric res = new Rubric();
			Collection<Assesment> ass = new ArrayList<Assesment>();
			res.setAssesments(ass);
			return res;
		}
		
		public Collection<Rubric> findAll(){
			Collection<Rubric> res = rubricRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		
		public Rubric findOne(int id){
			Rubric res = rubricRepository.findOne(id);
			Assert.notNull(res);
			return res;
		}
		
		public void save(Rubric r){
			UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);
			Assert.notNull(r);
			rubricRepository.saveAndFlush(r);
		}
		
		public void update(Rubric r){
			UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);
			Assert.notNull(r);
			rubricRepository.save(r);
		}
		
		public void delete(Rubric r){
			UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);
			Assert.notNull(r);
			rubricRepository.delete(r);
		}
		//OtherBussines methods
		public Collection<Rubric> findByAssigment(int assigmentId){
			Collection<Rubric> res = rubricRepository.findByAssigment(assigmentId);
			Assert.notNull(res);
			return res;
		}
		
		public Collection<Rubric> findByLecturer(){
			UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);
			Collection<Rubric>res = rubricRepository.findByLecturer(lecturerService.findByUserAccount(loginNow).getId());
			Assert.notNull(res);
			return res;
		}
		

}
