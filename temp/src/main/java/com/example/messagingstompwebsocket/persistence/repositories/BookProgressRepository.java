package com.example.messagingstompwebsocket.persistence.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.messagingstompwebsocket.persistence.entities.BookProgress;

public interface BookProgressRepository extends JpaRepository<BookProgress,Long>{

}
