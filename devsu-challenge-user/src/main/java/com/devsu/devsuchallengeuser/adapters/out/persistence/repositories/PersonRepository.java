package com.devsu.devsuchallengeuser.adapters.out.persistence.repositories;

import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.PersonEntity;
import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
