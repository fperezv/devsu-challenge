package com.devsu.devsuchallengeuser.domain.service;

import com.devsu.devsuchallengeuser.adapters.out.persistence.mappers.CustomerMapper;
import com.devsu.devsuchallengeuser.domain.models.CustomerModel;
import com.devsu.devsuchallengeuser.infrastructure.exceptions.HttpException;
import com.devsu.devsuchallengeuser.ports.in.CustomerUseCase;
import com.devsu.devsuchallengeuser.ports.out.CustomerPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.util.ObjectUtils.isEmpty;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerUseCase {

  private static final HttpException NOT_FOUND_EXCEPTION =
      new HttpException(NOT_FOUND, "Cliente no encontrado");

  private static final HttpException ALREADY_EXISTS_DOCUMENT_EXCEPTION =
      new HttpException(CONFLICT, "Cliente ya registrado con el mismo tipo y número de documento");

  private final CustomerPort customerPort;

  @Override
  public List<CustomerModel> findAllCustomers() {
    return customerPort.findAllCustomers();
  }

  @Override
  public CustomerModel findCustomerById(Integer id) {
    return customerPort.findCustomerById(id)
        .orElseThrow(() -> NOT_FOUND_EXCEPTION);
  }

  @Override
  public CustomerModel create(CustomerModel customer) {

    validateDocument(customer);

    return customerPort.createCustomer(
        CustomerMapper.INSTANCE.mapOut(customer));
  }

  @Override
  public CustomerModel edit(Integer id, CustomerModel customer) {

    customer.setId(id);

    validateDocument(customer);

    return customerPort.findCustomerById(id)
        .map(currentCustomer -> {

          populateIfPresent(customer.getName(), currentCustomer::setName);
          populateIfPresent(customer.getDocumentNumber(), currentCustomer::setDocumentNumber);
          populateIfPresent(customer.getGender(), currentCustomer::setGender);
          populateIfPresent(customer.getAddress(), currentCustomer::setAddress);
          populateIfPresent(customer.getPhone(), currentCustomer::setPhone);
          populateIfPresent(customer.getDocumentType(), currentCustomer::setDocumentType);

          return currentCustomer;
        })
        .map(CustomerMapper.INSTANCE::mapOut)
        .map(customerPort::saveCustomer)
        .orElseThrow(() -> NOT_FOUND_EXCEPTION);
  }

  @Override
  public CustomerModel update(Integer id, CustomerModel customer) {

    customer.setId(id);

    validateDocument(customer);

    return customerPort.findCustomerById(id)
        .map(ignored ->
            customerPort.saveCustomer(
                CustomerMapper.INSTANCE.mapOut(customer)))
        .orElseThrow(() -> NOT_FOUND_EXCEPTION);
  }

  @Override
  public void delete(Integer id) {

    final var customerDisabled = customerPort.findCustomerById(id)
        .map(customer -> {

          customer.setStatus(Boolean.FALSE);

          return customer;
        })
        .map(CustomerMapper.INSTANCE::mapOut)
        .map(customerPort::saveCustomer)
        .orElseThrow(() -> NOT_FOUND_EXCEPTION);

    log.info("Customer with id {} disabled successfully", customerDisabled.getId());
  }

  private <K> void populateIfPresent(K value, Consumer<K> setter) {

    if (isEmpty(value)) {
      return;
    }

    if (value instanceof String &&
        !hasText(value.toString())) {
      return;
    }

    setter.accept(value);
  }

  private void validateDocument(CustomerModel customer) {
    if (customerPort.findCustomerByDocument(
            customer.getDocumentType().getId(),
            customer.getDocumentNumber())
        .filter(currentCustomer -> isNull(customer.getId()) || !currentCustomer.getId().equals(customer.getId()))
        .isPresent()) {
      throw ALREADY_EXISTS_DOCUMENT_EXCEPTION;
    }
  }
}
