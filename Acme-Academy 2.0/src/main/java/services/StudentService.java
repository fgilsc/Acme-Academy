package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import domain.SocialIdentity;
import domain.Student;
import forms.StudentForm;

import repositories.StudentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class StudentService {
	
	//	Managed repository -----------------------------------------
	@Autowired
	private StudentRepository studentRepo;
	
//	Supporting Service-----------------------------------------
	@Autowired
	private AdministratorService adminService;
	
	//Constructor -------------------------------------------------
	public StudentService(){
		super();
	}
	
	//Simple CRUD methods -------------------------------------------------
	public Student create(){
		Student res = new Student();
		UserAccount ua = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setAuthority(Authority.STUDENT);
		authorities.add(a);
		ua.setAuthorities(authorities);
		res.setUserAccount(ua);
		Collection<SocialIdentity> si = new ArrayList<SocialIdentity>();
		res.setSocialIdentities(si);
		return res;
	}
	
	public Collection<Student> findAll(){
		Collection<Student>res = studentRepo.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Student findOne(int id){
		Student res = studentRepo.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public void save(Student s){
		Assert.notNull(s);
		studentRepo.saveAndFlush(s);
	}
	
	public void update(Student s){
		Assert.notNull(s);
		studentRepo.save(s);
	}
	
	public void delete(Student s){
		Assert.notNull(s);
		studentRepo.delete(s);
	}
	
	// Other business methods -------------------------------------------------
	public Double getStudentsAvgPerSubject(){
		UserAccount loginNow = LoginService.getPrincipal();
		adminService.isAdmin(loginNow);
		Double res = studentRepo.getStudentsAvgPerSubject();
		return res;
	}
	
	public void isStudent(UserAccount ua){
		Collection<Authority> authorities= ua.getAuthorities();
		Boolean res=false;
		for(Authority a:authorities){
			if(a.getAuthority().equals("STUDENT")) res=true;
		}
		Assert.isTrue(res);
	}
	
	public Student reconstruct(StudentForm sf){
		Assert.isTrue(sf.getPassword().equals(sf.getRepeatPassword()));
		Assert.isTrue(sf.getValid());
		Student res = create();
		UserAccount ua = res.getUserAccount();
		ua.setUsername(sf.getUsername());
		
		Md5PasswordEncoder enc = new Md5PasswordEncoder();
		String password = enc.encodePassword(sf.getPassword(), null);
		ua.setPassword(password);
		
		res.setUserAccount(ua);
		res.setName(sf.getName());
		res.setSurname(sf.getSurname());
		res.setEmail(sf.getEmail());
		res.setPhone(sf.getPhone());
		
		return res;
	}
	
	public Student findByUserAccount(UserAccount account){
		Student result = studentRepo.findByUserAccount(account);
		return result;
	}
	
	public Collection<Student> getStudentWithEnrolledMore20percentAvgSubject(){
		return studentRepo.getStudentWithEnrolledMore20percentAvgSubject();
	}
	
	public Collection<Student> getStudentWithEnrolledLess20percentAvgSubject(){
		return studentRepo.getStudentWithEnrolledLess20percentAvgSubject();
	}
}
