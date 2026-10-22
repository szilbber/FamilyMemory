package org.example.memorymap.controller;

import jakarta.validation.Valid;
import org.example.memorymap.dto.BurialLocationResponse;
import org.example.memorymap.dto.PersonPhotoResponse;
import org.example.memorymap.dto.PersonRequest;
import org.example.memorymap.dto.PersonResponse;
import org.example.memorymap.entity.BurialLocation;
import org.example.memorymap.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonResponse> getAll() {
        return personService.getAll()
                .stream()
                .map(PersonResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public PersonResponse getById(@PathVariable Long id) {
        return PersonResponse.from(personService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponse create(@Valid @RequestBody PersonRequest request) {
        return PersonResponse.from(personService.create(request));
    }

    @PutMapping("/{id}")
    public PersonResponse update(@PathVariable Long id, @Valid @RequestBody PersonRequest request) {
        return PersonResponse.from(personService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }

    @GetMapping("/{id}/photos")
    public Set<PersonPhotoResponse> getPhotos(@PathVariable Long id) {
        return personService.getPhotos(id).stream()
                .map(PersonPhotoResponse::from)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}/burial-location")
    public BurialLocationResponse getBurialLocation(@PathVariable Long id) {
        BurialLocation location = personService.getBurialLocation(id);
        return location == null ? null : BurialLocationResponse.from(location);
    }
}
