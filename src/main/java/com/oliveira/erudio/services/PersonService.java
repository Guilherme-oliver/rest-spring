package com.oliveira.erudio.services;

import com.oliveira.erudio.controllers.PersonController;
import com.oliveira.erudio.data.vo.v1.PersonVO;
import com.oliveira.erudio.exceptions.RequiredObjectIsNullException;
import com.oliveira.erudio.exceptions.ResourceNotFoundException;
import com.oliveira.erudio.mapper.DozerMapper;
import com.oliveira.erudio.mapper.custom.PersonMapper;
import com.oliveira.erudio.model.Person;
import com.oliveira.erudio.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    private PersonRepository repository;
    private PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.repository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonVO> findAll() {

        logger.info("Finding all people!");

        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons
                .stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return persons;
    }

    public PersonVO findById(Long id) {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        try {
            vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vo;
    }

    public PersonVO create(PersonVO person) {
        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one person!");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        try {
            vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vo;
    }

    public PersonVO update(PersonVO person) {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one person!");

        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        try {
            vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}