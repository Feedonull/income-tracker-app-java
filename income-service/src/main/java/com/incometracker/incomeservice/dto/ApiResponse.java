package com.incometracker.incomeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private Date date = new Date();
    private T data;


    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        formatter.format(date);
        this.data = data;
    }

}
