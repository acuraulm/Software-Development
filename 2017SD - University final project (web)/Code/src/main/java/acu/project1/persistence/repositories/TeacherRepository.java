package acu.project1.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acu.project1.persistence.entities.Teacher;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{
    Teacher findByName(String name);

	Teacher findByUsername(String username);	
	
}
