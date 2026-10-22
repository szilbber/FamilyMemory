package org.example.memorymap.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PersonRequest(
        @Size(max = 255) String firstName,
        @Size(max = 255) String lastName,
        @Size(max = 255) String middleName,
        LocalDate birthDate,
        Integer birthYear,
        LocalDate deathDate,
        Integer deathYear,
        String biography,
        @NotNull Long familyId
) {
    @JsonIgnore
    @AssertTrue(message = "Укажите либо birthDate, либо birthYear")
    public boolean isBirthValid() {
        return birthDate == null || birthYear == null;
    }

    @JsonIgnore
    @AssertTrue(message = "Укажите либо deathDate, либо deathYear")
    public boolean isDeathValid() {
        return deathDate == null || deathYear == null;
    }
}
