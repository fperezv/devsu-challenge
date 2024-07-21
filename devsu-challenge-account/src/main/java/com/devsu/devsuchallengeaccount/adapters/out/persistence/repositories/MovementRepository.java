package com.devsu.devsuchallengeaccount.adapters.out.persistence.repositories;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovementRepository extends JpaRepository<MovementEntity, Long> {

  List<MovementEntity> findAllByAccountId(Integer accountId);

  Optional<MovementEntity> findByAccountIdAndId(Integer accountId, Long movementId);

  void deleteByAccountIdAndId(Integer accountId, Long movementId);
}
