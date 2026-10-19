package org.example.memorymap.repository;

import org.example.memorymap.entity.GravePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GravePhotoRepository extends JpaRepository<GravePhoto, Long> {
}