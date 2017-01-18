package com.millervein.clinicalordertracker;

public class AppointmentCSVRow {
	private String slotId;
	private String date;
	private String startTime;
	private String patientId;
	private String department;
	private String appointmentType;
	private String appointmentStatus;

	public AppointmentCSVRow(String slotId, String date, String startTime, String patientId, String department,
			String appointmentType, String appointmentStatus) {
		super();
		this.slotId = slotId;
		this.date = date;
		this.startTime = startTime;
		this.patientId = patientId;
		this.department = department;
		this.appointmentType = appointmentType;
		this.appointmentStatus = appointmentStatus;
	}

	public String getSlotId() {
		return slotId;
	}

	public String getDate() {
		return date;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getPatientId() {
		return patientId;
	}

	public String getDepartment() {
		return department;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	@Override
	public String toString() {
		return "AppointmentCSVRow [slotId=" + slotId + ", date=" + date + ", startTime=" + startTime + ", patientId="
				+ patientId + ", department=" + department + ", appointmentType=" + appointmentType
				+ ", appointmentStatus=" + appointmentStatus + "]";
	}

}
