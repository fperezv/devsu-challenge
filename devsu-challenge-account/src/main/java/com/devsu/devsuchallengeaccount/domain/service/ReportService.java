package com.devsu.devsuchallengeaccount.domain.service;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers.ReportMapper;
import com.devsu.devsuchallengeaccount.application.ports.in.ReportUseCase;
import com.devsu.devsuchallengeaccount.application.ports.out.ReportPort;
import com.devsu.devsuchallengeaccount.domain.models.response.AccountReportResponse;
import com.devsu.devsuchallengeaccount.infrastructure.exceptions.HttpException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService implements ReportUseCase {

  private static final String REPORT_QUERY_DATE_FORMAT = "ddMMyyyy";

  private final ReportPort reportPort;

  @Override
  public List<AccountReportResponse> generateReport(String from, String to, Integer customerId) {

    final var formatter = DateTimeFormatter.ofPattern(REPORT_QUERY_DATE_FORMAT);

    LocalDate fromDate, toDate;
    try {
      fromDate = LocalDate.parse(from, formatter);
      toDate = LocalDate.parse(to, formatter);
    } catch (DateTimeParseException exception) {

      log.error(exception.getMessage(), exception);

      throw new HttpException(
          BAD_REQUEST,
          "El formato de la fecha debe ser %s"
              .formatted(REPORT_QUERY_DATE_FORMAT),
          exception);
    }

    return ReportMapper.INSTANCE.mapIn(
        reportPort
            .generateReport(
                fromDate.atStartOfDay(),
                toDate.atTime(23, 59, 59),
                customerId));
  }
}
