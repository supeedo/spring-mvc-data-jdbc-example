package ru.study.servlets;

import ru.study.model.EmployeeDTO;
import ru.study.service.EmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/show-all-employees"})
public class ShowAllEmployees extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EmployeeDTO> employees = new EmployeeService().getAllEmp();
        req.setAttribute("employees", employees);
        req.getRequestDispatcher("/WEB-INF/view/allEmployees.jsp").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
