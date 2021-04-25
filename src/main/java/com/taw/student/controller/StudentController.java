package com.taw.student.controller;

import com.taw.student.model.Status;
import com.taw.student.model.Student;
import com.taw.student.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(@RequestParam(required = false) Status status) {
        return studentService.getStudents(status);
    }

    @PostMapping
    public Student addStudent(@RequestBody @Valid Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable long id) throws Exception {
        return studentService.getStudent(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable long id) throws Exception {
        studentService.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public Student putStudent(@PathVariable long id, @Valid @RequestBody Student student) {
        return studentService.putStudent(id, student);
    }

    @PatchMapping("/{id}")
    public Student patchStudent(@PathVariable long id, @RequestBody Student student) {
        return studentService.patchStudent(id, student);
    }

    @PostMapping("/emails")
    public List<Student> findStudentsByEmailList(@RequestBody List<String> emails) {
        return studentService.findStudentsByEmailList(emails);
    }
}
