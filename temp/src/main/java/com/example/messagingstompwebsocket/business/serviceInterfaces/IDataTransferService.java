package com.example.messagingstompwebsocket.business.serviceInterfaces;

import com.example.messagingstompwebsocket.business.transfer.UserDTO;
import com.example.messagingstompwebsocket.business.transfer.AuthorDTO;
import com.example.messagingstompwebsocket.business.transfer.BookDTO;
import com.example.messagingstompwebsocket.business.transfer.BookProgressDTO;
import com.example.messagingstompwebsocket.business.transfer.ReaderDTO;
import com.example.messagingstompwebsocket.business.transfer.ReviewDTO;
import com.example.messagingstompwebsocket.persistence.entities.User;
import com.example.messagingstompwebsocket.persistence.entities.Book;
import com.example.messagingstompwebsocket.persistence.entities.BookProgress;
import com.example.messagingstompwebsocket.persistence.entities.Review;

public interface IDataTransferService {

	public UserDTO getUserDTO(User user);
	public BookDTO getBookDTO(Book book);
	public AuthorDTO getAuthorDTO(User user);
	public ReaderDTO getReaderDTO(User user);
	public BookProgressDTO getBookProgressDTO(BookProgress bookProgress);
	public ReviewDTO getReviewDTO(Review review);
	public User getUser(UserDTO userDTO);
	public BookProgress getBook(BookProgressDTO bookProgressDTO);
	public Review getReview(ReviewDTO reviewDTO);
	public Book getBook(BookDTO bookDTO);
}
