package com.example.messagingstompwebsocket.business.transfer;

import java.util.HashSet;
import java.util.Set;



public class ReaderDTO extends UserDTO{

	private Set<ReviewDTO> reviews = new HashSet<>();
	private Set<BookProgressDTO> booksInProgress = new HashSet<>();
	
	public Set<ReviewDTO> getReviews() {
		return reviews;
	}
	public void setReviews(Set<ReviewDTO> reviews) {
		this.reviews = reviews;
	}
	public Set<BookProgressDTO> getBooksInProgress() {
		return booksInProgress;
	}
	public void setBooksInProgress(Set<BookProgressDTO> booksInProgress) {
		this.booksInProgress = booksInProgress;
	}
	
	
}
