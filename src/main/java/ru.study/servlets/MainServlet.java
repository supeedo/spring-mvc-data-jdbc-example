package ru.study.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class MainServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Полученные запросы: {}, {}", req, resp);
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher("/show-all-employees");
        rd.forward(req, resp);
    }
}
