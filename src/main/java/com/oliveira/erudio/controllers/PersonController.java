package com.oliveira.erudio.controllers;
import com.oliveira.erudio.data.vo.v1.PersonVO;
import com.oliveira.erudio.data.vo.v2.PersonVOV2;
import com.oliveira.erudio.services.PersonService;

import com.oliveira.erudio.util.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    public List<PersonVO> findAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}", produces ={MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    public PersonVO findById(@PathVariable(value = "id")Long id ) throws Exception {
       return personService.findById(id);
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    public PersonVO create(@RequestBody PersonVO person) throws Exception {
        return personService.create(person);
    }

    @PostMapping(value = "/v2",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person) throws Exception {
        return personService.createV2(person);
    }

    @PutMapping(
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    public PersonVO update(@RequestBody PersonVO person) throws Exception {
        return personService.update(person);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    public ResponseEntity<?> deleteById(@PathVariable(value = "id")Long id ) throws Exception {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}