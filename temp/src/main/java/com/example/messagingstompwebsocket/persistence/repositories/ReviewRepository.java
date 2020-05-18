package com.example.messagingstompwebsocket.persistence.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.messagingstompwebsocket.persistence.entities.Review;

public interface ReviewRepository extends JpaRepository<Review,Long>{

}
