package com.taw.student.service;

import com.taw.student.exception.StudentError;
import com.taw.student.exception.StudentException;
import com.taw.student.model.Status;
import com.taw.student.model.Student;
import com.taw.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents(Status status) {
        if (status != null) {
            return studentRepository.findAllByStatus(status);
        }
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).map(student -> {
            if (Status.INACTIVE.equals(student.getStatus())) {
                throw new StudentException(StudentError.STUDENT_IS_NOT_ACTIVE);
            }
            return student;
        }).orElseThrow(() ->
                new StudentException(StudentError.STUDENT_NOT_FOUND));
    }

    @Override
    public Student addStudent(Student student) {
        validateStudentEmailExists(student);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
        student.setStatus(Status.INACTIVE);
        studentRepository.save(student);
    }

    @Override
    public Student putStudent(Long id, Student student) {
        return studentRepository.findById(id)
                .map(studentFromDb -> {
                    if (!student.getEmail().equals(studentFromDb.getEmail()) &&
                            studentRepository.existsByEmail(student.getEmail())) {
                        throw new StudentException(StudentError.STUDENT_EMAIL_ALREADY_EXISTS);
                    }
                    validateStudentEmailExists(student);
                    studentFromDb.setFirstName(student.getFirstName());
                    studentFromDb.setLastName(student.getLastName());
                    studentFromDb.setEmail(student.getEmail());
                    studentFromDb.setStatus(student.getStatus());
                    return studentRepository.save(studentFromDb);
                }).orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
    }

    @Override
    public Student patchStudent(Long id, Student student) {
        return studentRepository.findById(id)
                .map(studentFromDb -> {
                    if (!student.getFirstName().isEmpty()) {
                        studentFromDb.setFirstName(student.getFirstName());
                    }
                    if (!student.getLastName().isEmpty()) {
                        studentFromDb.setLastName(student.getLastName());
                    }
                    if (!student.getStatus().toString().isEmpty()) {
                        studentFromDb.setStatus(student.getStatus());
                    }
                    return studentRepository.save(studentFromDb);
                }).orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
    }

    @Override
    public List<Student> findStudentsByEmailList(List<String> emails) {
        List<Student> students = studentRepository.findAllByEmailIn(emails);
        return students.stream().filter(student -> emails.contains(student.getEmail())).collect(Collectors.toList());
    }

    private void validateStudentEmailExists(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new StudentException(StudentError.STUDENT_EMAIL_ALREADY_EXISTS);
        }
    }
}
