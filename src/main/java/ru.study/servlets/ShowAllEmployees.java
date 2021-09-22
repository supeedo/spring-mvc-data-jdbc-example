package ru.study.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.model.EmployeeDTO;
import ru.study.repository.EmployeeRepositoryCSVImpl;
import ru.study.service.EmployeeService;
import ru.study.service.EmployeeServiceImpl;
import ru.study.utils.parser.DataParserFromCSVImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/show-all-employees"}, name = "allEmployee")
public class ShowAllEmployees extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ShowAllEmployees.class);
    private EmployeeService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Полученные запросы: {}, {}", req, resp);
        final List<EmployeeDTO> employees = service.getAllEmp();
        logger.debug("Получен список: {}", employees);
        req.setAttribute("employees", employees);
        req.getRequestDispatcher("/WEB-INF/view/allEmployees.jsp").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = new EmployeeServiceImpl(new EmployeeRepositoryCSVImpl(new DataParserFromCSVImpl()));
    }
}
