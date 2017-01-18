package com.millervein.clinicalordertracker;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public abstract class AbstractCSVParser<T> {

	public List<T> parse(File file) throws IOException{
		FileReader fileReader = new FileReader(file);
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(fileReader);
		List<T> output = new ArrayList<T>();
		for (CSVRecord record : records) {
			T row = buildRow(record);
			output.add(row);
		}
		return output;
	}

	abstract protected T buildRow(CSVRecord record);
}
