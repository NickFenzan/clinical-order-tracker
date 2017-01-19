package com.millervein.clinicalordertracker.appointment;

public enum AppointmentType {
	ABLATION, MP, ABLATION_MP, EVCA, OTHER;

	static public AppointmentType fromString(String string) {
		if (string.equals("EVTA") || string.equals("MOCA") || string.equals("Varithena") || string.equals("VenaSeal")) {
			return AppointmentType.ABLATION;
		} else if (string.equals("EVTA MP")) {
			return AppointmentType.ABLATION_MP;
		} else if (string.equals("Microphlebectomy")) {
			return AppointmentType.MP;
		} else if (string.equals("EVCA")) {
			return AppointmentType.EVCA;
		} else {
			return AppointmentType.OTHER;
		}
	}
}
