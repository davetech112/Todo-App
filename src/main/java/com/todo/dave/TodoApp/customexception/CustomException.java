package com.todo.dave.TodoApp.customexception;

public class CustomException extends Exception{
    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }
}
