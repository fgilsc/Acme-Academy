package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import domain.Assigment;

import domain.Group;
import domain.Subject;
import domain.Syllabus;
import forms.SubjectForm;

import repositories.SubjectRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class SubjectService {
	
//	Managed repository -----------------------------------------
	@Autowired
	private SubjectRepository subjectRepo;
	
//	Supporting Service-----------------------------------------
	@Autowired
	private AdministratorService adminService;
	@Autowired
	private LecturerService lecturerService;

	//Simple CRUD methods -------------------------------------------------
	
	public Subject create(){
		UserAccount loginNow = LoginService.getPrincipal();
		adminService.isAdmin(loginNow);
		Subject res = new Subject();
		Collection<Assigment> ass = new ArrayList<Assigment>();
		Collection<Group> gr = new ArrayList<Group>();
		res.setGroups(gr);
		res.setAssigments(ass);
		Collection<Syllabus>s = new ArrayList<Syllabus>();
		res.setSyllabi(s);
		return res;
	}
	
	public Collection<Subject> findAll(){
		Collection<Subject> res = subjectRepo.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Subject findOne(int id){
		Subject res = subjectRepo.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public void save(Subject s){
		UserAccount loginNow = LoginService.getPrincipal();
		adminService.isAdmin(loginNow);
		Assert.notNull(s);
		subjectRepo.saveAndFlush(s);
	}
	
	public void update(Subject s){
		UserAccount loginNow = LoginService.getPrincipal();
		adminService.isAdmin(loginNow);
		Assert.notNull(s);
		subjectRepo.save(s);
	}
	
	public void delete(Subject s){
		Assert.notNull(s);
		subjectRepo.delete(s);
	}
	
	
// Other Bussines methods
	public Collection<Subject> findByLecturer(int id){
		/*Lecturer l = lecturerService.findOne(id);
		Collection<Subject> res = l.getSubjects();*/
		Collection<Subject> res = subjectRepo.findByLecturer(id);
		return res;
	}
	
	public Collection<Subject> findByLoginNow(){
		UserAccount loginNow = LoginService.getPrincipal();
		lecturerService.isLecturer(loginNow);
		int lecturerId = lecturerService.findByUserAccount(loginNow).getId();
		Collection<Subject> res = findByLecturer(lecturerId);
		return res;
	}
	
	public Subject getSubjectWithMoreLearningMaterials(){ //I think that it is a Climb-Algorithm
		Subject res = null;
		int maxLM = 0;
		Collection<Subject> all = findAll();
		for(Subject s : all){
			int lm = 0;
			for(Group g : s.getGroups()){
				int lmG = 0;
				lmG = g.getLearningMaterials().size();
				lm = lm + lmG;
			}
			if(lm>maxLM){
				maxLM = lm;
				res = s;
			}
		}
		return res;
	}
	
	public Subject recontruct(SubjectForm sf){
		Subject res = create();
		res.setCode(sf.getCode());
		res.setLecturers(sf.getLecturers());
		
		res.setTitle(sf.getTitle());
		return res;
	}
	
	public Subject recontructEdit(SubjectForm sf,Integer subjectId){
		Subject res = findOne(subjectId);
		res.setCode(sf.getCode());
		res.setLecturers(sf.getLecturers());
		
		res.setTitle(sf.getTitle());
		return res;	
	}
	
	public SubjectForm constructForm(Subject s ){
		Assert.notNull(s);
		SubjectForm res = new SubjectForm();
		res.setCode(s.getCode());
		
		res.setTitle(s.getTitle());
		res.setLecturers(s.getLecturers());
		return res;
	}
	
	/*public Subject SubjectWithTheLargestBibliography(){
		Collection<Subject> all = findAll();
		Subject res = null;
		int be = 0;
		for(Subject s : all){
			int beSubj = 0;
			for(Syllabus sy : s.getSyllabi()){
				 beSubj += sy.getBiographyEntries().size();
				int anyoActual = Calendar.getInstance().get(Calendar.YEAR);
				if(beSubj> be && sy.getAcademicYear() == anyoActual){ // Comprueba que estamos en el año actual
						be = beSubj;
						res = s;
			}		
			}
		}
		return res;
	}*/
	
	public Subject SubjectWithTheLargestBibliography(){
		Collection<Subject> all = findAll();
		Subject res = null;
		int be = 0;
		for(Subject s : all){
			//Collection<BiographyEntry> bio = new ArrayList<BiographyEntry>();
			for(Syllabus sy : s.getSyllabi()){
				int anyoActual = Calendar.getInstance().get(Calendar.YEAR);
				int beSy = 0;
				if(sy.getAcademicYear() == anyoActual){
					beSy = sy.getBiographyEntries().size();
					if(beSy>be){
						be = beSy;
						res = s;
					}
				}
			}
		}
		return res;
		
		
	}
	
	
}
