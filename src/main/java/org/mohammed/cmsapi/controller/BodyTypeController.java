package org.mohammed.cmsapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mohammed.cmsapi.dto.BodyTypeGetDto;
import org.mohammed.cmsapi.dto.BodyTypePostDto;
import org.mohammed.cmsapi.service.BodyTypeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/bodyType")
@RequiredArgsConstructor
@Slf4j
public class BodyTypeController {

    private final BodyTypeService service;

    @GetMapping
    public ResponseEntity<Page<BodyTypeGetDto>> findAll(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(service.findAll(pageNumber, pageSize));
    }

    @GetMapping("{id}")
    public ResponseEntity<BodyTypeGetDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("name/{name}")
    public ResponseEntity<BodyTypeGetDto> findByName(@PathVariable String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<BodyTypeGetDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<BodyTypeGetDto> create(@Valid @ModelAttribute BodyTypePostDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<BodyTypeGetDto> update(@PathVariable Long id, @Valid @ModelAttribute BodyTypePostDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
