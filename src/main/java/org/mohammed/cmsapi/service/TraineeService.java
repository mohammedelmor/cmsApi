package org.mohammed.cmsapi.service;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mohammed.cmsapi.constants.TraineeConstants;
import org.mohammed.cmsapi.dto.*;
import org.mohammed.cmsapi.exception.BodyTypeNotFoundException;
import org.mohammed.cmsapi.exception.BucketNotFoundException;
import org.mohammed.cmsapi.exception.MuscleBalanceNotFoundException;
import org.mohammed.cmsapi.exception.TraineeNotFoundException;
import org.mohammed.cmsapi.mapper.TraineeMapper;
import org.mohammed.cmsapi.model.BodyType;
import org.mohammed.cmsapi.model.MuscleBalance;
import org.mohammed.cmsapi.model.Trainee;
import org.mohammed.cmsapi.repository.TraineeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@Validated
public class TraineeService {

    private final TraineeRepository repository;
    private final TraineeMapper mapper;
    private final MinioClient minioClient;
    private final BodyTypeService bodyTypeService;
    private final MuscleBalanceService muscleBalanceService;

    public Page<TraineeGetDto> findAll(int pageNumber, int pageSize) {
        Sort sort = Sort.by(Sort.Order.asc("createdDate"));
        return repository.findAll(PageRequest.of(pageNumber, pageSize, sort)).map(mapper::toDto);
    }

    public TraineeGetDto findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new TraineeNotFoundException(TraineeConstants.NOT_FOUND)));
    }

    public Trainee getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new TraineeNotFoundException(TraineeConstants.NOT_FOUND));
    }

    public Page<TraineeGetDto> findAllByName(int pageNumber, int pageSize, String name) {
        Sort sort = Sort.by(Sort.Order.asc("createdDate"));
        return repository.findAllByName(PageRequest.of(pageNumber, pageSize, sort), name).map(mapper::toDto);
    }

    public TraineeGetDto create(@Valid TraineePostDto dto) {
        Trainee trainee = mapper.toEntity(dto);
        return mapper.toDto(repository.save(trainee));
    }

    public TraineeGetDto update(Long id, @Valid TraineePutDto dto) {
        Trainee updatedTrainee = repository.findById(id).map(trainee -> {
            trainee.setName(dto.name());
            trainee.setAge(dto.age());
            trainee.setPhoneNumber(dto.phoneNumber());
            return trainee;
        }).orElseThrow(() -> new TraineeNotFoundException(TraineeConstants.NOT_FOUND));
        return mapper.toDto(repository.save(updatedTrainee));
    }

    public void delete(Long id) {
        Optional<Trainee> trainee = repository.findById(id);
        if (trainee.isEmpty()) throw new TraineeNotFoundException(TraineeConstants.NOT_FOUND);
        repository.deleteById(id);
    }

    public TraineeGetDto updateBodyType(Long traineeId, @Valid UpdateTraineeBodyTypeDto dto) {
        try {
            BodyType bodyType = bodyTypeService.getById(dto.bodyTypeId());
            Trainee trainee = getById(traineeId);
            trainee.setBodyType(bodyType);
            return mapper.toDto(repository.save(trainee));
        } catch (BodyTypeNotFoundException | TraineeNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public TraineeGetDto updateMuscleBalance(Long traineeId, @Valid UpdateTraineeMuscleBalanceDto dto) {
        try {
           MuscleBalance muscleBalance = muscleBalanceService.getById(dto.muscleBalanceId());
            Trainee trainee = getById(traineeId);
            trainee.setMuscleBalance(muscleBalance);
            return mapper.toDto(repository.save(trainee));
        } catch (MuscleBalanceNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

}
