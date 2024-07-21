package com.devsu.devsuchallengeaccount.domain.service;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers.AccountMapper;
import com.devsu.devsuchallengeaccount.application.ports.in.AccountUseCase;
import com.devsu.devsuchallengeaccount.application.ports.out.AccountPort;
import com.devsu.devsuchallengeaccount.application.ports.out.CustomerPort;
import com.devsu.devsuchallengeaccount.domain.Exceptions;
import com.devsu.devsuchallengeaccount.domain.models.AccountModel;
import com.devsu.devsuchallengeaccount.domain.models.CustomerModel;
import com.devsu.devsuchallengeaccount.infrastructure.exceptions.HttpException;
import com.devsu.devsuchallengeaccount.infrastructure.utils.FunctionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNullElse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
class AccountService implements AccountUseCase {

  private final AccountPort accountPort;

  private final CustomerPort customerPort;

  @Override
  public List<AccountModel> findAllAccountsByCustomer(Integer customerId) {
    return accountPort.findAllAccountsByCustomer(customerId);
  }

  @Override
  public AccountModel findAccountById(Integer customerId, Integer accountId) {
    final var waitingCustomer = customerPort.retrieveCustomerById(customerId);

    return accountPort.findAccountById(customerId, accountId)
        .map(account -> {

          try {
            account.setCustomer(waitingCustomer.get().getName());
          } catch (InterruptedException | ExecutionException e) {
            throw new HttpException(INTERNAL_SERVER_ERROR, "Error inesperado", e);
          }

          return account;
        })
        .orElseThrow(() -> Exceptions.ACCOUNT_NOT_FOUND);
  }

  @Override
  public AccountModel create(Integer customerId, AccountModel account) {

    validateCustomer(customerId);
    validateAccountType(account.getTypeId());
    validateAccountNumber(null, account.getNumber());

    account.setCustomerId(customerId);
    account.setStatus(Boolean.TRUE);
    account.setCurrentBalance(requireNonNullElse(account.getCurrentBalance(), account.getInitialBalance()));

    return accountPort.createAccount(
        AccountMapper.INSTANCE.mapOut(account));
  }

  @Override
  public AccountModel edit(Integer customerId, Integer accountId, AccountModel account) {

    validateCustomer(customerId);
    validateAccountType(account.getTypeId());
    validateAccountNumber(accountId, account.getNumber());

    account.setId(accountId);
    account.setCustomerId(customerId);

    return accountPort.findAccountById(customerId, accountId)
        .map(currentAccount -> {

          validateNewFields(currentAccount, account);

          FunctionUtils.populateIfPresent(account.getNumber(), currentAccount::setNumber);
          FunctionUtils.populateIfPresent(account.getTypeId(), currentAccount::setTypeId);
          FunctionUtils.populateIfPresent(account.getInitialBalance(), currentAccount::setInitialBalance);

          return currentAccount;
        })
        .map(AccountMapper.INSTANCE::mapOut)
        .map(accountPort::saveAccount)
        .orElseThrow(() -> Exceptions.ACCOUNT_NOT_FOUND);
  }

  @Override
  public AccountModel update(Integer customerId, Integer accountId, AccountModel account) {

    validateCustomer(customerId);
    validateAccountType(account.getTypeId());
    validateAccountNumber(accountId, account.getNumber());

    account.setId(accountId);
    account.setCustomerId(customerId);

    return accountPort.findAccountById(customerId, accountId)
        .map(ignored ->
            accountPort.saveAccount(
                AccountMapper.INSTANCE.mapOut(account)))
        .orElseThrow(() -> Exceptions.ACCOUNT_NOT_FOUND);
  }

  @Override
  public void delete(Integer customerId, Integer accountId) {

    final var accountDisabled = accountPort.findAccountById(customerId, accountId)
        .map(account -> {

          account.setStatus(Boolean.FALSE);

          return account;
        })
        .map(AccountMapper.INSTANCE::mapOut)
        .map(accountPort::saveAccount)
        .orElseThrow(() -> Exceptions.ACCOUNT_NOT_FOUND);

    log.info("Account with number {} disabled successfully", accountDisabled.getNumber());
  }

  private void validateAccountType(Integer accountTypeId) {

    if (accountPort
        .findAccountTypeById(accountTypeId)
        .isEmpty()) {
      throw Exceptions.ACCOUNT_TYPE_NOT_VALID;
    }
  }

  private void validateAccountNumber(Integer accountId, String accountNumber) {

    if (accountPort
        .findAccountByNumber(accountNumber)
        .filter(currentAccount -> isNull(accountId) || !currentAccount.getId().equals(accountId))
        .isPresent()) {
      throw Exceptions.ACCOUNT_ALREADY_EXISTS_NUMBER;
    }
  }

  private void validateCustomer(Integer customerId) {

    try {
      CustomerModel customer =
          customerPort
              .retrieveCustomerById(customerId)
              .get();

      log.debug("Customer found [{}]", customer.getId());
    } catch (InterruptedException | ExecutionException e) {
      throw new HttpException(INTERNAL_SERVER_ERROR, "Error inesperado", e);
    }
  }

  private void validateNewFields(AccountModel currentAccount, AccountModel newAccount) {

    if (currentAccount.getCurrentBalance().compareTo(newAccount.getCurrentBalance()) != 0) {
      throw new HttpException(BAD_REQUEST, "No se posible cambiar el saldo actual de una cuenta");
    }
  }
}

