package org.mohammed.cmsapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mohammed.cmsapi.dto.TraineeGetDto;
import org.mohammed.cmsapi.dto.TraineePostDto;
import org.mohammed.cmsapi.dto.TraineePutDto;
import org.mohammed.cmsapi.service.TraineeService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/trainee")
@RequiredArgsConstructor
public class TraineeController {

    private final TraineeService service;


    @GetMapping
    public ResponseEntity<Page<TraineeGetDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                      @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(service.findAll(pageNumber, pageSize));
    }

    @GetMapping("{id}")
    public ResponseEntity<TraineeGetDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<TraineeGetDto> create(@Valid @RequestBody TraineePostDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<TraineeGetDto> update(@PathVariable Long id, @Valid @RequestBody TraineePutDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadToTraineeBucket(@RequestBody MultipartFile file) throws Exception {
        service.uploadToTraineeBucket(file);
        return ResponseEntity.ok("Uploaded!");
    }
}
