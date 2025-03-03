package view;


import controller.IKontrolleriForV;
import controller.Kontrolleri;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import org.w3c.dom.ls.LSOutput;
import simu.framework.Trace;

import java.text.DecimalFormat;

public class SimulaattorinGUI extends Application {
    private IKontrolleriForV kontrolleri;
    private Visualisointi naytto;
    private Kontrolleri controller = new Kontrolleri();


    @Override
    public void start(Stage stage) throws Exception {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/SimulatorVisual.fxml"));
        //Kontrolleri controller = new Kontrolleri();
        // Pass the UI reference
        //fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();

        stage.setScene(new Scene(root));
        stage.show();
    }

    public void init() {


        Trace.setTraceLevel(Trace.Level.INFO);

    }

    public void handle(ActionEvent event) {
        kontrolleri.kaynnistaSimulointi();
        //kaynnistaButton.setDisable(true);
    }
    
        public IVisualisointi getVisualisointi() {
            return naytto;
        }
    };

