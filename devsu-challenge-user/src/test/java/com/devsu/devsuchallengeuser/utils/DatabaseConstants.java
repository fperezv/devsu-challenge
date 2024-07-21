package com.devsu.devsuchallengeuser.utils;

import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.DocumentTypeEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DatabaseConstants {

  public DocumentTypeEntity DNI =
      DocumentTypeEntity.builder()
          .id(1)
          .description("Documento Nacional de Identidad")
          .build();

  public DocumentTypeEntity PASSPORT =
      DocumentTypeEntity.builder()
          .id(2)
          .description("Pasaporte")
          .build();

  public DocumentTypeEntity FOREIGN_CARD =
      DocumentTypeEntity.builder()
          .id(3)
          .description("Carnét de extranjería")
          .build();
}
