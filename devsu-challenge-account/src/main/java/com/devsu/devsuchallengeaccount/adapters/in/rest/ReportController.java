package com.devsu.devsuchallengeaccount.adapters.in.rest;

import com.devsu.devsuchallengeaccount.adapters.in.rest.constants.PathConstants;
import com.devsu.devsuchallengeaccount.application.ports.in.ReportUseCase;
import com.devsu.devsuchallengeaccount.domain.models.response.AccountReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class ReportController {

  private final ReportUseCase reportUseCase;

  @GetMapping(value = PathConstants.REPORTS, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AccountReportResponse>> generateReport(
      @RequestParam(name = "fecha-inicio") String from,
      @RequestParam(name = "fecha-fin") String to,
      @RequestParam(name = "cliente") Integer customerId) {

    return ResponseEntity
        .ok(reportUseCase.generateReport(from, to, customerId));
  }

}
