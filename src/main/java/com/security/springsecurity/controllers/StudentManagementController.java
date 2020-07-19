package com.security.springsecurity.controllers;

import com.security.springsecurity.models.Student;
import org.springframework.security.access.prepost.PreAuthorize;
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
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINNE')")
  public List<Student> getAllStudents() {
    return STUDENTS;
  }

  @PostMapping
  @PreAuthorize("hasAuthority('student:write')")
  public void registerStudent(@RequestBody Student student) {
    System.out.println("Register:");
    System.out.println(student);
  }

  @PutMapping(path = "{studentId}")
  @PreAuthorize("hasAuthority('student:write')")
  public void updateStudent(
      @PathVariable("studentId") Long studentId,
      @RequestBody Student student
  ) {
    System.out.println("Update:");
    System.out.println(String.format("%s %s", studentId, student));
  }

  @DeleteMapping(path = "{studentId}")
  @PreAuthorize("hasAuthority('student:write')")
  public void deleteStudent(@PathVariable("studentId") Long studentId) {
    System.out.println("Delete:");
    System.out.println(studentId);
  }
}
