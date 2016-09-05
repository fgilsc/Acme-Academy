package services;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

//import domain.BiographyEntry;
import domain.Syllabus;

import repositories.SyllabusRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class SyllabusService {

//	Managed repository -----------------------------------------
	@Autowired
	private SyllabusRepository syllaRepo;
	
	//Supporting Services---------------------------------------
	@Autowired
	private LecturerService lecturerService;
	// CRUD MEthods---------------------------------------------
	
	public Syllabus create(){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		Syllabus res = new Syllabus();
		res.setLecturer(lecturerService.findByUserAccount(loginNow));
		/*Collection<BiographyEntry> be = new ArrayList<BiographyEntry>();
		res.setBiographyEntries(be);*/
		return res;
	}
	
	public Syllabus findOne(int id){
		Syllabus res = syllaRepo.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Syllabus> findAll(){
		Collection<Syllabus>res = syllaRepo.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public void save(Syllabus s){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		Assert.notNull(s);
		syllaRepo.saveAndFlush(s);
	}
	
	public void update(Syllabus s){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		Assert.notNull(s);
		syllaRepo.save(s);
	}
	
	public void delete(Syllabus s){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		Assert.notNull(s);
		syllaRepo.delete(s);
	}
	
	//Other Bussiness Methods
	public Collection<Syllabus> findByLecturer(int lecturerId){
		Collection<Syllabus>res = syllaRepo.findByLecturer(lecturerId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Syllabus> findByLecturerLogin(){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		int lecturerId = lecturerService.findByUserAccount(loginNow).getId();
		Collection<Syllabus>res = syllaRepo.findByLecturer(lecturerId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Syllabus> findUnAssociated(){
		Collection<Syllabus> res = new ArrayList<Syllabus>();
		for(Syllabus s : findByLecturerLogin()){
			if(s.getSubject()==null){
				res.add(s);
			}
		}
		return res;
	}
	
	public Collection<Syllabus> getBySubject( int id){
		Collection<Syllabus> res = new ArrayList<Syllabus>();
		res = syllaRepo.getBySubject(id);
		Assert.notNull(res);
		return res;
	}
	
	public Syllabus getBySubjectCurrentYear( Collection<Syllabus> cs){
		Syllabus res = null;
		for(Syllabus s : cs){
			int anyoActual = Calendar.getInstance().get(Calendar.YEAR);
			if(s.getAcademicYear()== anyoActual){
				res = s;
				break;
			}
		}
		return res;
	}
	
	public Collection<Syllabus> getSyllabyPerBio(int bioId){
		Collection<Syllabus> res = syllaRepo.getSyllabyPerBio(bioId);
		Assert.notNull(res);
		return res;
	}
	
	public Double getAverageNumberSyllabiPerSubject(){
		return syllaRepo.getAverageNumberSyllabiPerSubject();
	}
}
