package com.millervein.clinicalordertracker;

public enum AppointmentType {
	NEW_PATIENT, LONGTERM_FOLLOWUP, EVTA, EVTA_MP, MOCA, MP, EVCA, OTHER;

	static public AppointmentType fromString(String string) {
		if (string.equals("New Patient")) {
			return AppointmentType.NEW_PATIENT;
		} else if (string.equals("3 Month Follow Up") || string.equals("6 Month Follow Up") || string.equals("Yearly Follow Up")) {
			return AppointmentType.LONGTERM_FOLLOWUP;
		} else if (string.equals("EVTA")) {
			return AppointmentType.EVTA;
		} else if (string.equals("EVTA MP")) {
			return AppointmentType.EVTA_MP;
		} else if (string.equals("MOCA")) {
			return AppointmentType.MOCA;
		} else if (string.equals("Microphlebectomy")) {
			return AppointmentType.MP;
		} else if (string.equals("EVCA")) {
			return AppointmentType.EVCA;
		} else {
			return AppointmentType.OTHER;
		}
	}
}
