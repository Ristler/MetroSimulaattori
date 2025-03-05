package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
private static Connection connection = null;

    public static Connection getConnection(){
        if(connection == null){
            try {
                connection = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/MetroSimulaattori?user=MetroSimulaattori&password=MetroSimulaattori");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;

        } else {
            System.out.println("Connection: " + connection);
            return connection;
        }
    }

    public static boolean isConnected(){
        return connection != null;
    }

    public static void closeConnection(){
        if(connection != null){
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
