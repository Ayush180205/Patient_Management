package com.pm.patientservice.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * REST client replacing the previous gRPC BillingServiceGrpcClient.
 * Calls billing-service via plain HTTP POST instead of gRPC.
 */
@Service
public class BillingServiceGrpcClient {

  private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);

  private final RestTemplate restTemplate;
  private final String billingServiceUrl;

  public BillingServiceGrpcClient(
      RestTemplate restTemplate,
      @Value("${billing.service.url:http://localhost:4001}") String billingServiceUrl) {
    this.restTemplate = restTemplate;
    this.billingServiceUrl = billingServiceUrl;
    log.info("Connecting to Billing Service at {}", billingServiceUrl);
  }

  public Map<String, String> createBillingAccount(String patientId, String name, String email) {
    Map<String, String> request = Map.of(
        "patientId", patientId,
        "name", name,
        "email", email
    );

    log.info("Sending createBillingAccount REST request for patientId={}", patientId);

    @SuppressWarnings("unchecked")
    Map<String, String> response = restTemplate.postForObject(
        billingServiceUrl + "/billing/accounts",
        request,
        Map.class
    );

    log.info("Received billing response: {}", response);
    return response;
  }
}
