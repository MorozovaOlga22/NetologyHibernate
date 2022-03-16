package com.example.hibernate.repository;

import com.example.hibernate.entities.Person;
import com.example.hibernate.entities.PersonId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository extends CrudRepository<Person, PersonId> {

    @Query("select p from Person p where p.cityOfLiving = :cityOfLiving")
    List<Person> findByCityOfLiving(@Param("cityOfLiving") String cityOfLiving);

    @Query("select p from Person p where p.id.age < :age order by p.id.age asc")
    List<Person> findByIdAgeLessThanOrderByIdAge(@Param("age") int age);

    @Query("select p from Person p where p.id.name = :name and p.id.surname = :surname")
    Optional<Person> findByIdNameAndIdSurname(@Param("name") String name, @Param("surname") String surname);
}