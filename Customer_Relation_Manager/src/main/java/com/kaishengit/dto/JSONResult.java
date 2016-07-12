package com.kaishengit.dto;

/**
 * Created by Administrator on 2016/7/11.
 */
public class JSONResult {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private String state;
    private String message;
    private Object object;

    public JSONResult(Object object) {
        this(SUCCESS,object);
    }

    public JSONResult(String message) {
        this(ERROR,message);
    }

    public JSONResult(String state, String message) {
        this.state = state;
        this.message = message;
    }

    public JSONResult(String state, Object object) {
        this.state = state;
        this.object = object;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
