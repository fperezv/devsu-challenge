package com.devsu.devsuchallengeaccount.application.ports.out;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.AccountEntity;
import com.devsu.devsuchallengeaccount.domain.models.AccountModel;
import com.devsu.devsuchallengeaccount.domain.models.AccountTypeModel;

import java.util.List;
import java.util.Optional;

public interface AccountPort {

  List<AccountModel> findAllAccountsByCustomer(Integer customerId);

  Optional<AccountModel> findAccountById(Integer accountId);

  Optional<AccountModel> findAccountById(Integer customerId, Integer accountId);

  Optional<AccountTypeModel> findAccountTypeById(Integer accountTypeId);

  Optional<AccountModel> findAccountByNumber(String number);

  Optional<AccountModel> findAccountByNumber(Integer customerId, String number);

  AccountModel createAccount(AccountEntity customer);

  AccountModel saveAccount(AccountEntity customer);

  void deleteAccountById(Integer customerId, Integer accountId);
}
