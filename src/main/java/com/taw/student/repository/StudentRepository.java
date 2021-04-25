package com.taw.student.repository;

import com.taw.student.model.Status;
import com.taw.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    List<Student> findAllByStatus(Status status);

    List<Student> findAllByEmailIn(List<String> emails);
}
