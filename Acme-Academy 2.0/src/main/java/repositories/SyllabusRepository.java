package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Syllabus;



@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus,Integer>{
	
	
	@Query("select avg(s.syllabi.size) from Subject s")
	Double getAverageNumberSyllabiPerSubject();
	
	@Query("select s from Syllabus s where s.lecturer.id = ?1")
	Collection<Syllabus> findByLecturer(Integer lecturerId);
	
	@Query("select s.syllabi from Subject s where s.id = ?1")
	Collection<Syllabus> getBySubject( int id);
	
	@Query("select b.syllabi from BiographyEntry b where b.id =?1")
	Collection<Syllabus> getSyllabyPerBio(int bioId);
}
