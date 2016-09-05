package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Group;
import domain.LearningMaterial;
import domain.Lecturer;
import domain.Student;

import repositories.LearningMaterialRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class LearningMaterialService {
	
	//Managed repository -----------------------------------------
		@Autowired
		private LearningMaterialRepository lmrepo;
	//Supporting Services-----------------------------------------
		@Autowired
		private LecturerService lecturerService;
		@Autowired
		private StudentService studentService;
		
	//CRUDS Methods
		public LearningMaterial create(){
			UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);
			Lecturer l = lecturerService.findByUserAccount(loginNow);
			LearningMaterial res = new LearningMaterial();
			res.setLecturer(l);
			return res;
		}
		public LearningMaterial findOne(Integer lmId){
			LearningMaterial res = lmrepo.findOne(lmId);
			Assert.notNull(res);
			return res;
		}
		
		public void save(LearningMaterial lm){
			UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);
			Assert.notNull(lm);
			lmrepo.saveAndFlush(lm);
		}
		
		public void upload(LearningMaterial lm){
			UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);
			Assert.notNull(lm);
			lmrepo.save(lm);
		}
		
		public void remove(LearningMaterial lm){
			UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);
			Assert.notNull(lm);
			lmrepo.delete(lm);
		}
		
		//Other Bussines Methods
		public Collection<LearningMaterial> findByLecturer(){
			UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);
			int lecturerId = lecturerService.findByUserAccount(loginNow).getId();
			Collection<LearningMaterial> res = lmrepo.findBylecturer(lecturerId);
			Assert.notNull(res);
			return res;
		}
		
		public Collection<LearningMaterial> findByStudent(){
			UserAccount loginNow = LoginService.getPrincipal();
			studentService.isStudent(loginNow);
			Student s = studentService.findByUserAccount(loginNow);
			Collection<LearningMaterial> res = new ArrayList<LearningMaterial>();
			for(Group g : s.getGroups()){
				for(LearningMaterial lm : g.getLearningMaterials()){
					res.add(lm);
				}
			}
			Assert.notNull(res);
			return res;
		}
		
		public Collection<LearningMaterial> findByGroup(Integer groupId){
			Collection<LearningMaterial> res = lmrepo.findByGroup(groupId);
			Assert.notNull(res);
			return res;		//Este metodo puede usarlo tanto Lecturer como Student y acceder desde una lista de grupos
		}
		
		public Double getAverageLearningMaterialsPerGroup(){
			return lmrepo.getAverageLearningMaterialsPerGroup();
		}
		
}
