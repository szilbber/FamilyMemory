package org.example.memorymap.service;

import org.example.memorymap.entity.BurialLocation;
import org.example.memorymap.entity.Person;
import org.example.memorymap.entity.PersonPhoto;
import org.example.memorymap.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public void delete(Long id) {
        Person person = getById(id);
        personRepository.delete(person);
    }

    public Person update(Long id, Person person) {

        Person updatedPerson = getById(id);

        updatedPerson.setFirstName(person.getFirstName());
        updatedPerson.setLastName(person.getLastName());
        updatedPerson.setMiddleName(person.getMiddleName());

        updatedPerson.setBirthDate(person.getBirthDate());
        updatedPerson.setBirthYear(person.getBirthYear());

        updatedPerson.setDeathDate(person.getDeathDate());
        updatedPerson.setDeathYear(person.getDeathYear());

        updatedPerson.setBiography(person.getBiography());

        return personRepository.save(updatedPerson);
    }

    public Person getById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }

    public Set<PersonPhoto> getPhotos(Long personId) {
        Person person = getById(personId);
        return person.getPersonPhotos();
    }

    public BurialLocation getBurialLocation(Long personId) {
        Person person = getById(personId);
        return person.getBurialLocation();
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }
}