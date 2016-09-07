package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Roma on 07.09.2016.
 */
public class PersonDao {
    public static Connection getConnection() {
        Connection result = null;

        try {
            Class.forName("org.postgresql.Driver");
            result = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "password");
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static int save(Person person){
        int status = 0;

        Connection connection = null;
        try {
            connection = PersonDao.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO person(name, password, country) VALUES (?,?,?)");

            statement.setString(1, person.getName());
            statement.setString(2, person.getPassword());
            statement.setString(3, person.getCountry());

            status = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return status;
    }
}
