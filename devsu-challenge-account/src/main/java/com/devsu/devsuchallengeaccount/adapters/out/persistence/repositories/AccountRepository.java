package com.devsu.devsuchallengeaccount.adapters.out.persistence.repositories;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

  List<AccountEntity> findAllByCustomerId(Integer customerId);

  Optional<AccountEntity> findByCustomerIdAndId(Integer customerId, Integer accountId);

  Optional<AccountEntity> findByCustomerIdAndNumber(Integer customerId, String number);

  Optional<AccountEntity> findByNumber(String number);

  void deleteByCustomerIdAndId(Integer customerId, Integer accountId);
}
