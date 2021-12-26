package com.example.demo.controller;

import com.example.demo.entity.Alphabet;
import com.example.demo.repository.AlphabetRepository;
import com.example.demo.repository.SymbolRepository;
import com.example.demo.resource.AlphabetResource;
import com.example.demo.resource.SymbolResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/alphabet")
public class AlphabetController {
    private final AlphabetRepository alphabetRepository;
    private final SymbolRepository symbolRepository;

    public AlphabetController(AlphabetRepository alphabetRepository, SymbolRepository symbolRepository) {
        this.alphabetRepository = alphabetRepository;
        this.symbolRepository = symbolRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    AlphabetResource get(@PathVariable Integer id,
                          @RequestParam(required = false) Object expand) {
        Alphabet entity = alphabetRepository.select(id);
        if (entity == null) return null;
        AlphabetResource resource = new AlphabetResource(entity);
        if (expand != null)
            resource.setSymbols(
                    Arrays.stream(symbolRepository.selectByAlphabet(entity.getName()))
                            .map(e -> new SymbolResource(e))
                            .toArray(SymbolResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    AlphabetResource[] getAll(@RequestParam(required = false) String name,
                         @RequestParam(required = false) Object expand) {
                Alphabet[] alphabets;
                if (name == null) {
                    alphabets = alphabetRepository.select();
                } else {
                    alphabets = alphabetRepository.selectByName(name);
                }
                return Arrays.stream(alphabets)
                .map(entity -> {
                    AlphabetResource resource = new AlphabetResource(entity);
                    if (expand != null)
                        resource.setSymbols(
                                Arrays.stream(symbolRepository.selectByAlphabet(entity.getName()))
                                        .map(e -> new SymbolResource(e))
                                        .toArray(SymbolResource[]::new)
                        );
                    return resource;
                })
                .toArray(AlphabetResource[]::new);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    AlphabetResource post(@RequestBody AlphabetResource resource) {
        Alphabet entity = alphabetRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new AlphabetResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    AlphabetResource put(@PathVariable Integer id,
                       @RequestBody AlphabetResource resource) {
        Alphabet entity = alphabetRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new AlphabetResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    AlphabetResource delete(@PathVariable Integer id) {
        Alphabet entity = alphabetRepository.delete(id);
        if (entity == null) return null;
        return new AlphabetResource(entity);
    }
}
