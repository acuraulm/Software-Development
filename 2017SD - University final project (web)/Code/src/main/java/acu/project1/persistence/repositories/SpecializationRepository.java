package acu.project1.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acu.project1.persistence.entities.Course;
import acu.project1.persistence.entities.Grade;
import acu.project1.persistence.entities.Groupa;
import acu.project1.persistence.entities.Specialization;
import acu.project1.persistence.entities.Student;

import java.util.List;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long>{


}
