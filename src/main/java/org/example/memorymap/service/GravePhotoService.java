package org.example.memorymap.service;

import org.example.memorymap.entity.GravePhoto;
import org.example.memorymap.repository.GravePhotoRepository;
import org.springframework.stereotype.Service;

@Service
public class GravePhotoService {

    private final GravePhotoRepository gravePhotoRepository;

    public GravePhotoService(GravePhotoRepository gravePhotoRepository) {
        this.gravePhotoRepository = gravePhotoRepository;
    }

    public GravePhoto create(GravePhoto gravePhoto) {
        return gravePhotoRepository.save(gravePhoto);
    }

    public void delete(Long id) {
        GravePhoto gravePhoto = getById(id);
        gravePhotoRepository.delete(gravePhoto);
    }

    public GravePhoto update(Long id, GravePhoto gravePhoto) {
        GravePhoto updatedPhoto = getById(id);

        updatedPhoto.setTitle(gravePhoto.getTitle());
        updatedPhoto.setFileUrl(gravePhoto.getFileUrl());

        return gravePhotoRepository.save(updatedPhoto);
    }

    public GravePhoto getById(Long id) {
        return gravePhotoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grave photo not found"));
    }

}