package com.devsu.devsuchallengeaccount.application.ports.out;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.ReportEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportPort {

  List<ReportEntity> generateReport(LocalDateTime from, LocalDateTime to, Integer customerId);
}
