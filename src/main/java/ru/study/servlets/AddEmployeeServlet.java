package ru.study.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.model.Employee;
import ru.study.repository.EmployeeRepositoryCSVImpl;
import ru.study.service.EmployeeService;
import ru.study.service.EmployeeServiceImpl;
import ru.study.validation.EmployeeValidator;
import ru.study.validation.ValidationResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/add-employees"}, name = "addEmployee")
public class AddEmployeeServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AddEmployeeServlet.class);

    private EmployeeService service;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/addEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final EmployeeValidator ev = new EmployeeValidator();
        final Employee employee = new Employee();
        employee.setFirstName(req.getParameter("firstName"));
        employee.setLastName(req.getParameter("lastName"));
        employee.setRole(req.getParameter("role"));
        final ValidationResult vr = ev.validate(employee);
        if (vr.isValid()) {
            service.addEmp(employee);
            RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/view/allEmployees.jsp");
            final List<Employee> employees = service.getAllEmp();
            req.setAttribute("employees", employees);
            view.forward(req, resp);
        } else {
            logger.error("Fields have errors: {}", vr);
            req.setAttribute("errorsEmp", vr.getMessages());
            req.getRequestDispatcher("/WEB-INF/view/addEmployee.jsp").forward(req, resp);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.service = new EmployeeServiceImpl(new EmployeeRepositoryCSVImpl());
    }

}
