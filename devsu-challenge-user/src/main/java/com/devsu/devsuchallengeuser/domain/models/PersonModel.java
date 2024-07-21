package com.devsu.devsuchallengeuser.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PersonModel {

  private Integer id;

  private String name;

  private String gender;

  private DocumentTypeModel documentType;

  private String documentNumber;

  private String address;

  private String phone;
}
