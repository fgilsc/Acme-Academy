package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;

import domain.Lecturer;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer,Integer>{
	
	@Query("select avg(l.subjects.size) from Lecturer l")
	Double getAverageSubjectPerLecturer();
	
	@Query("select l from Lecturer l where l.subjects.size > 0.2*(select avg(l.subjects.size) from Lecturer l)")
	Collection<Lecturer> findWithMoreTwentyPercentOfSubjectsAvg();
	
	@Query("select l from Lecturer l where l.subjects.size < 0.2*(select avg(l.subjects.size) from Lecturer l)")
	Collection<Lecturer> findWithLessTwentyPercentOfSubjectsAvg();
	
	@Query("select s.lecturers from Subject s where s.id=?1")
	Collection<Lecturer> findBySubject(int id);
	
	@Query("select a from Lecturer a where a.userAccount=?1")
	Lecturer findByUserAccount(UserAccount userAccount);
	
	@Query("select l from Lecturer l where l.learningMaterials.size = (select max(l2.learningMaterials.size) from Lecturer l2)")
	Collection<Lecturer> lecturersWithMoreLearningMaterials();
	
	@Query("select a.lecturer from Assigment a where a.rubrics.size=(select max(a2.rubrics.size) from Assigment a2)")
	Collection<Lecturer> getLecturerWithMoreRubricsPerAssignment();
	
	@Query("select a.lecturer from Assigment a where a.rubrics.size=(select min(a2.rubrics.size) from Assigment a2)")
	Collection<Lecturer> getLecturerWithLessRubricsPerAssignment();
}
