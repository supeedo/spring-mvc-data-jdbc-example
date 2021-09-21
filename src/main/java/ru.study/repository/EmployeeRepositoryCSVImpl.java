package ru.study.repository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.exceptions.ResourceException;
import ru.study.model.EmployeeDTO;
import ru.study.utils.PropertyLoader;
import ru.study.utils.parser.DataParser;
import ru.study.utils.parser.DataParserFromCSVImpl;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

import static ru.study.exceptions.ResourceException.ErrorCode.READING_FROM_DATABASE_ERROR;

public class EmployeeRepositoryCSVImpl implements EmployeeRepository {
    private static final Logger log = LoggerFactory.getLogger(EmployeeRepositoryCSVImpl.class);

    private final String dataLink;
    private final DataParser dataParser;

    public EmployeeRepositoryCSVImpl() {
        this.dataLink = PropertyLoader.getProperty().getProperty("db.employee.url");
        this.dataParser = new DataParserFromCSVImpl();
    }


    @Override
    public List<EmployeeDTO> getData() {
        List<EmployeeDTO> questionDTOList;
        try (Reader in = new FileReader(getAbsolutePathToDataFile(dataLink))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            log.info("Information received from the database = {}", records);
            questionDTOList = dataParser.parseDataInList(records);
        } catch (IOException error) {
            throw new ResourceException("Error reading data from resource", error, READING_FROM_DATABASE_ERROR);
        }
        log.info("Test list generated = {}", questionDTOList);
        return questionDTOList;
    }

    private String getAbsolutePathToDataFile(String dataLink) {
        log.info("Attempt to form full link to the data file = {}", dataLink);
        return Objects.requireNonNull(this.getClass().getClassLoader().getResource(dataLink)).getPath();
    }
}
