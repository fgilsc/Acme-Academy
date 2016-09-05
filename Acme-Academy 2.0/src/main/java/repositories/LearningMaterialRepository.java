package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.LearningMaterial;

@Repository
public interface LearningMaterialRepository extends JpaRepository<LearningMaterial,Integer>{

	@Query("select avg(g.learningMaterials.size) from Group g")
	Double getAverageLearningMaterialsPerGroup();
	
	@Query("select l.learningMaterials from Lecturer l where l.id=?1")
	Collection<LearningMaterial> findBylecturer(Integer lecturerId);
	
	@Query("select g.learningMaterials from Group g where g.id=?1")
	Collection<LearningMaterial> findByGroup(Integer groupId);
}
