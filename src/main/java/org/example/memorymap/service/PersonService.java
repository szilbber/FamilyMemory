package org.example.memorymap.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.memorymap.dto.PersonRequest;
import org.example.memorymap.entity.BurialLocation;
import org.example.memorymap.entity.Family;
import org.example.memorymap.entity.Person;
import org.example.memorymap.entity.PersonPhoto;
import org.example.memorymap.repository.FamilyRepository;
import org.example.memorymap.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final FamilyRepository familyRepository;

    public PersonService(PersonRepository personRepository, FamilyRepository familyRepository) {
        this.personRepository = personRepository;
        this.familyRepository = familyRepository;
    }

    public Person create(PersonRequest request) {
        Family family = familyRepository.findById(request.familyId())
                .orElseThrow(() -> new EntityNotFoundException("Family not found"));
        Person person = new Person();
        person.setFamily(family);
        apply(person, request);
        return personRepository.save(person);
    }

    public void delete(Long id) {
        Person person = getById(id);
        personRepository.delete(person);
    }

    public Person update(Long id, PersonRequest request) {
        Person person = getById(id);
        apply(person, request);
        return personRepository.save(person);
    }

    private void apply(Person p, PersonRequest r) {
        p.setFirstName(r.firstName());
        p.setLastName(r.lastName());
        p.setMiddleName(r.middleName());
        p.setBirthDate(r.birthDate());
        p.setBirthYear(r.birthYear());
        p.setDeathDate(r.deathDate());
        p.setDeathYear(r.deathYear());
        p.setBiography(r.biography());
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