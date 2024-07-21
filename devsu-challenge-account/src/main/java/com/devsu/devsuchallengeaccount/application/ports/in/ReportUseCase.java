package com.devsu.devsuchallengeaccount.application.ports.in;

import com.devsu.devsuchallengeaccount.domain.models.response.AccountReportResponse;

import java.util.List;

public interface ReportUseCase {

  List<AccountReportResponse> generateReport(String from, String to, Integer customerId);
}
