
package org.example.memorymap.dto;

import org.example.memorymap.entity.Person;

import java.time.LocalDate;

public record PersonResponse(
        Long id,
        String firstName,
        String lastName,
        String middleName,
        LocalDate birthDate,
        Integer birthYear,
        LocalDate deathDate,
        Integer deathYear,
        String biography,
        Long familyId
) {
    public static PersonResponse from(Person p) {
        return new PersonResponse(
                p.getId(), p.getFirstName(), p.getLastName(), p.getMiddleName(),
                p.getBirthDate(), p.getBirthYear(), p.getDeathDate(), p.getDeathYear(),
                p.getBiography(), p.getFamily().getId());
    }
}
