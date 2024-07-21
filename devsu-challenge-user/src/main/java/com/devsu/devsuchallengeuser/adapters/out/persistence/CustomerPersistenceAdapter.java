package com.devsu.devsuchallengeuser.adapters.out.persistence;

import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.CustomerEntity;
import com.devsu.devsuchallengeuser.adapters.out.persistence.mappers.CustomerMapper;
import com.devsu.devsuchallengeuser.adapters.out.persistence.repositories.CustomerRepository;
import com.devsu.devsuchallengeuser.adapters.out.persistence.repositories.DocumentTypeRepository;
import com.devsu.devsuchallengeuser.domain.models.CustomerModel;
import com.devsu.devsuchallengeuser.infrastructure.exceptions.HttpException;
import com.devsu.devsuchallengeuser.ports.out.CustomerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
@RequiredArgsConstructor
class CustomerPersistenceAdapter implements CustomerPort {

  private final CustomerRepository customerRepository;

  private final DocumentTypeRepository documentTypeRepository;

  @Override
  public List<CustomerModel> findAllCustomers() {
    return CustomerMapper.INSTANCE.mapIn(
        customerRepository.findAll());
  }

  @Override
  public Optional<CustomerModel> findCustomerById(Integer id) {
    return customerRepository.findById(id)
        .map(CustomerMapper.INSTANCE::mapIn);
  }

  @Override
  public Optional<CustomerModel> findCustomerByDocument(Integer documentTypeId, String documentNumber) {
    return customerRepository.findByPersonDocumentTypeIdAndPersonDocumentNumber(documentTypeId, documentNumber)
        .map(CustomerMapper.INSTANCE::mapIn);
  }

  @Override
  public CustomerModel createCustomer(CustomerEntity customer) {

    customer.setStatus(Boolean.TRUE);

    checkDocumentType(customer.getPerson().getDocumentType().getId());

    return CustomerMapper.INSTANCE.mapIn(
        customerRepository.save(customer));
  }

  @Override
  public CustomerModel saveCustomer(CustomerEntity customer) {

    checkDocumentType(customer.getPerson().getDocumentType().getId());

    return CustomerMapper.INSTANCE.mapIn(
        customerRepository.save(customer));
  }

  @Override
  public void deleteCustomerById(Integer id) {
    customerRepository.deleteById(id);
  }

  private void checkDocumentType(Integer documentTypeId) {
    if (!documentTypeRepository
        .existsById(documentTypeId)) {

      throw new HttpException(BAD_REQUEST, "Tipo de documento no válido");
    }
  }
}
