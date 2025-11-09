package com.inovalocal.repository;

import com.inovalocal.model.Occurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {
    List<Occurrence> findByCategory(String category);
}
