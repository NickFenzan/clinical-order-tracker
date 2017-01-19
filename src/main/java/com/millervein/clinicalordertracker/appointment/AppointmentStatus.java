package com.millervein.clinicalordertracker.appointment;

public enum AppointmentStatus {
	PENDING, CANCELLED, COMPLETE;

	public static AppointmentStatus fromString(String string) {
		if (string.equals("x - Cancelled")) {
			return AppointmentStatus.CANCELLED;
		} else if (string.equals("f - Filled")) {
			return AppointmentStatus.PENDING;
		} else {
			return AppointmentStatus.COMPLETE;
		}
	}
}
