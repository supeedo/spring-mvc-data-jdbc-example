package ru.study.utils.parser;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class DataParserFromCSVImpl implements DataParser{
    private static final Logger logger = LoggerFactory.getLogger(DataParserFromCSVImpl.class);

    /**
     * Парсер исходников из файлы с данными в список объектов
     * @param records Данные чтения CSV-файла
     * @return Список ДТО-объектов
     */
    @Override
    public List<Employee> parseDataInList(Iterable<CSVRecord> records) {
        logger.info("Parse from database and create employees");
        List<Employee> employeesDTOList = new ArrayList<>();
        for (CSVRecord record : records)
            employeesDTOList.add(new Employee(
                    Long.parseLong(record.get(0)),
                    record.get(1),
                    record.get(2),
                    record.get(3)
                    )
            );
        logger.debug("Parse ended = {}", employeesDTOList);
        return employeesDTOList;
    }
}
