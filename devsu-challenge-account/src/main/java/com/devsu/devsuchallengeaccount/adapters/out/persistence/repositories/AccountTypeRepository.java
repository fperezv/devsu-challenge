package com.devsu.devsuchallengeaccount.adapters.out.persistence.repositories;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.AccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, Integer> {
}
