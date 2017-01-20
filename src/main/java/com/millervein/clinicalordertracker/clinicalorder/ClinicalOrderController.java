package com.millervein.clinicalordertracker.clinicalorder;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/clinical-orders")
public class ClinicalOrderController {

	private ClinicalOrderRepository orderRepo;
	private ClincalOrderCSVLoader orderCSVLoader;
	private ClinicalOrderStateUpdater clinicalOrderStateUpdater;

	@Autowired
	public ClinicalOrderController(ClinicalOrderRepository orderRepo, ClincalOrderCSVLoader orderCSVLoader,
			ClinicalOrderStateUpdater clinicalOrderStateUpdater) {
		super();
		this.orderRepo = orderRepo;
		this.orderCSVLoader = orderCSVLoader;
		this.clinicalOrderStateUpdater = clinicalOrderStateUpdater;
	}

	@RequestMapping(value = "/csv-upload", method = RequestMethod.POST)
	public ResponseEntity<String> csvUpload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			File uploadDirectory = new File("uploads");
			uploadDirectory.mkdir();
			File uploadTarget = new File(uploadDirectory.getAbsolutePath() + "/orders.csv");
			try {
				file.transferTo(uploadTarget);
				orderCSVLoader.createOrders(new FileReader(uploadTarget));
				clinicalOrderStateUpdater.run();
				return new ResponseEntity<String>("", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("There was an error uploading the file",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>("File was empty", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/need-scheduling", params = { "department" }, method = RequestMethod.GET)
	public List<ClinicalOrder> needsScheduling(@RequestParam(value = "department") String department) {
		return orderRepo.findNeedSchedulingByDepartment(department);
	}

	@RequestMapping(value = "/{orderId}/scheduled")
	public void orderScheduled(@PathVariable String orderId) {
		ClinicalOrder order = orderRepo.findOne(orderId);
		order.markAsScheduled();
		orderRepo.saveAndFlush(order);
	}
	
	@RequestMapping("test")
	public ClinicalOrderType[] test(){
		return ClinicalOrderType.values();
	}

}
