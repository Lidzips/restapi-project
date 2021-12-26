package com.example.demo.entity;

public class Alphabet extends BaseEntity{
    private String name;
    private Integer symbolId;

    public Alphabet(Integer id, String name, Integer symbolId) {
        super(id);
        this.name = name;
        this.symbolId = symbolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSymbolId() {
        return symbolId;
    }

    public void setSymbolId(Integer symbolId) {
        this.symbolId = symbolId;
    }
}
