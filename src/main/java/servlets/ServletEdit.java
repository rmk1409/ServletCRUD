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
@WebServlet("/ServletEdit")
public class ServletEdit extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        PrintWriter writer = response.getWriter();
        request.getRequestDispatcher("welcome.html").include(request, response);
        writer.print("<b>Update</b>");

        String id = request.getParameter("id");
        Person person = PersonDao.getPersonById(Integer.parseInt(id));
        writer.print("<form method='post' action = '/ServletEdit'><table>");
        writer.printf("<tr><td><b>name</b></td><td><input type = 'text' name = 'userName' value = '%s'</td></tr>", person.getName());
        writer.printf("<tr><td><b>password</b></td><td><input type = 'text' name = 'userPassword' value = '%s'</td></tr>", person.getPassword());
        writer.printf("<tr><td><b>country</b></td><td><input type = 'text' name = 'userCountry' value = '%s'</td></tr>", person.getCountry());
        writer.printf("<input type = 'hidden' name = 'userId' value = '%d'</td></tr>", person.getId());
        writer.print("<tr><td align='right' colspan='2'><input type='submit' value='Update'></td></tr");

        writer.print("</table></form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        Person person = PersonDao.getPersonById(Integer.parseInt((req.getParameter("userId"))));
        person.setName(req.getParameter("userName"));
        person.setPassword(req.getParameter("userPassword"));
        person.setCountry(req.getParameter("userCountry"));

        int status = PersonDao.update(person);
        String msg = status > 0 ? String.format("Person with id '%s' was updated.", person.getId()) : String.format("Person with id '%s' wasn't updated.", person.getId());
        req.getSession().setAttribute("msg", msg);
        resp.sendRedirect("/ServletView");
    }
}
