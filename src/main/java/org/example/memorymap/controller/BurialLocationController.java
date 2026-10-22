package org.example.memorymap.controller;

import jakarta.validation.Valid;
import org.example.memorymap.dto.BurialLocationRequest;
import org.example.memorymap.dto.BurialLocationResponse;
import org.example.memorymap.dto.GravePhotoResponse;
import org.example.memorymap.entity.BurialLocation;
import org.example.memorymap.entity.GravePhoto;
import org.example.memorymap.service.BurialLocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/burial-locations")
public class BurialLocationController {

    private final BurialLocationService burialLocationService;

    public BurialLocationController(BurialLocationService burialLocationService) {
        this.burialLocationService = burialLocationService;
    }

    @GetMapping("/{id}")
    public BurialLocationResponse getById(@PathVariable Long id) {
        return BurialLocationResponse.from(burialLocationService.getById(id));
    }

    @PostMapping
    public BurialLocationResponse create(@Valid @RequestBody BurialLocationRequest request) {
        return BurialLocationResponse.from(burialLocationService.create(request));
    }

    @PutMapping("/{id}")
    public BurialLocationResponse update(@PathVariable Long id,
                                         @Valid @RequestBody BurialLocationRequest request) {
        return BurialLocationResponse.from(burialLocationService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        burialLocationService.delete(id);
    }

    @GetMapping("/{id}/grave-photos")
    public List<GravePhotoResponse> getGravePhotos(@PathVariable Long id) {
        return burialLocationService.getGravePhotos(id).stream()
                .map(GravePhotoResponse::from)
                .toList();
    }
}