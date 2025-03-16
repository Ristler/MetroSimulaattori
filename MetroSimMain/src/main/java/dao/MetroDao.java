package dao;

import datasource.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Tämä luokka on vastuussa tiedon tallentamisesta ja hakemisesta tietokannasta Metro-tauluun.
 * Luokka tarjoaa metodit:
 * - Tallentaa asiakasmäärän, keskimääräisen jonotusajan ja simulointiajan tietokantaan.
 * - Hakee viimeisimmän tallennetun tietueen tiedot tietokannasta.
 * Tämä luokka käyttää tietokantayhteyttä, joka saadaan DbConnection-luokan kautta, ja suorittaa SQL-kyselyitä
 * tietojen käsittelemiseksi. Luokka mahdollistaa yksinkertaisen tavan kommunikoida tietokannan kanssa ja
 * suorittaa perustoimintoja, kuten tietojen lisäämistä ja hakemista.
 */

public class MetroDao {


    /**
     * Tallentaa annetut tiedot tietokantaan määritettyyn tauluun.
     * Tämä metodi muodostaa yhteyden tietokantaan, valmistelee SQL-kyselyn ja suorittaa sen tallentaen
     * asiakasmäärän, keskimääräisen jonotusajan ja simulointiajan tauluun, jonka nimi annetaan parametrina.
     * @param taulu Taulun nimi, johon tiedot tallennetaan.
     * @param asMaara Asiakasmäärä, joka tallennetaan tietokantaan.
     * @param keskiaika Keskimääräinen jonotusaika, joka tallennetaan tietokantaan.
     * @param simaika Simulointiaika, joka tallennetaan tietokantaan.
     */
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

    /**
     * Hakee viimeisimmät tiedot tietokannasta annetusta taulusta.
     * Tämä metodi muodostaa yhteyden tietokantaan, suorittaa SQL-kyselyn, joka hakee viimeisimmän rivin
     * taulusta (järjestettynä id:n mukaan laskevasti) ja palauttaa nämä tiedot ArrayList-muodossa.
     * Palautetut tiedot sisältävät muun muassa asiakasmäärän, päivämäärän, kellonajan, keskimääräisen jonotusajan
     * ja simulointiajan.
     * @param taulu Taulun nimi, josta tiedot haetaan.
     * @return Palauttaa ArrayListin, joka sisältää viimeisimmän rivin tiedot.
     *         ArrayListin sisältö: id, pvm, Kello, Asiak_palveltu, Keskim_jonotusaika, Simulointiaika.
     */
    public ArrayList<String> getData(String taulu) {
        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM " + taulu + " ORDER BY id DESC LIMIT 1";
        
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
