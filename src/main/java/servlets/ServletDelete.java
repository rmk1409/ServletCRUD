package servlets;

import classes.PersonDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Roma on 07.09.2016.
 */
@WebServlet("/ServletDelete")
public class ServletDelete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        PrintWriter writer = response.getWriter();

        String id = request.getParameter("id");
        int status = PersonDao.delete(Integer.parseInt(id));

        String msg = status > 0 ? String.format("Person with id '%s' was deleted.", id) : String.format("Person with id '%s' wasn't deleted.", id);
        request.getSession().setAttribute("msg", msg);

        response.sendRedirect("/ServletView");

    }
}
