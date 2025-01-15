package org.mohammed.cmsapi.service;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mohammed.cmsapi.constants.MuscleBalanceConstants;
import org.mohammed.cmsapi.dto.MuscleBalanceGetDto;
import org.mohammed.cmsapi.dto.MuscleBalancePostDto;
import org.mohammed.cmsapi.exception.MuscleBalanceAlreadyExistsException;
import org.mohammed.cmsapi.exception.MuscleBalanceNotFoundException;
import org.mohammed.cmsapi.mapper.MuscleBalanceMapper;
import org.mohammed.cmsapi.model.MuscleBalance;
import org.mohammed.cmsapi.repository.MuscleBalanceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@Validated
public class MuscleBalanceService {

    private final MuscleBalanceRepository repository;
    private final MuscleBalanceMapper mapper;

    public Page<MuscleBalanceGetDto> findAll(int pageNumber, int pageSize) {
        Sort sort = Sort.by(Sort.Order.asc("createdDate"));
        return repository.findAll(PageRequest.of(pageNumber, pageSize, sort)).map(mapper::toDto);
    }

    public MuscleBalanceGetDto findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new MuscleBalanceNotFoundException(MuscleBalanceConstants.NOT_EXIST)));
    }

    public MuscleBalanceGetDto findByName( String name) {
        return mapper.toDto(repository.findByName(name).orElseThrow(() -> new MuscleBalanceNotFoundException(MuscleBalanceConstants.NOT_EXIST)));
    }

    public MuscleBalanceGetDto create(@Valid MuscleBalancePostDto dto) {
        repository.findByName(dto.name()).ifPresent(muscleBalance -> {
            throw new MuscleBalanceAlreadyExistsException("This muscle balance already exists!");
        });
        MuscleBalance muscleBalance = new MuscleBalance();
        muscleBalance.setFile(dto.image());
        muscleBalance.setName(dto.name());
        return mapper.toDto(repository.save(muscleBalance));
    }

    public MuscleBalanceGetDto update(Long id, @Valid MuscleBalancePostDto dto) {
        MuscleBalance muscleBalance = repository.findById(id)
                .map(entity -> {
                    entity.setName(dto.name());
                    entity.setFile(dto.image());
                    return entity;
                })
                .orElseThrow(() -> new MuscleBalanceNotFoundException(MuscleBalanceConstants.NOT_EXIST));
        return mapper.toDto(repository.save(muscleBalance));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new MuscleBalanceNotFoundException(MuscleBalanceConstants.NOT_EXIST));
        repository.deleteById(id);
    }

}
