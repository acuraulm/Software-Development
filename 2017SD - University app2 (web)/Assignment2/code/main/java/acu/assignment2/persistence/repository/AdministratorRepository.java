package acu.assignment2.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acu.assignment2.persistence.entities.Administrator;

import java.util.List;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long>{
    Administrator findByName(String name);

	Administrator findByUsername(String username);	
	
}
