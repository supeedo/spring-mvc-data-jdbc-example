package ru.study.utils.parser;

import org.apache.commons.csv.CSVRecord;
import ru.study.model.EmployeeDTO;

import java.util.List;

public interface DataParser {
    List<EmployeeDTO> parseDataInList(Iterable<CSVRecord> records);
}
