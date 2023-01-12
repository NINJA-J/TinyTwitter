package org.university.db.project.tinytwitter.entity.web;

import lombok.Data;

@Data
public class Response<T> {
    public static final int SUCCESS = 0;

    public static final int ERROR = 1;

    public static final int MISS_ARG = 2;

    private int status;

    private String message;

    private T data;

    public static <T> Response<T> ok(T data) {
        Response<T> response = new Response<>();
        response.setStatus(SUCCESS);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> error(String message) {
        Response<T> response = new Response<>();
        response.setStatus(ERROR);
        response.setMessage(message);
        return response;
    }

}
