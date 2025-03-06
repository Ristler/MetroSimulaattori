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
    public void showData(ArrayList<String> data) {
        System.out.println("SimulaattorinGUI: " + data);

        String id = data.get(0);
        String pvm = data.get(1);
        String kello = data.get(2);
        String asiakkaitapalveltu = data.get(3);
        String Keskim_jonotusaika = data.get(4);
        String Simulointiaikaa = data.get(5);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Simulaattori päättyi");
        alert.setHeaderText(null);

        alert.setContentText("Simulaattorin data: \nMatkustajien määrä: " + asiakkaitapalveltu);

        // Show the alert and wait for the user to click OK
        alert.showAndWait();
    }


    public void init() {
        Trace.setTraceLevel(Trace.Level.INFO);
    }

    
    public IVisualisointi getVisualisointi() {
            return naytto;
        }
    };

