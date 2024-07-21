package com.devsu.devsuchallengeaccount.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {

  private Integer id;

  private String name;

  private String gender;

  private DocumentTypeModel documentType;

  private String documentNumber;

  private String address;

  private String phone;

  private String password;

  private Boolean status;
}
