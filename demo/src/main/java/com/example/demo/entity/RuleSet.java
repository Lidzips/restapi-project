package com.example.demo.entity;

public class RuleSet extends BaseEntity {
    private Integer alphabetFromId;
    private Integer alphabetToId;
    private Integer ruleId;

    public RuleSet(Integer id, Integer alphabetFromId, Integer alphabetToId, Integer ruleId) {
        super(id);
        this.alphabetFromId = alphabetFromId;
        this.alphabetToId = alphabetToId;
        this.ruleId = ruleId;
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
}
