package ru.study.utils.parser;

import org.apache.commons.csv.CSVRecord;
import ru.study.model.Employee;

import java.util.List;

public interface DataParser {
    List<Employee> parseDataInList(Iterable<CSVRecord> records);
}
