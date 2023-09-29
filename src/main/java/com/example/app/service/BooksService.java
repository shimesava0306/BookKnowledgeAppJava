package com.example.app.service;

import java.util.List;

import com.example.app.domain.Books;

public interface BooksService {
	List<Books> getBooksList() throws Exception;
	Books getBooksById(Integer id) throws Exception;
	void addBooks(Books books) throws Exception;
	List<Books> searchBooks(String keyword) throws Exception;
}
