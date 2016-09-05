package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rubric;



@Repository
public interface RubricRepository extends JpaRepository<Rubric,Integer>{
	
	@Query("select avg(a.rubrics.size) from Assigment a")
	Double getAverageNumberRubricPerAssigment();
	
	@Query("select a.rubrics from Assigment a where a.id=?1")
	Collection<Rubric> findByAssigment(int assigmentId);
	
	@Query("select a.rubrics from Assigment a where a.lecturer.id=?1")
	Collection<Rubric> findByLecturer(int lecturerId);
}
