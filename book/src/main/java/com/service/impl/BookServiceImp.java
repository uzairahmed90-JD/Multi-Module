package com.service.impl;

import com.repository.BookRepository;
import com.service.BookService;
import com.tut.common.dto.BookDto;
import com.tut.common.entity.Book;
import com.tut.common.exception.ResourceNotFoundException;
import com.tut.common.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private  BookRepository repository;
//    public BookServiceImp(BookRepository repository){
//        this.repository=repository;
//    }

    @Override
    public BookDto addBook(BookDto dto) {
        Book b=BookMapper.toMap(dto);
        return BookMapper.toDto(repository.save(b));
    }

    @Override
    public List<BookDto> getAll() {
        return repository.findAll().stream().map(BookMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookDto getById(Long id) {
        Book b= repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("book not found "));
        return BookMapper.toDto(b);
    }

    @Override
    public BookDto updateBook(Long id, BookDto dto) {
        Book b= repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("book not found "));
        BookMapper.update(b,dto);
        return  BookMapper.toDto(repository.save(b));
    }

    @Override
    public void delete(Long id) {
        Book b= repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("book not found "));
        repository.deleteById(id);
    }
}
