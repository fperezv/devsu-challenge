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
public class MovementTypeModel {

  public static final boolean SUBTRACT = Boolean.FALSE;

  public static final boolean ADD = Boolean.TRUE;

  private Long id;

  private String name;

  private String description;

  private Boolean adjustmentType;

  public boolean isSubtract() {
    return adjustmentType == SUBTRACT;
  }

  public boolean isAdd() {
    return adjustmentType == ADD;
  }
}
