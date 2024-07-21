package com.devsu.devsuchallengeuser.adapters.out.persistence.repositories;

import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, Integer> {
}
