package com.example.hibernate.controller;

import com.example.hibernate.entities.Person;
import com.example.hibernate.entities.PersonId;
import com.example.hibernate.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    @GetMapping("/persons/by-city")
    public String getPersonsByCity(String city) {
        return repository.findByCityOfLiving(city).toString();
    }

    @GetMapping("/persons/by-age")
    public String getPersonsByAge(int age) {
        return repository.findByIdAgeLessThanOrderByIdAge(age).toString();
    }

    @GetMapping("/persons/by-name-surname")
    public String getPersonsByNameSurname(String name, String surname) {
        final Optional<Person> optionalPerson = repository.findByIdNameAndIdSurname(name, surname);
        return getPersonStrFromOptional(optionalPerson);
    }

    @GetMapping("/persons/save")
    public String save(Person person) {
        repository.save(person);
        return "Success";
    }

    @GetMapping("/persons/save-all")
    public String saveAll(List<Person> persons) {
        repository.saveAll(persons);
        return "Success";
    }

    @GetMapping("/persons/find-by-id")
    public String findById(PersonId personId) {
        final Optional<Person> optionalPerson = repository.findById(personId);
        return getPersonStrFromOptional(optionalPerson);
    }

    @GetMapping("/persons/exists-by-id")
    public boolean existsById(PersonId personId) {
        return repository.existsById(personId);
    }

    @GetMapping("/persons/find-all")
    public String findAll() {
        return repository.findAll().toString();
    }

    @GetMapping("/persons/find-all-by-id")
    public String findAllById(List<PersonId> personIds) {
        return repository.findAllById(personIds).toString();
    }

    @GetMapping("/persons/count")
    public long count() {
        return repository.count();
    }

    @GetMapping("/persons/delete-by-id")
    public String deleteById(PersonId personId) {
        repository.deleteById(personId);
        return "Success";
    }

    @GetMapping("/persons/delete")
    public String delete(Person person) {
        repository.delete(person);
        return "Success";
    }

    @GetMapping("/persons/delete-all-by-id")
    public String deleteAllById(List<PersonId> personIds) {
        repository.deleteAllById(personIds);
        return "Success";
    }

    @GetMapping("/persons/delete-all-by-entities")
    public String deleteAllByEntities(List<Person> persons) {
        repository.deleteAll(persons);
        return "Success";
    }

    @GetMapping("/persons/delete-all")
    public String deleteAll() {
        repository.deleteAll();
        return "Success";
    }


    private String getPersonStrFromOptional(Optional<Person> optionalPerson) {
        return optionalPerson.isPresent() ? optionalPerson.get().toString() : "Nothing found";
    }
}
