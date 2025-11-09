package com.inovalocal.service;

import com.inovalocal.model.Occurrence;

import java.util.List;
import java.util.Optional;

public interface OccurrenceService {
    Occurrence save(Occurrence o);
    List<Occurrence> findAll();
    Optional<Occurrence> findById(Long id);
    void delete(Long id);
    List<Occurrence> findByCategory(String category);
}
