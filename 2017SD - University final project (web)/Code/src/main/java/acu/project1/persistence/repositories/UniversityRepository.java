package acu.project1.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acu.project1.persistence.entities.Course;
import acu.project1.persistence.entities.University;

@Repository
public interface UniversityRepository extends JpaRepository<Course, Long>{
	University findByName(String name);
}
