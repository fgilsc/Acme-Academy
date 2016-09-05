package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Group;


@Repository
public interface GroupRepository extends JpaRepository<Group,Integer>{

	@Query("select avg(g.students.size) from Group g")
	Double getAverageStudentsPerGroup();
	
	@Query("select g from Group g where g.students.size > 0.2*(select avg(g1.students.size) from Group g1)")
	Collection<Group> findGroupsWithMoreThanTwentyPercentOfAvgStudentsPerGroup();
	
	@Query("select g from Group g where g.students.size < 0.2*(select avg(g1.students.size) from Group g1)")
	Collection<Group> findGroupsWithLessThanTwentyPercentOfAvgStudentsPerGroup();
	
	@Query("select l.groups from Lecturer l where l.id=?1")
	Collection<Group> findByLecturer(int id);
	
	@Query("select s.groups from Student s where s.id=?1")
	Collection<Group> findByStudent(int id);
}
