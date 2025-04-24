package com.example.exercise3;

import com.example.exercise3.model.Person;
import com.example.exercise3.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Exercise3Application implements CommandLineRunner {

	private PersonRepository repository;

	private final Logger LOG = LoggerFactory.getLogger(Exercise3Application.class);

	@Autowired
	public void personRepository(PersonRepository personRepository) { this.repository = personRepository; }

	public static void main(String[] args) {
		SpringApplication.run(Exercise3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Person person1 = new Person();
		person1.setName("Mary");
		person1.setLastName("Candelaria");
		person1.setAge((short) 42);
		person1.setCountry("Spain");
		person1.setPhone("696712182");
		repository.save(person1);

		Person person2 = new Person();
		person2.setName("Hugo");
		person2.setLastName("Lopez");
		person2.setAge((short) 4);
		person2.setCountry("Catalunya");
		repository.save(person2);

		Person person3 = new Person();
		person3.setName("Semi");
		person3.setLastName("Lopez");
		person3.setAge((short) 42);
		person3.setCountry("Catalunya");
		person3.setPhone("600463588");
		repository.save(person3);

		List<Person> allPeople = repository.findAll();
		LOG.info("People found in DB:");
		for (Person person : allPeople) {
			LOG.info(person.toString());
		}

		List<Person> ageMatch = repository.findByAge((short) 42);
		LOG.info("People aged 42 found in DB:");
		for (Person person : ageMatch) {
			LOG.info(person.toString());
		}

		Person personToUpdate = repository.findByNameAndLastName("Mary", "Candelaria");
		LOG.info("Person to be updated: {}", personToUpdate);
		if (personToUpdate != null){
			personToUpdate.setLastName("Candelario");
		}
		LOG.info("Person updated: {}", personToUpdate);
	}
}
