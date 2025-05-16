package com.openclassromms.api.model;

public class MessageRequest {
    private String message;
    private Long userId;
    private Long rentalId;

    public String getMessage(){
        return message;
    }

    public Long getUserId(){
        return userId;
    }

    public Long getRentalId(){
        return rentalId;
    }
}
