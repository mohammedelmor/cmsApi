package org.mohammed.cmsapi.repository;

import org.mohammed.cmsapi.model.FitnessTest;
import org.mohammed.cmsapi.model.Trainee;
import org.mohammed.cmsapi.projection.FitnessTestProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TraineeRepository extends JpaRepository<Trainee, Long> {

    Page<Trainee> findAllByName(Pageable pageable, String name);
}
