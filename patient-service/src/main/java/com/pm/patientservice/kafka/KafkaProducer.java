package com.pm.patientservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.patientservice.dto.PatientEventDTO;
import com.pm.patientservice.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

  private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendEvent(Patient patient) {
    PatientEventDTO event = new PatientEventDTO(
        patient.getId().toString(),
        patient.getName(),
        patient.getEmail(),
        "PATIENT_CREATED"
    );

    try {
      String eventJson = objectMapper.writeValueAsString(event);
      kafkaTemplate.send("patient", eventJson);
      log.info("Published PatientEvent to Kafka: {}", eventJson);
    } catch (Exception e) {
      log.error("Error sending PatientCreated event: {}", e.getMessage());
    }
  }
}
