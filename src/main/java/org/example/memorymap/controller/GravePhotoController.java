package org.example.memorymap.controller;

import jakarta.validation.Valid;
import org.example.memorymap.dto.GravePhotoRequest;
import org.example.memorymap.dto.GravePhotoResponse;
import org.example.memorymap.entity.GravePhoto;
import org.example.memorymap.service.GravePhotoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grave-photos")
public class GravePhotoController {

    private final GravePhotoService gravePhotoService;

    public GravePhotoController(GravePhotoService gravePhotoService) {
        this.gravePhotoService = gravePhotoService;
    }

    @GetMapping("/{id}")
    public GravePhotoResponse getById(@PathVariable Long id) {
        return GravePhotoResponse.from(gravePhotoService.getById(id));
    }

    @PostMapping
    public GravePhotoResponse create(@Valid  @RequestBody GravePhotoRequest request) {
        return GravePhotoResponse.from(gravePhotoService.create(request));
    }

    @PutMapping("/{id}")
    public GravePhotoResponse update(
            @PathVariable Long id,
            @Valid @RequestBody GravePhotoRequest request
    ) {
        return GravePhotoResponse.from(gravePhotoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gravePhotoService.delete(id);
    }
}