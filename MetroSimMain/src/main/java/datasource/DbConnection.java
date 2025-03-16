package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
private static Connection connection = null;


    /**
     * Palauttaa yhteyden MariaDB-tietokantaan.
     * Jos yhteyttä ei ole vielä olemassa, se luodaan.
     *
     * @return Tietokantayhteys {@link Connection}-oliona.
     */
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

    /**
     * Tarkistaa, onko tietokantayhteys muodostettu.
     *
     * @return {@code true}, jos yhteys on olemassa, {@code false} muuten.
     */
    public static boolean isConnected(){
        return connection != null;
    }

    /**
     * Sulkee tietokantayhteyden, jos se on avoinna
     * Tämä metodi tarkistaa, onko {@code connection} eri kuin {@code null} ja yrittää sulkea sen.
     * Jos tapahtuu {@link SQLException}, virheen pinojälki tulostetaan.
     */
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
