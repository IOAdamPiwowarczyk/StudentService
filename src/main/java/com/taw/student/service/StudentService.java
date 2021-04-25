package com.taw.student.service;

import com.taw.student.model.Status;
import com.taw.student.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudents(Status status);

    Student getStudent(Long id);

    Student addStudent(Student student);

    void deleteStudent(Long id);

    Student putStudent(Long id, Student student);

    Student patchStudent(Long id, Student student);

    List<Student> findStudentsByEmailList(List<String> emails);
}
