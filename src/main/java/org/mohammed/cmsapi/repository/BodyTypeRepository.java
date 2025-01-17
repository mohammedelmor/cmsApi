package org.mohammed.cmsapi.repository;

import org.mohammed.cmsapi.model.BodyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BodyTypeRepository extends JpaRepository<BodyType, Long> {
    Optional<BodyType> findByName(String name);
}
