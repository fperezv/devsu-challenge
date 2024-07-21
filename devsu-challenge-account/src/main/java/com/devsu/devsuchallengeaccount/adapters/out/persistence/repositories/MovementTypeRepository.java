package com.devsu.devsuchallengeaccount.adapters.out.persistence.repositories;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.MovementTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementTypeRepository extends JpaRepository<MovementTypeEntity, Integer> {
}
