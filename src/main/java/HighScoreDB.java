import java.sql.*;
import java.util.ResourceBundle;

public class HighScoreDB {

    private ResourceBundle rb = ResourceBundle.getBundle("mysql");

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String username = rb.getString("username");
        String password = rb.getString("password");
        String url = rb.getString("url");
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
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

    public void updateUser(String name, String column) throws SQLException, ClassNotFoundException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM players WHERE player_name = " + "'" + name + "'");
        String query = "UPDATE players SET `" + column + "` = (?) WHERE player_name = (?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        while (resultSet.next()){
            preparedStatement.setInt(1, resultSet.getInt(column)+1);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        }
        getConnection().close();
    }

    public void printLeaderboard() throws SQLException, ClassNotFoundException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM players ORDER BY wins DESC LIMIT 10");
        System.out.println("***************HIGH SCORE***************");
        while (resultSet.next()){
            System.out.println("Player: " + resultSet.getString("player_name") + " | Wins: " + resultSet.getInt("wins") + " | Losses: " + resultSet.getInt("losses") );
        }
    }
}
