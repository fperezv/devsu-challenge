package com.devsu.devsuchallengeaccount.adapters.out.persistence;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.AccountEntity;
import com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers.AccountMapper;
import com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers.AccountTypeMapper;
import com.devsu.devsuchallengeaccount.adapters.out.persistence.repositories.AccountRepository;
import com.devsu.devsuchallengeaccount.adapters.out.persistence.repositories.AccountTypeRepository;
import com.devsu.devsuchallengeaccount.application.ports.out.AccountPort;
import com.devsu.devsuchallengeaccount.domain.models.AccountModel;
import com.devsu.devsuchallengeaccount.domain.models.AccountTypeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class AccountPersistenceAdapter implements AccountPort {

  private final AccountRepository accountRepository;

  private final AccountTypeRepository accountTypeRepository;

  @Override
  public List<AccountModel> findAllAccountsByCustomer(Integer customerId) {
    return AccountMapper.INSTANCE.mapIn(
        accountRepository.findAllByCustomerId(customerId));
  }

  @Override
  public Optional<AccountModel> findAccountById(Integer id) {
    return accountRepository.findById(id)
        .map(AccountMapper.INSTANCE::mapIn);
  }

  @Override
  public Optional<AccountModel> findAccountById(Integer customerId, Integer accountId) {
    return accountRepository.findByCustomerIdAndId(customerId, accountId)
        .map(AccountMapper.INSTANCE::mapIn);
  }

  @Override
  public Optional<AccountTypeModel> findAccountTypeById(Integer movementTypeId) {
    return accountTypeRepository.findById(movementTypeId)
        .map(AccountTypeMapper.INSTANCE::mapIn);
  }

  @Override
  public Optional<AccountModel> findAccountByNumber(String number) {
    return accountRepository.findByNumber(number)
        .map(AccountMapper.INSTANCE::mapIn);
  }

  @Override
  public Optional<AccountModel> findAccountByNumber(Integer customerId, String number) {
    return accountRepository.findByCustomerIdAndNumber(customerId, number)
        .map(AccountMapper.INSTANCE::mapIn);
  }

  @Override
  public AccountModel createAccount(AccountEntity account) {
    return AccountMapper.INSTANCE.mapIn(
        accountRepository.save(account));
  }

  @Override
  public AccountModel saveAccount(AccountEntity account) {
    return AccountMapper.INSTANCE.mapIn(
        accountRepository.save(account));
  }

  @Override
  public void deleteAccountById(Integer customerId, Integer accountId) {
    accountRepository.deleteByCustomerIdAndId(customerId, accountId);
  }
}
