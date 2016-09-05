package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.BiographyEntry;

@Repository
public interface BiographyEntryRepository extends JpaRepository<BiographyEntry,Integer>{
	
	@Query("select avg(s.biographyEntries.size) from Syllabus s")
	Double getAverageNumberBiographyEntriesPerSyllabus();
	
	@Query("select s.biographyEntries from Syllabus s where s.id = ?1")
	Collection <BiographyEntry> getBySyllabus( int id);
}
