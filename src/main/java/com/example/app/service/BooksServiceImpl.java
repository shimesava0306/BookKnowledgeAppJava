package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Books;
import com.example.app.mapper.BooksMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService{
	
	private final BooksMapper booksMapper;

	@Override
	public List<Books> getBooksList() throws Exception {
		return booksMapper.selectAll();
	}

	@Override
	public void addBooks(Books books) throws Exception {
		booksMapper.addBooks(books);
	}

	@Override
	public Books getBooksById(Integer id) throws Exception {
		return booksMapper.selectById(id);
	}

}
