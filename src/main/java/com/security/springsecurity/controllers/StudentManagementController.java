package com.security.springsecurity.controllers;

import com.security.springsecurity.models.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/students")
public class StudentManagementController {
  private static final List<Student> STUDENTS = Arrays.asList(
      new Student(1L,"José"),
      new Student(2L,"Douglas"),
      new Student(3L,"Dayanne")
  );

  @GetMapping
  public List<Student> getAllStudents() {
    return STUDENTS;
  }

  @PostMapping
  public void registerStudent(@RequestBody Student student) {
    System.out.println(student);
  }

  @PutMapping(path = "{studentId}")
  public void updateStudent(
      @PathVariable("studentId") Long studentId,
      @RequestBody Student student
  ) {
    System.out.println(String.format("%s %s", studentId, student));
  }

  @DeleteMapping(path = "{studentId}")
  public void deleteStudent(@PathVariable("studentId") Long studentId) {
    System.out.println(studentId);
  }
}
