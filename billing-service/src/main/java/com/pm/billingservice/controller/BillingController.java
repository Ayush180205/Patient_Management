package com.pm.billingservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * REST replacement for the previous gRPC BillingGrpcService.
 * Exposes POST /billing/accounts to create a billing account for a patient.
 */
@RestController
@RequestMapping("/billing")
public class BillingController {

  private static final Logger log = LoggerFactory.getLogger(BillingController.class);

  @PostMapping("/accounts")
  public ResponseEntity<Map<String, String>> createBillingAccount(
      @RequestBody Map<String, String> request) {

    String patientId = request.get("patientId");
    String name = request.get("name");
    String email = request.get("email");

    log.info("createBillingAccount request received for patientId={}, name={}, email={}",
        patientId, name, email);

    // Business logic placeholder - e.g. save to DB, calculate billing, etc.
    String accountId = UUID.randomUUID().toString();

    return ResponseEntity.ok(Map.of(
        "accountId", accountId,
        "status", "ACTIVE"
    ));
  }
}
