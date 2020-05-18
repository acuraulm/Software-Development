package com.example.messagingstompwebsocket.persistence.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.messagingstompwebsocket.persistence.entities.Book;

public interface BookRepository extends JpaRepository<Book,Long>{

}
