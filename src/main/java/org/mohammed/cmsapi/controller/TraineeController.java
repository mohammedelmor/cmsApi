package org.mohammed.cmsapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mohammed.cmsapi.dto.*;
import org.mohammed.cmsapi.service.TraineeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
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


    @PostMapping("/{id}/updateBodyType")
    public ResponseEntity<TraineeGetDto> updateBodyType(@PathVariable Long id, @Valid @RequestBody UpdateTraineeBodyTypeDto dto) {
        return ResponseEntity.ok(service.updateBodyType(id, dto));
    }

    @PostMapping("/{id}/updateMuscleBalance")
    public ResponseEntity<TraineeGetDto> updateMuscleBalance(@PathVariable Long id, @Valid @RequestBody UpdateTraineeMuscleBalanceDto dto) {
        return ResponseEntity.ok(service.updateMuscleBalance(id, dto));
    }

    @PostMapping("/{id}/updateQuestionnaireCheck")
    public ResponseEntity<TraineeGetDto> updateQuestionnaireCheck(@PathVariable Long id, @Valid @RequestBody QuestionnaireCheckDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.updateQuestionnaireCheck(id, dto));
    }

}
