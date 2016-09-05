package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import domain.Lecturer;



import repositories.LecturerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class LecturerService {
	
	//	Managed repository -----------------------------------------
	@Autowired
	private LecturerRepository lecturerRepository;
	
	//	Supporting Service-----------------------------------------
	@Autowired
	private AdministratorService adminService;
	
	
	//Constructor -------------------------------------------------
			public LecturerService(){
				super();
			}
			
	//Simple CRUD methods -------------------------------------------------
			public Lecturer create(){
				UserAccount loginNow = LoginService.getPrincipal();
				adminService.isAdmin(loginNow);
				Lecturer res = new Lecturer();
				
				UserAccount ua = new UserAccount();
				List<Authority> authorities = new ArrayList<Authority>();
				Authority a = new Authority();
				a.setAuthority(Authority.LECTURER);
				authorities.add(a);
				ua.setAuthorities(authorities);
				res.setUserAccount(ua);
				return res;
			}
			
			public Lecturer findOne(int id){
				Lecturer res = lecturerRepository.findOne(id);
				Assert.notNull(adminService);
				return res;
			}
			
			public Collection<Lecturer> findAll(){
				Collection<Lecturer> res = lecturerRepository.findAll();
				Assert.notNull(res);
				return res;
			}
			
			public void save(Lecturer l){
				Assert.notNull(l);
				String pass = l.getUserAccount().getPassword();
				Md5PasswordEncoder enc = new Md5PasswordEncoder();
				String hash = enc.encodePassword(pass, null);
				l.getUserAccount().setPassword(hash);
				lecturerRepository.saveAndFlush(l);
			}
			
			public void update(Lecturer l){
				Assert.notNull(l);
				lecturerRepository.save(l);
			}
			
			public void delete(Lecturer l){
				Assert.notNull(l);
				lecturerRepository.delete(l);
			}
			
			
			
	// Other business methods -------------------------------------------------
			public Double getAverageSubjectPerLecturer(){
				UserAccount loginNow = LoginService.getPrincipal();
				adminService.isAdmin(loginNow);
				Double res = lecturerRepository.getAverageSubjectPerLecturer();
				return res;
			}
			
			public Collection<Lecturer> findWithMoreTwentyPercentOfSubjectsAvg(){
				UserAccount loginNow = LoginService.getPrincipal();
				adminService.isAdmin(loginNow);
				Collection<Lecturer> res = lecturerRepository.findWithMoreTwentyPercentOfSubjectsAvg();
				return res;
			}
			
			public Collection<Lecturer> findWithLessTwentyPercentOfSubjectsAvg(){
				UserAccount loginNow = LoginService.getPrincipal();
				adminService.isAdmin(loginNow);
				Collection<Lecturer> res = lecturerRepository.findWithLessTwentyPercentOfSubjectsAvg();
				return res;
			}
			
			public void isLecturer(UserAccount ua){
				Collection<Authority> authorities= ua.getAuthorities();
				Boolean res=false;
				for(Authority a:authorities){
					if(a.getAuthority().equals("LECTURER")) res=true;
				}
				Assert.isTrue(res);
			}
			
			public Collection<Lecturer> findBySubject(int id){
				/*Subject s = subjectService.findOne(id);
				Collection<Lecturer> res = s.getLecturers();*/
				Collection<Lecturer> res = lecturerRepository.findBySubject(id);
				return res;
			}
			
			public Lecturer findByUserAccount(UserAccount account){
				Lecturer result = lecturerRepository.findByUserAccount(account);
				return result;
			}
			
			public Collection<Lecturer>lecturersWithMoreLearningMaterials(){
				Collection<Lecturer> res = lecturerRepository.lecturersWithMoreLearningMaterials();
				Assert.notNull(res);
				return res;
			}
			
			public Collection<Lecturer> findByMoreRubricsPerAssigments(){
				Collection<Lecturer> res = lecturerRepository.getLecturerWithMoreRubricsPerAssignment();
				Assert.notNull(res);
				return res;
			}
			
			public Collection<Lecturer> findByLessRubricsPerAssigments(){
				Collection<Lecturer> res = lecturerRepository.getLecturerWithLessRubricsPerAssignment();
				Assert.notNull(res);
				return res;
			}

}
