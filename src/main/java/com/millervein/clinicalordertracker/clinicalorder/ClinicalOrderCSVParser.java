package com.millervein.clinicalordertracker.clinicalorder;

import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class ClinicalOrderCSVParser {
	public List<ClinicalOrder> parse(Reader in) throws Exception {
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
		List<ClinicalOrder> orders = new ArrayList<ClinicalOrder>();
		for (CSVRecord record : records) {
			ClinicalOrder order = recordToClincicalOrder(record);
			orders.add(order);
		}
		return orders;
	}

	private ClinicalOrder recordToClincicalOrder(CSVRecord record) throws Exception {
		String id = record.get("order id");
		LocalDate date = LocalDate.parse(record.get("order chartdate"), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		String department = record.get("order dprtmnt");
		String patientId = record.get("order pt");
		ClinicalOrderType type = ClinicalOrderType.fromString(record.get("order name"));
		return new ClinicalOrder(id, date, department, patientId, type);
	}
}
