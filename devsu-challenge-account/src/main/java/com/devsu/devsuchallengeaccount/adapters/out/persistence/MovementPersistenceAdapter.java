package com.devsu.devsuchallengeaccount.adapters.out.persistence;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.MovementEntity;
import com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers.MovementMapper;
import com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers.MovementTypeMapper;
import com.devsu.devsuchallengeaccount.adapters.out.persistence.repositories.AccountTypeRepository;
import com.devsu.devsuchallengeaccount.adapters.out.persistence.repositories.MovementRepository;
import com.devsu.devsuchallengeaccount.adapters.out.persistence.repositories.MovementTypeRepository;
import com.devsu.devsuchallengeaccount.application.ports.out.MovementPort;
import com.devsu.devsuchallengeaccount.domain.models.MovementModel;
import com.devsu.devsuchallengeaccount.domain.models.MovementTypeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class MovementPersistenceAdapter implements MovementPort {

  private final MovementRepository movementRepository;

  private final MovementTypeRepository movementTypeRepository;
  private final AccountTypeRepository accountTypeRepository;

  @Override
  public List<MovementModel> findAllMovementsByAccount(Integer accountId) {
    return MovementMapper.INSTANCE.mapIn(
        movementRepository.findAllByAccountId(accountId));
  }

  @Override
  public Optional<MovementModel> findMovementById(Integer accountId, Long movementId) {
    return movementRepository.findByAccountIdAndId(accountId, movementId)
        .map(MovementMapper.INSTANCE::mapIn);
  }

  @Override
  public Optional<MovementTypeModel> findMovementTypeById(Integer movementTypeId) {
    return movementTypeRepository.findById(movementTypeId)
        .map(MovementTypeMapper.INSTANCE::mapIn);
  }

  @Override
  public MovementModel createMovement(MovementEntity customer) {
    return MovementMapper.INSTANCE.mapIn(
        movementRepository.save(customer));
  }

  @Override
  public MovementModel saveMovement(MovementEntity customer) {
    return MovementMapper.INSTANCE.mapIn(
        movementRepository.save(customer));
  }

  @Override
  public void deleteMovementById(Integer accountId, Long movementId) {
    movementRepository.deleteByAccountIdAndId(accountId, movementId);
  }
}
