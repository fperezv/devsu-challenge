package com.devsu.devsuchallengeaccount.adapters.out.rest;

import com.devsu.devsuchallengeaccount.application.ports.out.CustomerPort;
import com.devsu.devsuchallengeaccount.domain.models.CustomerModel;
import com.devsu.devsuchallengeaccount.infrastructure.exceptions.HttpException;
import com.devsu.devsuchallengeaccount.infrastructure.models.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
class CustomerRestClient implements CustomerPort {

  private final RestTemplate restTemplate;

  private final ObjectMapper objectMapper;

  @Value("${application.customer.url}")
  private String customerBaseURL;

  @Async
  @Override
  public CompletableFuture<CustomerModel> retrieveCustomerById(Integer customerId) {

    final var customerByIdURL =
        customerBaseURL
            .concat("/")
            .concat(String.valueOf(customerId));

    try {

      return CompletableFuture.completedFuture(
          restTemplate.getForObject(
              customerByIdURL,
              CustomerModel.class));
    } catch (HttpClientErrorException exception) {

      throw new HttpException(
          HttpStatus.valueOf(exception.getStatusCode().value()),
          resolveError(exception).getMessage(),
          exception);
    }
  }

  private ErrorResponse resolveError(HttpClientErrorException exception) {
    try {
      return objectMapper.readValue(
          exception.getResponseBodyAsString(),
          ErrorResponse.class);
    } catch (JsonProcessingException e) {
      throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al intentar deserializar respuesta del servicio", e);
    }
  }
}
