package com.devsu.devsuchallengeaccount.adapters.out.persistence.repositories;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

  List<ReportEntity> findAllByMovementDateGreaterThanEqualAndMovementDateLessThanEqualAndCustomerIdOrderByMovementDateDesc(
      LocalDateTime beginDate, LocalDateTime endDate, Integer customerId);
}
