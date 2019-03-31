package acuraulm.ThoughtsFinder.persistence.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acuraulm.ThoughtsFinder.persistence.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
