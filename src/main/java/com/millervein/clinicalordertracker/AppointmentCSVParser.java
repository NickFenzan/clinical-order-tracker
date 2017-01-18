package com.millervein.clinicalordertracker;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class AppointmentCSVParser {
	public List<AppointmentCSVRow> parse(Reader in) throws IOException {
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
		List<AppointmentCSVRow> appointments = new ArrayList<AppointmentCSVRow>();
		String lastId = "";
		for (CSVRecord record : records) {
			AppointmentCSVRow row = new AppointmentCSVRow(record.get("prnt apptid"), record.get("apptdate"),
					record.get("apptstarttime"), record.get("patientid"), record.get("svc dprtmnt"),
					record.get("appttype"), record.get("apptslotstatus"));
			if (row.getSlotId().equals(lastId)) {
				continue;
			} else {
				appointments.add(row);
				lastId = row.getSlotId();
			}
		}
		return appointments;
	}
}
