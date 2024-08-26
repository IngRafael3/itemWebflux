package com.example.webfluxdemo.handlers;

public class NoFoundItem extends RuntimeException {

    public NoFoundItem(String message) {
        super(message);
    }
}
