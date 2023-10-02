package com.example.app.service;

import java.util.List;

import com.example.app.domain.Books;

public interface BooksService {
	List<Books> getBooksList() throws Exception;
	Books getBooksById(Integer id) throws Exception;
	void addBooks(Books books,String userId) throws Exception;
	List<Books> searchBooks(String keyword) throws Exception;
	List<Books> selectByUserId(String userId);
	void getBookDeleteById(Integer id);
	void updateBooks(Books books)throws Exception;
}
