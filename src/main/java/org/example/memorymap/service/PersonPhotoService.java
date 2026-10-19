package org.example.memorymap.service;

import org.example.memorymap.entity.PersonPhoto;
import org.example.memorymap.repository.PersonPhotoRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonPhotoService {

    private final PersonPhotoRepository personPhotoRepository;

    public PersonPhotoService(PersonPhotoRepository personPhotoRepository) {
        this.personPhotoRepository = personPhotoRepository;
    }

    public PersonPhoto create(PersonPhoto personPhoto) {
        return personPhotoRepository.save(personPhoto);
    }

    public void delete(Long id) {
        PersonPhoto personPhoto = getById(id);
        personPhotoRepository.delete(personPhoto);
    }

    public PersonPhoto update(Long id, PersonPhoto personPhoto) {
        PersonPhoto updatedPhoto = getById(id);

        updatedPhoto.setTitle(personPhoto.getTitle());
        updatedPhoto.setFileUrl(personPhoto.getFileUrl());

        return personPhotoRepository.save(updatedPhoto);
    }

    public PersonPhoto getById(Long id) {
        return personPhotoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person photo not found"));
    }

}