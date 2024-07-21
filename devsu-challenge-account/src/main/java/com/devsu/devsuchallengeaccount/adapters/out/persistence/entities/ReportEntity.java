package com.devsu.devsuchallengeaccount.adapters.out.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report")
public class ReportEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "customer_id")
  private Integer customerId;

  @Column(name = "account_id")
  private Integer accountId;

  @Column(name = "movement_id")
  private Long movementId;

  @Column(name = "movement_date")
  private LocalDateTime movementDate;

  @Column(name = "customer")
  private String customer;

  @Column(name = "account_number")
  private String accountNumber;

  @Column(name = "account_type")
  private String accountType;

  @Column(name = "movement_status")
  private Boolean movementStatus;

  @Column(name = "bef_mov_balance")
  private BigDecimal beforeMovementBalance;

  @Column(name = "movement_amount")
  private BigDecimal movementAmount;

  @Column(name = "aft_mov_balance")
  private BigDecimal afterMovementBalance;
}
