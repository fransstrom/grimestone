import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HighScoreDB {

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("JDBC:mysql://localhost:3306/players", "grimestone", "abc123");
    }

}
