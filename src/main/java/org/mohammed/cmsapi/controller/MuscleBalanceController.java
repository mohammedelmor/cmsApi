package org.mohammed.cmsapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mohammed.cmsapi.dto.MuscleBalanceGetDto;
import org.mohammed.cmsapi.dto.MuscleBalancePostDto;
import org.mohammed.cmsapi.service.MuscleBalanceService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/muscleBalance")
@RequiredArgsConstructor
@Slf4j
public class MuscleBalanceController {

    private final MuscleBalanceService service;

    @GetMapping
    public ResponseEntity<Page<MuscleBalanceGetDto>> findAll(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(service.findAll(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MuscleBalanceGetDto> findById(@PathVariable  Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<MuscleBalanceGetDto> findByName(@PathVariable String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MuscleBalanceGetDto> create(@ModelAttribute MuscleBalancePostDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MuscleBalanceGetDto> update(@PathVariable Long id, @ModelAttribute MuscleBalancePostDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }



}
