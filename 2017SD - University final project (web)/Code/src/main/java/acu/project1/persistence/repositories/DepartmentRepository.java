package acu.project1.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acu.project1.persistence.entities.Course;
import acu.project1.persistence.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Course, Long>{
	Department findByName(String name);
}
