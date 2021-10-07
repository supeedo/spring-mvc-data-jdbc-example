package ru.study.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.model.Employee;
import ru.study.repository.EmployeeRepositoryCSVImpl;
import ru.study.service.EmployeeService;
import ru.study.service.EmployeeServiceImpl;

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

    private static final String ERROR = "Поле не должно быть пустым, содержать только буквы и быть длиной от 2 до 23 символов";
    private EmployeeService service;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/addEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if(!checkEmployeeField(req)) {
            final Employee employee = new Employee();
            employee.setFirstName(req.getParameter("firstName"));
            employee.setLastName(req.getParameter("lastName"));
            employee.setRole(req.getParameter("role"));
            service.addEmp(employee);
            RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/view/allEmployees.jsp");
            final List<Employee> employees = service.getAllEmp();
            req.setAttribute("employees", employees);
            view.forward(req, resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/view/addEmployee.jsp").forward(req, resp);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.service = new EmployeeServiceImpl(new EmployeeRepositoryCSVImpl());
    }



    boolean checkEmployeeField(HttpServletRequest req) {
        String regex = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
        boolean error = false;
        String name = req.getParameter("firstName");
        if (!name.matches(regex)) {
            req.setAttribute("errorFirstName", ERROR);
            error = true;
        }
        if (!req.getParameter("lastName").matches(regex)) {
            req.setAttribute("errorLastName", ERROR);
            error = true;
        }
        if(!req.getParameter("role").matches(regex)){
            req.setAttribute("errorRole", ERROR);
            error = true;
        }
        return error;
    }
}
