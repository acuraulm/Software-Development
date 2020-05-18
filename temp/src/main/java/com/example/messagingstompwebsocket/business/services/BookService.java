package com.example.messagingstompwebsocket.business.services;

import java.beans.Transient;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.messagingstompwebsocket.business.serviceInterfaces.IBookService;
import com.example.messagingstompwebsocket.business.serviceInterfaces.IDataTransferService;
import com.example.messagingstompwebsocket.business.transfer.BookDTO;
import com.example.messagingstompwebsocket.persistence.entities.Book;
import com.example.messagingstompwebsocket.persistence.entities.User;
import com.example.messagingstompwebsocket.persistence.repositories.BookRepository;
import com.example.messagingstompwebsocket.persistence.repositories.UserRepository;

@Service
@Transactional
public class BookService implements IBookService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	IDataTransferService dataTransferService;

	@Override
	public BookDTO publishBook(BookDTO bookDTO) {
		Book book = dataTransferService.getBook(bookDTO);	
		
		if(book != null) {
			book = bookRepository.save(book);
			
			return dataTransferService.getBookDTO(book);
		}
		
		return null;
	}
	
	@Override
	public Set<BookDTO> findAllBooks() {

		Set<BookDTO> returnSet = new HashSet<BookDTO>();
		for(Book b: bookRepository.findAll()) {
			returnSet.add(dataTransferService.getBookDTO(b));
		}
		
		return returnSet;
	}
	
	/* OLD 
	@Override
	public BookDTO publishBook(BookDTO bookDTO) {
		Book book = new Book();
		
		Optional<User> optionalUser = userRepository.findById(bookDTO.getAuthorId());
		User user = optionalUser.get();
		
		System.out.println(user.getId() + "\n" + user.getName());
		
		if(user != null) {
			book.setAuthor(user);
			book.setGenre(bookDTO.getGenre());
			book.setId(bookDTO.getId());
			book.setTitle(bookDTO.getTitle());
			book = bookRepository.save(book);
			
			System.out.println(book.getAuthor().getName());
			return bookDTO;
		}
		return null;
	}
	

	@Override
	public Set<BookDTO> findAllBooks() {

		Set<BookDTO> returnSet = new HashSet<BookDTO>();
		for(Book b: bookRepository.findAll()) {
			BookDTO bookDTO = new BookDTO();
			bookDTO.setId(b.getId());
			bookDTO.setAuthorId(b.getAuthor().getId());
			bookDTO.setTitle(b.getTitle());
			bookDTO.setGenre(b.getGenre());
			bookDTO.setAuthorName(b.getAuthor().getName());
			returnSet.add(bookDTO);
		}
		
		return returnSet;
	}
	 */
	
}
