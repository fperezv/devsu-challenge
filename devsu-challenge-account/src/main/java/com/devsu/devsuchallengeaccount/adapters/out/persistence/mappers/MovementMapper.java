package com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.MovementEntity;
import com.devsu.devsuchallengeaccount.domain.models.MovementModel;
import com.devsu.devsuchallengeaccount.infrastructure.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovementMapper extends ModelMapper<MovementEntity, MovementModel> {

  MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

  @Override
  @Mapping(target = "accountId", source = "account.id")
  @Mapping(target = "typeId", source = "type.id")
  @Mapping(target = "type", source = "type.name")
  MovementModel mapIn(MovementEntity entity);

  @Override
  @Mapping(target = "account.id", source = "accountId")
  @Mapping(target = "type.id", source = "typeId")
  MovementEntity mapOut(MovementModel model);
}
