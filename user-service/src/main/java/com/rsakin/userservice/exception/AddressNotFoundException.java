package com.rsakin.userservice.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Integer id) {
        super("Address not found by id : " + id);
    }
}
