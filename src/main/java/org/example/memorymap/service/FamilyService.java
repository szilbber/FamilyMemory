package org.example.memorymap.service;

import org.example.memorymap.entity.Family;
import org.example.memorymap.entity.Person;
import org.example.memorymap.entity.User;
import org.example.memorymap.repository.FamilyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FamilyService {

    private final FamilyRepository familyRepository;

    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public Family create(Family family) {
        return familyRepository.save(family);
    }

    public void delete(Long id) {
        Family family = getById(id);
        familyRepository.delete(family);
    }

    public Family update(Long id, Family family) {
        Family updatedFamily = getById(id);
        updatedFamily.setName(family.getName());
        return familyRepository.save(updatedFamily);
    }

    public Family getById(Long id) {
        return familyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Family Not Found"));
    }

    public List<Family> getByName(String name) {
        return familyRepository.findByName(name);
    }

    public Set<User> getUsers(Long familyId) {
        Family family = getById(familyId);
        return family.getUsers();
    }

    public Set<Person> getPersons(Long familyId) {
        Family family = getById(familyId);
        return family.getPersons();
    }

}
