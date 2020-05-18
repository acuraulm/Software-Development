package com.example.messagingstompwebsocket.business.services;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.messagingstompwebsocket.business.serviceInterfaces.IDataTransferService;
import com.example.messagingstompwebsocket.business.transfer.UserDTO;
import com.example.messagingstompwebsocket.business.transfer.AuthorDTO;
import com.example.messagingstompwebsocket.business.transfer.BookDTO;
import com.example.messagingstompwebsocket.business.transfer.BookProgressDTO;
import com.example.messagingstompwebsocket.business.transfer.ReaderDTO;
import com.example.messagingstompwebsocket.business.transfer.ReviewDTO;
import com.example.messagingstompwebsocket.persistence.entities.User;
import com.example.messagingstompwebsocket.persistence.repositories.BookProgressRepository;
import com.example.messagingstompwebsocket.persistence.repositories.BookRepository;
import com.example.messagingstompwebsocket.persistence.repositories.ReviewRepository;
import com.example.messagingstompwebsocket.persistence.repositories.UserRepository;
import com.example.messagingstompwebsocket.persistence.entities.Book;
import com.example.messagingstompwebsocket.persistence.entities.BookProgress;
import com.example.messagingstompwebsocket.persistence.entities.Review;

@Service
public class DataTransferService implements IDataTransferService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	BookProgressRepository bookProgressRepository;
	
	@Override
	public UserDTO getUserDTO(User user) {

		UserDTO userDTO = new UserDTO();

		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setUserRole(user.getUserRole());
		
		return userDTO;
	}
	
	@Override
	public User getUser(UserDTO userDTO) {
		Optional<User> optionalUser = userRepository.findById(userDTO.getId());
		User user;
		try {
			user = optionalUser.get();
			user.setId(userDTO.getId());
			user.setName(userDTO.getName());
			user.setUsername(userDTO.getUsername());
			user.setPassword(userDTO.getPassword());
			user.setUserRole(userDTO.getUserRole());
		}catch(NoSuchElementException e) {
			user = new User();
			user.setId(userDTO.getId());
			user.setName(userDTO.getName());
			user.setUserRole(userDTO.getUserRole());
			user.setUsername(userDTO.getUsername());
			user.setPassword(userDTO.getPassword());
		}
		return user;
	}
	
	@Override
	public AuthorDTO getAuthorDTO(User user) {

		AuthorDTO authorDTO = new AuthorDTO();

		authorDTO.setId(user.getId());
		authorDTO.setName(user.getName());
		authorDTO.setUserRole(user.getUserRole());
		authorDTO.setUsername(user.getUsername());
		authorDTO.setPassword(user.getPassword());
		
		Set<BookDTO> writtenBooks = new HashSet<BookDTO>();
		for(Book b:user.getWrittenBooks())
		{
			writtenBooks.add(getBookDTO(b));
		}
		authorDTO.setWrittenBooks(writtenBooks);
		
		return authorDTO;
	}
	
	
	@Override
	public ReaderDTO getReaderDTO(User user) {

		ReaderDTO readerDTO = new ReaderDTO();

		readerDTO.setId(user.getId());
		readerDTO.setName(user.getName());
		readerDTO.setUserRole(user.getUserRole());
		readerDTO.setUsername(user.getUsername());
		readerDTO.setPassword(user.getPassword());
		
		Set<BookProgressDTO> booksInProgress = new HashSet<BookProgressDTO>();
		for(BookProgress b: user.getBooksInProgress())
		{
			booksInProgress.add(getBookProgressDTO(b));
		}
		Set<ReviewDTO> reviews = new HashSet<ReviewDTO>();
		
		readerDTO.setBooksInProgress(booksInProgress);
		readerDTO.setReviews(reviews);
		
		return readerDTO;
	}

	@Override
	public BookDTO getBookDTO(Book book) {
		
		BookDTO bookDTO = new BookDTO();
		bookDTO.setAuthorId(book.getAuthor().getId());
		bookDTO.setAuthorName(book.getAuthor().getName());
		bookDTO.setGenre(book.getGenre());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setId(book.getId());
		
		return bookDTO;
	}

	@Override
	public Book getBook(BookDTO bookDTO) {
		Optional<Book> optionalBook = bookRepository.findById(bookDTO.getId());
		Optional<User> optionalUser = userRepository.findById(bookDTO.getAuthorId());
		User user;
		Book book;
		try {
			user = optionalUser.get();
		}catch(NoSuchElementException e) {
			return null;
		}
		try {
			book = optionalBook.get();
			book.setId(bookDTO.getId());
			book.setGenre(bookDTO.getGenre());
			book.setTitle(bookDTO.getTitle());
			book.setAuthor(user);
		}catch(NoSuchElementException e) {
			book = new Book();
			book.setId(bookDTO.getId());
			book.setGenre(bookDTO.getGenre());
			book.setTitle(bookDTO.getTitle());
			book.setAuthor(user);
		}
		
		return book;
	}
	
	
	@Override
	public BookProgressDTO getBookProgressDTO(BookProgress bookProgress) {
		
		BookProgressDTO bookProgressDTO = new BookProgressDTO();
		
		/* Aici faci mapare pentru fiecare field din Entity --> DTO */
		
		
		return bookProgressDTO;
	}
	
	@Override
	public BookProgress getBook(BookProgressDTO bookProgressDTO) {
		BookProgress bookProgress = new BookProgress();
		
		/* Aici faci mapare pentru fiecare field din DTO --> Entity */
		return bookProgress;
	}
	
	@Override
	public ReviewDTO getReviewDTO(Review review) {
		
		ReviewDTO reviewDTO = new ReviewDTO();
		
		/* Aici faci mapare pentru fiecare field din Entity --> DTO */
		
		return reviewDTO;
	}
	
	@Override
	public Review getReview(ReviewDTO reviewDTO) {
		
		Review review = new Review();
		
		/* Aici faci mapare pentru fiecare field din DTO --> Entity */
		
		return review;
	}
	

}
