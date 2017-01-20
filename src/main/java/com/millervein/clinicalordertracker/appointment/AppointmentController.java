package com.millervein.clinicalordertracker.appointment;

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

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	AppointmentCSVLoader appointmentCSVLoader;

	@Autowired
	public AppointmentController(AppointmentCSVLoader appointmentCSVLoader) {
		super();
		this.appointmentCSVLoader = appointmentCSVLoader;
	}

	@RequestMapping(value = "/csv-upload", method = RequestMethod.POST)
	public ResponseEntity<String> csvUpload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			File uploadDirectory = new File("uploads");
			uploadDirectory.mkdir();
			File uploadTarget = new File(uploadDirectory.getAbsolutePath() + "/appointments.csv");
			try {
				file.transferTo(uploadTarget);
				appointmentCSVLoader.createAppointments(new FileReader(uploadTarget));
				return new ResponseEntity<String>("", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("There was an error uploading the file",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>("File was empty", HttpStatus.BAD_REQUEST);
		}
	}
}
