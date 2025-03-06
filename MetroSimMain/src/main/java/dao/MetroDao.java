package dao;

import datasource.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


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

    public ArrayList<String> getData(String taulu) {
        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM " + taulu + "ORDER BY id DESC LIMIT 1";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                data.add(Integer.toString(rs.getInt("id")));
                data.add(rs.getDate("pvm").toString());
                data.add(rs.getTime("Kello").toString());
                data.add(Integer.toString(rs.getInt("Asiak_palveltu")));
                data.add(Float.toString(rs.getFloat("Keskim_jonotusaika")));
                data.add(Float.toString(rs.getFloat("Simulointiaika")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
        
    }

}
