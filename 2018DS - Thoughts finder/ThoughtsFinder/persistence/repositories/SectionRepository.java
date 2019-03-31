package acuraulm.ThoughtsFinder.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acuraulm.ThoughtsFinder.persistence.entities.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

	//Category findByName();

}
