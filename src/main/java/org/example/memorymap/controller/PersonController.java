package org.example.memorymap.controller;

import org.example.memorymap.entity.BurialLocation;
import org.example.memorymap.entity.Person;
import org.example.memorymap.entity.PersonPhoto;
import org.example.memorymap.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Long id) {
        return personService.getById(id);
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable Long id, @RequestBody Person person) {
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }

    @GetMapping("/{id}/photos")
    public Set<PersonPhoto> getPhotos(@PathVariable Long id) {
        return personService.getPhotos(id);
    }

    @GetMapping("/{id}/burial-location")
    public BurialLocation getBurialLocation(@PathVariable Long id) {
        return personService.getBurialLocation(id);
    }
}