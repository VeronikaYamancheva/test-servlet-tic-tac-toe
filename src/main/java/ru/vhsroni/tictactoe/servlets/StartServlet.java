package ru.vhsroni.tictactoe.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.vhsroni.tictactoe.logic.Field;
import ru.vhsroni.tictactoe.logic.Sign;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "startServlet", value = "/start")
public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        Field field = new Field();
        Map<Integer, Sign> fieldData = field.getField();
        List<Sign> data = field.getFieldData();

        session.setAttribute("field", field);
        session.setAttribute("data", data);
        session.setAttribute("age", 19);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

    }
}
