package com.devsu.devsuchallengeaccount.adapters.in.rest;

import com.devsu.devsuchallengeaccount.adapters.in.rest.constants.PathConstants;
import com.devsu.devsuchallengeaccount.application.ports.in.AccountUseCase;
import com.devsu.devsuchallengeaccount.domain.models.AccountModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class AccountController {

  private final AccountUseCase accountUseCase;

  @GetMapping(value = PathConstants.ACCOUNTS, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AccountModel>> getAllAccountsByCustomer(
      @PathVariable("customerId") Integer customerId) {

    return ResponseEntity
        .ok(accountUseCase.findAllAccountsByCustomer(customerId));
  }

  @GetMapping(value = PathConstants.ACCOUNT_BY_ID, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<AccountModel> getAccountById(
      @PathVariable("customerId") Integer customerId,
      @PathVariable("id") Integer accountId) {

    return ResponseEntity
        .ok(accountUseCase.findAccountById(customerId, accountId));
  }

  @PostMapping(value = PathConstants.ACCOUNTS, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<AccountModel> createAccount(
      @PathVariable("customerId") Integer customerId,
      @RequestBody @Valid AccountModel account) {

    return ResponseEntity
        .ok(accountUseCase.create(customerId, account));
  }

  @PatchMapping(value = PathConstants.ACCOUNT_BY_ID, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<AccountModel> editAccount(
      @PathVariable("customerId") Integer customerId,
      @PathVariable("id") Integer accountId,
      @RequestBody @Valid AccountModel account) {

    return ResponseEntity
        .ok(accountUseCase.edit(customerId, accountId, account));
  }

  @PutMapping(value = PathConstants.ACCOUNT_BY_ID, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<AccountModel> updateAccount(
      @PathVariable("customerId") Integer customerId,
      @PathVariable("id") Integer accountId,
      @RequestBody @Valid AccountModel account) {

    return ResponseEntity
        .ok(accountUseCase.update(customerId, accountId, account));
  }

  @DeleteMapping(value = PathConstants.ACCOUNT_BY_ID, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deleteAccount(
      @PathVariable("customerId") Integer customerId,
      @PathVariable("id") Integer accountId) {

    accountUseCase.delete(customerId, accountId);
    return ResponseEntity.noContent().build();
  }
}
