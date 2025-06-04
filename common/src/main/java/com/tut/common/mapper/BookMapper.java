package com.tut.common.mapper;

import com.tut.common.dto.BookDto;
import com.tut.common.entity.Book;

public class BookMapper {
    public static BookDto toDto(Book book){
        return BookDto.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
    }
    public static Book toMap(BookDto dto){
        return Book.builder()
                .bookId(dto.getBookId())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .build();
    }
    public  static void update(Book book,BookDto dto){
        if(dto.getTitle()!=null)book.setTitle(dto.getTitle());
        if(dto.getAuthor()!=null)book.setAuthor(dto.getAuthor());
    }
}
