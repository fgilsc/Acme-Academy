package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Assesment;


@Repository
public interface AssesmentRepository  extends JpaRepository<Assesment,Integer>{
	
	@Query("select d.assesments from Deliverable d where d.id=?1")
	Collection<Assesment> findByDeliverable(int deliId);

}
