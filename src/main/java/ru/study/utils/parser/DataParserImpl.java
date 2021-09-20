package ru.study.utils.parser;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.model.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public class DataParserImpl implements DataParser{
    private static final Logger logger = LoggerFactory.getLogger(DataParserImpl.class);

    @Override
    public List<EmployeeDTO> parseDataInList(Iterable<CSVRecord> records) {
        logger.info("Parse from database and create employees");
        List<EmployeeDTO> employeesDTOList = new ArrayList<>();
        for (CSVRecord record : records)
            employeesDTOList.add(new EmployeeDTO(
                    Long.valueOf(record.get(0)),
                    record.get(1),
                    record.get(2),
                    record.get(3)
                    )
            );
        logger.debug("Parse ended = {}", employeesDTOList);
        return employeesDTOList;
    }
}
