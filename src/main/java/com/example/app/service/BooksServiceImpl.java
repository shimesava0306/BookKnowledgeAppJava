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
	public void addBooks(Books books,String userId) throws Exception {
		// セッションからユーザーIDを取得
		books.setUserId(userId);
		booksMapper.addBooks(books);
	}

	@Override
	public Books getBooksById(Integer id) throws Exception {
		return booksMapper.selectById(id);
	}

	@Override
	public List<Books> searchBooks(String keyword) throws Exception {
    return booksMapper.searchBooks(keyword);
}

	@Override
	public List<Books> selectByUserId(String userId) {
		return booksMapper.selectByUserId(userId);
	}

	@Override
	public void getBookDeleteById(Integer id) {
		booksMapper.BookDeleteById(id);
	}

	@Override
	public void updateBooks(Books books) throws Exception {
		booksMapper.updateBooks(books);
	}

}
