package com.oliveira.erudio.services;

import com.oliveira.erudio.exceptions.ResourceNotFoundException;
import com.oliveira.erudio.model.Person;
import com.oliveira.erudio.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    private PersonRepository personRepository;
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        logger.info("Finding all people!");
        List<Person> persons = new ArrayList<>();

        return personRepository.findAll();
    }

    public Person findById(Long id) {

        logger.info("Finding one person!");

        Person person = new Person();
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Person create(Person person) {
        logger.info("Creating one person!");
        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one person!");
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return personRepository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);
    }
}