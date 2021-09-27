package ru.study.repository;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.exceptions.ResourceException;
import ru.study.model.Employee;
import ru.study.utils.PropertyLoader;

import java.io.*;
import java.util.List;
import java.util.Objects;

import static com.opencsv.ICSVParser.DEFAULT_SEPARATOR;
import static ru.study.exceptions.ResourceException.ErrorCode.READING_FROM_DATABASE_ERROR;
import static ru.study.exceptions.ResourceException.ErrorCode.WRITING_TO_DATABASE_ERROR;

public class EmployeeRepositoryCSVImpl implements EmployeeRepository {
    private static final Logger log = LoggerFactory.getLogger(EmployeeRepositoryCSVImpl.class);

    private final String dataLink;

    public EmployeeRepositoryCSVImpl() {
        this.dataLink = PropertyLoader.getProperty().getProperty("db.employee.url");
    }

    @Override
    public List<Employee> getListOfModel() {
        List<Employee> employees;
        try (Reader reader = new BufferedReader(new FileReader(getAbsolutePathToDataFile(dataLink)))) {
            employees = new CsvToBeanBuilder<Employee>(reader)
                    .withType(Employee.class)
                    .withSeparator(DEFAULT_SEPARATOR)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new ResourceException("Error reading data from resource", e, READING_FROM_DATABASE_ERROR);
        }
        return employees;
    }

    @Override
    public void setListOfModel(List<Employee> list) {
        try (Writer writer = new FileWriter(getAbsolutePathToDataFile(dataLink))) {
            StatefulBeanToCsv<Employee> sbc = new StatefulBeanToCsvBuilder<Employee>(writer)
                    .withSeparator(DEFAULT_SEPARATOR)
                    .build();
            sbc.write(list);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new ResourceException("Error writing data to resource", e, WRITING_TO_DATABASE_ERROR);
        }
    }

    private String getAbsolutePathToDataFile(String dataLink) {
        log.info("Attempt to form full link to the data file = {}", dataLink);
        return Objects.requireNonNull(this.getClass().getClassLoader().getResource(dataLink)).getPath();
    }
}
