package org.example.memorymap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GravePhotoRequest(
        String title,
        @NotBlank String fileUrl,
        @NotNull Long burialId
) {}