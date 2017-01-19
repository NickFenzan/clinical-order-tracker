package com.millervein.clinicalordertracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.millervein.clinicalordertracker.appointment.AppointmentCSVLoader;
import com.millervein.clinicalordertracker.clinicalorder.ClincalOrderCSVLoader;
import com.millervein.clinicalordertracker.clinicalorder.ClinicalOrderStateUpdater;

@Service
public class AthenaCSVUpdateService {

	private static String appointmentsCSVPath = "/home/nick/Code/eclipse/clinicalordertracker/src/main/resources/appointments.csv";
	private static String ordersCSVPath = "/home/nick/Code/eclipse/clinicalordertracker/src/main/resources/orders.csv";
	private AppointmentCSVLoader appointmentCSVLoader;
	private ClincalOrderCSVLoader orderCSVLoader;
	private ClinicalOrderStateUpdater clinicalOrderStateUpdater;


	@Autowired
	public AthenaCSVUpdateService(AppointmentCSVLoader appointmentCSVUpdateService,
			ClincalOrderCSVLoader orderCSVUpdateService, ClinicalOrderStateUpdater clinicalOrderStateUpdater) {
		super();
		this.appointmentCSVLoader = appointmentCSVUpdateService;
		this.orderCSVLoader = orderCSVUpdateService;
		this.clinicalOrderStateUpdater = clinicalOrderStateUpdater;
	}

	public void run() throws Exception {
		FileReader appointmentReader = loadFileReader(appointmentsCSVPath);
		appointmentCSVLoader.createAppointments(appointmentReader);
		FileReader ordersReader = loadFileReader(ordersCSVPath);
		orderCSVLoader.createOrders(ordersReader);
		clinicalOrderStateUpdater.run();
	}

	private FileReader loadFileReader(String path) throws Exception {
		try {
			return new FileReader(new File(path));
		} catch (FileNotFoundException e) {
			new Exception("Could not load " + path);
			return null;
		}
	}

}

