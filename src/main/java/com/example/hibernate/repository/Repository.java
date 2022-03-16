package com.example.hibernate.repository;

import com.example.hibernate.entities.Person;
import com.example.hibernate.entities.PersonId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository extends CrudRepository<Person, PersonId> {

    List<Person> findByCityOfLiving(@Param("cityOfLiving") String cityOfLiving);

    List<Person> findByIdAgeLessThanOrderByIdAge(@Param("age") int age);

    Optional<Person> findByIdNameAndIdSurname(@Param("name") String name, @Param("surname") String surname);
}