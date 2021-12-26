package com.example.demo.resource;

import com.example.demo.entity.Alphabet;
import com.fasterxml.jackson.annotation.JsonInclude;

public class AlphabetResource extends BaseResource {
    private Integer id;
    private String name;
    private Integer symbolId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SymbolResource[] symbols;

    public AlphabetResource() {}

    public AlphabetResource(Alphabet alphabet) {
        this.id = alphabet.getId();
        this.name = alphabet.getName();
        this.symbolId = alphabet.getSymbolId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public SymbolResource[] getSymbols() {
        return symbols;
    }

    public void setSymbols(SymbolResource[] symbols) {
        this.symbols = symbols;
    }

    public Alphabet toEntity() {
        return new Alphabet(
                this.id,
                this.name,
                this.symbolId
        );
    }
}
