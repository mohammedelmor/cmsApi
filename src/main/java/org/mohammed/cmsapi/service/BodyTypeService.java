package org.mohammed.cmsapi.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mohammed.cmsapi.constants.BodyTypeConstants;
import org.mohammed.cmsapi.dto.BodyTypeGetDto;
import org.mohammed.cmsapi.dto.BodyTypePostDto;
import org.mohammed.cmsapi.exception.BodyTypeAlreadyExistsException;
import org.mohammed.cmsapi.exception.BodyTypeNotFoundException;
import org.mohammed.cmsapi.mapper.BodyTypeMapper;
import org.mohammed.cmsapi.model.BodyType;
import org.mohammed.cmsapi.repository.BodyTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@Validated
public class BodyTypeService {

    private final BodyTypeRepository repository;
    private final BodyTypeMapper mapper;

    public Collection<BodyTypeGetDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public Page<BodyTypeGetDto> findAll(int pageNumber, int pageSize) {
        Sort sort = Sort.by(Sort.Order.asc("createdDate"));
        return repository.findAll(PageRequest.of(pageNumber, pageSize, sort)).map(mapper::toDto);
    }

    public BodyTypeGetDto findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new BodyTypeNotFoundException(BodyTypeConstants.NOT_EXIST)));
    }

    public BodyType getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BodyTypeNotFoundException(BodyTypeConstants.NOT_EXIST));
    }

    public BodyTypeGetDto findByName(String name) {
        return mapper.toDto(repository.findByName(name).orElseThrow(() -> new BodyTypeNotFoundException(BodyTypeConstants.NOT_EXIST)));
    }

    public BodyTypeGetDto create(@Valid BodyTypePostDto dto) {
        repository.findByName(dto.name()).ifPresent(bodyType -> {
            throw new BodyTypeAlreadyExistsException(BodyTypeConstants.ALREADY_EXISTS);
        });
        BodyType bodyType = new BodyType();
        bodyType.setFile(dto.image());
        bodyType.setName(dto.name());
        return mapper.toDto(repository.save(bodyType));
    }

    public BodyTypeGetDto update(Long id, @Valid BodyTypePostDto dto) {
        BodyType bodyType = repository.findById(id)
                .map(entity -> {
                    entity.setName(dto.name());
                    if (dto.image() != null)
                        entity.setFile(dto.image());
                    return entity;
                })
                .orElseThrow(() -> new BodyTypeNotFoundException(BodyTypeConstants.NOT_EXIST));
        return mapper.toDto(repository.save(bodyType));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new BodyTypeNotFoundException(BodyTypeConstants.NOT_EXIST));
        repository.deleteById(id);
    }

}
