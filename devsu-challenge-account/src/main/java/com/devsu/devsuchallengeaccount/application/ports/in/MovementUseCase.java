package com.devsu.devsuchallengeaccount.application.ports.in;

import com.devsu.devsuchallengeaccount.domain.models.MovementModel;

import java.util.List;

public interface MovementUseCase {

  List<MovementModel> findAllMovementsByAccount(Integer accountId);

  MovementModel findMovementById(Integer accountId, Long movementId);

  MovementModel create(Integer accountId, MovementModel movement);

  MovementModel edit(Integer accountId, Long movementId, MovementModel movement);

  MovementModel update(Integer accountId, Long movementId, MovementModel movement);

  void delete(Integer accountId, Long movementId);
}
