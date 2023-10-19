package org.example.client;

public class FailedToDeleteEmployeeException extends Exception
{
    @Override
    public String getMessage(){
        return "Failed to delete Delivery Employee";
    }
}
