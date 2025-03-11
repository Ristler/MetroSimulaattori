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

public class SimulaattorinGUI extends Application {
    private IKontrolleriForV kontrolleri;
    private Visualisointi naytto;
    private Kontrolleri controller;

    @Override
    public void start(Stage stage) throws Exception {
        controller = new Kontrolleri();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/SimulatorVisual.fxml"));
        // Pass the UI reference
        //fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();

        stage.setScene(new Scene(root));
        stage.show();
    }


    //Prompt näkyy simuloinnin loputtua. Kesken.
    public void showData(String asiakkaitapalveltuMetroasema, String asiakkaitapalveltuLippuhalli, String asiakkaitapalveltuLaituri, double keskiarvo) {

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
                        "\n" +
                        "\nAsiakkaiden keskimääräinen palveluaika: " + Math.round(keskiarvo * 100.0) / 100.0
        );
        alert.showAndWait();
    }


    public void init() {
        Trace.setTraceLevel(Trace.Level.INFO);
    }

    
    public IVisualisointi getVisualisointi() {
            return naytto;
        }
    };

