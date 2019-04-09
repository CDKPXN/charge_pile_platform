package com.company.project.core;

/**
 * @Author lides
 * @Description
 * @Date 18-9-5 15:17
 **/
public class AuthoriedException extends RuntimeException{
    public AuthoriedException() {
    }

    public AuthoriedException(String message) {
        super(message);
    }

    public AuthoriedException(String message, Throwable cause) {
        super(message, cause);
    }
}
