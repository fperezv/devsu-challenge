package com.devsu.devsuchallengeaccount.domain.service;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers.AccountMapper;
import com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers.MovementMapper;
import com.devsu.devsuchallengeaccount.application.ports.out.AccountPort;
import com.devsu.devsuchallengeaccount.application.ports.out.MovementPort;
import com.devsu.devsuchallengeaccount.domain.Exceptions;
import com.devsu.devsuchallengeaccount.domain.models.AccountModel;
import com.devsu.devsuchallengeaccount.domain.models.MovementModel;
import com.devsu.devsuchallengeaccount.domain.models.MovementTypeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.requireNonNullElse;

@Component
@RequiredArgsConstructor
public class MovementCreationFacade {

  private final MovementPort movementPort;

  private final AccountPort accountPort;

  @Transactional
  public MovementModel create(Integer accountId, MovementModel movement) {

    final AccountModel account = accountPort.findAccountById(accountId)
        .orElseThrow(() -> Exceptions.ACCOUNT_NOT_FOUND);

    final var transformedMovAmount =
        movementPort.findMovementTypeById(movement.getTypeId())
            .filter(MovementTypeModel::isSubtract)
            .map(ignored -> {

              checkBalance(account.getCurrentBalance(), movement.getAmount());

              return movement.getAmount().multiply(BigDecimal.ONE.negate());
            })
            .orElse(movement.getAmount());

    movement.setAccountId(accountId);
    movement.setDate(requireNonNullElse(movement.getDate(), LocalDateTime.now()));
    movement.setAccountBalance(account.getCurrentBalance());
    movement.setAmount(transformedMovAmount);

    MovementModel newMovement =
        movementPort.createMovement(
            MovementMapper.INSTANCE.mapOut(movement));

    updateBalance(account, newMovement.getAmount());

    return newMovement;
  }

  private void checkBalance(BigDecimal accountBalance, BigDecimal movementAmount) {

    if (accountBalance.compareTo(movementAmount) < 0) {
      throw Exceptions.ACCOUNT_BALANCE_UNAVAILABLE;
    }
  }

  private void updateBalance(AccountModel account, BigDecimal movementAmount) {

    account.setCurrentBalance(account.getCurrentBalance().add(movementAmount));

    accountPort.saveAccount(
        AccountMapper.INSTANCE.mapOut(account));
  }
}
