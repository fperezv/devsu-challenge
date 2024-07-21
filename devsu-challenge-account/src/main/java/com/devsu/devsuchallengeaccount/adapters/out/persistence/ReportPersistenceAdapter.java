package com.devsu.devsuchallengeaccount.adapters.out.persistence;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.ReportEntity;
import com.devsu.devsuchallengeaccount.adapters.out.persistence.repositories.ReportRepository;
import com.devsu.devsuchallengeaccount.application.ports.out.ReportPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReportPersistenceAdapter implements ReportPort {

  private final ReportRepository reportRepository;

  @Override
  public List<ReportEntity> generateReport(LocalDateTime from, LocalDateTime to, Integer customerId) {
    return reportRepository
        .findAllByMovementDateGreaterThanEqualAndMovementDateLessThanEqualAndCustomerIdOrderByMovementDateDesc(
            from, to, customerId);
  }
}
