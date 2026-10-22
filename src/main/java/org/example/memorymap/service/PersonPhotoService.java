package org.example.memorymap.service;

import org.example.memorymap.dto.PersonPhotoRequest;
import org.example.memorymap.entity.Person;
import org.example.memorymap.entity.PersonPhoto;
import org.example.memorymap.repository.PersonPhotoRepository;
import org.example.memorymap.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonPhotoService {

    private final PersonPhotoRepository personPhotoRepository;
    private final PersonRepository personRepository;

    public PersonPhotoService(PersonPhotoRepository personPhotoRepository, PersonRepository personRepository) {
        this.personPhotoRepository = personPhotoRepository;
        this.personRepository = personRepository;
    }

    public PersonPhoto create(PersonPhotoRequest request) {
        Person person = personRepository.findById(request.personId())
                .orElseThrow(() -> new RuntimeException("Person not found"));
        PersonPhoto personPhoto = new PersonPhoto();
        personPhoto.setFileUrl(request.fileUrl());
        personPhoto.setTitle(request.title());
        personPhoto.setPerson(person);
        return personPhotoRepository.save(personPhoto);
    }

    public void delete(Long id) {
        PersonPhoto personPhoto = getById(id);
        personPhotoRepository.delete(personPhoto);
    }

    public PersonPhoto update(Long id, PersonPhotoRequest request) {
        PersonPhoto updatedPhoto = getById(id);

        updatedPhoto.setTitle(request.title());
        updatedPhoto.setFileUrl(request.fileUrl());

        return personPhotoRepository.save(updatedPhoto);
    }

    public PersonPhoto getById(Long id) {
        return personPhotoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person photo not found"));
    }

}