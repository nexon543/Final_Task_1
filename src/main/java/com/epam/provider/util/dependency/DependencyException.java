package com.epam.provider.util.dependency;

public class DependencyException extends Exception {
    public DependencyException (String message, Exception ex ){
        super(message, ex);
    }
}
