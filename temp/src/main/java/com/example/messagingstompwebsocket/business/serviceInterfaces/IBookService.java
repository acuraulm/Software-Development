package com.example.messagingstompwebsocket.business.serviceInterfaces;

import java.util.Set;

import com.example.messagingstompwebsocket.business.transfer.BookDTO;

public interface IBookService {

	BookDTO publishBook(BookDTO bookDTO);
	Set<BookDTO> findAllBooks();
	
}
