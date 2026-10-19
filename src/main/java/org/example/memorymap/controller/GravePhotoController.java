package org.example.memorymap.controller;

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
    public GravePhoto getById(@PathVariable Long id) {
        return gravePhotoService.getById(id);
    }

    @PostMapping
    public GravePhoto create(@RequestBody GravePhoto gravePhoto) {
        return gravePhotoService.create(gravePhoto);
    }

    @PutMapping("/{id}")
    public GravePhoto update(
            @PathVariable Long id,
            @RequestBody GravePhoto gravePhoto
    ) {
        return gravePhotoService.update(id, gravePhoto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gravePhotoService.delete(id);
    }
}