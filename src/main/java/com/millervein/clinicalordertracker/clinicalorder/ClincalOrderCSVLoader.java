package com.millervein.clinicalordertracker.clinicalorder;

import java.io.FileReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClincalOrderCSVLoader {
	private ClinicalOrderCSVParser clinicalOrderCSVParser;
	private ClinicalOrderRepository clinicalOrderRepo;
	
	@Autowired
	public ClincalOrderCSVLoader(ClinicalOrderCSVParser clinicalOrderCSVParser,
			ClinicalOrderRepository clinicalOrderRepo) {
		super();
		this.clinicalOrderCSVParser = clinicalOrderCSVParser;
		this.clinicalOrderRepo = clinicalOrderRepo;
	}


	public void createOrders(FileReader ordersCSVFile) throws Exception {
		List<ClinicalOrder> orders = clinicalOrderCSVParser.parse(ordersCSVFile);
		for(ClinicalOrder order : orders){
			if(!clinicalOrderRepo.exists(order.getId())){
				clinicalOrderRepo.save(order);
			}
		}
		clinicalOrderRepo.flush();
	}
}
