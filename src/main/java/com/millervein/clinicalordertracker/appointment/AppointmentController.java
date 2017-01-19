package com.millervein.clinicalordertracker.appointment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	@RequestMapping(value="/csv-upload",method=RequestMethod.POST)
	public @ResponseBody String csvUpload(@RequestParam("file") MultipartFile file){
		return null;
		
	}
}
