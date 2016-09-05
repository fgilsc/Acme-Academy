package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.BiographyEntry;
import domain.Subject;
import domain.Syllabus;


import repositories.BiographyEntryRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class BiographyEntryService {
	
//	Managed repository -----------------------------------------
	@Autowired
	private BiographyEntryRepository bioRepo;
	
	//Supporting Services---------------------------------------
	@Autowired
	private LecturerService lecturerService;
	@Autowired
	private SyllabusService syllabusService;
	
	// CRUD MEthods---------------------------------------------
	public BiographyEntry create(){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		BiographyEntry res = new BiographyEntry();
		return res;
	}
	
	public Collection<BiographyEntry> findAll(){
		Collection<BiographyEntry> res= bioRepo.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public BiographyEntry findOne(int id){
		BiographyEntry res = bioRepo.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public void save(BiographyEntry be){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		Assert.notNull(be);
		bioRepo.saveAndFlush(be);
	}
	
	public void update(BiographyEntry be){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		Assert.notNull(be);
		bioRepo.save(be);
	}
	
	public void delete(BiographyEntry be){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		Assert.notNull(be);
		bioRepo.delete(be);
	}
	
	//Other Bussines Methods-----------------------------------------------
	
	public Double getAverageNumberBiographyEntriesPerSyllabus(){
		return bioRepo.getAverageNumberBiographyEntriesPerSyllabus();
	}
	
	public Collection<BiographyEntry> getBySyllabus( int id){
		Collection<BiographyEntry> res = bioRepo.getBySyllabus(id);
		Assert.notNull(res);
		return res;
	}
	
	public Integer contadorBioPorAsignatura(int bioId){
		Collection<Syllabus> s = syllabusService.getSyllabyPerBio(bioId);
		Collection<Subject> aux = new ArrayList<Subject>();
		for(Syllabus sy : s){
			Subject sj = sy.getSubject();
			if(!(aux.contains(sj))){
				aux.add(sj);
			}
		}
		return aux.size();
		
	}
	
	public Integer counterBioByLecturer(int lecturerId){
		int res = 0;
		Collection<Syllabus> s = syllabusService.findByLecturer(lecturerId);
		for(Syllabus sy : s){
			res += sy.getBiographyEntries().size();
		}
		return res;
	}
	
	
		
}
