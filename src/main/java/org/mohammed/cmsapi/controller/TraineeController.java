package org.mohammed.cmsapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mohammed.cmsapi.dto.TraineeGetDto;
import org.mohammed.cmsapi.dto.TraineePostDto;
import org.mohammed.cmsapi.dto.TraineePutDto;
import org.mohammed.cmsapi.service.TraineeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public TraineeGetDto create(@Valid @RequestBody TraineePostDto dto) {
        return service.create(dto);
    }

    @PutMapping("{id}")
    public TraineeGetDto update(Long id, @Valid @RequestBody TraineePutDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("{id}")
    public void delete(Long id) {
        service.delete(id);
    }
}
