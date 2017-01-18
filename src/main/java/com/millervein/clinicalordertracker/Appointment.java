package com.millervein.clinicalordertracker;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Appointment {
	@Id
	private String id;
	private LocalDateTime start;
	private String patientId;
	private String department;
	@Enumerated(EnumType.STRING)
	private AppointmentType type;
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;

	public Appointment(String id, LocalDateTime start, String patientId, String department, AppointmentType type,
			AppointmentStatus status) {
		super();
		this.id = id;
		this.start = start;
		this.patientId = patientId;
		this.department = department;
		this.type = type;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public String getPatientId() {
		return patientId;
	}

	public String getDepartment() {
		return department;
	}

	public AppointmentType getType() {
		return type;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

}
