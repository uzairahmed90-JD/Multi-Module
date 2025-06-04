package com.service;

import com.tut.common.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto addBook(BookDto dto);
    List<BookDto> getAll();
    BookDto getById(Long id);
    BookDto updateBook(Long id,BookDto dto);
    void delete(Long id);
}
