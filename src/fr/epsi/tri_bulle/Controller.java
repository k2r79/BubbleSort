package fr.epsi.tri_bulle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ProgressIndicator;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ProgressIndicator progressIndicator;

    private XYChart<Number, Number> chart;

    private TriBulle triBulle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.resetTableau();
        triBulle = new TriBulle(Main.getTableau());
        progressIndicator.progressProperty().bind(triBulle.progressProperty());
    }

    public void start(ActionEvent actionEvent) {
        triBulle.start();
    }

    public void stop(ActionEvent actionEvent) {
        triBulle.cancel();
    }
}
