package org.example.memorymap.controller;

import org.example.memorymap.entity.BurialLocation;
import org.example.memorymap.entity.GravePhoto;
import org.example.memorymap.service.BurialLocationService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/burial-locations")
public class BurialLocationController {

    private final BurialLocationService burialLocationService;

    public BurialLocationController(BurialLocationService burialLocationService) {
        this.burialLocationService = burialLocationService;
    }

    @GetMapping("/{id}")
    public BurialLocation getById(@PathVariable Long id) {
        return burialLocationService.getById(id);
    }

    @PostMapping
    public BurialLocation create(@RequestBody BurialLocation burialLocation) {
        return burialLocationService.create(burialLocation);
    }

    @PutMapping("/{id}")
    public BurialLocation update(@PathVariable Long id, @RequestBody BurialLocation burialLocation) {
        return burialLocationService.update(id, burialLocation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        burialLocationService.delete(id);
    }

    @GetMapping("/{id}/grave-photos")
    public Set<GravePhoto> getGravePhotos(@PathVariable Long id) {
        return burialLocationService.getGravePhotos(id);
    }
}