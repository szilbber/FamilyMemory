package org.example.memorymap.dto;

import org.example.memorymap.entity.GravePhoto;

public record GravePhotoResponse(
        Long id,
        String title,
        String fileUrl,
        Long burialId
) {
    public static GravePhotoResponse from(GravePhoto g) {
        return new GravePhotoResponse(
                g.getId(), g.getTitle(), g.getFileUrl(), g.getBurialLocation().getId());
    }
}