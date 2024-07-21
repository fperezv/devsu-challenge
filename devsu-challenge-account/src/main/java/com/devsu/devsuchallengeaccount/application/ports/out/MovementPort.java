package com.devsu.devsuchallengeaccount.application.ports.out;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.MovementEntity;
import com.devsu.devsuchallengeaccount.domain.models.MovementModel;
import com.devsu.devsuchallengeaccount.domain.models.MovementTypeModel;

import java.util.List;
import java.util.Optional;

public interface MovementPort {

  List<MovementModel> findAllMovementsByAccount(Integer accountId);

  Optional<MovementModel> findMovementById(Integer accountId, Long id);

  Optional<MovementTypeModel> findMovementTypeById(Integer movementTypeId);

  MovementModel createMovement(MovementEntity customer);

  MovementModel saveMovement(MovementEntity customer);

  void deleteMovementById(Integer accountId, Long id);
}
