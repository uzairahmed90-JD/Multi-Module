package com.tut.service;

import com.tut.common.dto.StudentDto;

import java.util.List;

public interface StudentService {
     StudentDto createStudent(StudentDto dto);
     List<StudentDto> getAllStudent();
     StudentDto getById(Long id);
     StudentDto update(Long id,StudentDto dto);
     void deleteById(Long delete);
}
