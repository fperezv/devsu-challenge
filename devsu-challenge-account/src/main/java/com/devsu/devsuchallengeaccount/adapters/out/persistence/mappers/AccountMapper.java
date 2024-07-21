package com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.AccountEntity;
import com.devsu.devsuchallengeaccount.domain.models.AccountModel;
import com.devsu.devsuchallengeaccount.infrastructure.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper extends ModelMapper<AccountEntity, AccountModel> {

  AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

  @Override
  @Mapping(target = "typeId", source = "type.id")
  @Mapping(target = "type", source = "type.name")
  AccountModel mapIn(AccountEntity entity);

  @Override
  @Mapping(target = "type.id", source = "typeId")
  AccountEntity mapOut(AccountModel model);
}
