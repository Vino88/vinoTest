package com.vino.test.dto.component;

public class Select2Default {
    private String id;
    private String text;
    private Object dataProperties;

    public Select2Default(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public Select2Default(String id, String text, Object dataProperties) {
        this.id = id;
        this.text = text;
        this.dataProperties = dataProperties;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getDataProperties() {
        return dataProperties;
    }

    public void setDataProperties(Object dataProperties) {
        this.dataProperties = dataProperties;
    }
}
