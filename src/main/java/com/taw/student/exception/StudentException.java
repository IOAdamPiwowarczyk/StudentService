package com.taw.student.exception;

public class StudentException extends RuntimeException{
    private com.taw.student.exception.StudentError studentError;

    public StudentException(com.taw.student.exception.StudentError studentError) {
        this.studentError = studentError;
    }

    public com.taw.student.exception.StudentError getStudentError() {
        return studentError;
    }
}
