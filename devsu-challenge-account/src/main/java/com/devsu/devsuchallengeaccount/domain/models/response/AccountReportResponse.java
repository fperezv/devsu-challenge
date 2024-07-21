package com.devsu.devsuchallengeaccount.domain.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class AccountReportResponse {

  @JsonProperty("Fecha")
  @JsonFormat(pattern = "dd/M/yyyy")
  private LocalDateTime date;

  @JsonProperty("Cliente")
  private String customer;

  @JsonProperty("Numero Cuenta")
  private String accountNumber;

  @JsonProperty("Tipo")
  private String accountType;

  @JsonProperty("Saldo Inicial")
  private BigDecimal accountBalanceBeforeMovement;

  @JsonProperty("Estado")
  private Boolean movementStatus;

  @JsonProperty("Movimiento")
  private BigDecimal movementAmount;

  @JsonProperty("Saldo Disponible")
  private BigDecimal accountBalanceAfterMovement;
}
