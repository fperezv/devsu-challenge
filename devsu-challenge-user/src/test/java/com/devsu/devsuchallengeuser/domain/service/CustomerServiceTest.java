package com.devsu.devsuchallengeuser.domain.service;

import com.devsu.devsuchallengeuser.adapters.out.persistence.entities.CustomerEntity;
import com.devsu.devsuchallengeuser.adapters.out.persistence.mappers.CustomerMapper;
import com.devsu.devsuchallengeuser.domain.models.CustomerModel;
import com.devsu.devsuchallengeuser.domain.models.DocumentTypeModel;
import com.devsu.devsuchallengeuser.infrastructure.exceptions.HttpException;
import com.devsu.devsuchallengeuser.ports.out.CustomerPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

  @Mock
  private CustomerPort customerPort;

  @InjectMocks
  private CustomerService customerService;

  @Test
  void testFindAllCustomers() {
    // Arrange
    List<CustomerModel> mockCustomers = List.of(new CustomerModel());
    when(customerPort.findAllCustomers()).thenReturn(mockCustomers);

    // Act
    List<CustomerModel> allCustomers = customerService.findAllCustomers();

    // Assert
    verify(customerPort).findAllCustomers();
    assertEquals(mockCustomers, allCustomers);
  }

  @Test
  void testFindCustomerById_Success() {
    // Arrange
    Integer id = 1;
    CustomerModel mockCustomer =
        CustomerModel.builder()
            .id(id)
            .build();
    when(customerPort.findCustomerById(id))
        .thenReturn(Optional.of(mockCustomer));

    // Act
    CustomerModel foundCustomer = customerService.findCustomerById(id);

    // Assert
    verify(customerPort).findCustomerById(id);
    assertEquals(mockCustomer, foundCustomer);
  }

  @Test
  void testFindCustomerById_NotFound() {
    // Arrange
    Integer id = 1;
    when(customerPort.findCustomerById(id)).thenReturn(Optional.empty());

    // Act & assert
    assertThrows(HttpException.class, () -> customerService.findCustomerById(id));

    // Assert
    verify(customerPort).findCustomerById(id);
  }

}
