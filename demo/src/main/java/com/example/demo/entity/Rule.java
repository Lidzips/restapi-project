package com.example.demo.entity;

public class Rule extends BaseEntity {
    private Integer inSsid;
    private Integer outSsid;

    public Rule(Integer id, Integer inSsid, Integer outSsid) {
        super(id);
        this.inSsid = inSsid;
        this.outSsid = outSsid;
    }

    public Integer getInSsid() {
        return inSsid;
    }

    public void setInSsid(Integer inSsid) {
        this.inSsid = inSsid;
    }

    public Integer getOutSsid() {
        return outSsid;
    }

    public void setOutSsid(Integer outSsid) {
        this.outSsid = outSsid;
    }
}
