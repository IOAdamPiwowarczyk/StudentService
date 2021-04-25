package com.taw.student.exception;

public enum StudentError {
    STUDENT_NOT_FOUND("Student does not exists"),
    STUDENT_EMAIL_ALREADY_EXISTS("Mail already exists"),
    STUDENT_IS_NOT_ACTIVE("Student is inactive");

    private final String message;

    StudentError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
