package org.mohammed.cmsapi.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mohammed.cmsapi.dto.FitnessTestGetDto;
import org.mohammed.cmsapi.dto.FitnessTestPostDto;
import org.mohammed.cmsapi.projection.FitnessTestProjection;
import org.mohammed.cmsapi.service.FitnessTestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/fitnessTest")
@RequiredArgsConstructor
public class FitnessTestController {

    private final FitnessTestService service;

    @GetMapping("/trainee/{id}")
    public ResponseEntity<Collection<FitnessTestProjection>> findAll(@PathVariable Long id) {
        return ResponseEntity.ok(service.findFitnessTestByTraineeId(id));
    }

    @PostMapping
    public ResponseEntity<FitnessTestGetDto> create(@Valid @RequestBody FitnessTestPostDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FitnessTestGetDto> create(@PathVariable Long id, @Valid @RequestBody FitnessTestPostDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
