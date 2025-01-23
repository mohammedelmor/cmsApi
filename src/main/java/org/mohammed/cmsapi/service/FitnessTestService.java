package org.mohammed.cmsapi.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mohammed.cmsapi.dto.FitnessTestGetDto;
import org.mohammed.cmsapi.dto.FitnessTestPostDto;
import org.mohammed.cmsapi.exception.FitnessTestNotFoundException;
import org.mohammed.cmsapi.mapper.FitnessTestMapper;
import org.mohammed.cmsapi.model.FitnessTest;
import org.mohammed.cmsapi.model.Trainee;
import org.mohammed.cmsapi.projection.FitnessTestProjection;
import org.mohammed.cmsapi.repository.FitnessTestRepository;
import org.mohammed.cmsapi.repository.TraineeRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@Validated
public class FitnessTestService {

    private final FitnessTestRepository repository;
    private final FitnessTestMapper mapper;
    private final TraineeRepository traineeRepository;
    private final TraineeService traineeService;

    public Collection<FitnessTestProjection> findFitnessTestByTraineeId(Long traineeId) {
        return repository.findAllByTraineeId(traineeId);
    }

    public FitnessTestGetDto create(@Valid FitnessTestPostDto dto) {
        Trainee trainee = traineeService.getById(dto.traineeId());
        FitnessTest fitnessTest = mapper.toEntity(dto);
        trainee.getFitnessTests().add(fitnessTest);
        trainee = traineeRepository.save(trainee);
        return mapper.toDto(trainee.getFitnessTests().get(trainee.getFitnessTests().size() - 1));
    }

    public FitnessTestGetDto update(Long id, @Valid FitnessTestPostDto dto) {
        FitnessTest fitnessTest = repository.findById(id)
                .map(test -> {
                    test.setExercise(dto.exercise());
                    test.setWeight(dto.weight());
                    test.setRepetition(dto.repetition());
                    test.setDuration(dto.duration());
                    test.setComment(dto.comment());
                    return test;
                })
                .orElseThrow(() -> new FitnessTestNotFoundException("Fitness test not found"));
        return mapper.toDto(repository.save(fitnessTest));
    }

    public void delete(Long id) {
        FitnessTest fitnessTest = repository.findById(id)
                .orElseThrow(() -> new FitnessTestNotFoundException("Fitness test not found"));
        Trainee trainee = fitnessTest.getTrainee();
        trainee.getFitnessTests().remove(fitnessTest);
        traineeRepository.save(trainee);
    }


}
