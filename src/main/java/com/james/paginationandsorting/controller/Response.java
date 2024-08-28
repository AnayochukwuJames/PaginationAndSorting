package com.james.paginationandsorting.controller;

import com.james.paginationandsorting.model.Product;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Response<T> {
    private String message;
    private LocalDateTime completedTime;
    private boolean status;
    private T data;

    public Response(T data) {
        this.message = "Request successful";
        this.completedTime = LocalDateTime.now();
        this.status = true;
        this.data = data;
    }
//
//    // Constructor for custom message response with data
//    public Response(String message, T data) {
//        this.message = message;
//        this.completedTime = LocalDateTime.now();
//        this.status = true;
//        this.data = data;
//    }
//
//    // Constructor for custom message response without data
//    public Response(String message, boolean status) {
//        this.message = message;
//        this.completedTime = LocalDateTime.now();
//        this.status = status;
//    }
}
