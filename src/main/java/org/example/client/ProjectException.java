package org.example.client;

public class ProjectException extends Exception {
    @Override
    public String getMessage(){
        return "Something went wrong!";
    }
}
