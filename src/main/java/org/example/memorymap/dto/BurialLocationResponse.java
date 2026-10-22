package org.example.memorymap.dto;


import org.example.memorymap.entity.BurialLocation;

public record BurialLocationResponse(
        Long id,
        Double latitude,
        Double longitude,
        Boolean isExact,
        String description,
        Long personId
) {
    public static BurialLocationResponse from(BurialLocation b) {
        return new BurialLocationResponse(
                b.getId(), b.getLatitude(), b.getLongitude(),
                b.getIsExact(), b.getDescription(), b.getPerson().getId());
    }
}