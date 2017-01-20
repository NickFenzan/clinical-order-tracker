package com.millervein.clinicalordertracker.clinicalorder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.millervein.clinicalordertracker.appointment.Appointment;
import com.millervein.clinicalordertracker.appointment.AppointmentStatus;

@Entity
public class ClinicalOrder {
	@Id
	private String id;
	private LocalDate date;
	private String department;
	private String patientId;
	@Enumerated(EnumType.STRING)
	private ClinicalOrderType type;
	private boolean insuranceApprovalValid;
	private boolean outOfPocketCommunicated;
	private boolean procedureInstructionsCommunicated;
	private boolean scheduled;
	private boolean closed;
	@ManyToOne
	private Appointment linkedAppointment;

	protected ClinicalOrder() {
	}

	public ClinicalOrder(String id, LocalDate date, String department, String patientId, ClinicalOrderType type) {
		super();
		this.id = id;
		this.date = date;
		this.department = department;
		this.patientId = patientId;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getDepartment() {
		return department;
	}

	public String getPatientId() {
		return patientId;
	}

	public ClinicalOrderType getType() {
		return type;
	}

	public boolean isInsuranceApprovalValid() {
		return insuranceApprovalValid;
	}

	public boolean isOutOfPocketCommunicated() {
		return outOfPocketCommunicated;
	}

	public boolean isProcedureInstructionsCommunicated() {
		return procedureInstructionsCommunicated;
	}

	public boolean isScheduled() {
		return scheduled;
	}

	public boolean isClosed() {
		return closed;
	}

	public Appointment getLinkedAppointment() {
		return linkedAppointment;
	}

	public void linkAppointment(Appointment appointment) throws Exception {
		if (appointment.getStatus() == AppointmentStatus.CANCELLED) {
			throw new Exception("Cannot link cancelled appointment");
		}
		if (appointment.getStart().toLocalDate().isBefore(date)) {
			throw new Exception("Appointment cannot pre-date order");
		}
		this.linkedAppointment = appointment;
		this.updateAppointmentState();
	}

	public void unlinkAppointment() {
		this.linkedAppointment = null;
		this.updateAppointmentState();
	}

	/**
	 * This method is intended to be used when a user marks an appointment as
	 * scheduled, but cannot provide the appointment. It will be wiped out upon
	 * state check.
	 */
	public void markAsScheduled() {
		this.scheduled = true;
	}

	private void updateAppointmentState() {
		if (this.linkedAppointment != null) {
			if (this.linkedAppointment.getStatus() == AppointmentStatus.CANCELLED) {
				this.unlinkAppointment();
			} else if (isLinkedAppointmentComplete()) {
				this.closed = true;
				this.scheduled = true;
			} else {
				this.scheduled = true;
			}
		} else {
			this.scheduled = false;
		}
	}

	private boolean isLinkedAppointmentComplete() {
		return this.linkedAppointment != null && this.linkedAppointment.getStart().isBefore(LocalDateTime.now())
				&& this.linkedAppointment.getStatus() == AppointmentStatus.COMPLETE;
	}

}
