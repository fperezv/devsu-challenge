package com.devsu.devsuchallengeaccount.application.ports.in;

import com.devsu.devsuchallengeaccount.domain.models.AccountModel;

import java.util.List;

public interface AccountUseCase {

  List<AccountModel> findAllAccountsByCustomer(Integer customerId);

  AccountModel findAccountById(Integer customerId, Integer accountId);

  AccountModel create(Integer customerId, AccountModel customer);

  AccountModel edit(Integer customerId, Integer accountId, AccountModel customer);

  AccountModel update(Integer customerId, Integer accountId, AccountModel customer);

  void delete(Integer customerId, Integer id);
}
