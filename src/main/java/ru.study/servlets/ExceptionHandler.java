package ru.study.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/exception"}, name = "ExceptionHandler")
public class ExceptionHandler extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Полученные запросы: {}, {}", req, resp);
        processError(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Полученные запросы: {}, {}", req, resp);
        processError(req, resp);
    }

    private void processError(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        req.setAttribute("requestUri", requestUri);
        if (statusCode != 500) {
            req.setAttribute("statusCode", statusCode);
            getServletContext().getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
        } else {
            req.setAttribute("exceptionType", throwable.getClass().getName());
            req.setAttribute("exceptionMessage", throwable.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/view/exception.jsp").forward(req, resp);
        }


    }
}
