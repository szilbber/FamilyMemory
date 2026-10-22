package org.example.memorymap.dto;

import jakarta.validation.constraints.NotBlank;

public record FamilyRequest(
        @NotBlank String name
) {}