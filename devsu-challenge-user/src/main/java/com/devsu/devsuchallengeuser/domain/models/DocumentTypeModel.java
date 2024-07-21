package com.devsu.devsuchallengeuser.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentTypeModel {

  private Integer id;

  private String description;
}
