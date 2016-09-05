package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Deliverable;



@Repository
public interface DeliverableRepository extends JpaRepository<Deliverable,Integer>{

}
