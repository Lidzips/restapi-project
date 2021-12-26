package com.example.demo.controller;

import com.example.demo.entity.RuleSet;
import com.example.demo.repository.AlphabetRepository;
import com.example.demo.repository.RuleRepository;
import com.example.demo.repository.RuleSetRepository;
import com.example.demo.resource.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/ruleset")
public class RuleSetController {
    private final RuleSetRepository ruleSetRepository;
    private final AlphabetRepository alphabetRepository;
    private final RuleRepository ruleRepository;

    public RuleSetController(RuleSetRepository ruleSetRepository, AlphabetRepository alphabetRepository, RuleRepository ruleRepository) {
        this.ruleSetRepository = ruleSetRepository;
        this.alphabetRepository = alphabetRepository;
        this.ruleRepository = ruleRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    RuleSetResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(ruleSetRepository.select())
                .map(entity -> {
                    RuleSetResource resource = new RuleSetResource(entity);
                    if (expand != null) {
                        resource.setAlphabetFrom(
                                new AlphabetResource(alphabetRepository.selectFromByRuleSetId(entity.getId()))
                        );
                        resource.setAlphabetTo(
                                new AlphabetResource(alphabetRepository.selectToByRuleSetId(entity.getId()))
                        );
                        resource.setRule(
                                new RuleResource(ruleRepository.selectByRuleSetId(entity.getId()))
                        );
                    }
                    return resource;
                })
                .toArray(RuleSetResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    RuleSetResource get(@PathVariable Integer id,
                         @RequestParam(required = false) Object expand) {
        RuleSet entity = ruleSetRepository.select(id);
        if (entity == null) return null;
        RuleSetResource resource = new RuleSetResource(entity);
        if (expand != null) {
            resource.setAlphabetFrom(
                    new AlphabetResource(alphabetRepository.selectFromByRuleSetId(entity.getId()))
            );
            resource.setAlphabetTo(
                    new AlphabetResource(alphabetRepository.selectToByRuleSetId(entity.getId()))
            );
            resource.setRule(
                    new RuleResource(ruleRepository.selectByRuleSetId(entity.getId()))
            );
        }
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    RuleSetResource post(@RequestBody RuleSetResource resource) {
        RuleSet entity = ruleSetRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new RuleSetResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    RuleSetResource put(@PathVariable Integer id,
                         @RequestBody RuleSetResource resource) {
        RuleSet entity = ruleSetRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new RuleSetResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    RuleSetResource delete(@PathVariable Integer id) {
        RuleSet entity = ruleSetRepository.delete(id);
        if (entity == null) return null;
        return new RuleSetResource(entity);
    }
}
