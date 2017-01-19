package com.millervein.clinicalordertracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicalOrderTrackerApplication implements CommandLineRunner {
	
	@Autowired
	private AthenaCSVUpdateService a;

	public static void main(String[] args) {
		SpringApplication.run(ClinicalOrderTrackerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		a.run();
	}
}
