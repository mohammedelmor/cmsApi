package org.mohammed.cmsapi.repository;

import org.mohammed.cmsapi.model.MuscleBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MuscleBalanceRepository extends JpaRepository<MuscleBalance, Long> {

    Optional<MuscleBalance> findByName(String name);
}
