package com.millervein.clinicalordertracker;

import java.io.File;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicalordertrackerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ClinicalordertrackerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		File file = new File("/home/nick/Code/eclipse/clinicalordertracker/src/main/resources/appointments.csv");
		List<AppointmentCSVRow> appointmentCSV = AppointmentCSVParser.parse(file);
		for(AppointmentCSVRow row : appointmentCSV){
			System.out.println(row);
		}
	}
}
