package org.example.memorymap.controller;

import jakarta.validation.Valid;
import org.example.memorymap.dto.FamilyRequest;
import org.example.memorymap.dto.FamilyResponse;
import org.example.memorymap.dto.PersonResponse;
import org.example.memorymap.dto.UserResponse;
import org.example.memorymap.service.FamilyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/families")
public class FamilyController {

    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping("/{id}")
    public FamilyResponse getById(@PathVariable Long id) {
        return FamilyResponse.from(familyService.getById(id));
    }

    @PostMapping
    public FamilyResponse create(@Valid @RequestBody FamilyRequest request) {
        return FamilyResponse.from(familyService.create(request));
    }

    @PutMapping("/{id}")
    public FamilyResponse update(@Valid @PathVariable Long id, @RequestBody FamilyRequest request) {
        return FamilyResponse.from(familyService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        familyService.delete(id);
    }

    @GetMapping("/name/{name}")
    public List<FamilyResponse> getByName(@PathVariable String name) {
        return familyService.getByName(name).stream().map(FamilyResponse::from).toList();
    }

    @GetMapping("/{id}/users")
    public Set<UserResponse> getUsers(@PathVariable Long id) {
        return familyService.getUsers(id).stream().map(UserResponse::from).collect(Collectors.toSet());
    }

    @GetMapping("/{id}/persons")
    public Set<PersonResponse> getPersons(@PathVariable Long id) {
        return familyService.getPersons(id).stream().map(PersonResponse::from).collect(Collectors.toSet());
    }

}