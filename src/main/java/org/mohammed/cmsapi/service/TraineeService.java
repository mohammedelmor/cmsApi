package org.mohammed.cmsapi.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mohammed.cmsapi.dto.TraineeGetDto;
import org.mohammed.cmsapi.dto.TraineePostDto;
import org.mohammed.cmsapi.dto.TraineePutDto;
import org.mohammed.cmsapi.exception.TraineeNotFoundException;
import org.mohammed.cmsapi.mapper.TraineeMapper;
import org.mohammed.cmsapi.model.Trainee;
import org.mohammed.cmsapi.repository.TraineeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@Validated
public class TraineeService {

    private final TraineeRepository repository;
    private final TraineeMapper mapper;

    public Page<TraineeGetDto> findAll(int pageNumber, int pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize)).map(mapper::toDto);
    }

    public TraineeGetDto findById(Long id) {
        Trainee trainee = repository.findById(id).orElseThrow(() -> new TraineeNotFoundException("This trainee is not registered!"));
        return mapper.toDto(trainee);
    }

    public Collection<TraineeGetDto> findAllByName(String name) {
        return repository.findAllByName(name).stream().map(mapper::toDto).toList();
    }

    public TraineeGetDto create(@Valid TraineePostDto dto) {
        Trainee trainee = mapper.toEntity(dto);
        return mapper.toDto(repository.save(trainee));
    }

    public TraineeGetDto update(Long id, @Valid TraineePutDto dto) {
        Trainee updatedTrainee = repository.findById(id).map(trainee -> {
            trainee.setName(dto.name());
            trainee.setAge(dto.age());
            return trainee;
        }).orElseThrow(() -> new TraineeNotFoundException("This trainee is not registered!"));
        return mapper.toDto(repository.save(updatedTrainee));
    }

    public void delete(Long id) {
        Optional<Trainee> trainee = repository.findById(id);
        if (trainee.isEmpty()) throw new TraineeNotFoundException("This trainee is not registered!");
        repository.deleteById(id);
    }

}
