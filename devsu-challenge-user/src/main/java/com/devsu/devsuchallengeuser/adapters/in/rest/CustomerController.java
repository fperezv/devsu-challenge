package com.devsu.devsuchallengeuser.adapters.in.rest;

import com.devsu.devsuchallengeuser.adapters.in.rest.constants.PathConstants;
import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.CustomerEntity;
import com.devsu.devsuchallengeuser.domain.models.CustomerModel;
import com.devsu.devsuchallengeuser.ports.in.CustomerUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerUseCase customerUseCase;

  @GetMapping(value = PathConstants.CUSTOMERS, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CustomerModel>> getAllCustomers() {

    return ResponseEntity
        .ok(customerUseCase.findAllCustomers());
  }

  @GetMapping(value = PathConstants.CUSTOMER_BY_ID, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerModel> getCustomerById(
      @PathVariable("id") Integer customerId) {

    return ResponseEntity
        .ok(customerUseCase.findCustomerById(customerId));
  }

  @PostMapping(value = PathConstants.CUSTOMERS, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerModel> createCustomer(
      @RequestBody @Valid CustomerModel customer) {

    return ResponseEntity
        .ok(customerUseCase.create(customer));
  }

  @PatchMapping(value = PathConstants.CUSTOMER_BY_ID, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerModel> editCustomer(
      @PathVariable("id") Integer customerId,
      @RequestBody @Valid CustomerModel customer) {

    return ResponseEntity
        .ok(customerUseCase.edit(customerId, customer));
  }

  @PutMapping(value = PathConstants.CUSTOMER_BY_ID, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerModel> updateCustomer(
      @PathVariable("id") Integer customerId,
      @RequestBody @Valid CustomerModel customer) {

    return ResponseEntity
        .ok(customerUseCase.update(customerId, customer));
  }

  @DeleteMapping(value = PathConstants.CUSTOMER_BY_ID, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deleteCustomer(
      @PathVariable("id") Integer customerId) {

    customerUseCase.delete(customerId);
    return ResponseEntity.noContent().build();
  }
}
