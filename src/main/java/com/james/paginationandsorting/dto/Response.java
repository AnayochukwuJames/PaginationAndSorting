package com.james.paginationandsorting.dto;
import lombok.Data;
import java.time.LocalDateTime;

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
}
