package org.mohammed.cmsapi.repository;

import org.mohammed.cmsapi.model.Trainee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TraineeRepository extends JpaRepository<Trainee, Long> {

    Page<Trainee> findAllByName(Pageable pageable, String name);

}
