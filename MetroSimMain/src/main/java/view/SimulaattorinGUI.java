package view;


import controller.IKontrolleriForV;
import controller.Kontrolleri;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import org.w3c.dom.ls.LSOutput;
import simu.framework.Trace;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * Luokka toimii simulaattorin visuaalisena komponenttina, kutsutaan Main luokasta ja alustaa kontrollerin ja visualisoi FXML tiedoston.
 * <p>
 * Luokka luo myös simulaattorin lopussa promptin simulaattorin loppu tiedoista
 */
public class SimulaattorinGUI extends Application {
    private IKontrolleriForV kontrolleri;
    private Visualisointi naytto;
    private Kontrolleri controller;

    /**
     * Aloitus.
     * <p>
     * Luo uuden kontrollerin ja visualisoi FXML tiedoston. Methodi myös lisää "styles" CSS tiedoston
     */
    @Override
    public void start(Stage stage) throws Exception {
        controller = new Kontrolleri();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/SimulatorVisual.fxml"));
        // Pass the UI reference
        //fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Prompt lopputuloksia varten.
     * <p>
     * Luo uuden alert ikkunan johon lisätään tarvittavat tiedot
     * @param asiakkaitapalveltuMetroasema kuinka monta asiakasta on palveltu palvelupisteellä
     * @param asiakkaitapalveltuLippuhalli kuinka monta asiakasta on palveltu palvelupisteellä
     * @param asiakkaitapalveltuLaituri kuinka monta asiakasta on palveltu palvelupisteellä
     * @param m1asiakkaat kuinka monta asiakasta on palveltu palvelupisteellä
     * @param m2asiakkaat kuinka monta asiakasta on palveltu palvelupisteellä
     * @param keskiarvo keskimääräinen palveluaika kaikkien palvelupisteiden läpikäytyä
     * @param tulot kuinka paljon on tienattu lipuista joita myytiin
     */
    //Prompt näkyy simuloinnin loputtua. Kesken.
    public void showData(int asiakkaitapalveltuMetroasema, int asiakkaitapalveltuLippuhalli, int asiakkaitapalveltuLaituri, int m1asiakkaat, int m2asiakkaat,  double keskiarvo, double tulot) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Simulaattori päättyi");
        alert.setHeaderText(null);

        //Set dataa, lisää myös asiakkaiden määrä keskiarvo + kaikkien palveluajan keskiarvo
        alert.setContentText(
                "Simulaattorin data:" +
                        "\n" +
                        "\nMetroaseman asiakkaiden määrä: "
                        + asiakkaitapalveltuMetroasema +
                        "\nLippuhallin asiakkaiden määrä: "
                        + asiakkaitapalveltuLippuhalli +
                        "\nLaiturin asiakkaiden määrä: " + asiakkaitapalveltuLaituri  +
                        "\nMetro 1:n asiakkaiden määrä: " + m1asiakkaat +
                        "\nMetro 2:n asiakkaiden määrä: " + m2asiakkaat +
                        "\n" +
                        "\nAsiakkaiden keskimääräinen palveluaika: " + Math.round(keskiarvo * 100.0) / 100.0 +
                        "\nMetroasema tienasi tänään: " + Math.round(tulot * 100.0) / 100.0 + "€"
        );
        alert.showAndWait();
    }

    public void init() {
        Trace.setTraceLevel(Trace.Level.INFO);
    }

    /**
     * Visualioinnin palautus
     * <p>
     * @return Palauttaa Simulaattori GUI:n
     */
    public IVisualisointi getVisualisointi() {
            return naytto;
        }
    };

