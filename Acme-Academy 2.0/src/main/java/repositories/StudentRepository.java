package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;


import domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{
	
	@Query("select ((select count(s) from Group g join g.students s)/count(o) * 1.0)  from Subject o")
	Double getStudentsAvgPerSubject();
	
	@Query("select s from Student s where s.groups.size > 0.2 *(select (count(o)/(select count(s) from Group g join g.students s) * 1.0)  from Subject o)")
	Collection<Student> getStudentWithEnrolledMore20percentAvgSubject();
	
	@Query("select s from Student s where s.groups.size < 0.2 *(select (count(o)/(select count(s) from Group g join g.students s) * 1.0)  from Subject o)")
	Collection<Student> getStudentWithEnrolledLess20percentAvgSubject();
	
	
	@Query("select a from Student a where a.userAccount=?1")
	Student findByUserAccount(UserAccount userAccount);

}
