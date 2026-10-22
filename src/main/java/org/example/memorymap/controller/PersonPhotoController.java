package org.example.memorymap.controller;

import jakarta.validation.Valid;
import org.example.memorymap.dto.PersonPhotoRequest;
import org.example.memorymap.dto.PersonPhotoResponse;
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
    public PersonPhotoResponse getById(@PathVariable Long id) {
        return PersonPhotoResponse.from(personPhotoService.getById(id));
    }

    @PostMapping
    public PersonPhotoResponse create(@Valid @RequestBody PersonPhotoRequest request) {
        return PersonPhotoResponse.from(personPhotoService.create(request));
    }

    @PutMapping("/{id}")
    public PersonPhotoResponse update(@PathVariable Long id, @Valid @RequestBody PersonPhotoRequest request) {
        return PersonPhotoResponse.from(personPhotoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personPhotoService.delete(id);
    }
}