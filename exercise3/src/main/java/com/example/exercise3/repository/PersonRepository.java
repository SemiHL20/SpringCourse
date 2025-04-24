package com.example.exercise3.repository;

import com.example.exercise3.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    List<Person> findByAge(short age);
    List<Person> findByName(String name);
    List<Person> findByCountry(String country);

    List<Person> findByNameAndAge(String name, short age);
    Person findByNameAndLastName(String name, String lastName);

}
