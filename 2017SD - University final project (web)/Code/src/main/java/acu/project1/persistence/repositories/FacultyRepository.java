package acu.project1.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acu.project1.persistence.entities.Course;
import acu.project1.persistence.entities.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Course, Long>{
	Faculty findByName(String name);
}
