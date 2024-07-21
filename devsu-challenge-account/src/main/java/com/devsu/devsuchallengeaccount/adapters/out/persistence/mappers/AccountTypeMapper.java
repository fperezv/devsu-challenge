package com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.AccountTypeEntity;
import com.devsu.devsuchallengeaccount.domain.models.AccountTypeModel;
import com.devsu.devsuchallengeaccount.infrastructure.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountTypeMapper extends ModelMapper<AccountTypeEntity, AccountTypeModel> {

  AccountTypeMapper INSTANCE = Mappers.getMapper(AccountTypeMapper.class);

  @Override
  AccountTypeModel mapIn(AccountTypeEntity entity);

  @Override
  AccountTypeEntity mapOut(AccountTypeModel model);
}
