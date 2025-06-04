package com.service.impl;

import com.repository.BookRepository;
import com.repository.IssueBookRepository;
import com.service.IssueBookService;
import com.tut.common.dto.IssueBookDto;
import com.tut.common.entity.Book;
import com.tut.common.entity.IssueBook;
import com.tut.common.entity.Student;
import com.tut.common.exception.ResourceNotFoundException;
import com.tut.common.mapper.IssueBookMapper;
import com.tut.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class IssueBookServiceImp implements IssueBookService {
    @Autowired
    private IssueBookRepository repo;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public IssueBookDto issueBook(IssueBookDto dto) {
        Student st =studentRepository.findById(dto.getStudentId()).orElseThrow(() -> new ResourceNotFoundException("not found std id "+dto.getStudentId()));
        Book book =bookRepository.findById(dto.getBookId()).orElseThrow(() -> new ResourceNotFoundException("not found std id "+dto.getBookId()));
        IssueBook issueBook=IssueBookMapper.toMap(st,book,dto);
        issueBook.setIssueDate(dto.getIssueDate());
        issueBook.setDueDate(dto.getDueDate());
        issueBook.setReturnDate(dto.getReturnDate());
        return IssueBookMapper.toDto(repo.save(issueBook));
    }

    @Override
    public List<IssueBookDto> getAllIssuesBook() {
        return repo.findAll()
                .stream()
                .map(IssueBookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public IssueBookDto getIssueBookById(Long id) {
        IssueBook ib=repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not dound"));
        return IssueBookMapper.toDto(ib);
    }

    @Override
    public IssueBookDto updateIssueBook(Long id, IssueBookDto dto) {
        IssueBook issueBook=repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found id"));
        if(dto.getStudentId()!=null){
        Student st =studentRepository.findById(dto.getStudentId()).orElseThrow(() -> new ResourceNotFoundException("not found std id "+dto.getStudentId()));
            issueBook.setStudent(st);
        }
        if(dto.getBookId()!=null) {
            Book book=bookRepository.findById(dto.getBookId()).orElseThrow(() -> new ResourceNotFoundException("not found std id " + dto.getBookId()));
            issueBook.setBook(book);
        }

       IssueBookMapper.update(issueBook,dto);


        return  IssueBookMapper.toDto(repo.save(issueBook));
    }

    @Override
    public void deleteIssueBook(Long id) {
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found id "));
        repo.deleteById(id);
    }
}
