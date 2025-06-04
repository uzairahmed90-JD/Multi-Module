package com.tut.common.mapper;

import com.tut.common.dto.BookDto;
import com.tut.common.dto.IssueBookDto;
import com.tut.common.entity.Book;
import com.tut.common.entity.IssueBook;
import com.tut.common.entity.Student;

public class IssueBookMapper {

    public static IssueBookDto toDto( IssueBook issue){
        return IssueBookDto.builder()
                .issueId(issue.getIssueId())
                .studentId(issue.getStudent().getStudentId())
                .bookId(issue.getBook().getBookId())
                .issueDate(issue.getIssueDate())
                .dueDate(issue.getDueDate())
                .returnDate(issue.getReturnDate())
                .build();
    }

    public static IssueBook toMap(Student st,Book book,IssueBookDto dto){

        return IssueBook.builder()
                .issueId(dto.getIssueId())
                .student(st)
                .book(book)
                .issueDate(dto.getIssueDate())
                .dueDate(dto.getDueDate())
                .returnDate(dto.getReturnDate())
                .build();
    }
    public static void update(IssueBook ib,IssueBookDto dto){
       // if(dto.getBookId()!=null)ib.setBook(dto.getBookId());
        if(dto.getIssueDate()!=null)ib.setIssueDate(dto.getIssueDate());
        if(dto.getReturnDate()!=null)ib.setReturnDate(dto.getReturnDate());
        if(dto.getDueDate()!=null)ib.setDueDate(dto.getDueDate());
    }
}
