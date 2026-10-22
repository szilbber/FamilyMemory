package org.example.memorymap.dto;

import org.example.memorymap.entity.Family;

public record FamilyResponse(
        Long id,
        String name
) {
    public static FamilyResponse from(Family f) {
        return new FamilyResponse(f.getId(), f.getName());
    }
}