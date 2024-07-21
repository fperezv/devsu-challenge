package com.devsu.devsuchallengeuser.ports.out;

import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.CustomerEntity;
import com.devsu.devsuchallengeuser.domain.models.CustomerModel;

import java.util.List;
import java.util.Optional;

public interface CustomerPort {

  List<CustomerModel> findAllCustomers();

  Optional<CustomerModel> findCustomerById(Integer id);

  Optional<CustomerModel> findCustomerByDocument(Integer documentTypeId, String documentNumber);

  CustomerModel createCustomer(CustomerEntity customer);

  CustomerModel saveCustomer(CustomerEntity customer);

  void deleteCustomerById(Integer id);
}
