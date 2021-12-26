package com.example.demo.controller;

import com.example.demo.entity.Rule;
import com.example.demo.repository.RuleRepository;
import com.example.demo.repository.SymbolSetRepository;
import com.example.demo.resource.RuleResource;
import com.example.demo.resource.SymbolSetResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/rule")
public class RuleController {
    private final RuleRepository ruleRepository;
    private final SymbolSetRepository symbolSetRepository;

    public RuleController(RuleRepository ruleRepository, SymbolSetRepository symbolSetRepository) {
        this.ruleRepository = ruleRepository;
        this.symbolSetRepository = symbolSetRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    RuleResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(ruleRepository.select())
                .map(entity -> {
                    RuleResource resource = new RuleResource(entity);
                    if (expand != null) {
                        resource.setInSS(
                                new SymbolSetResource(symbolSetRepository.selectInSSByRuleId(entity.getId()))
                        );
                        resource.setOutSS(
                                new SymbolSetResource(symbolSetRepository.selectOutSSByRuleId(entity.getId()))
                        );
                    }

                    return resource;
                })
                .toArray(RuleResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    RuleResource get(@PathVariable Integer id,
                         @RequestParam(required = false) Object expand) {
        Rule entity = ruleRepository.select(id);
        if (entity == null) return null;
        RuleResource resource = new RuleResource(entity);
        if (expand != null) {
            resource.setInSS(
                    new SymbolSetResource(symbolSetRepository.selectInSSByRuleId(entity.getId()))
            );
            resource.setOutSS(
                    new SymbolSetResource(symbolSetRepository.selectOutSSByRuleId(entity.getId()))
            );
        }
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    RuleResource post(@RequestBody RuleResource resource) {
        Rule entity = ruleRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new RuleResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    RuleResource put(@PathVariable Integer id,
                         @RequestBody RuleResource resource) {
        Rule entity = ruleRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new RuleResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    RuleResource delete(@PathVariable Integer id) {
        Rule entity = ruleRepository.delete(id);
        if (entity == null) return null;
        return new RuleResource(entity);
    }
}
