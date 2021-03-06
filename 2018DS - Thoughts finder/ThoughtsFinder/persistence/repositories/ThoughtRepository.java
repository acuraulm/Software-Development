package acuraulm.ThoughtsFinder.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acuraulm.ThoughtsFinder.persistence.entities.Thought;

@Repository
public interface ThoughtRepository extends JpaRepository<Thought, Long>{

}
