package com.millervein.clinicalordertracker;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class AppointmentCSVParser {
	public List<Appointment> parse(Reader in) throws IOException {
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
		List<Appointment> appointments = new ArrayList<Appointment>();
		String lastId = "";
		for (CSVRecord record : records) {
			Appointment appointment = recordToAppointment(record);
			if (appointment.getId().equals(lastId)) {
				continue;
			} else {
				appointments.add(appointment);
				lastId = appointment.getId();
			}
		}
		return appointments;
	}
	
	private Appointment recordToAppointment(CSVRecord record){
		String id = record.get("prnt apptid");
		LocalDate startDate = LocalDate.parse(record.get("apptdate"),DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		LocalTime startTime = LocalTime.parse(record.get("apptstarttime"), DateTimeFormatter.ofPattern("hh:mm a"));
		LocalDateTime start = LocalDateTime.of(startDate, startTime);
		String patientId = record.get("patientid");
		String department = record.get("svc dprtmnt");
		AppointmentType type = AppointmentType.fromString(record.get("appttype"));
		AppointmentStatus status = AppointmentStatus.fromString(record.get("apptslotstatus"));
		return new Appointment(id,start,patientId,department,type,status);
	}
}
