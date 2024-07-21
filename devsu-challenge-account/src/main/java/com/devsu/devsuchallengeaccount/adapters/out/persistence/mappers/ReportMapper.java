package com.devsu.devsuchallengeaccount.adapters.out.persistence.mappers;

import com.devsu.devsuchallengeaccount.adapters.out.persistence.entities.ReportEntity;
import com.devsu.devsuchallengeaccount.domain.models.response.AccountReportResponse;
import com.devsu.devsuchallengeaccount.infrastructure.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportMapper extends ModelMapper<ReportEntity, AccountReportResponse> {

  ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

  @Override
  @Mapping(target = "date", source = "movementDate")
  @Mapping(target = "accountBalanceBeforeMovement", source = "beforeMovementBalance")
  @Mapping(target = "movementStatus", source = "movementStatus")
  @Mapping(target = "accountBalanceAfterMovement", source = "afterMovementBalance")
  AccountReportResponse mapIn(ReportEntity entity);
}
