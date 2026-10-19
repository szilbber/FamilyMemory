package org.example.memorymap.controller;

import org.example.memorymap.entity.Family;
import org.example.memorymap.entity.Person;
import org.example.memorymap.entity.User;
import org.example.memorymap.service.FamilyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/families")
public class FamilyController {

    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping("/{id}")
    public Family getById(@PathVariable Long id) {
        return familyService.getById(id);
    }

    @PostMapping
    public Family create(@RequestBody Family family) {
        return familyService.create(family);
    }

    @PutMapping("/{id}")
    public Family update(@PathVariable Long id, @RequestBody Family family) {
        return familyService.update(id, family);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        familyService.delete(id);
    }

    @GetMapping("/name/{name}")
    public List<Family> getByName(@PathVariable String name) {
        return familyService.getByName(name);
    }

    @GetMapping("/{id}/users")
    public Set<User> getUsers(@PathVariable Long id) {
        return familyService.getUsers(id);
    }

    @GetMapping("/{id}/persons")
    public Set<Person> getPersons(@PathVariable Long id) {
        return familyService.getPersons(id);
    }

}