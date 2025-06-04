package com.tut.service.impl;

import com.tut.common.dto.StudentDto;
import com.tut.common.entity.Student;
import com.tut.common.exception.ResourceNotFoundException;
import com.tut.common.mapper.StudentMapper;
import com.tut.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.tut.repository.StudentRepository;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private  StudentRepository repository;

//    public StudentServiceImp(StudentRepository repository) {
//        this.repository=repository;
//    }


    @Override
    public StudentDto createStudent(StudentDto dto) {
        Student st=StudentMapper.toEntity(dto);
        return StudentMapper.toDto(repository.save(st));
    }

    @Override
    public List<StudentDto> getAllStudent() {
        return repository.findAll().stream().map(StudentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto getById( Long id) {
        return StudentMapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found")));
    }

    @Override
    public StudentDto update(Long id, StudentDto dto) {
        Student st=repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found id"));
        StudentMapper.update(st,dto);
        return StudentMapper.toDto(repository.save(st));
    }

    @Override
    public void deleteById(Long deleteId) {

        repository.deleteById(deleteId);
    }
}
