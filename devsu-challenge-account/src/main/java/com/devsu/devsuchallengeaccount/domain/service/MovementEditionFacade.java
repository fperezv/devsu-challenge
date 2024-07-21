package com.devsu.devsuchallengeaccount.domain.service;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers.AccountMapper;
import com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers.MovementMapper;
import com.devsu.devsuchallengeaccount.application.ports.out.AccountPort;
import com.devsu.devsuchallengeaccount.application.ports.out.MovementPort;
import com.devsu.devsuchallengeaccount.domain.Exceptions;
import com.devsu.devsuchallengeaccount.domain.models.AccountModel;
import com.devsu.devsuchallengeaccount.domain.models.MovementModel;
import com.devsu.devsuchallengeaccount.infrastructure.utils.FunctionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class MovementEditionFacade {

  private final MovementPort movementPort;

  private final AccountPort accountPort;

  public MovementModel edit(Integer accountId, Long movementId, MovementModel newMovement) {

    final var account = accountPort.findAccountById(accountId)
        .orElseThrow(() -> Exceptions.ACCOUNT_NOT_FOUND);
    final var currentMovement = movementPort.findMovementById(account.getId(), movementId)
        .orElseThrow(() -> Exceptions.MOVEMENT_NOT_FOUND);

    checkBalance(
        account.getCurrentBalance(),
        currentMovement.getAmount(),
        newMovement.getAmount());

    FunctionUtils.populateIfPresent(currentMovement.getAmount(), newMovement::setAmount);
    FunctionUtils.populateIfPresent(currentMovement.getDate(), newMovement::setDate);

    final var editedMovement =
        movementPort.saveMovement(
            MovementMapper.INSTANCE.mapOut(currentMovement));

    updateBalance(account, editedMovement);

    return editedMovement;
  }

  private void checkBalance(BigDecimal accountBalance, BigDecimal currentAmount, BigDecimal newAmount) {

    final var amountComparison = currentAmount.compareTo(newAmount);

    // Current amount greater than or equals to new amount
    if (amountComparison >= 0) {
      return;
    }

    final var diff = currentAmount.subtract(newAmount);

    if (accountBalance.compareTo(diff) < 0) {
      throw Exceptions.ACCOUNT_BALANCE_UNAVAILABLE;
    }
  }

  private void updateBalance(AccountModel account, MovementModel movement) {

    account.setCurrentBalance(account.getCurrentBalance().subtract(movement.getAmount()));

    accountPort.saveAccount(
        AccountMapper.INSTANCE.mapOut(account));
  }
}
