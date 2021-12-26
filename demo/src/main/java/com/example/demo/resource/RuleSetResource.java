package com.example.demo.resource;

import com.example.demo.entity.RuleSet;
import com.fasterxml.jackson.annotation.JsonInclude;

public class RuleSetResource extends BaseResource {
    private Integer id;
    private Integer alphabetFromId;
    private Integer alphabetToId;
    private Integer ruleId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AlphabetResource alphabetFrom;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AlphabetResource alphabetTo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RuleResource rule;

    public RuleSetResource() {}

    public RuleSetResource(RuleSet ruleSet) {
        this.id = ruleSet.getId();
        this.alphabetFromId = ruleSet.getAlphabetFromId();
        this.alphabetToId = ruleSet.getAlphabetToId();
        this.ruleId = ruleSet.getRuleId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlphabetFromId() {
        return alphabetFromId;
    }

    public void setAlphabetFromId(Integer alphabetFromId) {
        this.alphabetFromId = alphabetFromId;
    }

    public Integer getAlphabetToId() {
        return alphabetToId;
    }

    public void setAlphabetToId(Integer alphabetToId) {
        this.alphabetToId = alphabetToId;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public AlphabetResource getAlphabetFrom() {
        return alphabetFrom;
    }

    public void setAlphabetFrom(AlphabetResource alphabetFrom) {
        this.alphabetFrom = alphabetFrom;
    }

    public AlphabetResource getAlphabetTo() {
        return alphabetTo;
    }

    public void setAlphabetTo(AlphabetResource alphabetTo) {
        this.alphabetTo = alphabetTo;
    }

    public RuleResource getRule() {
        return rule;
    }

    public void setRule(RuleResource rule) {
        this.rule = rule;
    }

    public RuleSet toEntity() {
        return new RuleSet(
                this.id,
                this.alphabetFromId,
                this.alphabetToId,
                this.ruleId
        );
    }
}
