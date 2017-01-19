package com.millervein.clinicalordertracker.appointment;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentCSVLoader {
	private AppointmentRepository appointmentRepo;
	private AppointmentCSVParser csvParser;

	@Autowired
	public AppointmentCSVLoader(AppointmentRepository appointmentRepo, AppointmentCSVParser csvParser) {
		super();
		this.appointmentRepo = appointmentRepo;
		this.csvParser = csvParser;
	}

	public void createAppointments(FileReader appointmentCSVFile) throws IOException {
		List<Appointment> appointments = csvParser.parse(appointmentCSVFile);
		appointmentRepo.deleteAll();
		appointmentRepo.save(appointments);
		appointmentRepo.flush();
	}
}
