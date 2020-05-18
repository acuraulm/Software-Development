package com.example.messagingstompwebsocket.business.transfer;

import java.util.HashSet;
import java.util.Set;



public class AuthorDTO extends UserDTO{
	
    private Set<BookDTO> writtenBooks = new HashSet<>();

	public Set<BookDTO> getWrittenBooks() {
		return writtenBooks;
	}

	public void setWrittenBooks(Set<BookDTO> writtenBooks) {
		this.writtenBooks = writtenBooks;
	}
    
    
	
}
