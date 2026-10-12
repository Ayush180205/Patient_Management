package com.pm.analyticsservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.analyticsservice.dto.PatientEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

  private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
  private final ObjectMapper objectMapper = new ObjectMapper();

  @KafkaListener(topics = "patient", groupId = "analytics-service")
  public void consumeEvent(String event) {
    try {
      PatientEventDTO patientEvent = objectMapper.readValue(event, PatientEventDTO.class);

      // ... perform any business logic related to analytics here

      log.info("Received Patient Event: [PatientId={}, PatientName={}, PatientEmail={}, EventType={}]",
          patientEvent.getPatientId(),
          patientEvent.getName(),
          patientEvent.getEmail(),
          patientEvent.getEventType());

    } catch (Exception e) {
      log.error("Error deserializing patient event: {}", e.getMessage());
    }
  }
}
