package com.rookies4.MySpringBoot3Homework.exception;

import java.time.OffsetDateTime;

public class ErrorObject {
    private final OffsetDateTime timestamp = OffsetDateTime.now();
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    public ErrorObject(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public OffsetDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
}
