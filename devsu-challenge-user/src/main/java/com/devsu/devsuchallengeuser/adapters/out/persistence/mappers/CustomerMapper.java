package com.devsu.devsuchallengeuser.adapters.out.persistence.mappers;

import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.CustomerEntity;
import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.DocumentTypeEntity;
import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.PersonEntity;
import com.devsu.devsuchallengeuser.domain.models.CustomerModel;
import com.devsu.devsuchallengeuser.domain.models.DocumentTypeModel;
import com.devsu.devsuchallengeuser.infrastructure.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper extends ModelMapper<CustomerEntity, CustomerModel> {

  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

  @Override
  @Mapping(target = "id", source = "person.id")
  @Mapping(target = "name", source = "person.name")
  @Mapping(target = "gender", source = "person.gender")
  @Mapping(target = "documentType", source = "person.documentType", qualifiedByName = "mapInDocumentType")
  @Mapping(target = "documentNumber", source = "person.documentNumber")
  @Mapping(target = "address", source = "person.address")
  @Mapping(target = "phone", source = "person.phone")
  @Mapping(target = "password", ignore = true)
  CustomerModel mapIn(CustomerEntity entity);

  @Override
  @Mapping(target = "person", source = "model", qualifiedByName = "mapOutPerson")
  CustomerEntity mapOut(CustomerModel model);

  @Named("mapInDocumentType")
  default DocumentTypeModel mapInDocumentType(DocumentTypeEntity documentType) {
    return PersonMapper.INSTANCE.mapInDocumentType(documentType);
  }

  @Named("mapOutPerson")
  default PersonEntity mapOutPerson(CustomerModel model) {
    return PersonMapper.INSTANCE.mapOut(model);
  }
}
