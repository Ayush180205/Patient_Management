package com.pm.analyticsservice.dto;

/**
 * DTO representing a patient event received from Kafka.
 * Replaces the Protobuf-generated PatientEvent class.
 */
public class PatientEventDTO {

  private String patientId;
  private String name;
  private String email;
  private String eventType;

  // Default constructor for Jackson deserialization
  public PatientEventDTO() {}

  public PatientEventDTO(String patientId, String name, String email, String eventType) {
    this.patientId = patientId;
    this.name = name;
    this.email = email;
    this.eventType = eventType;
  }

  public String getPatientId() { return patientId; }
  public void setPatientId(String patientId) { this.patientId = patientId; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getEventType() { return eventType; }
  public void setEventType(String eventType) { this.eventType = eventType; }

  @Override
  public String toString() {
    return "PatientEventDTO{patientId='" + patientId + "', name='" + name +
           "', email='" + email + "', eventType='" + eventType + "'}";
  }
}
