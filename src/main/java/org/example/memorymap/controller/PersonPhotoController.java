package org.example.memorymap.controller;

import org.example.memorymap.entity.PersonPhoto;
import org.example.memorymap.service.PersonPhotoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person-photos")
public class PersonPhotoController {

    private final PersonPhotoService personPhotoService;

    public PersonPhotoController(PersonPhotoService personPhotoService) {
        this.personPhotoService = personPhotoService;
    }

    @GetMapping("/{id}")
    public PersonPhoto getById(@PathVariable Long id) {
        return personPhotoService.getById(id);
    }

    @PostMapping
    public PersonPhoto create(@RequestBody PersonPhoto personPhoto) {
        return personPhotoService.create(personPhoto);
    }

    @PutMapping("/{id}")
    public PersonPhoto update(@PathVariable Long id, @RequestBody PersonPhoto personPhoto) {
        return personPhotoService.update(id, personPhoto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personPhotoService.delete(id);
    }
}