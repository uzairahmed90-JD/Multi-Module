package tut.t.controller;

import com.service.BookService;
import com.tut.common.dto.BookDto;
import com.tut.common.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto dto){
        return ResponseEntity.ok(service.addBook(dto));
    }
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookId(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id,@RequestBody BookDto dto){
        return ResponseEntity.ok(service.updateBook(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        service.delete(id);
       return ResponseEntity.noContent().build();
    }



}
