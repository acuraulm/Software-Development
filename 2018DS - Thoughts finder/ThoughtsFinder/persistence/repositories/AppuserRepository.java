package acuraulm.ThoughtsFinder.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acuraulm.ThoughtsFinder.persistence.entities.Appuser;

@Repository
public interface AppuserRepository extends JpaRepository<Appuser, Long> {

	Appuser findByUsername(String username);

}
