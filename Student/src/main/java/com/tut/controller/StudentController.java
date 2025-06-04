package com.tut.controller;

import com.tut.common.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tut.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.createStudent(studentDto));
    }
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }
    @GetMapping("/{id}")
    public  ResponseEntity<StudentDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getById(id));
    }
    @PutMapping("/{id}")
    public  ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,@RequestBody StudentDto dto){
        return ResponseEntity.ok(studentService.update(id,dto));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id){
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
