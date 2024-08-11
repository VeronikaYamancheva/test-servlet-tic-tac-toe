package ru.vhsroni.tictactoe.servlets;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet(name = "logicServlet", value = "/logic")
public class LogicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Field field = getFieldFromSession(session);
        int index = getIndexFromRequest(req);
        Sign currentSign = field.getField().get(index);
        if(Sign.EMPTY != currentSign) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }

        field.getField().put(index, Sign.CROSS);
        if (checkWin(resp, session, field)) {
            return;
        }

        int emptyCellIndex = field.getEmptyIndex();
        if(emptyCellIndex >= 0) {
            field.getField().put(emptyCellIndex, Sign.ZERO);
            if (checkWin(resp, session, field)) {
                return;
            }
        }
        else {
            session.setAttribute("draw", true);
        }

        List<Sign> data = field.getFieldData();

        session.setAttribute("field", field);
        session.setAttribute("data", data);

        resp.sendRedirect("/index.jsp");
    }

    private int getIndexFromRequest(HttpServletRequest req) {
        String cell = req.getParameter("cell");
        return cell.chars().allMatch(Character::isDigit) ? Integer.parseInt(cell) : 0;
    }

    private Field getFieldFromSession (HttpSession session) {
        Object field = session.getAttribute("field");
        if (Field.class != field.getClass()) {
            session.invalidate();
            throw new RuntimeException("session invalidate(((");
        }
        return (Field) field;
    }

    private boolean checkWin(HttpServletResponse resp, HttpSession session, Field field) throws IOException {
        Sign winner = field.checkWin();
        if (Sign.CROSS == winner || Sign.ZERO == winner) {
            session.setAttribute("winner", winner);

            List<Sign> data = field.getFieldData();
            session.setAttribute("data", data);
            resp.sendRedirect("/index.jsp");
            return true;
        }
        return false;
    }

}
