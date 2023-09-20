package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Books;

@Mapper
public interface BooksMapper {
	void addBooks(Books books);
}
