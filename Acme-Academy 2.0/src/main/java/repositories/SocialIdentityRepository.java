package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialIdentity;

@Repository
public interface SocialIdentityRepository  extends JpaRepository<SocialIdentity,Integer>{
	
	@Query("select avg(a.socialIdentities.size) from Actor a")
	Double getAverageSocialIdentitiesPerActor();
	
	@Query("select a.socialIdentities from Actor a where a.id=?1")
	Collection<SocialIdentity>findByActor(Integer actorId);
}
