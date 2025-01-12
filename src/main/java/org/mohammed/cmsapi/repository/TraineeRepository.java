package org.mohammed.cmsapi.repository;

import org.mohammed.cmsapi.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TraineeRepository extends JpaRepository<Trainee, Long> {

    Collection<Trainee> findAllByName(String name);

}
