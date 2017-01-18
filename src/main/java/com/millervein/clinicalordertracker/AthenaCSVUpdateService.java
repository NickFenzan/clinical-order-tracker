package com.millervein.clinicalordertracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class AthenaCSVUpdateService {

	private static String appointmentsCSVPath = "/home/nick/Code/eclipse/clinicalordertracker/src/main/resources/appointments.csv";
	private static String ordersCSVPath = "/home/nick/Code/eclipse/clinicalordertracker/src/main/resources/orders.csv";

	public void run() {
		try {
			FileReader appointmentReader = loadFileReader(appointmentsCSVPath);
			
		}catch(Exception e){
			
		}
	}

	private FileReader loadFileReader(String path) throws Exception  {
		try {
			return new FileReader(new File(appointmentsCSVPath));
		} catch (FileNotFoundException e){
			new Exception("Could not load " + appointmentsCSVPath);
			return null;
		}
	}
	

}
