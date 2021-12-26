package com.example.demo.controller;

import com.example.demo.entity.SymbolSet;
import com.example.demo.repository.SymbolRepository;
import com.example.demo.repository.SymbolSetRepository;
import com.example.demo.resource.SymbolResource;
import com.example.demo.resource.SymbolSetResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/symbolset")
public class SymbolSetController {
    private final SymbolSetRepository symbolSetRepository;
    private final SymbolRepository symbolRepository;

    public SymbolSetController(SymbolSetRepository symbolSetRepository, SymbolRepository symbolRepository) {
        this.symbolSetRepository = symbolSetRepository;
        this.symbolRepository = symbolRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    SymbolSetResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(symbolSetRepository.select())
                .map(entity -> {
                    SymbolSetResource resource = new SymbolSetResource(entity);
                    if (expand != null)
                        resource.setSymbols(
                                Arrays.stream(symbolRepository.selectBySymbolSetId(entity.getId()))
                                        .map(e -> new SymbolResource(e))
                                        .toArray(SymbolResource[]::new)
                        );
                    return resource;
                })
                .toArray(SymbolSetResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    SymbolSetResource get(@PathVariable Integer id,
                         @RequestParam(required = false) Object expand) {
        SymbolSet entity = symbolSetRepository.select(id);
        if (entity == null) return null;
        SymbolSetResource resource = new SymbolSetResource(entity);
        if (expand != null)
            resource.setSymbols(
                    Arrays.stream(symbolRepository.selectBySymbolSetId(entity.getId()))
                            .map(e -> new SymbolResource(e))
                            .toArray(SymbolResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    SymbolSetResource post(@RequestBody SymbolSetResource resource) {
        SymbolSet entity = symbolSetRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new SymbolSetResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    SymbolSetResource put(@PathVariable Integer id,
                         @RequestBody SymbolSetResource resource) {
        SymbolSet entity = symbolSetRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new SymbolSetResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    SymbolSetResource delete(@PathVariable Integer id) {
        SymbolSet entity = symbolSetRepository.delete(id);
        if (entity == null) return null;
        return new SymbolSetResource(entity);
    }
}
