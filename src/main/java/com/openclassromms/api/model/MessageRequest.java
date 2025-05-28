package com.openclassromms.api.model;

public class MessageRequest {
    private String message;
    private Long user_id;
    private Long rental_id;

    public String getMessage(){
        return message;
    }

    public Long getUserId(){
        return user_id;
    }

    public Long getRentalId(){
        return rental_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setRental_id(Long rental_id) {
        this.rental_id = rental_id;
    }
}
