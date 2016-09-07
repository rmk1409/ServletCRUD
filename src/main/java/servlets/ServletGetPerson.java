package servlets;

import classes.Person;
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
@WebServlet("/ServletGetPerson")
public class ServletGetPerson extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        request.getRequestDispatcher("welcome.html").include(request,response);
        writer.println("<h4>Read</h4>");

        Integer id = Integer.parseInt(request.getParameter("id"));
        Person person = PersonDao.getPersonById(id);
        if (person!=null){
            writer.println(String.format("<table><tr><td>id:</td><td>%d</td></tr>",person.getId()));
            writer.println(String.format("<tr><td>name:</td><td>%s</td></tr>",person.getName()));
            writer.println(String.format("<tr><td>password:</td><td>%s</td></tr>",person.getPassword()));
            writer.println(String.format("<tr><td>country:</td><td>%s</td></tr></table>",person.getCountry()));
        }else {
            writer.println("There is no person with such id");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        request.getRequestDispatcher("welcome.html").include(request,response);

        writer.println("<h4>Read</h4>");
        writer.println("<form action='/ServletGetPerson' method='post'><b>id:</b><input type=number name='id'>");
        writer.println("<input type=submit name='Seach'></form>");
    }
}
