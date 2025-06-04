package com.controller;

import com.service.IssueBookService;
import com.tut.common.dto.IssueBookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issue")
@RequiredArgsConstructor
public class IssueBookController {

    private final  IssueBookService bookService;

    @PostMapping
    public ResponseEntity<IssueBookDto> issue(@RequestBody IssueBookDto dto){
        return ResponseEntity.ok(bookService.issueBook(dto));
    }
    @GetMapping
    public  ResponseEntity<List<IssueBookDto>> getAll(){
        return ResponseEntity.ok(bookService.getAllIssuesBook());
    }
    @PutMapping("/{id}")
    public ResponseEntity<IssueBookDto> updateOrReturnBook(@PathVariable Long id,@RequestBody IssueBookDto dto){
        return ResponseEntity.ok(bookService.updateIssueBook(id,dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<IssueBookDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getIssueBookById(id));
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long  id ){
        bookService.deleteIssueBook(id);
        return ResponseEntity.noContent().build();
    }



}
