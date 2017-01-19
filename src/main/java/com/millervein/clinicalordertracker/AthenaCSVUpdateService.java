package com.millervein.clinicalordertracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AthenaCSVUpdateService {

	private static String appointmentsCSVPath = "/home/nick/Code/eclipse/clinicalordertracker/src/main/resources/appointments.csv";
	private static String ordersCSVPath = "/home/nick/Code/eclipse/clinicalordertracker/src/main/resources/orders.csv";
	private AppointmentCSVUpdateService appointmentCSVUpdateService;
	private OrderCSVUpdateService orderCSVUpdateService;

	@Autowired
	public AthenaCSVUpdateService(AppointmentCSVUpdateService appointmentCSVUpdateService,
			OrderCSVUpdateService orderCSVUpdateService) {
		super();
		this.appointmentCSVUpdateService = appointmentCSVUpdateService;
		this.orderCSVUpdateService = orderCSVUpdateService;
	}

	public void run() throws Exception {
		FileReader appointmentReader = loadFileReader(appointmentsCSVPath);
		appointmentCSVUpdateService.updateAppointments(appointmentReader);
		FileReader ordersReader = loadFileReader(ordersCSVPath);
		orderCSVUpdateService.updateOrders(ordersReader);

	}

	private FileReader loadFileReader(String path) throws Exception {
		try {
			return new FileReader(new File(appointmentsCSVPath));
		} catch (FileNotFoundException e) {
			new Exception("Could not load " + appointmentsCSVPath);
			return null;
		}
	}

}
