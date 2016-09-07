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
@WebServlet("/ServletSave")
public class ServletSave extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();

        Person person = new Person(request.getParameter("userPassword"),request.getParameter("userCountry"),request.getParameter("userName"));

        int status = PersonDao.save(person);

        request.getRequestDispatcher("welcome.html").include(request,response);
        writer.println("<h4>Create</h4>");
        if (status>0){
            writer.print("<p>Record saved successfully</p>");
        }else {
            writer.print("<p>Unable to save, next time try other name</p>");
        }

        writer.close();
    }

}
