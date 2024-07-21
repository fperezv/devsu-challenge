package com.devsu.devsuchallengeaccount.domain.service;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers.MovementMapper;
import com.devsu.devsuchallengeaccount.application.ports.in.MovementUseCase;
import com.devsu.devsuchallengeaccount.application.ports.out.MovementPort;
import com.devsu.devsuchallengeaccount.domain.Exceptions;
import com.devsu.devsuchallengeaccount.domain.models.MovementModel;
import com.devsu.devsuchallengeaccount.infrastructure.exceptions.HttpException;
import com.devsu.devsuchallengeaccount.infrastructure.utils.FunctionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
class MovementService implements MovementUseCase {

  private final MovementPort movementPort;

  @Override
  public List<MovementModel> findAllMovementsByAccount(Integer accountId) {
    return movementPort.findAllMovementsByAccount(accountId);
  }

  @Override
  public MovementModel findMovementById(Integer accountId, Long movementId) {
    return movementPort.findMovementById(accountId, movementId)
        .orElseThrow(() -> Exceptions.MOVEMENT_NOT_FOUND);
  }

  @Override
  public MovementModel create(Integer accountId, MovementModel movement) {
    throw Exceptions.MISSED_METHOD_INVOKED;
  }

  @Override
  public MovementModel edit(Integer accountId, Long movementId, MovementModel movement) {

    movement.setId(movementId);
    movement.setAccountId(accountId);

    return movementPort.findMovementById(accountId, movementId)
        .map(currentMovement -> {

          if (currentMovement.getAmount().compareTo(movement.getAmount()) != 0) {
            throw new HttpException(BAD_REQUEST, "No está permitida la edición de monto");
          }

          if (!Objects.equals(currentMovement.getTypeId(), movement.getTypeId())) {
            throw new HttpException(BAD_REQUEST, "No está permitida la edición de tipo de movimiento");
          }

          FunctionUtils.populateIfPresent(movement.getDate(), currentMovement::setDate);

          return currentMovement;
        })
        .map(MovementMapper.INSTANCE::mapOut)
        .map(movementPort::saveMovement)
        .orElseThrow(() -> Exceptions.MOVEMENT_NOT_FOUND);
  }

  @Override
  public MovementModel update(Integer accountId, Long movementId, MovementModel movement) {
    throw Exceptions.MISSED_METHOD_INVOKED;
  }

  @Override
  public void delete(Integer accountId, Long movementId) {

    if (movementPort.findMovementById(accountId, movementId).isEmpty()) {
      throw Exceptions.MOVEMENT_NOT_FOUND;
    }

    movementPort.deleteMovementById(accountId, movementId);
  }
}
