import java.sql.*;

public class HighScoreDB {

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("JDBC:mysql://localhost:3306/grimestone", "grimestone", "abc123");
    }

    public void addUserIfNew(String name) throws SQLException, ClassNotFoundException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM players WHERE player_name = " + "'" + name + "'");
        int rowCount = 0;
        if(resultSet.last()){
            rowCount = resultSet.getRow();
            resultSet.beforeFirst();
        }
        if(rowCount == 0){
            String query = "INSERT INTO players (player_name, wins, losses, draws) VALUES (?,0,0,0)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
        getConnection().close();
    }

}
