package com.devsu.devsuchallengeaccount.application.ports.out;

import com.devsu.devsuchallengeaccount.domain.models.CustomerModel;

import java.util.concurrent.CompletableFuture;

public interface CustomerPort {

  CompletableFuture<CustomerModel> retrieveCustomerById(Integer customerId);
}
