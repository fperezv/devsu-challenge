package com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.MovementTypeEntity;
import com.devsu.devsuchallengeaccount.domain.models.MovementTypeModel;
import com.devsu.devsuchallengeaccount.infrastructure.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovementTypeMapper extends ModelMapper<MovementTypeEntity, MovementTypeModel> {

  MovementTypeMapper INSTANCE = Mappers.getMapper(MovementTypeMapper.class);

  @Override
  MovementTypeModel mapIn(MovementTypeEntity entity);

  @Override
  MovementTypeEntity mapOut(MovementTypeModel model);
}
