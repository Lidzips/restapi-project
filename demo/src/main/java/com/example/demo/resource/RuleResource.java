package com.example.demo.resource;

import com.example.demo.entity.Rule;
import com.fasterxml.jackson.annotation.JsonInclude;

public class RuleResource extends BaseResource {
    private Integer id;
    private Integer inSsid;
    private Integer outSsid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SymbolSetResource inSS;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SymbolSetResource outSS;

    public RuleResource() {}

    public RuleResource(Rule rule) {
        this.id = rule.getId();
        this.inSsid = rule.getInSsid();
        this.outSsid = rule.getOutSsid();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public SymbolSetResource getInSS() {
        return inSS;
    }

    public void setInSS(SymbolSetResource inSS) {
        this.inSS = inSS;
    }

    public SymbolSetResource getOutSS() {
        return outSS;
    }

    public void setOutSS(SymbolSetResource outSS) {
        this.outSS = outSS;
    }

    public Rule toEntity() {
        return new Rule(
                this.id,
                this.inSsid,
                this.outSsid
        );
    }
}
