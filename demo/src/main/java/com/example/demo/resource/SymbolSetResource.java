package com.example.demo.resource;

import com.example.demo.entity.Symbol;
import com.example.demo.entity.SymbolSet;
import com.fasterxml.jackson.annotation.JsonInclude;

public class SymbolSetResource extends BaseResource {
    private Integer id;
    private Integer symbolId1;
    private Integer symbolId2;
    private Integer symbolId3;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SymbolResource symbol1;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SymbolResource symbol2;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SymbolResource symbol3;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SymbolResource[] symbols;

    public SymbolSetResource() {}

    public SymbolSetResource(SymbolSet symbolSet) {
        this.id = symbolSet.getId();
        this.symbolId1 = symbolSet.getSymbolId1();
        this.symbolId2 = symbolSet.getSymbolId2();
        this.symbolId3 = symbolSet.getSymbolId3();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSymbolId1() {
        return symbolId1;
    }

    public void setSymbolId1(Integer symbolId1) {
        this.symbolId1 = symbolId1;
    }

    public Integer getSymbolId2() {
        return symbolId2;
    }

    public void setSymbolId2(Integer symbolId2) {
        this.symbolId2 = symbolId2;
    }

    public Integer getSymbolId3() {
        return symbolId3;
    }

    public void setSymbolId3(Integer symbolId3) {
        this.symbolId3 = symbolId3;
    }

    public SymbolResource getSymbol1() {
        return symbol1;
    }

    public void setSymbol1(SymbolResource symbol1) {
        this.symbol1 = symbol1;
    }

    public SymbolResource getSymbol2() {
        return symbol2;
    }

    public void setSymbol2(SymbolResource symbol2) {
        this.symbol2 = symbol2;
    }

    public SymbolResource getSymbol3() {
        return symbol3;
    }

    public void setSymbol3(SymbolResource symbol3) {
        this.symbol3 = symbol3;
    }

    public SymbolResource[] getSymbols() {
        return symbols;
    }

    public void setSymbols(SymbolResource[] symbols) {
        this.symbols = symbols;
    }

    public SymbolSet toEntity() {
        return new SymbolSet(
                this.id,
                this.symbolId1,
                this.symbolId2,
                this.symbolId3
        );
    }
}
