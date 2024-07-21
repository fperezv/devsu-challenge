package com.devsu.devsuchallengeuser.adapters.out.persistence.repositories;

import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

  Optional<CustomerEntity> findByPersonDocumentTypeIdAndPersonDocumentNumber(
      Integer documentTypeId, String documentNumber);
}
