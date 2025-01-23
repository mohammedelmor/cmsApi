package org.mohammed.cmsapi.repository;

import org.mohammed.cmsapi.model.FitnessTest;
import org.mohammed.cmsapi.projection.FitnessTestProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;


public interface FitnessTestRepository extends JpaRepository<FitnessTest, Long> {

    @Query("SELECT f FROM FitnessTest f JOIN FETCH f.trainee WHERE f.trainee.id = :traineeId order by f.createdDate asc")
    Collection<FitnessTestProjection> findAllByTraineeId(Long traineeId);

}
