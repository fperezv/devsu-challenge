package com.devsu.devsuchallengeaccount.adapters.out.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "customer_id", nullable = false)
  private Integer customerId;

  @ManyToOne
  @JoinColumn(name = "account_type_id", nullable = false)
  private AccountTypeEntity type;

  @Column(nullable = false)
  private String number;

  @Column(name = "initial_balance", nullable = false)
  private BigDecimal initialBalance;

  @Column(name = "current_balance", nullable = false)
  private BigDecimal currentBalance;

  @Builder.Default
  @Column(nullable = false)
  private Boolean status = Boolean.TRUE;
}
