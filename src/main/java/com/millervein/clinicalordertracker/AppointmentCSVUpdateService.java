package com.millervein.clinicalordertracker;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentCSVUpdateService {
	private AppointmentRepository appointmentRepo;
	private AppointmentCSVParser csvParser;

	@Autowired
	public AppointmentCSVUpdateService(AppointmentRepository appointmentRepo, AppointmentCSVParser csvParser) {
		super();
		this.appointmentRepo = appointmentRepo;
		this.csvParser = csvParser;
	}

	public void updateAppointments(FileReader appointmentCSVFile) throws IOException {
		List<Appointment> appointments = csvParser.parse(appointmentCSVFile);
		appointmentRepo.deleteAll();
		appointmentRepo.save(appointments);
		appointmentRepo.flush();
	}
}
