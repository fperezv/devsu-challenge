package com.devsu.devsuchallengeuser.ports.in;

import com.devsu.devsuchallengeuser.domain.models.CustomerModel;

import java.util.List;

public interface CustomerUseCase {

  List<CustomerModel> findAllCustomers();

  CustomerModel findCustomerById(Integer id);

  CustomerModel create(CustomerModel customer);

  CustomerModel edit(Integer id, CustomerModel customer);

  CustomerModel update(Integer id, CustomerModel customer);

  void delete(Integer id);
}
