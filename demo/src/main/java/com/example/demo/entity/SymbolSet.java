package com.example.demo.entity;

public class SymbolSet extends BaseEntity {
    private Integer symbolId1;
    private Integer symbolId2;
    private Integer symbolId3;

    public SymbolSet(Integer id, Integer symbolId1, Integer symbolId2, Integer symbolId3) {
        super(id);
        this.symbolId1 = symbolId1;
        this.symbolId2 = symbolId2;
        this.symbolId3 = symbolId3;
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

}
