package com.security.springboot.SpringSecurityBoot.controller;

import com.security.springboot.SpringSecurityBoot.student.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> listStudent = Arrays.asList(
      new Student(1,"James Bond"),
            new Student(2,"Madhav Aman"),
            new Student(3,"Harsh Pathak")
    );
    @GetMapping("{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
        return  listStudent.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("Student"+studentId));
    }
}
