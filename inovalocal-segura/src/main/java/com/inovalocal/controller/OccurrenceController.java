package com.inovalocal.controller;

import com.inovalocal.model.Occurrence;
import com.inovalocal.model.User;
import com.inovalocal.service.OccurrenceService;
import com.inovalocal.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/occurrences")
public class OccurrenceController {
    private final OccurrenceService service;
    private final UserService userService;

    public OccurrenceController(OccurrenceService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public List<Occurrence> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Occurrence> get(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public List<Occurrence> byCategory(@PathVariable String category) {
        return service.findByCategory(category);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OccurrenceDto dto) {
        User reporter = null;
        if (dto.reporterId != null) {
            reporter = userService.findById(dto.reporterId).orElse(null);
        }
        Occurrence o = new Occurrence(dto.title, dto.description, dto.category, reporter);
        Occurrence saved = service.save(o);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Occurrence> update(@PathVariable Long id, @RequestBody OccurrenceDto dto) {
        return service.findById(id).map(existing -> {
            existing.setTitle(dto.title);
            existing.setDescription(dto.description);
            existing.setCategory(dto.category);
            if (dto.reporterId != null) {
                User reporter = userService.findById(dto.reporterId).orElse(null);
                existing.setReporter(reporter);
            }
            service.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Occurrence> updateStatus(@PathVariable Long id, @RequestBody StatusDto s) {
        return service.findById(id).map(o -> {
            o.setStatus(s.status);
            service.save(o);
            return ResponseEntity.ok(o);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    public static class OccurrenceDto {
        public String title;
        public String description;
        public String category;
        public Long reporterId;
    }

    public static class StatusDto {
        public String status;
    }
}
