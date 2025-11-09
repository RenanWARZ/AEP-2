package com.inovalocal.service.impl;

import com.inovalocal.model.Occurrence;
import com.inovalocal.repository.OccurrenceRepository;
import com.inovalocal.service.OccurrenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OccurrenceServiceImpl implements OccurrenceService {
    private final OccurrenceRepository repo;

    public OccurrenceServiceImpl(OccurrenceRepository repo) { this.repo = repo; }

    @Override
    public Occurrence save(Occurrence o) { return repo.save(o); }

    @Override
    public List<Occurrence> findAll() { return repo.findAll(); }

    @Override
    public Optional<Occurrence> findById(Long id) { return repo.findById(id); }

    @Override
    public void delete(Long id) { repo.deleteById(id); }

    @Override
    public List<Occurrence> findByCategory(String category) { return repo.findByCategory(category); }
}
