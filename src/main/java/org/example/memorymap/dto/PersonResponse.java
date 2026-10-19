
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
        String biography
) {
    public static PersonResponse from(Person person) {
        return new PersonResponse(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getMiddleName(),
                person.getBirthDate(),
                person.getBirthYear(),
                person.getDeathDate(),
                person.getDeathYear(),
                person.getBiography()
        );
    }
}
