package com.example.demo.resource;

import com.example.demo.entity.Symbol;

public class SymbolResource extends BaseResource {
    private Integer id;
    private String code;

    public SymbolResource() {}

    public SymbolResource(Symbol symbol) {
        this.id = symbol.getId();
        this.code = symbol.getCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Symbol toEntity() {
        return new Symbol(
                this.id,
                this.code
        );
    }
}
