package com.taw.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler(value = com.taw.student.exception.StudentException.class)
    public ResponseEntity<com.taw.student.exception.ErrorInfo> handleException(com.taw.student.exception.StudentException exception) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception.getStudentError().equals(com.taw.student.exception.StudentError.STUDENT_NOT_FOUND)) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (exception.getStudentError().equals(com.taw.student.exception.StudentError.STUDENT_EMAIL_ALREADY_EXISTS)) {
            httpStatus = HttpStatus.CONFLICT;
        } else if (exception.getStudentError().equals(com.taw.student.exception.StudentError.STUDENT_IS_NOT_ACTIVE)){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus)
                .body(new com.taw.student.exception.ErrorInfo(exception.getStudentError().getMessage()));
    }
}
