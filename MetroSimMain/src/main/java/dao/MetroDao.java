package dao;

import datasource.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MetroDao {

    public void setData(String taulu, int asMaara, double keskiaika, double simaika ) {
        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO " + taulu + " (Asiak_palveltu, Keskim_jonotusaika, Simulointiaika) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, asMaara);
            ps.setDouble(2, keskiaika);
            ps.setDouble(3, simaika);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
