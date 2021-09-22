package ru.study.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.study.model.Employee;
import ru.study.repository.EmployeeRepositoryCSVImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeServiceImplTest {
    private final static int FIRST_EMPLOYEE_ID = 1;
    private final static int SECOND_EMPLOYEE_ID = 2;
    private final static int LIST_OF_EMPLOYEE_SIZE = 2;
    private final static int LIST_OF_EMPLOYEE_SIZE_WITH_FILTER = 1;
    private final static String FIRST_EMPLOYEE_ROLE = "QA";
    private final static String SECOND_EMPLOYEE_ROLE = "Java developer";
    private static final List<Employee> expectedEmpList = new ArrayList<>();
    private EmployeeRepositoryCSVImpl empRepo;
    private EmployeeServiceImpl service;

    @BeforeAll
    public void addToList(){
        expectedEmpList.add(new Employee(FIRST_EMPLOYEE_ID, "Иванов", "Иван", FIRST_EMPLOYEE_ROLE));
        expectedEmpList.add(new Employee(SECOND_EMPLOYEE_ID, "Петров", "Петр", SECOND_EMPLOYEE_ROLE));
    }

    @BeforeEach
    public void setUp() {
        empRepo = Mockito.mock(EmployeeRepositoryCSVImpl.class);
        Mockito.when(empRepo.getData()).thenReturn(expectedEmpList);
        service = new EmployeeServiceImpl(empRepo);
    }

    @Test
    @DisplayName("Получение списка всех сотрудников")
    public void testGetAllEmp() {
        final List<Employee> actualEmpList = service.getAllEmp();
        Assertions.assertThat(actualEmpList)
                .isNotNull()
                .isNotEmpty()
                .doesNotContainNull()
                .hasSize(LIST_OF_EMPLOYEE_SIZE)
                .usingRecursiveComparison().isEqualTo(expectedEmpList);
    }

    @Test
    @DisplayName("Получение списка сотрудников по роли")
    public void testGetEmpByRole() {
        final List<Employee> expectedEmpListByRole = expectedEmpList
                .stream()
                .filter(x -> x.getRole().equals(FIRST_EMPLOYEE_ROLE))
                .collect(Collectors.toList());
        final List<Employee> actualEmpList = service.getEmpByRole(FIRST_EMPLOYEE_ROLE);
        Assertions.assertThat(actualEmpList)
                .isNotNull()
                .isNotEmpty()
                .doesNotContainNull()
                .hasSize(LIST_OF_EMPLOYEE_SIZE_WITH_FILTER)
                .usingRecursiveComparison().isEqualTo(expectedEmpListByRole);
    }

    @Test
    @DisplayName("Получение списка сотрудников по id")
    public void testGetEmpById() {
        final Employee actualEmp = service.getEmpById(FIRST_EMPLOYEE_ID);
        Assertions.assertThat(actualEmp)
                .isNotNull()
                .isEqualTo(expectedEmpList.get(0))
                .isNotEqualTo(expectedEmpList.get(1));
    }
}