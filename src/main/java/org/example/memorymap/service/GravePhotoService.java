package org.example.memorymap.service;

import org.example.memorymap.dto.GravePhotoRequest;
import org.example.memorymap.entity.BurialLocation;
import org.example.memorymap.entity.GravePhoto;
import org.example.memorymap.repository.BurialLocationRepository;
import org.example.memorymap.repository.GravePhotoRepository;
import org.springframework.stereotype.Service;

@Service
public class GravePhotoService {

    private final GravePhotoRepository gravePhotoRepository;
    private final BurialLocationRepository burialLocationRepository;

    public GravePhotoService(GravePhotoRepository gravePhotoRepository, BurialLocationRepository burialLocationRepository) {
        this.gravePhotoRepository = gravePhotoRepository;
        this.burialLocationRepository = burialLocationRepository;
    }

    public GravePhoto create(GravePhotoRequest request) {
        BurialLocation burialLocation = burialLocationRepository.findById(request.burialId())
                .orElseThrow(() -> new RuntimeException("BurialLocation Not Found"));
        GravePhoto gravePhoto = new GravePhoto();
        gravePhoto.setFileUrl(request.fileUrl());
        gravePhoto.setTitle(request.title());
        gravePhoto.setBurialLocation(burialLocation);
        return gravePhotoRepository.save(gravePhoto);
    }

    public void delete(Long id) {
        GravePhoto gravePhoto = getById(id);
        gravePhotoRepository.delete(gravePhoto);
    }

    public GravePhoto update(Long id, GravePhotoRequest request) {
        GravePhoto updatedPhoto = getById(id);

        updatedPhoto.setTitle(request.title());
        updatedPhoto.setFileUrl(request.fileUrl());

        return gravePhotoRepository.save(updatedPhoto);
    }

    public GravePhoto getById(Long id) {
        return gravePhotoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grave photo not found"));
    }

}