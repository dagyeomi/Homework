package com.rookies4.MySpringBoot3Homework.exception;

public class BusinessException extends RuntimeException {
    private final int status;
    public BusinessException(String message, int status) {
        super(message);
        this.status = status;
    }
    public int getStatus() { return status; }
    public static BusinessException notFound(String message) {
        return new BusinessException(message, 404);
    }
}
