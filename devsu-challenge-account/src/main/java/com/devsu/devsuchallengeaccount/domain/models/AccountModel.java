package com.devsu.devsuchallengeaccount.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class AccountModel {

  private Integer id;

  @JsonIgnore
  private Integer customerId;

  private String customer;

  @NotNull
  private Integer typeId;

  private String type;

  @NotBlank
  private String number;

  @NotNull
  private BigDecimal initialBalance;

  private BigDecimal currentBalance;

  private Boolean status;

  @JsonIgnore
  public boolean isActive() {
    return status;
  }
}
