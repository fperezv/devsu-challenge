package com.devsu.devsuchallengeuser.adapters.out.persistence.mappers;

import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.DocumentTypeEntity;
import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.PersonEntity;
import com.devsu.devsuchallengeuser.domain.models.DocumentTypeModel;
import com.devsu.devsuchallengeuser.domain.models.PersonModel;
import com.devsu.devsuchallengeuser.infrastructure.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper extends ModelMapper<PersonEntity, PersonModel> {

  PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

  @Override
  @Mapping(source = "documentType", target = "documentType", qualifiedByName = "mapInDocumentType")
  PersonModel mapIn(PersonEntity entity);

  @Override
  @Mapping(source = "documentType", target = "documentType", qualifiedByName = "mapOutDocumentType")
  @Mapping(target = "customer", ignore = true)
  PersonEntity mapOut(PersonModel model);

  @Named("mapInDocumentType")
  default DocumentTypeModel mapInDocumentType(DocumentTypeEntity entity) {
    return DocumentTypeMapper.INSTANCE.mapIn(entity);
  }

  @Named("mapOutDocumentType")
  default DocumentTypeEntity mapOutDocumentType(DocumentTypeModel model) {
    return DocumentTypeMapper.INSTANCE.mapOut(model);
  }
}
