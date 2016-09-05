package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Assigment;
import domain.Deliverable;
import domain.Group;
import domain.LearningMaterial;
import domain.Student;
import forms.GroupForm;


import repositories.GroupRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class GroupService {
	
	//Managed repository -----------------------------------------
		@Autowired
		private GroupRepository groupRepository;
		
//		Supporting Service-----------------------------------------
		@Autowired
		private LecturerService lecturerService;
		@Autowired
		private StudentService studentService;
		
		//Simple CRUD methods -------------------------------------------------
		
		public Group create(){
			UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);
			Group res = new Group();
			res.setLecturer(lecturerService.findByUserAccount(loginNow));
			Collection<Student> students = new ArrayList<Student>();
			res.setStudents(students);
			Collection<Assigment> a = new ArrayList<Assigment>();
			res.setAssigments(a);
			Collection<Deliverable> cd = new ArrayList<Deliverable>();
			res.setDeliverables(cd);
			Collection<LearningMaterial> lm = new ArrayList<LearningMaterial>();
			
			res.setLearningMaterials(lm);
			return res;
		}
		
		public Collection<Group> findAll(){
			Collection<Group>res = groupRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		
		public Group findOne(int id){
			Group res = groupRepository.findOne(id);
			Assert.notNull(res);
			return res;
		}
		
		public void save(Group g){
			UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);
			Assert.notNull(g);
			groupRepository.saveAndFlush(g);
		}
		
		public void update(Group g){
			UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);
			Assert.notNull(g);
			groupRepository.save(g);
		}
		
		public void delete(Group g){
			Assert.notNull(g);
			groupRepository.delete(g);
		}
	// Other business methods -------------------------------------------------	
		
		public Double getAverageStudentsPerGroup(){
			return groupRepository.getAverageStudentsPerGroup();
		}
		
		public Collection<Group> findGroupsWithMoreThanTwentyPercentOfAvgStudentsPerGroup(){
			return groupRepository.findGroupsWithMoreThanTwentyPercentOfAvgStudentsPerGroup();
		}
		
		public Collection<Group> findGroupsWithLessThanTwentyPercentOfAvgStudentsPerGroup(){
			return groupRepository.findGroupsWithLessThanTwentyPercentOfAvgStudentsPerGroup();
		}
		
		public Collection<Group> findByLecturer(int id){
			Collection<Group> res = groupRepository.findByLecturer(id);
			return res;
		}
		
		public Collection<Group> findByStudent(int id){
			Collection<Group> res = groupRepository.findByStudent(id);
			return res;
		}
		
		public Collection<Group> findByLecturerLogin(){
			UserAccount loginNow = LoginService.getPrincipal();
			lecturerService.isLecturer(loginNow);
			int lecturerId = lecturerService.findByUserAccount(loginNow).getId();
			Collection<Group> res = findByLecturer(lecturerId);
			return res;
		}
		
		public Collection<Group> findByStudentLogin(){
			UserAccount loginNow = LoginService.getPrincipal();
			studentService.isStudent(loginNow);
			int studentId = studentService.findByUserAccount(loginNow).getId();
			Collection<Group> res = findByStudent(studentId);
			return res;
		}
		
		public void enrol(Group g){
			UserAccount loginNow = LoginService.getPrincipal();
			studentService.isStudent(loginNow);
			Student s = studentService.findByUserAccount(loginNow);
			if(!(s.getGroups().contains(g))){
				g.getStudents().add(s);
				s.getGroups().add(g);
			}	
			studentService.update(s);
		}
		
		public Group recontruct(GroupForm gf){
			Group res = create();
			res.setAcademicYear(gf.getAcademicYear());
			res.setName(gf.getName());
			res.setDescription(gf.getDescription());
			res.setSubject(gf.getSubject());
			return res;
		}
}
