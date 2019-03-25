package acu.project1.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acu.project1.persistence.entities.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

	Course findByName(String name);
	
}
