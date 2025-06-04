package com.tut.common.mapper;

import com.tut.common.dto.StudentDto;
import com.tut.common.entity.Student;

public class StudentMapper {
    public static StudentDto toDto(Student student) {
        return StudentDto.builder()
                .studentId(student.getStudentId())
                .name(student.getName())
                .email(student.getEmail())
                .build();
    }
    public static Student toEntity(StudentDto dto) {
        return Student.builder()
                .studentId(dto.getStudentId())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }
    public static void update(Student st,StudentDto dto){
        if(dto.getName()!=null)st.setName(dto.getName());
        if(dto.getEmail()!=null)st.setEmail(dto.getEmail());
    }
}
