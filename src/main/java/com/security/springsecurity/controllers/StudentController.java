package com.security.springsecurity.controllers;

import com.security.springsecurity.models.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
  private static final List<Student> STUDENTS = Arrays.asList(
      new Student(1L, "José"),
      new Student(2L, "Douglas"),
      new Student(3L, "Dayanne")
  );

  @GetMapping(path = "{studentId}")
  public Student getUser(
      @PathVariable("studentId") Long studentId
  ) {
    return STUDENTS.stream().filter(student -> student.getId().equals(studentId))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("User: " + studentId + " doesn't exist."));
  }
}
