package com.service;

import com.tut.common.dto.IssueBookDto;

import java.util.List;

public interface IssueBookService {
    IssueBookDto issueBook(IssueBookDto dto);
    List<IssueBookDto> getAllIssuesBook();
    IssueBookDto getIssueBookById(Long id);
    IssueBookDto updateIssueBook(Long id, IssueBookDto dto);
    void deleteIssueBook(Long id);
}
