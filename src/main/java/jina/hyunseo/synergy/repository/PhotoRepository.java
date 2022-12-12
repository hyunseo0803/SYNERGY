package jina.hyunseo.synergy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jina.hyunseo.synergy.domain.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    
}
