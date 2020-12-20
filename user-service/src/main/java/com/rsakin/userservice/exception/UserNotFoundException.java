package com.rsakin.userservice.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("User not found by id : " + id);
    }
}
