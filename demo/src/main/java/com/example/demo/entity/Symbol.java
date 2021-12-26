package com.example.demo.entity;

public class Symbol extends BaseEntity{
    private String code;

    public Symbol(Integer id, String code) {
        super(id);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
