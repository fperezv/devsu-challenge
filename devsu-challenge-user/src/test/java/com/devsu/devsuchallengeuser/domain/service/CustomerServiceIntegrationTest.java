package com.devsu.devsuchallengeuser.domain.service;

import com.devsu.devsuchallengeuser.DevsuChallengeUserApplication;
import com.devsu.devsuchallengeuser.adapters.out.persistence.repositories.DocumentTypeRepository;
import com.devsu.devsuchallengeuser.domain.models.CustomerModel;
import com.devsu.devsuchallengeuser.domain.models.DocumentTypeModel;
import com.devsu.devsuchallengeuser.utils.DatabaseConstants;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = DevsuChallengeUserApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {CustomerServiceIntegrationTest.Initializer.class})
public class CustomerServiceIntegrationTest {

  @ClassRule
  public static MySQLContainer<?> mysqlContainer = new MySQLContainer("mysql:5")
      .withDatabaseName("devsu_challenge")
      .withUsername("devsu-database")
      .withPassword("devsu-database");

  @Autowired
  private DocumentTypeRepository documentTypeRepository;

  @Autowired
  private CustomerService customerService;

  @BeforeAll
  static void beforeAll() {
    mysqlContainer.start();
  }

  @AfterAll
  static void afterAll() {
    mysqlContainer.stop();
  }

  @BeforeEach
  void beforeEach() {
    documentTypeRepository.save(DatabaseConstants.DNI);
    documentTypeRepository.save(DatabaseConstants.PASSPORT);
    documentTypeRepository.save(DatabaseConstants.FOREIGN_CARD);
  }

  @Test
  void testCreateCustomer() {
    // Arrange
    CustomerModel customer = CustomerModel.builder()
        .name("Test")
        .gender("M")
        .documentType(
            DocumentTypeModel.builder()
                .id(DatabaseConstants.DNI.getId())
                .build())
        .documentNumber("88888888")
        .address("Test address")
        .phone("Test phone")
        .build();

    // Act
    CustomerModel response = customerService.create(customer);

    // Verify interactions
    assertNotNull(response.getId());
    assertTrue(response.getStatus());
  }

  static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
          "spring.datasource.url=" + mysqlContainer.getJdbcUrl(),
          "spring.datasource.username=" + mysqlContainer.getUsername(),
          "spring.datasource.password=" + mysqlContainer.getPassword()
      ).applyTo(configurableApplicationContext.getEnvironment());
    }
  }
}
