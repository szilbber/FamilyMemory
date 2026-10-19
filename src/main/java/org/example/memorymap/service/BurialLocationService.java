package org.example.memorymap.service;

import org.example.memorymap.entity.BurialLocation;
import org.example.memorymap.entity.GravePhoto;
import org.example.memorymap.repository.BurialLocationRepository;
import org.example.memorymap.repository.GravePhotoRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BurialLocationService {

    private final BurialLocationRepository burialLocationRepository;
    private final GravePhotoRepository gravePhotoRepository;

    public BurialLocationService(BurialLocationRepository burialLocationRepository, GravePhotoRepository gravePhotoRepository) {
        this.burialLocationRepository = burialLocationRepository;
        this.gravePhotoRepository = gravePhotoRepository;
    }

    public BurialLocation create(BurialLocation burialLocation) {
        return burialLocationRepository.save(burialLocation);
    }

    public void delete(Long id) {
        BurialLocation burialLocation = getById(id);
        burialLocationRepository.delete(burialLocation);
    }

    public BurialLocation update(Long id, BurialLocation burialLocation) {
        BurialLocation updatedLocation = getById(id);

        updatedLocation.setLatitude(burialLocation.getLatitude());
        updatedLocation.setLongitude(burialLocation.getLongitude());
        updatedLocation.setIsExact(burialLocation.getIsExact());
        updatedLocation.setDescription(burialLocation.getDescription());

        return burialLocationRepository.save(updatedLocation);
    }

    public BurialLocation getById(Long id) {
        return burialLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Burial location not found"));
    }

    public Set<GravePhoto> getGravePhotos(Long burialLocationId) {
        BurialLocation burialLocation = getById(burialLocationId);
        return burialLocation.getGravePhotos();
    }
}