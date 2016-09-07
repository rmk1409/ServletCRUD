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
import java.util.List;

/**
 * Created by Roma on 07.09.2016.
 */
@WebServlet("/ServletView")
public class ServletView extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        PrintWriter writer = response.getWriter();
        request.getRequestDispatcher("welcome.html").include(request, response);

        List<Person> allPeople = PersonDao.getAllPeople();
        writer.print("<table border='1' width='100%'><tr><th>id</th><th>Name</th><th>Password</th><th>Country</th><th>Edit</th><th>Delete</th></tr>");

        allPeople.forEach(person -> writer.println(String.format("<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td>"
                        + "<td><a href='ServletEdit?id=%d'>edit</a></td>"
                        + "<td><a href='ServletDelete?id=%d'>delete</a></td></tr>"
                , person.getId(), person.getName(), person.getPassword(), person.getCountry(), person.getId(), person.getId())));

        writer.print("</table>");

        //check msg
        String msg = (String) request.getSession().getAttribute("msg");
        if (msg != null){
            writer.println(String.format("<br><b>%s</b>", msg));
            request.getSession().removeAttribute("msg");
        }

        writer.close();
    }
}
