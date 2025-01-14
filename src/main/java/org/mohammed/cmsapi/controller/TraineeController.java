package org.mohammed.cmsapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mohammed.cmsapi.dto.TraineeGetDto;
import org.mohammed.cmsapi.dto.TraineePostDto;
import org.mohammed.cmsapi.dto.TraineePutDto;
import org.mohammed.cmsapi.service.TraineeService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/trainee")
@RequiredArgsConstructor
public class TraineeController {

    private final TraineeService service;


    @GetMapping
    public Page<TraineeGetDto> findAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return service.findAll(pageNumber, pageSize);
    }

    @GetMapping("{id}")
    public TraineeGetDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public TraineeGetDto create(@Valid @RequestBody TraineePostDto dto) {
        return service.create(dto);
    }

    @PutMapping("{id}")
    public TraineeGetDto update(@PathVariable Long id, @Valid @RequestBody TraineePutDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadToTraineeBucket(@RequestBody MultipartFile file) throws Exception {
        service.uploadToTraineeBucket(file);
    }
}
