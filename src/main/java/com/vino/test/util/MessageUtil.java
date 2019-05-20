package com.vino.test.util;

import com.vino.test.dto.BaseFormDto;

public class MessageUtil {
    private String status;
    private String message;
    private BaseFormDto data;

    public MessageUtil(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public MessageUtil(String status, String message, BaseFormDto data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BaseFormDto getData() {
        return data;
    }

    public void setData(BaseFormDto data) {
        this.data = data;
    }
}
