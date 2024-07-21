package com.devsu.devsuchallengeuser.adapters.out.persistence.mappers;

import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.DocumentTypeEntity;
import com.devsu.devsuchallengeuser.domain.models.DocumentTypeModel;
import com.devsu.devsuchallengeuser.infrastructure.utils.ModelMapper;
import com.devsu.devsuchallengeuser.domain.models.DocumentTypeModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DocumentTypeMapper extends ModelMapper<DocumentTypeEntity, DocumentTypeModel> {

  DocumentTypeMapper INSTANCE = Mappers.getMapper(DocumentTypeMapper.class);

  @Override
  DocumentTypeModel mapIn(DocumentTypeEntity entity);

  @Override
  DocumentTypeEntity mapOut(DocumentTypeModel model);
}
