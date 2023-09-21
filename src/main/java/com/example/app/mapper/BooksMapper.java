package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Books;

@Mapper
public interface BooksMapper {
	List<Books> selectAll() throws Exception;
	Books selectById(Integer id) throws Exception;
	void addBooks(Books books);
}
