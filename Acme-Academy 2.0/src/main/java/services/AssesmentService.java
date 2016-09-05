package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Assesment;


import repositories.AssesmentRepository;
import security.LoginService;
import security.UserAccount;


@Service
@Transactional
public class AssesmentService {
	
	//Managed repository -----------------------------------------
	@Autowired
	private AssesmentRepository assesmentRepository;
			
	//Supporting Service-----------------------------------------
	@Autowired
	private LecturerService lecturerService;
	
	//CRUD METHDOS
	
	public Assesment create(){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		Assesment res = new Assesment();
		return res;
	}
	
	public Collection<Assesment> findAll(){
		Collection<Assesment> res = assesmentRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Assesment findOne(int id){
		Assesment res = assesmentRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public void save(Assesment a){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		Assert.notNull(a);
		assesmentRepository.saveAndFlush(a);
	}
	
	public void update(Assesment a){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		Assert.notNull(a);
		assesmentRepository.save(a);
	}
	
	public void delete(Assesment a){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		Assert.notNull(a);
		assesmentRepository.delete(a);
	}
	
	//Other Bussiness Methods
	public Collection<Assesment> findByDeliverable(int deliId){
		Collection<Assesment> res = assesmentRepository.findByDeliverable(deliId);
		Assert.notNull(res);
		return res;
	}
	
	
}
