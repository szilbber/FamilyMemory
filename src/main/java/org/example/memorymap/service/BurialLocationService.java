package org.example.memorymap.service;

import org.example.memorymap.dto.BurialLocationRequest;
import org.example.memorymap.entity.BurialLocation;
import org.example.memorymap.entity.GravePhoto;
import org.example.memorymap.entity.Person;
import org.example.memorymap.repository.BurialLocationRepository;
import org.example.memorymap.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BurialLocationService {

    private final BurialLocationRepository burialLocationRepository;
    private final PersonRepository personRepository;

    public BurialLocationService(BurialLocationRepository burialLocationRepository, PersonRepository personRepository) {
        this.burialLocationRepository = burialLocationRepository;
        this.personRepository = personRepository;
    }

    public BurialLocation create(BurialLocationRequest request) {
        Person person = personRepository.findById(request.personId())
                .orElseThrow(() -> new RuntimeException("Person not found"));
        BurialLocation burialLocation = new BurialLocation();
        burialLocation.setLatitude(request.latitude());
        burialLocation.setLongitude(request.longitude());
        burialLocation.setIsExact(request.isExact());
        burialLocation.setDescription(request.description());
        burialLocation.setPerson(person);
        return burialLocationRepository.save(burialLocation);
    }

    public void delete(Long id) {
        BurialLocation burialLocation = getById(id);
        burialLocationRepository.delete(burialLocation);
    }

    public BurialLocation update(Long id, BurialLocationRequest request) {
        BurialLocation updatedLocation = getById(id);

        updatedLocation.setLatitude(request.latitude());
        updatedLocation.setLongitude(request.longitude());
        updatedLocation.setIsExact(request.isExact());
        updatedLocation.setDescription(request.description());

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