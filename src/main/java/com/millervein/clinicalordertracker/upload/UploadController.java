package com.millervein.clinicalordertracker.upload;

import java.io.File;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.millervein.clinicalordertracker.appointment.AppointmentCSVLoader;
import com.millervein.clinicalordertracker.clinicalorder.ClincalOrderCSVLoader;
import com.millervein.clinicalordertracker.clinicalorder.ClinicalOrderStateUpdater;

@RestController
@RequestMapping("/upload")
public class UploadController {
	
	private AppointmentCSVLoader appointmentCSVLoader;
	private ClincalOrderCSVLoader orderCSVLoader;
	private ClinicalOrderStateUpdater clinicalOrderStateUpdater;
	
	@Autowired
	public UploadController(AppointmentCSVLoader appointmentCSVLoader, ClincalOrderCSVLoader orderCSVLoader,
			ClinicalOrderStateUpdater clinicalOrderStateUpdater) {
		super();
		this.appointmentCSVLoader = appointmentCSVLoader;
		this.orderCSVLoader = orderCSVLoader;
		this.clinicalOrderStateUpdater = clinicalOrderStateUpdater;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> csvUpload(@RequestParam MultipartFile appointments,
			@RequestParam MultipartFile orders) {
		File uploadDirectory = new File("uploads");
		uploadDirectory.mkdir();
		File appointmentsFile = new File(uploadDirectory.getAbsolutePath() + "/appointments.csv");
		File ordersFile = new File(uploadDirectory.getAbsolutePath() + "/orders.csv");
		try {
			receiveFile(appointments, appointmentsFile);
			receiveFile(orders, ordersFile);
			appointmentCSVLoader.createAppointments(new FileReader(appointmentsFile));
			orderCSVLoader.createOrders(new FileReader(ordersFile));
			clinicalOrderStateUpdater.run();
			return new ResponseEntity<String>("{}", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void receiveFile(MultipartFile file, File destinationFile) throws Exception {
		if (file.isEmpty()) {
			throw new Exception("No file received for " + destinationFile.getName());
		}
		file.transferTo(destinationFile);
	}
}
