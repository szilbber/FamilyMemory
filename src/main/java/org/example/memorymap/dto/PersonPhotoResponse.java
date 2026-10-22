package org.example.memorymap.dto;


import org.example.memorymap.entity.PersonPhoto;

public record PersonPhotoResponse(
        Long id,
        String title,
        String fileUrl,
        Long personId
) {
    public static PersonPhotoResponse from(PersonPhoto p) {
        return new PersonPhotoResponse(
                p.getId(), p.getTitle(), p.getFileUrl(), p.getPerson().getId());
    }
}