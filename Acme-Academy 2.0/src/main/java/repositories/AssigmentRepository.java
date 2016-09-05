package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Assigment;


@Repository
public interface AssigmentRepository extends JpaRepository<Assigment,Integer>{
	
	
	
	@Query("select a from Assigment a where a.rubrics.size > 0.2*(select avg(a1.rubrics.size) from Assigment a1)")
	Collection<Assigment> getAssigmentWithTwentyPercetMoreThanAverageNumberRubricPerAssigment();
	
	@Query("select a from Assigment a where a.rubrics.size < 0.2*(select avg(a1.rubrics.size) from Assigment a1)")
	Collection<Assigment> getAssigmentWithTwentyPercetLessThanAverageNumberRubricPerAssigment();
	
	@Query("select l.assigments from Lecturer l where l.id=?1")
	Collection<Assigment>findByLecturer(int id);
}
